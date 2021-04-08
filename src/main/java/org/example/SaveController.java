package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class SaveController extends Controller {
    @FXML
    public Button button;
    private boolean valid = false;

    @FXML
    private void saveButton() throws IOException {
        getApp().soundManager.playSound("button-click");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder la variante");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ChessBurger Variante (.cbvr)", "*.cbvr"));
        File file = fileChooser.showSaveDialog(getApp().scene.getWindow());
        if (file != null) {
            try {
                getApp().varianteManager.saveCurrent(file.getAbsolutePath());
            }
            catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur : impossible de sauvegarder le fichier : "+file.getAbsolutePath());
                return;
            }
            button.getStyleClass().remove("backButton");
            button.getStyleClass().add("specialButton");
            valid = true;
        }
    }

    @FXML
    private void continueButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");
        if (!valid) {
            Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Attention! Vous n'avez pas sauvegarder! Quitter quand même?");
            result.ifPresent(response -> {if (response == ButtonType.OK) {
                try {
                    getApp().setRoot("home");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});
        }
        else {
            getApp().varianteManager.applyCurrent();
            getApp().setRoot("home");
        }
    }
}
