package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.model.*;

import java.io.IOException;

public class PromotionController extends Controller {
    @FXML
    public AnchorPane pane;
    @FXML
    public TableView<PieceRow> tab;
    @FXML
    public TableColumn<PieceRow, ImageView> imgCol;
    @FXML
    public TableColumn<PieceRow, String> nameCol;

    private ObservableList<PieceRow> pieces;

    private boolean hidden = false;
    private PromotionData data;

    @Override
    public void initialise() {
        if (userVar == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur critique : Mauvaise initialisation");
            try {
                getApp().setRoot("home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        data = (PromotionData) userVar;
        imgCol.setCellValueFactory(cellData -> cellData.getValue().imgProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        pieces = FXCollections.observableArrayList();
        tab.setItems(pieces);

        for (Piece p: data.getCasePromotion().getPieceOnCase().getJoueur().getListPromotion()) {
            ImageView iv = new ImageView(new Image(p.getSprite()));
            iv.setPreserveRatio(true);
            iv.setFitWidth(imgCol.getWidth());
            iv.setFitHeight(100);

            pieces.add(new PieceRow(iv, p));
        }
    }

    @FXML
    public void selectButton() {
        PieceRow pr = tab.getSelectionModel().getSelectedItem();
        if (pr == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : Pas de piece selectionnee");
            return;
        }

        data.getGameController().promote(data.getCasePromotion(), pr.getPiece());
    }

    @FXML
    public void lookButton() {
        getApp().soundManager.playSound("button-confirm");
        if (hidden) {
            hidden = false;
            pane.setOpacity(100);
            pane.setMouseTransparent(false);
        }
        else {
            hidden = true;
            pane.setOpacity(0);
            pane.setMouseTransparent(true);
        }
    }

    public static class PromotionData {
        private Case casePromotion;
        private GameController gameController;

        public PromotionData(Case casePromotion, GameController c) {
            this.casePromotion = casePromotion;
            this.gameController = c;
        }

        public Case getCasePromotion() {
            return casePromotion;
        }

        public GameController getGameController() {
            return gameController;
        }
    }
}
