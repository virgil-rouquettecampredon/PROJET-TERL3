package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class GameOverController extends Controller {
    @FXML
    private void backButton() throws IOException {
        App.soundManager.playSound("button-cancel");
        getApp().setRoot("home");
    }

    @FXML
    private void restartButton() throws IOException {
        //TODO passer à la scene la même variante
        App.soundManager.playSound("button-confirm");
        getApp().setRoot("game");
    }
}
