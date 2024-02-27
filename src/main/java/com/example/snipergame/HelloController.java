package com.example.snipergame;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HelloController {

    @FXML
    private Pane poligonPane;

    @FXML
    private Circle startBullet;
    private Circle bullet;

    @FXML
    void clickM(MouseEvent event) {
        setStartPositionBullet(event);
    }

    void setStartPositionBullet(MouseEvent event) {
        startBullet.setLayoutX(getBulletRadius());
        double y = correctBulletCoordinateY(event.getY());
        startBullet.setLayoutY(y);
    }

    double correctBulletCoordinateY(double y) {
        if (y - getBulletRadius() < 0) {
            return getBulletRadius();
        } else if (y + startBullet.getRadius() > getPoligonHeight()) {
            return getPoligonHeight() - getBulletRadius();
        } else
            return y;
    }

    double getBulletRadius() {
        return startBullet.getRadius();
    }

    double getPoligonHeight() {
        return poligonPane.heightProperty().getValue();
    }

    @FXML
    protected void onStartClick() {

        bullet = new Circle();
        bullet.setRadius(10);
        bullet.setStroke(Color.BLUE);
        bullet.setLayoutX(startBullet.getCenterX());

        bullet.setCenterY(0);
    }
}