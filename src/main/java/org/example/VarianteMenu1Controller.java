package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class VarianteMenu1Controller extends Controller {

    @FXML
    private void boardButton() throws IOException {
        App.soundManager.playSound("button-click");
        App.setRoot("board");
    }

    @FXML
    private void playerButton() throws IOException {
        App.soundManager.playSound("button-click");
        App.setRoot("player");
    }

    @FXML
    private void continueButton() throws IOException {
        App.soundManager.playSound("button-confirm");
        App.setRoot("VarianteMenu1");
    }
}
