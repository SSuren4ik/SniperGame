package com.example.snipergame;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class HelloController {

    @FXML
    private Pane poligonPane;

    @FXML
    private Circle bullet;

    @FXML
    private Circle bigCircle;

    @FXML
    private Circle smallCircle;

    private Target bigTarget;
    private Target smallTarget;

    private boolean inStart;


    Thread thread;

    @FXML
    void clickM(MouseEvent event) {
        setStartPositionBullet(event);
    }

    public void initialize() {
        bigTarget = new Target(bigCircle, 2);
        bigTarget.setPoligonHeight(getPoligonHeight());
        smallTarget = new Target(smallCircle, 4);
        smallTarget.setPoligonHeight(getPoligonHeight());
    }

    @FXML
    protected void onStartClick() {
        if (thread != null)
            return;

        thread = new Thread(
                () -> {
                    while (true) {
                        Platform.runLater(
                                () -> {
                                    bigTarget.moveAction();
                                    smallTarget.moveAction();
                                }
                        );

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException();
                        }
                    }

                }
        );

        thread.start();

    }

    void setStartPositionBullet(MouseEvent event) {
        bullet.setLayoutX(getBulletRadius());
        double y = correctBulletCoordinateY(event.getY());
        bullet.setLayoutY(y);
    }

    double correctBulletCoordinateY(double y) {
        if (y - getBulletRadius() < 0) {
            return getBulletRadius();
        } else if (y + bullet.getRadius() > getPoligonHeight()) {
            return getPoligonHeight() - getBulletRadius();
        } else return y;
    }

    double getBulletRadius() {
        return bullet.getRadius();
    }

    double getPoligonWidth() {
        return poligonPane.getPrefWidth();
    }

    double getPoligonHeight() {
        return poligonPane.getPrefHeight();
    }

}