package org.example;

import javafx.fxml.FXML;
import org.example.App;

import java.io.IOException;

public class HomeController {

    @FXML
    private void playButton() throws IOException {
        App.setRoot("play");
    }

    @FXML
    private void varianteButton() throws IOException {
        App.setRoot("variante");
    }
}
