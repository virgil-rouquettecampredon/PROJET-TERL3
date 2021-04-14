package org.example;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

public class EditRuleController extends Controller {
    @FXML
    public ComboBox<String> transitionBox;
    @FXML
    public VBox conditionVBox;
    @FXML
    public VBox consequenceVBox;

    private ArrayList<ComboBox<String>> conditionBoxes;
    private ArrayList<ComboBox<String>> consequenceBoxes;

    public ArrayList<HBox> conditionHBoxes;
    public ArrayList<HBox> consequenceHBoxes;

    @Override
    public void initialise() {
        conditionBoxes = new ArrayList<>();
        consequenceBoxes = new ArrayList<>();

        conditionHBoxes = new ArrayList<>();
        HBox conditionHBox = generateNewHBox();
        conditionHBoxes.add(conditionHBox);
        conditionVBox.getChildren().add(conditionHBox);

        consequenceHBoxes = new ArrayList<>();
        HBox consequenceHBox = generateNewHBox();
        consequenceHBoxes.add(consequenceHBox);
        consequenceVBox.getChildren().add(consequenceHBox);

        appearNextCondition();
        appearNextConsequence();
    }

    private ComboBox<String> generateNewConditionComboBox() {
        ComboBox<String> box = new ComboBox<>();
        box.setPromptText("Sujet");
        //TODO A changer
        box.getItems().addAll("Joueur", "Piece", "PieceToken", "Et");

        box.setOnAction(e -> conditionBoxAction(consequenceBoxes.size()));
        return box;
    }

    private ComboBox<String> generateNewConsequenceComboBox() {
        ComboBox<String> box = new ComboBox<>();
        box.setPromptText("Sujet");
        //TODO A changer
        box.getItems().addAll("Joueur", "Piece", "PieceToken", "Et");

        box.setOnAction(e -> consequenceBoxAction(consequenceBoxes.size()));
        return box;
    }

    private HBox generateNewHBox() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        hbox.prefHeight(50);
        return hbox;
    }

    @FXML
    private void validateButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        //TODO: valider
        getApp().setRoot("rules");
    }

    private boolean lastBoxIsAndOr(ArrayList<ComboBox<String>> list) {
        String lastBox = list.get(list.size()-1).getSelectionModel().getSelectedItem();
        return lastBox.equals("Et") || lastBox.equals("Ou");
    }

    private void deleteLastConditionBox() {
        conditionBoxes.remove(conditionBoxes.size()-1);

        HBox lastHBox = conditionHBoxes.get(conditionHBoxes.size()-1);
        lastHBox.getChildren().remove(lastHBox.getChildren().size()-1);

        if (conditionHBoxes.get(conditionHBoxes.size()-1).getChildren().size()==0) {
            conditionHBoxes.remove(conditionHBoxes.size()-1);

            conditionVBox.getChildren().remove(conditionVBox.getChildren().size()-1);
        }
    }

    private void deleteLastConsequenceBox() {
        consequenceBoxes.remove(consequenceBoxes.size()-1);

        HBox lastHBox = consequenceHBoxes.get(consequenceHBoxes.size()-1);
        lastHBox.getChildren().remove(lastHBox.getChildren().size()-1);

        if (consequenceHBoxes.get(consequenceHBoxes.size()-1).getChildren().size()==0) {
            consequenceHBoxes.remove(consequenceHBoxes.size()-1);

            consequenceVBox.getChildren().remove(consequenceVBox.getChildren().size()-1);
        }
    }

    private void conditionBoxAction(int index) {
        System.out.println(index);

        int size = conditionBoxes.size()-1;
        for (int i = index; i < size; i++) {
            deleteLastConditionBox();
        }

        appearNextCondition();
    }

    private void consequenceBoxAction(int index) {
        System.out.println(index);

        int size = consequenceBoxes.size()-1;
        for (int i = index; i < size; i++) {
            deleteLastConsequenceBox();
        }

        appearNextConsequence();
    }

    private void appearNext(ComboBox<String> box, ArrayList<ComboBox<String>> comboList, ArrayList<HBox> hBoxList, VBox vBox) {
        //SI ET/OU ALORS AJOUTER DANS UN AUTRE HBOX, LE PROCHAIN DANS ENCORE UN AUTRE
        //SINON AJOUTER DANS LE DERNIER HBOX
        if (comboList.size()>0 && lastBoxIsAndOr(comboList)) {
            HBox conditionHBox = generateNewHBox();
            conditionHBox.getChildren().add(box);
            hBoxList.add(conditionHBox);
            vBox.getChildren().add(conditionHBox);
        }
        else {
            System.out.println(hBoxList.get(hBoxList.size()-1).getChildren());
            hBoxList.get(hBoxList.size()-1).getChildren().add(box);
            System.out.println(hBoxList.get(hBoxList.size()-1).getChildren());
        }

        comboList.add(box);
    }

    private void appearNextCondition() {
        appearNext(generateNewConditionComboBox(), conditionBoxes, conditionHBoxes, conditionVBox);
    }

    private void appearNextConsequence() {
        appearNext(generateNewConsequenceComboBox(), consequenceBoxes, consequenceHBoxes, consequenceVBox);
    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "texte"); //todo texte edition regle
    }
}
