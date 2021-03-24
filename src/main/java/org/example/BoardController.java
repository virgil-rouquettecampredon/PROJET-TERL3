package org.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
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

    @Override
    public void initialise() {
        xInput.setText(""+getApp().varianteManager.getCurrent().getPlateau().getWitdhX());
        yInput.setText(""+getApp().varianteManager.getCurrent().getPlateau().getHeightY());
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
        if (nbSquareX <= 0 || nbSquareY <= 0) return;

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
        getApp().soundManager.playSound("button-click");
        int x = 8;
        int y = 8;
        try {
            x = Integer.parseInt(xInput.getText());
            y = Integer.parseInt(yInput.getText());
            if (x <= 0 || y <= 0) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, xInput.getText()+" ou "+yInput.getText()+" n'est pas un entier > 0.");
            return;
        }

        System.out.println(xInput.getText() + " x " + yInput.getText());

        getApp().varianteManager.getCurrent().getPlateau().setWitdhX(x);
        getApp().varianteManager.getCurrent().getPlateau().setHeightY(y);

        getApp().setRoot("VarianteMenu1");
    }
}
