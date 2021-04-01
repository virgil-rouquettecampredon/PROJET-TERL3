package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import org.example.model.Case;
import org.example.model.Joueur;
import org.example.model.JoueurBox;
import org.example.model.Piece;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private GraphicsContext context;
    private double rectSize;

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
            for (int j = 0; j < nbSquareX; j++) {
                if (getApp().varianteManager.getCurrent().getPlateau().getEchiquier().get(i).get(j).isClickable()) {
                    if ((j+i)%2==0) {
                        context.setFill(Color.PURPLE);
                    }
                    else {
                        context.setFill(Color.PINK);
                    }
                }
                else {
                    if ((j+i)%2==0) {
                        context.setFill(Color.GRAY);
                    }
                    else {
                        context.setFill(Color.DARKGRAY);
                    }
                }
                context.fillRect(j * rectSize, i * rectSize, rectSize, rectSize);
            }
        }

        for (ArrayList<Case> ligne: getApp().varianteManager.getCurrent().getPlateau().getEchiquier()) {
            for (Case c: ligne) {
                if (c.getPieceOnCase() != null) {
                    putPiece(rectSize*(c.getPosition().getX()+0.5), rectSize*(c.getPosition().getY()+0.5), c.getPieceOnCase());
                }
            }
        }

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
        context.drawImage(img, x-w/2, y-h/2, w, h);
    }

    @FXML
    public void onClick(MouseEvent mouseEvent) {
        PieceRow pr = tab.getSelectionModel().getSelectedItem();
        if (pr != null) {
            int x = (int) (mouseEvent.getX() / rectSize);
            int y = (int) (mouseEvent.getY() / rectSize);

            switch (mouseEvent.getButton()) {
                case PRIMARY -> {
                    if (getApp().varianteManager.getCurrent().getPlateau().getEchiquier().get(y).get(x).isClickable()) {

                        Piece p = new Piece(pr.getPiece());
                        Joueur j = joueurBox.getValue().getJoueur();
                        p.setJoueur(j);
                        j.getPawnList().add(p);

                        getApp().varianteManager.getCurrent().getPlateau().getEchiquier().get(y).get(x).setPieceOnCase(p);
                    }
                }
                case SECONDARY -> {
                    Case c = getApp().varianteManager.getCurrent().getPlateau().getEchiquier().get(y).get(x);

                    if (c.getPieceOnCase() != null) {
                        c.getPieceOnCase().getJoueur().getPawnList().remove(c.getPieceOnCase());
                    }

                    c.setPieceOnCase(null);
                }
            }
            updateCanvas();
        }
    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "texte");//todo texte positions initiales
    }

    private static class PieceRow {
        private final SimpleObjectProperty<ImageView> img;
        private final SimpleStringProperty name;
        private final Piece piece;

        public PieceRow(ImageView img, Piece piece) {
            this.img = new SimpleObjectProperty<>(img);
            this.name = new SimpleStringProperty(piece.getName());
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

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public Piece getPiece() {
            return piece;
        }
    }
}
