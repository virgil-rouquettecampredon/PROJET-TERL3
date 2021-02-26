package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ImportController extends Controller {

    @FXML
    public TextField input;

    @FXML
    private void chooseButton() throws IOException {
        App.soundManager.playSound("button-click");
        //TODO: importer alert window tous ça tous ça et mettre dans l'input
    }

    @FXML
    private void validateButton() throws IOException {
        App.soundManager.playSound("button-confirm");
        //TODO: valider texte de l'input et l'importer depuis un fichier (appdata?)
        System.out.println(input.getText());
        App.setRoot("home");
    }
}
