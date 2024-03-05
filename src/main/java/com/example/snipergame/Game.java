package com.example.snipergame;

import com.example.snipergame.gameElements.Bullet;
import com.example.snipergame.gameElements.Target;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Game {
    private Pane poligonPane;
    private Target bigTarget;
    private Target smallTarget;
    private Bullet bullet;
    private Thread thread;
    private double sumPoints;

    public void initialize(Pane poligon, Circle bigTargetCircle, Circle smallTargetCircle, Circle bulletCircle) {
        this.poligonPane = poligon;

        bigTarget = new Target(bigTargetCircle, 2);
        bigTarget.setPoligonHeight(poligonPane.getHeight());

        smallTarget = new Target(smallTargetCircle, 4);
        smallTarget.setPoligonHeight(poligonPane.getHeight());

        bullet = new Bullet(bulletCircle);
        bullet.setPoligonParams(poligonPane);
    }

    void moveAction() {
        if (thread != null) return;

        thread = new Thread(
                () -> {
                    while (true) {
                        Platform.runLater(
                                () -> {
                                    bigTarget.moveAction();
                                    smallTarget.moveAction();
                            try {
                                bullet.move();
                            } catch (Exception e) {
                                bullet.moveToStart();
                                //Пуля мимо
                            }
                            /*
                            if (isHitting()) {
                                sumPoints = getHitPoints();
                                bullet.moveToStart();
                            }
                            */
                                });
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        //sleep для отрисовки
                    }
                });

        thread.start();
    }

    public double getHitPoints() {
        if (hittingBigTarget()) return 10;
        else if (hittingSmallTarget()) return 20;
        else return 0;
    }

    private boolean isHitting() {
        return hittingBigTarget() || hittingSmallTarget();
    }

    public void ShootAction() {
        bullet.Shooting();
    }

    private boolean hittingBigTarget() {
        return distantToBigTarget() < bigTarget.getRadius();
    }

    private boolean hittingSmallTarget() {
        return distantToSmallTarget() < smallTarget.getRadius();
    }

    private double distantToSmallTarget() {
        double sum = square(smallTarget.getX()) + square(smallTarget.getY());
        return Math.sqrt(sum);
    }

    private double distantToBigTarget() {
        double sum = square(bigTarget.getX()) + square(bigTarget.getY());
        return Math.sqrt(sum);
    }

    public void bulletStartPosition(MouseEvent event) {
        Platform.runLater(bullet.setStartPositionBullet(event));
    }

    double square(double x) {
        return x * x;
    }

}
