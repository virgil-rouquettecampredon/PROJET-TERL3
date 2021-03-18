package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateController extends Controller {
    @FXML
    public TextField input;

    @FXML
    private void continueButton() throws IOException {
        App.soundManager.playSound("button-click");
        //TODO: valider texte de l'input
        System.out.println(input.getText());
        getApp().setRoot("VarianteMenu1");
    }
}
