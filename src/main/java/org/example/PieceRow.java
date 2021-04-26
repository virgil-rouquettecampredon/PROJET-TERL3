package org.example;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;
import org.example.model.Piece;

public class PieceRow {
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
