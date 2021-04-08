package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ImportController extends Controller {

    @FXML
    public TextField input;

    @FXML
    private void chooseButton(){
        getApp().soundManager.playSound("button-click");
        //Ouvrir une fenetre pour selectionner un fichier et le mettre dans l'input
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une variante");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ChessBurger Variante", "*.cbvr"));
        File file = fileChooser.showOpenDialog(getApp().scene.getWindow());
        if (file != null) {
            input.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void validateButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");

        try {
            getApp().varianteManager.importFile(input.getText());
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur : Fichier non valide : "+input.getText());
            return;
        }

        System.out.println(input.getText());
        getApp().setRoot("home");
    }

    @FXML
    public void backButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");
        getApp().setRoot("home");
    }
}
