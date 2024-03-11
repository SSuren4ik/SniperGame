package com.example.snipergame.gameElements;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Target {
    private final Circle circle;
    private double poligonHeight;
    private final double speed;
    private boolean moveDown;

    public Target(Circle cr, double speed) {
        circle = cr;
        this.speed = speed;
        moveDown = true;
    }

    public void moveAction() {
        switch (moveDown ? 1 : 0) {
            case 1 -> moveDown();
            case 0 -> moveUp();
        }
    }

    private void moveDown() {
        if (canMoveDown())
            move(speed);
        else
            moveDown = false;
    }

    private void moveUp() {
        if (canMoveUp())
            move(-speed);
        else
            moveDown = true;
    }

    private void move(double y) {
        circle.setLayoutY(getY() + y);
    }

    public double getX() {
        return circle.getLayoutX();
    }

    public double getY() {
        return circle.getLayoutY();
    }

    public double getRadius() {
        return circle.getRadius();
    }

    public void moveToStart() {
        circle.setLayoutY(getRadius());
    }

    public void setPoligonHeight(double value) {
        poligonHeight = value;
    }
    private boolean canMoveDown() {
        return getY() + speed + circle.getRadius() < poligonHeight;
    }

    private boolean canMoveUp() {
        return getY() - speed - circle.getRadius() > 0;
    }


}
