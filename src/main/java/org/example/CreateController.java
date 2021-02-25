package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateController {
    @FXML
    public TextField input;

    @FXML
    private void continueButton() throws IOException {
        //TODO: valider texte de l'input
        System.out.println(input.getText());
        App.setRoot("createBoard");
    }
}
