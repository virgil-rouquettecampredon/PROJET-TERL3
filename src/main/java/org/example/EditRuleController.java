package org.example;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditRuleController extends Controller {
    @FXML
    public ComboBox<String> sujet1Box;
    @FXML
    public ComboBox<String> actionBox;
    @FXML
    public ComboBox<String> cibleBox;
    @FXML
    public ComboBox<String> sujet2Box;
    @FXML
    public ComboBox<String> etatBox;
    @FXML
    public ComboBox<String> consequenceBox;

    @FXML
    private void validateButton() throws IOException {
        App.soundManager.playSound("button-click");
        //TODO: valider texte de l'input
        System.out.println("sujet1Box="+sujet1Box.getValue());
        System.out.println("actionBox="+actionBox.getValue());
        System.out.println("cibleBox="+cibleBox.getValue());
        System.out.println("sujet2Box="+sujet2Box.getValue());
        System.out.println("etatBox="+etatBox.getValue());
        System.out.println("consequenceBox="+consequenceBox.getValue());
        getApp().setRoot("rules");
    }

    @FXML
    public void initialize() {
        //TODO A changer
        sujet1Box.getItems().addAll("Pawn", "AllPiece");
        actionBox.getItems().addAll("Mange", "Meurs");
        cibleBox.getItems().addAll("Pawn", "Any");
        sujet2Box.getItems().addAll("Pawn", "AllPiece");
        etatBox.getItems().addAll("Mooved", "can Roque");
        consequenceBox.getItems().addAll("Meurs", "Promotion");
    }
}
