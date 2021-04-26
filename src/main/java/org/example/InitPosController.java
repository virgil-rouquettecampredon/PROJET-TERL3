package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.model.*;

import java.io.IOException;

public class InitPosController extends Controller {
    @FXML
    public TextField joueurInput;
    @FXML
    public TableView<PieceRow> tab;
    @FXML
    public TableColumn<PieceRow, ImageView> imgCol;
    @FXML
    public TableColumn<PieceRow, String> nameCol;
    @FXML
    public ComboBox<JoueurBox> joueurBox;

    @FXML
    public Canvas canvas;

    private CanvasManager canvasManager;

    private ObservableList<PieceRow> pieces;

    @Override
    public void initialise() {
        imgCol.setCellValueFactory(cellData -> cellData.getValue().imgProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        pieces = FXCollections.observableArrayList();
        tab.setItems(pieces);

        for (Joueur p : getApp().varianteManager.getCurrent().getJoueurs()) {
            joueurBox.getItems().add(new JoueurBox(p.getName(), p));
        }

        joueurBox.getSelectionModel().selectFirst();
        updatePawnPlayer();

        int nbSquareX = getApp().varianteManager.getCurrent().getPlateau().getWitdhX();
        int nbSquareY = getApp().varianteManager.getCurrent().getPlateau().getHeightY();
        canvasManager = new CanvasManager(canvas, getApp().varianteManager.getCurrent().getPlateau());
        updateCanvas();
    }

    public void updateCanvas() {
        canvasManager.drawCanvas();
        canvasManager.drawPawn();
    }

    @FXML
    private void validateButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        System.out.println(tab.getRowFactory());
        getApp().setRoot("varianteMenu2");
    }

    @FXML
    public void updatePawnPlayer() {
        getApp().soundManager.playSound("button-hover");

        if (joueurBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Joueur j = joueurBox.getSelectionModel().getSelectedItem().getJoueur();

        pieces.clear();
        System.out.println(j.getTypePawnList());
        for (Piece p:
                j.getTypePawnList()) {

            ImageView iv = new ImageView(new Image(p.getSprite()));
            iv.setPreserveRatio(true);
            iv.setFitWidth(imgCol.getWidth());
            iv.setFitHeight(100);

            pieces.add(new PieceRow(iv, p));
        }
    }

    @FXML
    public void onClick(MouseEvent mouseEvent) {
        PieceRow pr = tab.getSelectionModel().getSelectedItem();

        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());

        switch (mouseEvent.getButton()) {
            case PRIMARY -> {
                if (c.isAccessible() && pr != null) {
                    Piece p = new Piece(pr.getPiece());
                    Joueur j = joueurBox.getValue().getJoueur();
                    p.setJoueur(j);
                    j.getPawnList().add(p);

                    c.setPieceOnCase(p);
                }
            }
            case SECONDARY -> {

                if (c.getPieceOnCase() != null) {
                    c.getPieceOnCase().getJoueur().getPawnList().remove(c.getPieceOnCase());
                }

                c.setPieceOnCase(null);
            }
        }
        updateCanvas();

    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Pour poser une pièce d'un joueur sur le plateau, il suffit de sélectionner le joueur en question sur le déroulant en haut du tableau de gauche, puis de sélectionner une pièce et de faire un clique droit sur la case voulu afin de placer la pièce.\n\nSi vous voulez enlevez une pièce du plateau, il suffit de faire un clique droit sur cette pièce positionné sur le plateau.\n\nFaites attention, vous ne pouvez pas poser de pièce sur une case grisée, car elle est inaccessible.");
    }

}
