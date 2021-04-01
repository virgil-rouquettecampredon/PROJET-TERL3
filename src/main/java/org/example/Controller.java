package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    private App app;

    protected Object userVar;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void buttonHover() throws IOException {
        //getApp().soundManager.playSound("button-hover");
    }

    public void initialise() {

    }

    public void setUserVar(Object userVar) {
        this.userVar = userVar;
    }

    public Optional<ButtonType> showAlert(Alert.AlertType type, String text) {
        Alert alert = new Alert(type);
        alert.setContentText(text);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        return alert.showAndWait();
    }
}
