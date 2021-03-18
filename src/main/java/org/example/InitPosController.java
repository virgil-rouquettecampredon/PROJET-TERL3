package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.model.Piece;

import java.io.IOException;

public class InitPosController extends Controller {
    @FXML
    public TextField joueurInput;
    @FXML
    public TableView<PieceRow> tab;
    @FXML
    public TableColumn<PieceRow, ImageView> imgCol;

    @FXML
    public Canvas canvas;

    private GraphicsContext context;

    private ObservableList<PieceRow> pieces;

    @FXML
    public void initialize() {
        imgCol.setCellValueFactory(cellData -> cellData.getValue().imgProperty());

        pieces = FXCollections.observableArrayList();

        //TODO REMPLIR PROPREMENT AVEC LES DONNES ENREGISTREES
        Piece p = new Piece("Pawn", "file:src/main/resources/org/example/images/pawn.png");
        ImageView iv = new ImageView(new Image(p.getSprite()));
        iv.setPreserveRatio(true);
        iv.setFitWidth(256/(float)4); // TODO metre une vraie taille

        pieces.add(new PieceRow(iv, p));

        Piece p2 = new Piece("King", "file:src/main/resources/org/example/images/king.png");
        ImageView iv2 = new ImageView(new Image(p2.getSprite()));
        iv2.setPreserveRatio(true);
        iv2.setFitWidth(256/(float)4); // TODO metre une vraie taille

        pieces.add(new PieceRow(iv2, p2));

        tab.setItems(pieces);

        context = canvas.getGraphicsContext2D();
        updateCanvas();
    }

    public void updateCanvas() {
        //TODO METTRE le vrai plateau
        int nbSquareX = 8;
        int nbSquareY = 8;


        if (nbSquareX > nbSquareY) {
            canvas.setHeight(300*((float)nbSquareY/nbSquareX));
            canvas.setWidth(300);
        }
        else {
            canvas.setHeight(300);
            canvas.setWidth(300*((float)nbSquareX/nbSquareY));
        }

        context.setFill(Color.PURPLE);
        context.fillRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight());

        double rectSize = canvas.getWidth()/nbSquareX;
        context.setFill(Color.PINK);
        for (int i = 0; i < nbSquareY; i++) {
            for (int j = 0; j < nbSquareX; j+=2) {
                context.fillRect((j+(i%2))*rectSize, i*rectSize, rectSize, rectSize);
            }
        }
    }

    @FXML
    private void validateButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        //TODO: enregistrer les positions
        System.out.println(tab.getRowFactory());
        getApp().setRoot("varianteMenu2");
    }

    public void updatePawnPlayer() {
        
    }

    public void putPiece(MouseEvent mouseEvent) {
        PieceRow pr = tab.getSelectionModel().getSelectedItem();
        if (pr != null) {
            Image img = new Image(pr.getPiece().getSprite());
            double s = 300/8;//TODO A CHANGER BIEN SUR
            double w;
            double h;
            if (img.getHeight() > img.getWidth()) {
                h = s;
                w = img.getWidth()/img.getHeight() * s;
            }
            else {
                w = s;
                h = img.getHeight()/img.getWidth() * s;
            }
            context.drawImage(img, mouseEvent.getX()-w/2, mouseEvent.getY()-h/2, w, h);
        }
    }

    private static class PieceRow {
        private final SimpleObjectProperty<ImageView> img;
        private final Piece piece;

        public PieceRow(ImageView img, Piece piece) {
            this.img = new SimpleObjectProperty<>(img);
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

        public Piece getPiece() {
            return piece;
        }
    }
}
