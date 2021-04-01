package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.example.model.Joueur;
import org.example.model.JoueurBox;
import org.example.model.Piece;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PieceMoveController extends Controller {
    @FXML
    public TextField nomInput;
    @FXML
    public Canvas canvas;
    @FXML
    public ComboBox<JoueurBox> joueurBox;
    @FXML
    public ImageView image;
    @FXML
    public Button boxButton;
    @FXML
    public Button arrowButton;

    private GraphicsContext context;
    private String file;


    @Override
    public void initialise() {
        for (Joueur p : getApp().varianteManager.getCurrent().getJoueurs()) {
            joueurBox.getItems().add(new JoueurBox(p.getName(), p));
        }

        if (userVar != null) {
            Piece p = (Piece) userVar;
            nomInput.setText(p.getName());
            image.setImage(new Image(p.getSprite()));
            joueurBox.getSelectionModel().select(joueurBox.getItems().stream().filter(jb -> jb.getJoueur() == p.getJoueur()).findFirst().get());
            file = p.getSprite().split("file:")[1];
        }
        else {
            joueurBox.getSelectionModel().selectFirst();
            nomInput.setText("Pawn");
        }

        context = canvas.getGraphicsContext2D();
        updateCanvas();
    }

    public void updateCanvas() {
        //TODO mettre la véritable taille du plateau
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

        if (file == null || file.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur : image vide!");
            return;
        }

        File f = new File(file);
        if (!f.exists() || f.isDirectory()) {
            showAlert(Alert.AlertType.ERROR, "Erreur : image invalide : "+file);
            return;
        }

        if (nomInput.getText().length() == 0) {
            showAlert(Alert.AlertType.ERROR, "Erreur : nom invalide");
            return;
        }
        if (joueurBox.getSelectionModel().getSelectedItem() == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : joueur non selectionné");
            return;
        }

        if (userVar != null) {
            Piece p = (Piece) userVar;
            p.setName(nomInput.getText());
            p.setSprite("file:" + file);

            p.getJoueur().getTypePawnList().remove(p);
            p.setJoueur(joueurBox.getSelectionModel().getSelectedItem().getJoueur());
            p.getJoueur().getTypePawnList().add(p);
        }
        else {
            Piece p = new Piece(nomInput.getText(), "file:" + file, joueurBox.getSelectionModel().getSelectedItem().getJoueur());
            p.getJoueur().getTypePawnList().add(p);
        }

        getApp().setRoot("piece");
    }

    public void openImageButton() {
        getApp().soundManager.playSound("button-click");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une variante");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image (*.bmp, *.gif, *.jpeg, *.png)", "*.bmp", "*.gif", "*.jpeg", "*.png"));
        File f = fileChooser.showOpenDialog(getApp().scene.getWindow());

        if (f != null) {
            file = f.getAbsolutePath();

            image.setImage(new Image("file:"+file));
        }
    }

    public void selectBoxTool() {
        getApp().soundManager.playSound("button-click");
        //TODO: selectionner l'outil de case
    }

    public void selectArowTool() {
        getApp().soundManager.playSound("button-click");
        //TODO: selectionner l'outil de fleche
    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "texte"); //todo texte definition piece
    }
}
