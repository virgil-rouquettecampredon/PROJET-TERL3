package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SaveController extends Controller {
    @FXML
    public Button button;

    @FXML
    private void saveButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        button.getStyleClass().remove("backButton");
        button.getStyleClass().add("specialButton");
        //TODO ouvrir explorer pour enregistrer
    }

    @FXML
    private void continueButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");
        getApp().setRoot("home");
    }
}
