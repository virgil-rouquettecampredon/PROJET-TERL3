package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;

public class GameController {
    private int nb = 0;

    @FXML
    private void bigButton() throws IOException {
        nb=0;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Tu as cliquer\nTu vas mourrir");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> formatSystem());
    }

    private void formatSystem() {
        nb++;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("HAHA "+nb+" (ne me ferme pas avec la croix)");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> formatSystem());
    }
}
