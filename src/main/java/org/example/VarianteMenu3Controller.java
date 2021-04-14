package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class VarianteMenu3Controller extends Controller {

    @FXML
    private void rulesButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("rules");
    }

    @FXML
    private void backButton() {
        getApp().soundManager.playSound("button-cancel");
        Optional<ButtonType> result =
                showAlert(Alert.AlertType.CONFIRMATION, "Attention !\nSi vous revenez en arrière, les les règles créées seront supprimés. \nÊtes vous sûr de revenir en arrière ?");
        result.ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    getApp().setRoot("varianteMenu2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void endButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");
        getApp().setRoot("save");
    }
}
