package org.example;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import org.example.model.*;
import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Automate.Automate_Interface;
import org.example.model.Regles.Structure.Automate.Automate_Interface_Condition;
import org.example.model.Regles.Structure.Automate.Automate_Interface_Consequence;


import java.io.IOException;
import java.util.*;

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

        RegleInterface regle = new RegleInterface();
        if (automateCondition.getCurEtat() != -1) {
            showAlert(Alert.AlertType.ERROR, "Erreur : la condition n'est pas finie!");
            return;
        }
        if (automateConsequence.getCurEtat() != -1) {
            showAlert(Alert.AlertType.ERROR, "Erreur : la consequence n'est pas finie!");
            return;
        }
        try {
            for (ControlElement condition : conditionBoxes) {
                if (condition.getComboBox() != null) {
                    regle.getRegle().add(condition.getComboBox().getValue().getElementRegle());
                } else if (condition.getField() != null && condition.isNumber()) {
                    regle.getRegle().add(new ElementRegle(Jeton_Interface.NOMBRE, condition.getField().getText(), condition.getField().getText()));
                } else if (condition.getField() != null){
                    regle.getRegle().add(new ElementRegle(Jeton_Interface.ALIAS, "as", condition.getField().getText()));
                }
            }
            for (ControlElement consequence : consequenceBoxes) {
                if (consequence.getComboBox() != null) {

                    regle.getRegle().add(consequence.getComboBox().getValue().getElementRegle());
                } else if (consequence.getField() != null){
                    //pas besoin car pas de textField dans les consequences mais si il y en avait : pareil que condition
                    regle.getRegle().add(new ElementRegle(Jeton_Interface.NOMBRE, consequence.getField().getText(), consequence.getField().getText()));
                }
            }
        } catch (NullPointerException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur : il y a des boites non remplies!");
            e.printStackTrace();
            return;
        }
        getApp().varianteManager.getCurrent().getRegles().add(regle);
        getApp().setRoot("rules");
    }

    @Override
    public void initialise() {
        //INITIALISTION DES AUTOMATES INTERFACE
        VarianteBuilder current = getApp().varianteManager.getCurrent();
        List<Piece> pieces = current.getAllPawn();
        List<GroupCases> cases = current.getListGroupCases();
        List<Joueur> joueurs = current.getJoueurs();


        automateCondition = new Automate_Interface_Condition(pieces,cases,joueurs);
        automateConsequence = new Automate_Interface_Consequence(pieces,cases,joueurs);
        automateCondition.initialiserAutomate();
        automateConsequence.initialiserAutomate();


        //INITIALISATION DE L'INTERFACE
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

        //CHARGEMENT DE LA REGLE A MODIFIER
        if (userVar != null) {
            RegleInterface regle = (RegleInterface) userVar;
            boolean beforeAlors = true;
            for (ElementRegle element : regle.getRegle()) {
                if (beforeAlors) {
                    ControlElement ce = generateNewConditionComboBox(element);
                    appearNext(ce, conditionBoxes, conditionHBoxes, conditionVBox);
                    try {
                        automateCondition.selectionnerElement(element);
                    } catch (MauvaiseDefinitionRegleException e) {
                        showAlert(Alert.AlertType.ERROR, "Erreur critique : "+e.getMessage());
                    }
                }
                else {
                    appearNext(generateNewConsequenceComboBox(element), consequenceBoxes, consequenceHBoxes, consequenceVBox);
                    try {
                        automateConsequence.selectionnerElement(element);
                    } catch (MauvaiseDefinitionRegleException e) {
                        showAlert(Alert.AlertType.ERROR, "Erreur critique : "+e.getMessage());
                    }
                }
                if (element.getJetonAssocie() == Jeton_Interface.ALORS) {
                    beforeAlors = false;
                    conditionBoxes.add(new ControlElement());
                }
            }
            consequenceBoxes.add(new ControlElement());
        }
        else {
            appearNextCondition();
        }

    }

    private ControlElement generateNewConditionComboBox(ElementRegle e) {
        ControlElement control = generateNewConditionComboBox();
        if (control.getComboBox() != null) {
            control.getComboBox().getSelectionModel().select(new ComboBoxItem(e));
        }
        else {
            if (control.isNumber()) {
                control.getField().setText("" + e.getNomInterface());
            }
            else {
                control.getField().setText("" + e.getNomRegle());
            }
            control.getField().setDisable(true);
        }
        return control;
    }

    private ControlElement generateNewConsequenceComboBox(ElementRegle e) {
        ControlElement control = generateNewConsequenceComboBox();
        if (control.getComboBox() != null) {
            control.getComboBox().getSelectionModel().select(new ComboBoxItem(e));
        }
        else {
            control.getField().setText(""+e.getNomInterface());
            control.getField().setDisable(true);
        }
        return control;
    }

    private ControlElement generateNewConditionComboBox() {
        //RECURER LE PROCHAIN DANS L'AUTO
        List<ElementRegle> prochains = null;
        try {
            prochains = automateCondition.elementSelectionnables();
        }catch (MauvaiseDefinitionRegleException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur Critique (PROPOSITION) : " + e.getMessage());
        }
        if(prochains == null){
            prochains = new ArrayList<>();
        }

        //AJOUTER A L'INTERFACE
        int index = conditionBoxes.size();
        ControlElement control;

        //if(prochains.size() == 1 && prochains.get(0).getJetonAssocie() == Jeton_Interface.NOMBRE){
        if (conditionBoxes.size() > 0 && conditionBoxes.getLast().getComboBox() != null &&
                conditionBoxes.getLast().getComboBox().getValue().getElementRegle().getJetonAssocie() == Jeton_Interface.COMPARATEUR) {
            TextField field = new TextField();
            field.setPromptText("Nombre");

            field.setOnAction(e -> conditionNombreInputAction(index));
            control = new ControlElement(field, true);
        }
        else if(conditionBoxes.size() > 0 && conditionBoxes.getLast().getComboBox() != null &&
                conditionBoxes.getLast().getComboBox().getValue().getElementRegle().getJetonAssocie() == Jeton_Interface.ALIASDEF){
            TextField field = new TextField();
            field.setPromptText("Alias");

            field.setOnAction(e -> conditionAliasInputAction(index));
            control = new ControlElement(field, false);
        }
        else {
            ComboBox<ComboBoxItem> box = new ComboBox<>();
            box.setPromptText("Sujet");

            for (ElementRegle e : prochains) {
                box.getItems().add(new ComboBoxItem(e));
            }

            box.setOnAction(e -> conditionBoxAction(index));

            control = new ControlElement(box);
        }

        return control;
    }

    private ControlElement generateNewConsequenceComboBox() {
        //RECURER LE PROCHAIN DANS L'AUTO
        List<ElementRegle> prochains = null;
        try {
            prochains = automateConsequence.elementSelectionnables();
        }catch (MauvaiseDefinitionRegleException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur Critique (PROPOSITION) : " + e.getMessage());
        }
        if(prochains == null){
            prochains = new ArrayList<>();
        }

        //AJOUTER A L'INTERFACE
        int index = consequenceBoxes.size();

        ComboBox<ComboBoxItem> box = new ComboBox<>();
        box.setPromptText("Sujet");

        for (ElementRegle e : prochains) {
            box.getItems().add(new ComboBoxItem(e));
        }
        box.setOnAction(e -> consequenceBoxAction(index));

        return new ControlElement(box);
    }

    private HBox generateNewHBox() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        hbox.prefHeight(50);
        return hbox;
    }

    private boolean lastBoxIsAndOr(ArrayDeque<ControlElement> list) {
        if (list.isEmpty()) return false;
        ComboBox<ComboBoxItem> last = list.getLast().getComboBox();
        if (last != null && last.getValue() != null) {
            Jeton_Interface j = last.getSelectionModel().getSelectedItem().getElementRegle().getJetonAssocie();
            return (j == Jeton_Interface.OU || j == Jeton_Interface.ET);
        }
        else {
            return false;
        }
    }

    private boolean lastBoxIsAlors(ArrayDeque<ControlElement> list) {
        if (list.isEmpty()) return false;
        ComboBox<ComboBoxItem> last = list.getLast().getComboBox();
        if (last != null && last.getValue() != null) {
            Jeton_Interface j = last.getSelectionModel().getSelectedItem().getElementRegle().getJetonAssocie();
            return (j == Jeton_Interface.ALORS || j == Jeton_Interface.FIN);
        }
        else {
            return false;
        }
    }

    private void deleteLastBox(ArrayDeque<ControlElement> boxes, ArrayDeque<HBox> hBoxes, VBox vBox) {
        //supprimer le dernier comboBox
        ControlElement last = boxes.removeLast();
        HBox lastHBox = hBoxes.getLast();
        if (last.getComboBox() != null && last.getField() == null) {
            lastHBox.getChildren().remove(last.getComboBox());
        }
        else if (last.getComboBox() == null && last.getField() != null){
            lastHBox.getChildren().remove(last.getField());
        }


        //Si la dernière HBox est vide alors la supprimer
        if (lastHBox.getChildren().size() == 0) {
            hBoxes.removeLast();

            vBox.getChildren().remove(lastHBox);
        }
    }

    private void deleteLastAutomateCondition() {
        //On dépile si:
        //   On est une boite validé
        //ou on est un nombre ou un input alias validé
        if ((conditionBoxes.getLast().getField() != null && conditionBoxes.getLast().getField().isDisabled())
                || (conditionBoxes.getLast().getComboBox() != null && conditionBoxes.getLast().getComboBox().getValue() != null)) {
            System.out.println("Automate Condition depilé : "+conditionBoxes.getLast());
            automateCondition.revenirEnArriere();
        }
    }

    private void deleteLastConditionBox() {
        //System.out.println("Interface Condition depilé");
        deleteLastBox(conditionBoxes, conditionHBoxes, conditionVBox);
    }

    private void deleteLastAutomateConsequence() {
        if ((consequenceBoxes.getLast().getField() != null && consequenceBoxes.getLast().getField().isDisabled())
                || (consequenceBoxes.getLast().getComboBox() != null && consequenceBoxes.getLast().getComboBox().getValue() != null)) {
            //System.out.println("Automate Consequence depilé");
            automateConsequence.revenirEnArriere();
        }
    }

    private void deleteLastConsequenceBox() {
        //System.out.println("Interface Consequence depilé");
        deleteLastBox(consequenceBoxes, consequenceHBoxes, consequenceVBox);
    }

    private void deleteAllConsequence() {
        VarianteBuilder current = getApp().varianteManager.getCurrent();
        List<Piece> pieces = current.getAllPawn();
        List<GroupCases> cases = current.getListGroupCases();
        List<Joueur> joueurs = current.getJoueurs();

        automateConsequence = new Automate_Interface_Consequence(pieces,cases,joueurs);
        automateConsequence.initialiserAutomate();
        automateConsequence.setAlias(automateCondition.getAlias());

        //INITIALISATION DE L'INTERFACE
        consequenceBoxes = new ArrayDeque<>();
        consequenceHBoxes = new ArrayDeque<>();

        consequenceVBox.getChildren().clear();
        HBox consequenceHBox = generateNewHBox();
        consequenceHBoxes.add(consequenceHBox);
        consequenceVBox.getChildren().add(consequenceHBox);
    }

    private void conditionBoxAction(int index) {
        //System.out.println("ACTION id="+index + " size="+conditionBoxes.size());
        //SUPPRIMER LES CHOIX APRES
        int size = conditionBoxes.size() - 1;
        for (int i = index; i < size; i++) {
            deleteLastAutomateCondition();
            deleteLastConditionBox();
        }
        //Si je n'était pas dernier et que je ne suis pas un nombre alors depiler automate encore
        if (index < size && conditionBoxes.getLast().getField() == null) {
            deleteLastAutomateCondition();
        }
        deleteAllConsequence();

        //DIRE A L'AUTOMATE LE CHOIX
        ControlElement c = conditionBoxes.getLast();

        if (c.getComboBox() != null) {
            if (c.getComboBox().getValue() != null) {
                ElementRegle choix = c.getComboBox().getValue().getElementRegle();
                try {
                    System.out.println("On empile dans l'automate : "+choix);
                    automateCondition.selectionnerElement(choix);
                } catch (MauvaiseDefinitionRegleException e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur Critique (CHOIX) : " + e.getMessage());
                }

            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur : choix vide");
            }
        }

        //FAIRE APPARAÎTRE LE PROCHAIN
        appearNextCondition();
    }

    private void conditionNombreInputAction(int index) {
        //System.out.println("ACTION DEMANDEE id="+index);
        ControlElement c = conditionBoxes.getLast();
        if(c.getField() != null) {
            try {
                int value = Integer.parseInt(c.getField().getText());
                System.out.println("On empile dans l'automate le nombre "+value);
                automateCondition.selectionnerElement(new ElementRegle(Jeton_Interface.NOMBRE,""+value,""+value));

                c.getField().setDisable(true);

                conditionBoxAction(index);
            } catch(NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur : " + c.getField().getText() + " n'est pas un nombre.");
            } catch (MauvaiseDefinitionRegleException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur critique (CHOIX) : " + e.getMessage());
            }
        }

    }

    private void conditionAliasInputAction(int index) {
        //System.out.println("ACTION DEMANDEE id="+index);
        ControlElement c = conditionBoxes.getLast();
        if(c.getField() != null) {
            String alias = c.getField().getText();
            if (automateCondition.peutEtreRenseigne(alias)) {
                try {
                    //conditionBoxes.getLast().getComboBox().getValue().getElementRegle().setNomRegle(alias);
                    System.out.println("On empile dans l'automate l'alias "+alias);
                    automateCondition.selectionnerElement(new ElementRegle(Jeton_Interface.ALIAS, "as", alias));

                    c.getField().setDisable(true);

                    conditionBoxAction(index);
                }
                catch (MauvaiseDefinitionRegleException e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur critique (CHOIX) : " + e.getMessage());
                }
            }
            else {
                showAlert(Alert.AlertType.ERROR, "Erreur : nom d'alias impossible ("+alias+")");
            }
        }
    }

    private void consequenceBoxAction(int index) {
        //SUPPRIMER LES CHOIX APRES
        int size = consequenceBoxes.size() -1;
        //Si le dernier element est un nombre alors le supprimer de l'inteface sans dépiler l'automate
        if (consequenceBoxes.getLast().getField() != null) {
            size--;
            deleteLastConsequenceBox();
        }
        for (int i = index; i < size; i++) {
            deleteLastAutomateConsequence();
            deleteLastConsequenceBox();
        }
        //Si pas dernier alors depiler automate encore
        if (index < size) {
            deleteLastAutomateConsequence();
        }

        //DIRE A L'AUTOMATE LE CHOIX
        ControlElement c = consequenceBoxes.getLast();

        if (c.getComboBox().getValue() != null) {
            ElementRegle choix = c.getComboBox().getValue().getElementRegle();
            try {
                automateConsequence.selectionnerElement(choix);
            } catch (MauvaiseDefinitionRegleException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur critique (CHOIX) : " + e.getMessage());
            }
        }
        else {
            showAlert(Alert.AlertType.ERROR, "Erreur : choix vide");
        }

        appearNextConsequence();
    }

    private void consequenceInputAction(int index) {
        showAlert(Alert.AlertType.ERROR, "Erreur critique (IL N'Y A PAS D'INPUT DE CONSEQUENCE)");
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
        //SI "ALORS" ou "FIN" ALORS FAIRE APPARAITRE UN VIDE ET FAIRE APPARAITRE LES CONSEQUENCES
        if (!lastBoxIsAlors(conditionBoxes)) {
            appearNext(generateNewConditionComboBox(), conditionBoxes, conditionHBoxes, conditionVBox);
        }
        else {
            conditionBoxes.add(new ControlElement());
            appearNextConsequence();
        }
    }

    private void appearNextConsequence() {
        //SI "ALORS" ou "FIN" ALORS FAIRE APPARAITRE UN VIDE
        if (!lastBoxIsAlors(consequenceBoxes)) {
            appearNext(generateNewConsequenceComboBox(), consequenceBoxes, consequenceHBoxes, consequenceVBox);
        }
        else {
            consequenceBoxes.add(new ControlElement());
        }
    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "texte"); //todo texte edition regle
    }

    @FXML
    public void backButton() {
        getApp().soundManager.playSound("button-cancel");
        Optional<ButtonType> result =
                showAlert(Alert.AlertType.CONFIRMATION, "Attention !\nSi vous annulez, la règle serat supprimée. \nÊtes vous sûr d'annuler ?");
        result.ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    getApp().setRoot("rules");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static class ControlElement {
        private final ComboBox<ComboBoxItem> comboBox;
        private final TextField field;
        private final boolean isNumberField;

        public ControlElement(ComboBox<ComboBoxItem> comboBox) {
            this.comboBox = comboBox;
            this.field = null;
            isNumberField = false;
        }

        public ControlElement(TextField field, boolean isNumber) {
            this.field = field;
            this.comboBox = null;
            isNumberField = isNumber;
        }

        public ControlElement() {
            isNumberField = false;
            this.comboBox = null;
            this.field = null;
        }

        public ComboBox<ComboBoxItem> getComboBox() {
            return comboBox;
        }

        public TextField getField() {
            return field;
        }

        public boolean isNumber() {return isNumberField;}

        @Override
        public String toString() {
            if (comboBox != null) {
                if (comboBox.getValue() != null) {
                    return comboBox.getValue().toString();
                }
                else {
                    return comboBox.toString();
                }
            }
            else if (field != null && isNumber()) {
                return "Number "+field.toString();
            }
            else if (field != null) {
                return "Alias "+field.toString();
            }
            return "indéfinie";
        }
    }

    public static class ComboBoxItem {
        private final ElementRegle elementregle;

        public ComboBoxItem(ElementRegle elementregle) {
            this.elementregle = elementregle;
        }

        public String toString() {
            return elementregle.getNomInterface();
        }

        public ElementRegle getElementRegle() {return elementregle;}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ComboBoxItem that = (ComboBoxItem) o;
            return Objects.equals(elementregle, that.elementregle);
        }

        @Override
        public int hashCode() {
            return Objects.hash(elementregle);
        }
    }
}