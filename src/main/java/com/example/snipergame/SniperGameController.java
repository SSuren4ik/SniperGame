package com.example.snipergame;

import com.example.snipergame.gameElements.Bullet;
import com.example.snipergame.gameElements.Target;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Circle bullet;

    @FXML
    private Circle bigCircle;

    @FXML
    private Circle smallCircle;

    private Game game;

    public void initialize() {
        Image targetImage = new Image("TargetImage.jpg");
        Image bulletImage = new Image("BulletImage.png");

        bigCircle.setFill(new ImagePattern(targetImage));
        smallCircle.setFill(new ImagePattern(targetImage));

        bullet.setStroke(Color.GREEN);
        bullet.setFill(new ImagePattern(bulletImage));
    }
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
    protected void onStopClick() {
        if(game == null)
            return;
        game.StopAction();
    }

    @FXML
    protected void onShootClick() {
        game.ShootAction();
    }
}