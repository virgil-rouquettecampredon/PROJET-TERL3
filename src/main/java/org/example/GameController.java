package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;

public class GameController {

    @FXML
    private void bigButton() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Tu as cliquer\nTu vas mourrir");
        alert.showAndWait();

    }
}
