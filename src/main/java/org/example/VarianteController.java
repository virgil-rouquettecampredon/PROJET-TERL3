package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class VarianteController extends Controller {

    @FXML
    private void importButton() throws IOException {
        App.soundManager.playSound("button-click");
        App.setRoot("import");
    }

    @FXML
    private void createButton() throws IOException {
        App.soundManager.playSound("button-click");
        App.setRoot("create");
    }
}
