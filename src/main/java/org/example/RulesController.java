package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.model.Regles.Regle;

import java.io.IOException;

public class RulesController extends Controller {

    @FXML
    public TableView<RegleRow> tab;
    @FXML
    public TableColumn<RegleRow, String> ruleCol;

    private ObservableList<RegleRow> rules;

    @FXML
    private void confirmButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        //TODO: valider
        getApp().setRoot("varianteMenu3");
    }

    @FXML
    private void addButton() {
        getApp().soundManager.playSound("button-click");
        try {
            getApp().setRoot("editRule");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialise() {
        ruleCol.setCellValueFactory(cellData -> cellData.getValue().ruleTextProperty());

        ContextMenu contextMenu = new ContextMenu();

        MenuItem editItem = new MenuItem("Modifier");
        editItem.setOnAction((event) -> {
            editRegle();
        });

        MenuItem addItem = new MenuItem("Ajouter");
        addItem.setOnAction((event) -> {
            addButton();
        });

        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setOnAction((event) -> {
            deletRegle();
        });

        contextMenu.getItems().addAll(editItem, addItem, deleteItem);
        tab.setContextMenu(contextMenu);

        rules = FXCollections.observableArrayList();
        for (Regle r : getApp().varianteManager.getCurrent().getRegles()) {
            rules.add(new RegleRow(r));
        }
        tab.setItems(rules);
    }

    public void editRegle() {
        getApp().soundManager.playSound("button-click");

        RegleRow r = tab.getSelectionModel().getSelectedItem();
        if (r == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : aucune regle selectionne");
            return;
        }

        try {
            getApp().setRoot("editRule", r.getRegle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletRegle() {
        getApp().soundManager.playSound("lose");

        RegleRow r = tab.getSelectionModel().getSelectedItem();
        if (r == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : aucune regle selectionne");
            return;
        }

        rules.remove(r);
    }

    private static class RegleRow {
        private final SimpleStringProperty ruleText;
        private final Regle rule;

        public RegleRow(Regle rule) {
            this.rule = rule;
            this.ruleText = new SimpleStringProperty(rule.toString()); //todo a changer
        }

        public String getRegleText() {
            return ruleText.get();
        }

        public SimpleStringProperty ruleTextProperty() {
            return ruleText;
        }

        public void setRegleText(String ruleText) {
            this.ruleText.set(ruleText);
        }

        public Regle getRegle() {
            return rule;
        }
    }
}
