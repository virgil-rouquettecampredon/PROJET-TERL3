package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
import org.example.model.*;
import org.example.model.EquationDeDeplacement.*;

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

    private CanvasManager canvasManager;
    private String file;
    private ArrayList<PositionDeDeplacement> posDeplacements;
    private ArrayList<VecteurDeDeplacement> vecDeplacements;

    private int posX, posY;

    private Tool tool;

    @Override
    public void initialise() {
        tool = Tool.BOX;
        for (Joueur p : getApp().varianteManager.getCurrent().getJoueurs()) {
            joueurBox.getItems().add(new JoueurBox(p.getName(), p));
        }

        if (userVar != null) {
            Piece p = (Piece) userVar;
            nomInput.setText(p.getName());
            image.setImage(new Image(p.getSprite()));
            joueurBox.getSelectionModel().select(joueurBox.getItems().stream().filter(jb -> jb.getJoueur() == p.getJoueur()).findFirst().get());
            file = p.getSprite().split("file:")[1];
            posDeplacements = p.getPosDeplacements();
            vecDeplacements = p.getVecDeplacements();
        }
        else {
            joueurBox.getSelectionModel().selectFirst();
            nomInput.setText("Pawn");
            posDeplacements = new ArrayList<>();
            vecDeplacements = new ArrayList<>();
        }
        Plateau p = getApp().varianteManager.getCurrent().getPlateau();
        posX = p.getWitdhX()/2;
        posY = p.getHeightY()/2;

        canvasManager = new CanvasManager(canvas, p);
        updateCanvas();
    }

    public void updateCanvas() {
        canvasManager.drawCanvas();
        canvasManager.drawDeplacement(image.getImage(), posX, posY, posDeplacements, vecDeplacements);
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
        tool = Tool.BOX;
        arrowButton.setDisable(false);
        boxButton.setDisable(true);
    }

    public void selectArowTool() {
        getApp().soundManager.playSound("button-click");
        tool = Tool.ARROW;
        arrowButton.setDisable(true);
        boxButton.setDisable(false);
    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Ici, vous pouvez modifier le nom de la pièce créée ou modifier, ainsi que le joueur auquel elle appartient et de même pour l'image de la pièce (Ce sera une image sur votre ordinateur).\n\n ");//todo finir texte pour déplacement de la pièce
    }

    public void onClick(MouseEvent mouseEvent) {
        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());
        int relativeX = c.getPosition().getX()-posX;
        int relativeY = c.getPosition().getY()-posY;

        switch (mouseEvent.getButton())  {
            case PRIMARY -> {
                switch (tool) {
                    case BOX -> {
                        //add case to piece deplacement
                        if (!posDeplacements.contains(new PositionDeDeplacement(relativeX, relativeY)) && (relativeX != 0 || relativeY != 0)) {
                            posDeplacements.add(new PositionDeDeplacement(relativeX, relativeY));
                        }
                    }
                    case ARROW -> {
                        //add arrow to piece deplacement
                        if (!vecDeplacements.contains(new VecteurDeDeplacement(relativeX, relativeY)) && (relativeX != 0 || relativeY != 0)) {
                            vecDeplacements.add(new VecteurDeDeplacement(relativeX, relativeY));
                        }
                    }
                }
            }
            case SECONDARY -> {
                switch (tool) {
                    case BOX -> {
                        //remove case to piece deplacement
                        posDeplacements.remove(new PositionDeDeplacement(relativeX, relativeY));
                    }
                    case ARROW -> {
                        //remove arrow to piece deplacement
                        vecDeplacements.remove(new VecteurDeDeplacement(relativeX, relativeY));
                    }
                }
            }
            case MIDDLE -> {
                posX = c.getPosition().getX();
                posY = c.getPosition().getY();
            }
        }
        updateCanvas();
    }

    private static enum Tool {
        BOX, ARROW
    }
}
