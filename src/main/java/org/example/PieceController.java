package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        App.soundManager.playSound("button-click");
        App.setRoot("varianteMenu2");
    }

    @FXML
    private void addButton() throws IOException {
        App.soundManager.playSound("button-click");
        //TODO AJOUTER UNE PIECE
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

        public Button getEdit() {
            return edit.get();
        }

        public SimpleObjectProperty<Button> editProperty() {
            return edit;
        }

        public void setEdit(Button edit) {
            this.edit.set(edit);
        }

        public Button getDel() {
            return del.get();
        }

        public SimpleObjectProperty<Button> delProperty() {
            return del;
        }

        public void setDel(Button del) {
            this.del.set(del);
        }
    }
}
