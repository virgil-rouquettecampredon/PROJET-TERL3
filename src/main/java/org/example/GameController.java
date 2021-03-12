package org.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    private GraphicsContext context;

    @FXML
    public void initialize() {
        //TODO prendre les vraie valeurs
        varLabel.setText("Classique");
        playerLabel.setText("Player1");
        context = canvas.getGraphicsContext2D();
        updateCanvas();
    }

    public void updateCanvas() {
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

    public void play(MouseEvent mouseEvent) throws IOException {
        App.soundManager.playSound("button-hover");
        System.out.println(mouseEvent);
        coupsBox.getChildren().add(new Label(playerLabel.getText() + " : label de coup"));
        scroll.setVvalue(2);

        if (playerLabel.getText().equals("Player1"))
            playerLabel.setText("Player2");
        else
            playerLabel.setText("Player1");
        if (mouseEvent.getX() > 250 && mouseEvent.getY() > 250) {
            App.soundManager.playSound("win");
            App.setRoot("gameOver");
        }
    }
}
