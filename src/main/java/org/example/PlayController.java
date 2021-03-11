package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class PlayController extends Controller {
    @FXML
    public ComboBox<String> varianteBox;
    @FXML
    public Button button;

    @FXML
    private void beginButton() throws IOException {
        App.soundManager.playSound("button-click");
        //TODO: valider texte de l'input
        System.out.println(varianteBox.getValue());
        App.setRoot("game");
    }

    @FXML
    public void initialize() {
        //TODO prendre les vraie variantes
        varianteBox.getItems().addAll("Classique", "CHESS950", "MaVariante");
    }

    public void boxModified() {
        App.soundManager.playSound("button-hover");
        button.setDisable(false);
    }
}
