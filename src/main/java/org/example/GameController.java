package org.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.model.CanvasManager;
import org.example.model.Case;
import org.example.model.Variante;

import java.io.IOException;
import java.util.Random;

public class GameController extends Controller {
    @FXML
    public Label varLabel;
    @FXML
    public Label playerLabel;
    @FXML
    public VBox coupsBox;
    @FXML
    public Canvas canvas;
    @FXML
    public ScrollPane scroll;

    private CanvasManager canvasManager;
    private Variante gameVariante;

    @Override
    public void initialise(){
        if (userVar == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : Aucune variante n'as été selectionné");
            try {
                getApp().setRoot("home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            gameVariante = (Variante) userVar;
            varLabel.setText(gameVariante.getName());
            playerLabel.setText(gameVariante.getJoueurs().get(0).getName());//todo a changer?

            canvasManager = new CanvasManager(canvas, gameVariante.getPlateau());
            updateCanvas();
        }
    }

    public void updateCanvas() {
        canvasManager.drawCanvas();
        canvasManager.drawPawn();
    }

    public void play(MouseEvent mouseEvent) throws IOException {
        //todo bien sur
        getApp().soundManager.playSound("button-hover");
        System.out.println(mouseEvent);
        coupsBox.getChildren().add(new Label(playerLabel.getText() + " : label de coup"));
        scroll.setVvalue(2);

        if (playerLabel.getText().equals("Player1"))
            playerLabel.setText("Player2");
        else
            playerLabel.setText("Player1");
        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());
        if (c != null && c.getPosition().getX() == 0 && c.getPosition().getY() == 0) {
            getApp().soundManager.playSound("win");
            getApp().setRoot("gameOver", gameVariante);
        }
    }
}
