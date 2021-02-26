package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class Controller {
    @FXML
    private void buttonHover() throws IOException {
        App.soundManager.playSound("button-hover");
    }
}
