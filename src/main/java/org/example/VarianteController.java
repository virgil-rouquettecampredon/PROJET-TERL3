package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class VarianteController {

    @FXML
    private void importButton() throws IOException {
        App.setRoot("import");
    }

    @FXML
    private void createButton() throws IOException {
        App.setRoot("create");
    }
}
