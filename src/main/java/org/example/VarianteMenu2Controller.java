package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class VarianteMenu2Controller extends Controller {

    @FXML
    private void pieceButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("piece");
    }

    @FXML
    private void initPosButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("initPos");
    }

    @FXML
    private void rulesButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("rules");
    }

    @FXML
    private void backButton() throws IOException {
        getApp().soundManager.playSound("button-cancel");
        getApp().setRoot("varianteMenu1");
    }

    @FXML
    private void endButton() throws IOException {
        //TODO SAUVEGARDER JE PENSE LOL
        getApp().soundManager.playSound("button-confirm");
        getApp().setRoot("save");
    }
}
