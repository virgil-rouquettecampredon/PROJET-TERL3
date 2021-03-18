package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class GameOverController extends Controller {
    @FXML
    private void backButton() throws IOException {
        getApp().soundManager.playSound("button-cancel");
        getApp().setRoot("home");
    }

    @FXML
    private void restartButton() throws IOException {
        //TODO passer à la scene la même variante
        getApp().soundManager.playSound("button-confirm");
        getApp().setRoot("game");
    }
}
