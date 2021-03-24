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
import org.example.model.Case;
import org.example.model.Piece;

import java.io.IOException;
import java.util.ArrayList;

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
    private double rectSize;

    private ObservableList<PieceRow> pieces;

    @Override
    public void initialise() {
        imgCol.setCellValueFactory(cellData -> cellData.getValue().imgProperty());

        pieces = FXCollections.observableArrayList();

        for (Piece p:
                getApp().varianteManager.getCurrent().getPieces()) {

            ImageView iv = new ImageView(new Image(p.getSprite()));
            iv.setPreserveRatio(true);
            iv.setFitWidth(imgCol.getWidth());
            iv.setFitHeight(100);

            pieces.add(new PieceRow(iv, p));
        }

        tab.setItems(pieces);

        context = canvas.getGraphicsContext2D();
        updateCanvas();
    }

    public void updateCanvas() {
        int nbSquareX = getApp().varianteManager.getCurrent().getPlateau().getWitdhX();
        int nbSquareY = getApp().varianteManager.getCurrent().getPlateau().getHeightY();

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

        rectSize = canvas.getWidth()/nbSquareX;
        context.setFill(Color.PINK);
        for (int i = 0; i < nbSquareY; i++) {
            for (int j = 0; j < nbSquareX; j+=2) {
                context.fillRect((j+(i%2))*rectSize, i*rectSize, rectSize, rectSize);
            }
        }

        //TODO mettre les pieces
        for (ArrayList<Case> ligne: getApp().varianteManager.getCurrent().getPlateau().getEchiquier()) {
            for (Case c: ligne) {
                if (c.getPieceOnCase() != null) {
                    putPiece(c.getPosition().getX()*rectSize, c.getPosition().getY()*rectSize, c.getPieceOnCase());
                }
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

    private void putPiece(double x, double y, Piece p) {
        Image img = new Image(p.getSprite());
        double w;
        double h;
        if (img.getHeight() > img.getWidth()) {
            h = rectSize;
            w = img.getWidth()/img.getHeight() * rectSize;
        }
        else {
            w = rectSize;
            h = img.getHeight()/img.getWidth() * rectSize;
        }
        context.drawImage(img, (int)(x/rectSize)*rectSize-w, (int)(y/rectSize)*rectSize-h, w, h);
    }

    public void onClick(MouseEvent mouseEvent) {
        PieceRow pr = tab.getSelectionModel().getSelectedItem();
        if (pr != null) {
            putPiece(mouseEvent.getX(), mouseEvent.getY(), pr.getPiece());
            int x =(int)(mouseEvent.getX()/rectSize);
            int y =(int)(mouseEvent.getY()/rectSize);
            getApp().varianteManager.getCurrent().getPlateau().getEchiquier().get(y).get(x).setPieceOnCase(pr.getPiece());
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
