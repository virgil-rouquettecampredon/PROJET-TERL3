package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class VarianteMenu1Controller extends Controller {

    @FXML
    private void boardButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("board");
    }

    @FXML
    private void playerButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("player");
    }

    @FXML
    private void continueButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");
        getApp().setRoot("varianteMenu2");
    }
}
