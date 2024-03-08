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
    private Target bigTarget;
    private Target smallTarget;
    private Bullet bullet;
    private Thread thread;
    private double sumPoints;

    private boolean fPause = false;

    public void initialize(Pane poligon, Circle bigTargetCircle, Circle smallTargetCircle, Circle bulletCircle) {
        this.poligonPane = poligon;

        bigTarget = new Target(bigTargetCircle, 2);
        bigTarget.setPoligonHeight(poligonPane.getHeight());

        smallTarget = new Target(smallTargetCircle, 4);
        smallTarget.setPoligonHeight(poligonPane.getHeight());

        bullet = new Bullet(bulletCircle);
        bullet.setPoligonParams(poligonPane);
    }

    public void moveAction() {
        if (thread != null) return;

        thread = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    bigTarget.moveAction();
                    smallTarget.moveAction();
                    bullet.move();
                    int points = getHitPoints();
                    if (points != 0) {
                        sumPoints += points;
                        bullet.moveToStart();
                    }
                });
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });

        thread.start();
    }

    public void StopAction() {

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

    public void bulletStartPosition(MouseEvent event) {
        Platform.runLater(() -> bullet.setStartPositionBullet(event));
    }
}