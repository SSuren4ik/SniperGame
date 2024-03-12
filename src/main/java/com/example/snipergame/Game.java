package com.example.snipergame;

import com.example.snipergame.gameElements.Bullet;
import com.example.snipergame.gameElements.Target;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import static com.example.snipergame.supportFunctions.MathUtils.square;

public class Game {
    private Pane poligonPane;

    private GameInfo gameInfo;
    private Target bigTarget;
    private Target smallTarget;
    private Bullet bullet;
    private Thread thread;
    private double sumPoints;

    private boolean isPause = false;
    private boolean isRun = false;

    public void initialize(Pane poligon, Circle bigTargetCircle, Circle smallTargetCircle, Circle bulletCircle, GameInfo gameInfo) {
        this.poligonPane = poligon;

        this.gameInfo = gameInfo;

        bigTarget = new Target(bigTargetCircle, 1);
        bigTarget.setPoligonHeight(poligonPane.getHeight());

        smallTarget = new Target(smallTargetCircle, 2);
        smallTarget.setPoligonHeight(poligonPane.getHeight());

        bullet = new Bullet(bulletCircle);
        bullet.setPoligonParams(poligonPane);
    }

    public void Start() {
        if (thread != null)
            return;
        isRun = true;

        thread = new Thread(() -> {
            try {
                while (isRun) {
                    if (isPause) {
                        threadStop();
                    }
                    move();
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        });

        thread.start();
    }

    public void Pause() {
        isPause = true;
    }

    public void Continue() {
        isPause = false;
        synchronized (this) {
            notifyAll();
        }
    }

    public void endGame() {
        if (thread == null) {
            return;
        }
        isRun = false;
        moveAllToStart();
        thread.interrupt();
        thread = null;

    }

    void moveAllToStart() {
        Platform.runLater(() -> {
            bullet.moveToStart();
            bigTarget.moveToStart();
            smallTarget.moveToStart();
        });
    }

    void move() {
        Platform.runLater(() -> {
            bigTarget.moveAction();
            smallTarget.moveAction();
            bullet.move();
            int points = getHitPoints();
            if (points != 0) {
                gameInfo.upScore(points);
                bullet.moveToStart();
            }
        });
    }

    void threadStop() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getHitPoints() {
        if (bullet.getX() > bigTarget.getX() + bigTarget.getRadius()) {
            if (hittingSmallTarget()) return 20;
        } else {
            if (hittingBigTarget()) return 10;
        }
        return 0;
    }


    private boolean hittingBigTarget() {
        return distantToBigTarget() < bigTarget.getRadius();
    }

    private boolean hittingSmallTarget() {
        return distantToSmallTarget() < smallTarget.getRadius();
    }

    private double distantToSmallTarget() {
        double x = smallTarget.getX();
        double y = smallTarget.getY();
        return distantFromBullet(x, y);
    }

    private double distantToBigTarget() {
        double x = bigTarget.getX();
        double y = bigTarget.getY();
        return distantFromBullet(x, y);
    }

    private double distantFromBullet(double x, double y) {
        double distantX = square(x - bullet.getX());
        double distantY = square(y - bullet.getY());
        return Math.sqrt(distantX + distantY);
    }

    public void ShootAction() {
        bullet.Shooting();
    }

    public boolean getPause() {
        return isPause;
    }

    public GameInfo getGameInfo () {
        return gameInfo;
    }

    public void bulletStartPosition(MouseEvent event) {
        Platform.runLater(() -> bullet.setStartPositionBullet(event));
    }
}