package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.model.Piece;

import java.io.IOException;

public class PieceController extends Controller {

    @FXML
    private TableView<PieceRow> tab;
    @FXML
    private TableColumn<PieceRow, ImageView> imgCol;
    @FXML
    private TableColumn<PieceRow, Button> editCol;
    @FXML
    private TableColumn<PieceRow, Button> delCol;

    private ObservableList<PieceRow> pieces;

    @FXML
    private void confirmButton() throws IOException {
        //TODO SAUVEGARDER JE PENSE LOL
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("varianteMenu2");
    }

    @FXML
    private void addButton() {
        getApp().soundManager.playSound("button-click");
        //TODO AJOUTER UNE PIECE
        Piece p = new Piece("Pawn", "file:src/main/resources/org/example/images/pawn.png", null);
        ImageView iv = new ImageView(new Image(p.getSprite()));
        iv.setPreserveRatio(true);
        iv.setFitWidth(256/(float)4); // TODO changer 4 par le nombre d'images de la piece
        Button be = new Button("Edit");
        be.setOnAction(event -> {
            try {
                editPiece(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button bd = new Button("Delete");
        bd.setOnAction(event -> {
            deletPiece(p);
        });

        PieceRow pr = new PieceRow(iv, be, bd, p);
        pieces.add(pr);
    }

    @FXML
    public void initialize() {
        //TODO REMPLIR PROPREMENT AVEC LES DONNES ENREGISTREES
        imgCol.setCellValueFactory(cellData -> cellData.getValue().imgProperty());
        editCol.setCellValueFactory(cellData -> cellData.getValue().editProperty());
        delCol.setCellValueFactory(cellData -> cellData.getValue().delProperty());

        pieces = FXCollections.observableArrayList();
        tab.setItems(pieces);
    }

    public void editPiece(Piece p) throws IOException {
        //TODO passer p à la scene
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("pieceMove");
    }

    public void deletPiece(Piece p) {
        pieces.remove(p);
    }

    private static class PieceRow {
        private final SimpleObjectProperty<ImageView> img;
        private final SimpleObjectProperty<Button> edit;
        private final SimpleObjectProperty<Button> del;
        private final Piece piece;

        public PieceRow(ImageView img, Button edit, Button del, Piece piece) {
            this.img = new SimpleObjectProperty<>(img);
            this.edit = new SimpleObjectProperty<>(edit);
            this.del = new SimpleObjectProperty<>(del);
            this.piece = piece;
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
