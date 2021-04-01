package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.model.Piece;

import java.io.IOException;

public class PieceController extends Controller {

    @FXML
    private TableView<PieceRow> tab;
    @FXML
    private TableColumn<PieceRow, ImageView> imgCol;
    @FXML
    private TableColumn<PieceRow, String> nomCol;
    @FXML
    private TableColumn<PieceRow, String> joueurCol;

    private ObservableList<PieceRow> pieces;

    @FXML
    private void confirmButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("varianteMenu2");
    }

    @FXML
    private void addButton() throws IOException{
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("pieceMove");
    }

    @Override
    public void initialise(){
        imgCol.setCellValueFactory(cellData -> cellData.getValue().imgProperty());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        joueurCol.setCellValueFactory(cellData -> cellData.getValue().joueurProperty());

        pieces = FXCollections.observableArrayList();

        for (Piece p:
                getApp().varianteManager.getCurrent().getAllPawn()) {

            pieces.add(new PieceRow(imgCol.getWidth(), p));
        }

        tab.setItems(pieces);

        ContextMenu contextMenu = new ContextMenu();

        MenuItem editItem = new MenuItem("Edit");
        editItem.setOnAction((event) -> {
            editSelectedPiece();
        });

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction((event) -> {
            deleteSelectedPiece();
        });

        MenuItem duplicateItem = new MenuItem("Duplicate");
        duplicateItem.setOnAction((event) -> {
            duplicateSelectedPiece();
        });

        contextMenu.getItems().addAll(editItem, deleteItem, duplicateItem);

        tab.setContextMenu(contextMenu);
    }

    private void duplicateSelectedPiece() {
        PieceRow pr = tab.getSelectionModel().getSelectedItem();
        Piece p = new Piece(pr.getPiece());
        p.getJoueur().getTypePawnList().add(p);
        PieceRow pr2 = new PieceRow(imgCol.getWidth(), p);
        pieces.add(pr2);
    }

    private void deleteSelectedPiece() {
        PieceRow pr = tab.getSelectionModel().getSelectedItem();
        pieces.remove(pr);
        Piece p = pr.getPiece();
        p.getJoueur().getTypePawnList().remove(p);
    }

    private void editSelectedPiece(){
        PieceRow p = tab.getSelectionModel().getSelectedItem();
        if (p == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : aucune piece selectionne");
            return;
        }

        getApp().soundManager.playSound("button-click");
        try {
            getApp().setRoot("pieceMove", p.getPiece());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "texte");//todo texte modifier piece
    }

    private static class PieceRow {
        private final SimpleObjectProperty<ImageView> img;
        private final SimpleStringProperty nom;
        private final SimpleStringProperty joueur;
        private final Piece piece;

        public PieceRow(double width, Piece p) {
            ImageView iv = new ImageView(new Image(p.getSprite()));
            iv.setPreserveRatio(true);
            iv.setFitWidth(width);
            iv.setFitHeight(100);
            this.img = new SimpleObjectProperty<>(iv);
            this.nom = new SimpleStringProperty(p.getName());
            this.joueur = new SimpleStringProperty(p.getJoueur().getName());
            this.piece = p;
        }

        public ImageView getImg() {
            return img.get();
        }

        public SimpleObjectProperty<ImageView> imgProperty() {
            return img;
        }

        public void setImg(ImageView img) {
            this.img.set(img);
        }

        public String getNom() {
            return nom.get();
        }

        public SimpleStringProperty nomProperty() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom.set(nom);
        }

        public String getJoueur() {
            return joueur.get();
        }

        public SimpleStringProperty joueurProperty() {
            return joueur;
        }

        public void setJoueur(String joueur) {
            this.joueur.set(joueur);
        }

        public Piece getPiece() {
            return piece;
        }
    }
}
