package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.model.RegleInterface;
import org.example.model.Regles.Regle;

import java.io.IOException;
import java.util.ArrayList;

public class RulesController extends Controller {

    @FXML
    public TableView<RegleRow> tab;
    @FXML
    public TableColumn<RegleRow, String> ruleCol;
    @FXML
    public TableColumn<RegleRow, Pane> radioCol;

    private ObservableList<RegleRow> rules;

    @FXML
    private void confirmButton() throws IOException {
        getApp().soundManager.playSound("button-click");

        ArrayList<RegleInterface> list = getApp().varianteManager.getCurrent().getRegles();
        ArrayList<RegleInterface> listBackup = new ArrayList<>(list);
        list.clear();
        try {
            for (RegleRow r : rules) {
                RegleInterface ri = r.getRegle();
                ri.setTraitementAvantCoup(r.isAvant());
                list.add(ri);
            }
        } catch (SecurityException se) {
            showAlert(Alert.AlertType.ERROR, "Erreur : Une regle est à la fois avant et après! ("+se.getMessage()+")");
            list.addAll(listBackup);
        }

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
        radioCol.setCellValueFactory(cellData -> cellData.getValue().radioProperty());

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
        for (RegleInterface r : getApp().varianteManager.getCurrent().getRegles()) {
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

        getApp().varianteManager.getCurrent().getRegles().remove(r.getRegle());
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
        private final SimpleObjectProperty<Pane> radioPane;
        private final RegleInterface rule;

        private final RadioButton rbAvant;
        private final RadioButton rbApres;

        public RegleRow(RegleInterface rule) {
            this.rule = rule;
            this.ruleText = new SimpleStringProperty(rule.toString());

            Pane pane = new AnchorPane();
            pane.maxWidth(219);

            ToggleGroup tg = new ToggleGroup();

            rbAvant = new RadioButton();
            rbAvant.setSelected(rule.isTraitementAvantCoup());
            rbAvant.setToggleGroup(tg);
            rbAvant.setText("Avant le coup");

            rbApres = new RadioButton();
            rbApres.setSelected(!rule.isTraitementAvantCoup());
            rbApres.setToggleGroup(tg);
            rbApres.setText("Apres le coup");
            rbApres.setTranslateX(120);

            pane.getChildren().add(rbAvant);
            pane.getChildren().add(rbApres);
            radioPane = new SimpleObjectProperty<>(pane);
        }

        public String getRegleText() {
            return ruleText.get();
        }

        public SimpleStringProperty ruleTextProperty() {
            return ruleText;
        }

        public SimpleObjectProperty<Pane> radioProperty() {
            return radioPane;
        }

        public void setRegleText(String ruleText) {
            this.ruleText.set(ruleText);
        }

        public RegleInterface getRegle() {
            return rule;
        }

        public boolean isAvant() throws SecurityException{
            if (rbAvant.isSelected() && rbApres.isSelected()) {
                throw new SecurityException(rule.toString());
            }
            return rbAvant.isSelected();
        }
    }
}
