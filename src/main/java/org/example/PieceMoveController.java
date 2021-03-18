package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

public class PieceMoveController extends Controller {
    @FXML
    public TextField nomInput;
    @FXML
    public TextField nbPlayerInput;
    @FXML
    public Canvas canvas;

    private GraphicsContext context;
    private ArrayList<String> file;

    @FXML
    public void initialize() {
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
        //TODO: modifier la piece
        getApp().setRoot("piece");
    }

    public void openImageButton() {
        getApp().soundManager.playSound("button-click");
        //TODO: fenetre pour ouvrir une image
    }

    public void selectBoxTool() {
        getApp().soundManager.playSound("button-click");
        //TODO: selectionner l'outil de case
    }

    public void selectArowTool() {
        getApp().soundManager.playSound("button-click");
        //TODO: selectionner l'outil de fleche
    }
}
