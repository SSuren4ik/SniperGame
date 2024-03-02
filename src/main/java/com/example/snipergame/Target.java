package com.example.snipergame;

import javafx.scene.shape.Circle;

public class Target {
    private final Circle circle;
    private double poligonHeight;
    private final double moveDistant;
    private boolean moveDown;

    Target(Circle cr, double _moveDistant) {
        circle = cr;
        moveDistant = _moveDistant;
        moveDown = true;
    }
    void setPoligonHeight(double value) {
        poligonHeight = value;
    }

    void moveAction() {
        switch (moveDown ? 1 : 0) {
            case 1 -> moveDown();
            case 0 ->moveUp();
        }
    }

    private void moveDown() {
        if (canMoveDown())
            move(moveDistant);
        else
            moveDown = false;
    }

    private void moveUp() {
        if (canMoveUp())
            move(-moveDistant);
        else
            moveDown = true;
    }

    private void move(double y) {
        circle.setLayoutY(getY() + y);
    }

    private boolean canMoveDown() {
        return getY() + moveDistant + circle.getRadius() < poligonHeight;
    }

    private boolean canMoveUp() {
        return getY() - moveDistant - circle.getRadius() > 0;
    }
    private double getY() {
        return circle.getLayoutY();
    }
}
