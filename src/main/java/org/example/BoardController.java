package org.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class BoardController extends Controller {
    @FXML
    public TextField xInput;
    @FXML
    public TextField yInput;
    @FXML
    public Canvas canvas;
    private GraphicsContext context;

    @FXML
    public void initialize() {
        context = canvas.getGraphicsContext2D();
        updateCanvas();
    }

    public void updateCanvas() {
        int nbSquareX = 0;
        int nbSquareY = 0;
        try {
            nbSquareX = Integer.parseInt(xInput.getText());
            nbSquareY = Integer.parseInt(yInput.getText());
        } catch (NumberFormatException ignored) {

        }
        if (nbSquareX == 0 || nbSquareY == 0) return;

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
    private void continueButton() throws IOException {
        App.soundManager.playSound("button-click");
        //TODO: valider texte de l'input
        System.out.println(xInput.getText() + " x " + yInput.getText());
        App.setRoot("VarianteMenu1");
    }
}
