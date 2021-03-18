package org.example;

import javafx.fxml.FXML;
import org.example.App;

import java.io.IOException;

public class HomeController extends Controller {

    @FXML
    private void playButton() throws IOException {
        App.soundManager.playSound("button-click");
        getApp().setRoot("play");
    }

    @FXML
    private void varianteButton() throws IOException {
        App.soundManager.playSound("button-click");
        getApp().setRoot("variante");
    }
}
