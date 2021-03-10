package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

import java.io.IOException;

public class InitPosController extends Controller {
    @FXML
    public TextField joueurInput;
    @FXML
    public ToggleGroup radioButtonToggleGroup;
    @FXML
    public Canvas canvas;
    private GraphicsContext context;

    @FXML
    public void initialize() {
        context = canvas.getGraphicsContext2D();
        updateCanvas();
    }

    public void updateCanvas() {
        //TODO METTRE le vrai plateau
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
        App.soundManager.playSound("button-click");
        //TODO: enregistrer les positions
        System.out.println(radioButtonToggleGroup.getSelectedToggle());
        App.setRoot("varianteMenu2");
    }
}
