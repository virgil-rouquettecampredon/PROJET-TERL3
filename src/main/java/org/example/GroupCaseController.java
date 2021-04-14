package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.model.*;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;

import java.io.IOException;
import java.util.ArrayList;

public class GroupCaseController extends Controller {
    @FXML
    public TextField joueurInput;
    @FXML
    public TableView<GroupeRow> tab;
    @FXML
    public TableColumn<GroupeRow, String> nameCol;
    @FXML
    public Button absoluButton;
    @FXML
    public Button relatifButton;

    @FXML
    public Canvas canvas;

    private CanvasManager canvasManager;

    private ObservableList<GroupeRow> groupes;
    private int posX, posY;
    private ModeCase modeCase;

    @Override
    public void initialise() {
        posX = 0;
        posY = 0;
        modeCase = ModeCase.ABSOLU;

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());


        ContextMenu contextMenu = new ContextMenu();

        MenuItem addItem = new MenuItem("Ajouter");
        addItem.setOnAction((event) -> {
            addGroup();
        });

        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setOnAction((event) -> {
            deleteSelectedGroup();
        });

        contextMenu.getItems().addAll(addItem, deleteItem);
        tab.setContextMenu(contextMenu);

        groupes = FXCollections.observableArrayList();
        tab.setItems(groupes);

        for (GroupCases gc : getApp().varianteManager.getCurrent().getListGroupCases()) {
            groupes.add(new GroupeRow(gc));
        }

        canvasManager = new CanvasManager(canvas, getApp().varianteManager.getCurrent().getPlateau());
        updateCanvas();
    }

    private void addGroup() {
        groupes.add(new GroupeRow(new GroupCases("Groupe"+groupes.size())));

        updateCanvas();
    }

    private void deleteSelectedGroup() {
        GroupeRow gr = tab.getSelectionModel().getSelectedItem();
        if (gr == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : aucun groupe selectionne");
            return;
        }

        groupes.remove(gr);

        updateCanvas();
    }

    public void updateCanvas() {
        canvasManager.drawCanvas();
        if (tab.getSelectionModel().getSelectedItem() != null) {
            canvasManager.drawGroupCases(posX, posY, tab.getSelectionModel().getSelectedItem().getGroup());
        }
        else {
            for (GroupeRow g : groupes) {
                canvasManager.drawGroupCases(posX, posY, g.getGroup());
            }
        }
    }

    @FXML
    private void validateButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        System.out.println(tab.getRowFactory());

        getApp().varianteManager.getCurrent().getListGroupCases().clear();
        for (GroupeRow gr : groupes) {
            getApp().varianteManager.getCurrent().getListGroupCases().add(gr.getGroup());
        }

        getApp().setRoot("varianteMenu2");
    }

    @FXML
    public void onClick(MouseEvent mouseEvent) {
        GroupeRow gr = tab.getSelectionModel().getSelectedItem();

        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());

        switch (mouseEvent.getButton()) {
            case PRIMARY -> {
                switch (modeCase) {
                    case ABSOLU -> {
                        if (gr != null && c.isClickable() && !gr.getGroup().getCasesAbsolue().contains(c)) {
                            gr.getGroup().getCasesAbsolue().add(c);
                        }
                    }
                    case RELATIF -> {
                        if (gr != null && !gr.getGroup().getPositionsRelatives().contains(c.getPosition())) {
                            Position p = new Position(c.getPosition().getX() - posX, c.getPosition().getY() - posY);
                            gr.getGroup().getPositionsRelatives().add(p);
                        }
                    }
                }
            }
            case SECONDARY -> {
                switch (modeCase) {
                    case ABSOLU -> {
                        if (gr != null) {
                            gr.getGroup().getCasesAbsolue().remove(c);
                        }
                    }
                    case RELATIF -> {
                        if (gr != null) {
                            Position p = new Position(c.getPosition().getX() - posX, c.getPosition().getY() - posY);
                            gr.getGroup().getPositionsRelatives().remove(p);
                        }
                    }
                }
            }
            case MIDDLE -> {
                posX = c.getPosition().getX();
                posY = c.getPosition().getY();
            }
        }
        updateCanvas();
    }

    @FXML
    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "TEXT");//todo text info group cases
    }

    @FXML
    public void selectAbsolu() {
        modeCase = ModeCase.ABSOLU;
        absoluButton.setDisable(true);
        relatifButton.setDisable(false);
    }

    @FXML
    public void selectRelatif() {
        modeCase = ModeCase.RELATIF;
        absoluButton.setDisable(false);
        relatifButton.setDisable(true);
    }

    @FXML
    public void updateSelectedName(TableColumn.CellEditEvent<GroupeRow, String> groupeRowStringCellEditEvent) {
        groupeRowStringCellEditEvent.getRowValue().setName(groupeRowStringCellEditEvent.getNewValue());
        groupeRowStringCellEditEvent.getRowValue().getGroup().setName(groupeRowStringCellEditEvent.getNewValue());
        updateCanvas();
    }

    private static enum ModeCase {
        ABSOLU,
        RELATIF
    }

    private static class GroupeRow {
        private final SimpleStringProperty name;
        private final GroupCases group;

        public GroupeRow(GroupCases group) {
            this.group = group;
            this.name = new SimpleStringProperty();
            this.setName(group.getName());
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public GroupCases getGroup() {
            return group;
        }
    }
}
