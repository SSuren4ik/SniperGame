package com.example.snipergame;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SniperGameController {

    @FXML
    private Pane poligonPane;

    @FXML
    private TextField scoreText;

    @FXML
    private TextField shootsText;

    @FXML
    private Circle bullet;

    @FXML
    private Circle bigCircle;

    @FXML
    private Circle smallCircle;

    private Game game;

    private GameInfo gameInfo;

    @FXML
    protected void clickM(MouseEvent event) {
        if (game == null) return;
        game.bulletStartPosition(event);
    }

    @FXML
    protected void onStartClick() {
        if (game == null) {
            game = new Game();
            gameInfo = new GameInfo(scoreText, shootsText);
            game.initialize(poligonPane, bigCircle, smallCircle, bullet, gameInfo);
        }
        game.Start();
    }

    @FXML
    protected void onPauseClick() {
        if (game == null)
            return;
        game.Pause();
    }

    @FXML
    protected void onContinueClick() {
        if (game == null)
            return;
        game.Continue();
    }

    @FXML
    protected void onEndGameClick() {
        if (game == null)
            return;
        game.endGame();
        gameInfo.reset();
    }

    @FXML
    protected void onShootClick() {
        if (game == null || game.getPause()) {
            return;
        }
        game.ShootAction();
        gameInfo.upShoots();
    }
}