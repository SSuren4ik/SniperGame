package com.example.snipergame.gameElements;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Bullet {
    private final Circle cr;
    private double poligonWidth;
    private double poligonHeight;
    private boolean isShooting;

    private double speed;

    public Bullet(Circle cr) {
        this.cr = cr;
        isShooting = false;
        speed = 6;
    }

    public void setPoligonParams(Pane poligon) {
        poligonWidth = poligon.getWidth();
        poligonHeight = poligon.getHeight();
    }

    public void move() throws Exception {
        if (canMove()) {
            cr.setLayoutX(getX() + speed);
        } else {
            cr.setLayoutX(startPositionX());
            isShooting = false;
            throw new Exception();
        }
    }

    public void moveToStart() {
        cr.setLayoutX(cr.getRadius());
        isShooting = false;
    }

    public Runnable setStartPositionBullet(MouseEvent event) {
        cr.setLayoutX(startPositionX());
        double y = correctBulletCoordinateY(event.getY());
        cr.setLayoutY(y);
        return null;
    }

    double correctBulletCoordinateY(double y) {
        if (y - cr.getRadius() < 0) {
            return cr.getRadius();
        } else if (y + cr.getRadius() > poligonHeight) {
            return poligonHeight - cr.getRadius();
        } else return y;
    }

    public void Shooting() {
        isShooting = true;
    }

    double startPositionX() {
        return cr.getRadius();
    }

    boolean canMove() {
        return getX() + getBulletRadius() + speed < poligonWidth && isShooting;
    }

    public double getBulletRadius() {
        return cr.getRadius();
    }

    double getX() {
        return cr.getLayoutX();
    }
}
