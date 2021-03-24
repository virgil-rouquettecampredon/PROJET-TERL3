package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateController extends Controller {
    @FXML
    public TextField input;

    @FXML
    private void continueButton() throws IOException {
        getApp().soundManager.playSound("button-click");

        getApp().varianteManager.getCurrent().setName(input.getText());

        System.out.println(input.getText());
        getApp().setRoot("VarianteMenu1");
    }
}
