package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class RulesController extends Controller {

    @FXML
    public TableView<RuleRow> tab;
    @FXML
    public TableColumn<RuleRow, Button> delCol;
    @FXML
    public TableColumn<RuleRow, Button> editCol;
    @FXML
    public TableColumn<RuleRow, Label> ruleCol;

    private ObservableList<RulesController.RuleRow> rules;

    @FXML
    private void confirmButton() throws IOException {
        App.soundManager.playSound("button-click");
        //TODO: valider texte de l'input
        getApp().setRoot("varianteMenu2");
    }

    @FXML
    private void addButton() {
        App.soundManager.playSound("button-click");
        //TODO AJOUTER UNE PIECE
        //Rule r = new Rule();
        Button be = new Button("Edit");
        be.setOnAction(event -> {
            try {
                editRule(); //(r)
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button bd = new Button("Delete");
        bd.setOnAction(event -> {
            deletRule(); //(r)
        });
        Label l = new Label("Si Pawn meurs alors perdu");

        RulesController.RuleRow rr = new RulesController.RuleRow(be, bd, l);// p);
        rules.add(rr);
    }

    @FXML
    public void initialize() {
        //TODO REMPLIR PROPREMENT AVEC LES DONNES ENREGISTREES
        editCol.setCellValueFactory(cellData -> cellData.getValue().editProperty());
        delCol.setCellValueFactory(cellData -> cellData.getValue().delProperty());
        ruleCol.setCellValueFactory(cellData -> cellData.getValue().ruleTextProperty());

        rules = FXCollections.observableArrayList();
        tab.setItems(rules);
    }

    public void editRule() throws IOException {
        //r parameter
        //TODO passer r à la scene
        App.soundManager.playSound("button-click");
        getApp().setRoot("editRule");
    }

    public void deletRule() {
        App.soundManager.playSound("lose");
        //rules.remove(r);
    }

    private static class RuleRow {
        private final SimpleObjectProperty<Button> edit;
        private final SimpleObjectProperty<Button> del;
        private final SimpleObjectProperty<Label> ruleText;
        //private final Rule rule;

        public RuleRow(Button edit, Button del, Label ruleText) {//, Rule rule) {
            this.edit = new SimpleObjectProperty<>(edit);
            this.del = new SimpleObjectProperty<>(del);
            this.ruleText = new SimpleObjectProperty<>(ruleText);
            //this.rule = rule;
        }

        public Button getEdit() {
            return edit.get();
        }

        public SimpleObjectProperty<Button> editProperty() {
            return edit;
        }

        public void setEdit(Button edit) {
            this.edit.set(edit);
        }

        public Button getDel() {
            return del.get();
        }

        public SimpleObjectProperty<Button> delProperty() {
            return del;
        }

        public void setDel(Button del) {
            this.del.set(del);
        }

        public Label getRuleText() {
            return ruleText.get();
        }

        public SimpleObjectProperty<Label> ruleTextProperty() {
            return ruleText;
        }

        public void setRuleText(Label ruleText) {
            this.ruleText.set(ruleText);
        }

        /*
        public Piece getRule() {
            return rule;
        }
        */

    }
}
