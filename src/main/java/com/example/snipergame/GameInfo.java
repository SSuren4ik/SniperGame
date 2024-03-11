package com.example.snipergame;

import javafx.scene.control.TextField;

public class GameInfo {

    private TextField scoreText;
    private TextField shootsText;
    private double score;
    private int shoots;

    public GameInfo(TextField scoreText, TextField shootsText) {
        this.scoreText = scoreText;
        this.shootsText = shootsText;
        score = 0;
        shoots = 0;
    }

    public double getScore() {
        return score;
    }

    public int getShoots() {
        return shoots;
    }

    public void upScore(double score) {
        this.score += score;
        String text = Double.toString(this.score);
        scoreText.textProperty().setValue(text);
    }

    public void upShoots() {
        this.shoots++;
        String text = Integer.toString(shoots);
        shootsText.textProperty().setValue(text);
    }

    public void reset() {
        shootsText.textProperty().setValue("0");
        scoreText.textProperty().setValue("0");
        score = 0;
        shoots = 0;
    }
}
