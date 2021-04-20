package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.model.RegleInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class VarianteMenu2Controller extends Controller {

    @FXML
    private void pieceButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("piece");
    }

    @FXML
    private void initPosButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("initPos");
    }

    @FXML
    public void groupCaseButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("groupCase");
    }

    @FXML
    private void backButton() {
        getApp().soundManager.playSound("button-cancel");
        Optional<ButtonType> result =
                showAlert(Alert.AlertType.CONFIRMATION, "Attention !\nSi vous revenez en arrière, les pièces posées, les pièces créés seront supprimés. \nÊtes vous sûr de revenir en arrière ?");
        result.ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    getApp().setRoot("varianteMenu1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void endButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");
        ArrayList<RegleInterface> list =  getApp().varianteManager.getCurrent().getRegles();
        list.clear();
        getApp().varianteManager.addClassiqueRules(list, getApp().varianteManager.getCurrent().getJoueurs());
        getApp().setRoot("varianteMenu3");
    }

    @FXML
    public void orderPlayerButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("orderPlayer");
    }
}
