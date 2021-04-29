package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import org.example.model.*;

import java.io.IOException;

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
    private Plateau plateau;

    private double mouseX, mouseY;
    private boolean primaryMouseDown;
    private boolean secondaryMouseDown;
    private boolean middleMouseDown;

    @Override
    public void initialise() {
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


        plateau = getApp().varianteManager.getCurrent().getPlateau();
        posX = plateau.getWitdhX()/2;
        posY = plateau.getHeightY()/2;

        canvasManager = new CanvasManager(canvas, plateau);

        updateCanvas();
    }

    private void addGroup() {
        groupes.add(new GroupeRow(new GroupCases("Groupe"+groupes.size(), plateau)));

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
    public void onClick() {
        GroupeRow gr = tab.getSelectionModel().getSelectedItem();

        Case c = canvasManager.getCase(mouseX, mouseY);
        if (c == null) {
            System.out.println("NULL : ("+mouseX+", "+mouseY+")");
            return;
        }

        if (secondaryMouseDown) {
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
        else if (primaryMouseDown) {
            switch (modeCase) {
                case ABSOLU -> {
                    if (gr != null && c.isAccessible() && !gr.getGroup().getCasesAbsolue().contains(c)) {
                        gr.getGroup().getCasesAbsolue().add(c);
                    }
                }
                case RELATIF -> {
                    Position p = new Position(c.getPosition().getX() - posX, c.getPosition().getY() - posY);
                    if (gr != null && !gr.getGroup().getPositionsRelatives().contains(p)) {
                        gr.getGroup().getPositionsRelatives().add(p);
                    }
                }
            }
        }
        else if (middleMouseDown) {
            posX = c.getPosition().getX();
            posY = c.getPosition().getY();
        }

        updateCanvas();
    }

    @FXML
    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Sur cette partie, il vous est possible de renseigner des groupements particuliers de cases (réutilisables dans les règles).\n\n" +
                "Pour ce faire, vous pourrez gérer vos différents groupes dans le menu de gauche (clic droit) \n" +
                "Une fois un groupe sélectionné, vous pourrez lui renseigner des cases qui le composent (plateau de gauche).\n" +
                "Ces cases peuvent soit être définies de manière absolue (vrai case du plateau) => sélectionner \"Case absolue\" puis clic gauche pour en ajouter, clic droit pour en enlever.\n" +
                "Soit de manière relative (~position) par rapport à une case (ici la case au point rouge) => même manipulation en sélectionnant \"Case relative\".\n" +
                "On peut déplacer la case au point rouge par un clic molette sur le plateau.");
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

    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
        onClick();
    }

    public void mousePressed(MouseEvent mouseEvent) {
        switch (mouseEvent.getButton()) {
            case PRIMARY -> {
                primaryMouseDown = true;
            }
            case SECONDARY -> {
                secondaryMouseDown = true;
            }
            case MIDDLE -> {
                middleMouseDown = true;
            }
        }
        mouseMoved(mouseEvent);
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        switch (mouseEvent.getButton()) {
            case PRIMARY -> {
                primaryMouseDown = false;
            }
            case SECONDARY -> {
                secondaryMouseDown = false;
            }
            case MIDDLE -> {
                middleMouseDown = false;
            }
        }
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
