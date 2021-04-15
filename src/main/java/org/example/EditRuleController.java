package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import org.example.model.*;
import org.example.model.Regles.ElementRegle;
import org.example.model.Regles.Jeton_Interface;
import org.example.model.Regles.Structure.Automate.Automate_Interface;
import org.example.model.Regles.Structure.Automate.Automate_Interface_Condition;
import org.example.model.Regles.Structure.Automate.Automate_Interface_Consequence;
import org.example.model.Regles.Regle;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;

public class EditRuleController extends Controller {
    @FXML
    public ComboBox<String> transitionBox;
    @FXML
    public VBox conditionVBox;
    @FXML
    public VBox consequenceVBox;

    private ArrayDeque<ControlElement> conditionBoxes;
    private ArrayDeque<ControlElement> consequenceBoxes;

    private ArrayDeque<HBox> conditionHBoxes;
    private ArrayDeque<HBox> consequenceHBoxes;


    //Automtes pour l'aide à la création des règles
    private Automate_Interface<Jeton_Interface> automateCondition;
    private Automate_Interface<Jeton_Interface> automateConsequence;


    @FXML
    private void validateButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        //TODO: valider
        /*
        Regle regle = new Regle();
        for (ComboBox<String> condition : conditionBoxes) {
            regle.addCondition(condition.getValue());
        }
        for (ComboBox<String> consequence : consequenceBoxes) {
            regle.addConsequence(consequence.getValue());
        }
         */
        getApp().setRoot("rules");
    }

    @Override
    public void initialise() {
        //getApp().varianteManager.getCurrent().
        VarianteBuilder current = getApp().varianteManager.getCurrent();
        List<Piece> pieces = current.getAllPawn();
        List<GroupCases> cases = current.getListGroupCases();
        List<Joueur> joueurs = current.getJoueurs();


        automateCondition = new Automate_Interface_Condition(pieces,cases,joueurs);
        automateConsequence = new Automate_Interface_Consequence(pieces,cases,joueurs);
        automateCondition.initialiserAutomate();
        automateConsequence.initialiserAutomate();


        conditionBoxes = new ArrayDeque<>();
        consequenceBoxes = new ArrayDeque<>();

        conditionHBoxes = new ArrayDeque<>();
        consequenceHBoxes = new ArrayDeque<>();

        HBox conditionHBox = generateNewHBox();
        conditionHBoxes.add(conditionHBox);
        conditionVBox.getChildren().add(conditionHBox);

        HBox consequenceHBox = generateNewHBox();
        consequenceHBoxes.add(consequenceHBox);
        consequenceVBox.getChildren().add(consequenceHBox);

        if (userVar != null) {
            Regle regle = (Regle) userVar;
            /*for (Machin m : regle.getCondition()) {
                appearNext(generateNewConditionComboBox(m), conditionBoxes, conditionHBoxes, conditionVBox);
            }
            for (Machin m : regle.getConsequence()) {
                appearNext(generateNewConsequenceComboBox(m), consequenceBoxes, consequenceHBoxes, consequenceVBox);
            }

             */
        }
        else {
            appearNextCondition();
            appearNextConsequence();
        }

    }

    /*private ComboBox<String> generateNewConditionComboBox(Machin m) {
        ComboBox<String> box = generateNewConditionComboBox();
        box.setValue(m);
    }*/

    private ControlElement generateNewConditionComboBox() {
        boolean demandeEntier = false;//todo
        int index = conditionBoxes.size();

        ControlElement control;
        if (demandeEntier) {
            TextField field = new TextField();
            field.setPromptText("Nombre");

            field.setOnAction(e -> conditionInputAction(index));
            control = new ControlElement(field);
        }
        else {
            ComboBox<ComboBoxItem> box = new ComboBox<>();
            box.setPromptText("Sujet");

            //TODO A changer
            //box.getItems().addAll("Joueur", "Piece", "PieceToken", "Et");

            box.setOnAction(e -> conditionBoxAction(index));

            control = new ControlElement(box);
        }

        return control;
    }

    private ControlElement generateNewConsequenceComboBox() {
        boolean demandeEntier = false; //todo
        int index = consequenceBoxes.size();

        ControlElement control;
        if (demandeEntier) {
            TextField field = new TextField();
            field.setPromptText("Nombre");

            field.setOnAction(e -> consequenceInputAction(index));
            control = new ControlElement(field);
        }
        else {
            ComboBox<ComboBoxItem> box = new ComboBox<>();
            box.setPromptText("Sujet");

            //TODO A changer
            //box.getItems().addAll("Joueur", "Piece", "PieceToken", "Et");

            box.setOnAction(e -> consequenceBoxAction(index));

            control = new ControlElement(box);
        }

        return control;
    }

    private HBox generateNewHBox() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        hbox.prefHeight(50);
        return hbox;
    }

    private boolean lastBoxIsAndOr(ArrayDeque<ControlElement> list) {
        ComboBox<ComboBoxItem> last = list.getLast().getComboBox();
        if (last != null) {
            last.getSelectionModel().getSelectedItem();
            return last.equals("Et") || last.equals("Ou");//Todo elementRegle est ET
        }
        else {
            return false;
        }
    }

    private void deleteLastBox(ArrayDeque<ControlElement> boxes, ArrayDeque<HBox> hBoxes, VBox vBox) {
        //supprimer le dernier comboBox
        ControlElement last = boxes.removeLast();
        HBox lastHBox = hBoxes.getLast();
        if (last.getComboBox() != null) {
            lastHBox.getChildren().remove(last.getComboBox());
        }
        else {
            lastHBox.getChildren().remove(last.getField());
        }


        //Si la dernière HBox est vide alors la supprimer
        if (lastHBox.getChildren().size() == 0) {
            HBox sameLastBox = hBoxes.removeLast();

            vBox.getChildren().remove(lastHBox);
        }
    }

    private void deleteLastConditionBox() {
        deleteLastBox(conditionBoxes, conditionHBoxes, conditionVBox);
    }

    private void deleteLastConsequenceBox() {
        deleteLastBox(consequenceBoxes, consequenceHBoxes, consequenceVBox);
    }

    private void conditionBoxAction(int index) {
        int size = conditionBoxes.size() -1;
        for (int i = index; i < size; i++) {
            deleteLastConditionBox();
        }

        appearNextCondition();
    }

    private void conditionInputAction(int index) {
        try {
            Integer.parseInt(conditionBoxes.getLast().getField().getText());
        } catch (NumberFormatException e) {
            return;
        }
        conditionBoxAction(index);
    }

    private void consequenceBoxAction(int index) {
        int size = consequenceBoxes.size() -1;
        for (int i = index; i < size; i++) {
            deleteLastConsequenceBox();
        }

        appearNextConsequence();
    }

    private void consequenceInputAction(int index) {
        try {
            Integer.parseInt(consequenceBoxes.getLast().getField().getText());
        } catch (NumberFormatException e) {
            return;
        }
        consequenceBoxAction(index);
    }

    private void appearNext(ControlElement box, ArrayDeque<ControlElement> comboList, ArrayDeque<HBox> hBoxList, VBox vBox) {
        //SI "ET" ou "OU" ALORS AJOUTER DANS UN AUTRE HBOX, LE PROCHAIN DANS ENCORE UN AUTRE
        //SINON AJOUTER DANS LE DERNIER HBOX
        if (comboList.size()>0 && lastBoxIsAndOr(comboList)) {
            HBox conditionHBox = generateNewHBox();
            if (box.getComboBox() != null) {
                conditionHBox.getChildren().add(box.getComboBox());
            }
            else {
                conditionHBox.getChildren().add(box.getField());
            }
            hBoxList.add(conditionHBox);
            vBox.getChildren().add(conditionHBox);
        }
        else {
            if (box.getComboBox() != null) {
                hBoxList.getLast().getChildren().add(box.getComboBox());
            }
            else {
                hBoxList.getLast().getChildren().add(box.getField());
            }
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

    public static class ControlElement {
        private ComboBox<ComboBoxItem> comboBox;
        private TextField field;
        private boolean isComboBox;

        public ControlElement(ComboBox<ComboBoxItem> comboBox) {
            this.comboBox = comboBox;
            isComboBox = true;
        }

        public ControlElement(TextField field) {
            this.field = field;
            isComboBox = false;
        }

        public ComboBox<ComboBoxItem> getComboBox() {
            return comboBox;
        }

        public TextField getField() {
            return field;
        }
    }

    public static class ComboBoxItem {
        private ElementRegle elementregle;

        public ComboBoxItem(ElementRegle elementregle) {
            this.elementregle = elementregle;
        }

        public String toString() {
            return elementregle.getNomInterface();
        }
    }
}
