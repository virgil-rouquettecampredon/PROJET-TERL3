package org.example;

import javafx.fxml.FXML;
import org.example.model.Joueur;
import org.example.model.Piece;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VarianteMenu1Controller extends Controller {

    @FXML
    private void boardButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("board");
    }

    @FXML
    private void playerButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("player");
    }

    @FXML
    private void continueButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");

        if (getApp().varianteManager.getCurrent().getAllPawn().size() == 0) {
            getApp().varianteManager.addClassiquePawn(getApp().varianteManager.getCurrent().getJoueurs(), getApp().varianteManager.getCurrent().getPlateau());
            getApp().varianteManager.getCurrent().getListGroupCases().clear();
            getApp().varianteManager.addClassiqueGroupeCases(getApp().varianteManager.getCurrent().getListGroupCases(), getApp().varianteManager.getCurrent().getPlateau());
        }
        getApp().varianteManager.addClassiqueOrderPlayer();

        getApp().setRoot("varianteMenu2");
    }
}
