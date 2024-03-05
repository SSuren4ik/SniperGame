package com.example.snipergame;

import com.example.snipergame.gameElements.Target;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class SniperGameController {

    @FXML
    private Pane poligonPane;

    @FXML
    private Circle bullet;

    @FXML
    private Circle bigCircle;

    @FXML
    private Circle smallCircle;

    private Game game;
    Thread thread;

    @FXML
    protected void clickM(MouseEvent event) {
        if (game == null) return;
        game.bulletStartPosition(event);
    }

    @FXML
    protected void onStartClick() {
        if (game != null) return;
        game = new Game();
        game.initialize(poligonPane, bigCircle, smallCircle, bullet);
        game.moveAction();
    }

    @FXML
    protected void onShootClick() {
        game.ShootAction();
    }

    double getPoligonWidth() {
        return poligonPane.getPrefWidth();
    }

    double getPoligonHeight() {
        return poligonPane.getPrefHeight();
    }

}