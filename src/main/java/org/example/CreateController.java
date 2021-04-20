package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.model.VarianteBuilder;

import java.io.IOException;

public class CreateController extends Controller {
    @FXML
    public TextField input;

    @FXML
    private void continueButton() throws IOException {
        getApp().soundManager.playSound("button-click");

        if (!input.getText().isEmpty()) {
            getApp().varianteManager.setCurrent(new VarianteBuilder().setName(input.getText()));
            getApp().varianteManager.addClassiquePlayers();
            System.out.println(input.getText());
            getApp().setRoot("VarianteMenu1");
        }
        else {
            showAlert(Alert.AlertType.ERROR, "Erreur : le nom ne doit pas être vide!");
        }
    }
}
