package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.model.Position;

import java.io.IOException;

public class BoardController extends Controller {
    @FXML
    public TextField xInput;
    @FXML
    public TextField yInput;
    @FXML
    public Canvas canvas;
    private GraphicsContext context;
    private double rectSize;

    @Override
    public void initialise() {
        xInput.setText(""+getApp().varianteManager.getCurrent().getPlateau().getWitdhX());
        yInput.setText(""+getApp().varianteManager.getCurrent().getPlateau().getHeightY());
        context = canvas.getGraphicsContext2D();
        updateCanvas();
    }

    public void inputAction() {
        int nbSquareX = getApp().varianteManager.getCurrent().getPlateau().getWitdhX();
        int nbSquareY = getApp().varianteManager.getCurrent().getPlateau().getHeightY();

        try {
            nbSquareX = Integer.parseInt(xInput.getText());
            nbSquareY = Integer.parseInt(yInput.getText());
        }
        catch (NumberFormatException ignore) {
        }

        getApp().varianteManager.getCurrent().getPlateau().setWitdhX(nbSquareX);
        getApp().varianteManager.getCurrent().getPlateau().setHeightY(nbSquareY);
        updateCanvas();
    }

    public void updateCanvas() {
        int nbSquareX = getApp().varianteManager.getCurrent().getPlateau().getWitdhX();
        int nbSquareY = getApp().varianteManager.getCurrent().getPlateau().getHeightY();

        try {
            nbSquareX = Integer.parseInt(xInput.getText());
            nbSquareY = Integer.parseInt(yInput.getText());
        }
        catch (NumberFormatException ignore) {
        }

        if (nbSquareX > nbSquareY) {
            canvas.setHeight(300*((float)nbSquareY/nbSquareX));
            canvas.setWidth(300);
        }
        else {
            canvas.setHeight(300);
            canvas.setWidth(300*((float)nbSquareX/nbSquareY));
        }

        rectSize = canvas.getWidth()/nbSquareX;
        for (int i = 0; i < nbSquareY; i++) {
            for (int j = 0; j < nbSquareX; j++) {
                if (getApp().varianteManager.getCurrent().getPlateau().getEchiquier().get(i).get(j).isClickable()) {
                    if ((j+i)%2==0) {
                        context.setFill(Color.PURPLE);
                    }
                    else {
                        context.setFill(Color.PINK);
                    }
                }
                else {
                    if ((j+i)%2==0) {
                        context.setFill(Color.GRAY);
                    }
                    else {
                        context.setFill(Color.DARKGRAY);
                    }
                }
                context.fillRect(j * rectSize, i * rectSize, rectSize, rectSize);
            }
        }
    }

    @FXML
    public void onClick(MouseEvent mouseEvent) {
        int x = (int) (mouseEvent.getX() / rectSize);
        int y = (int) (mouseEvent.getY() / rectSize);

        getApp().varianteManager.getCurrent().getPlateau().getEchiquier().get(y).get(x).switchClickable();

        updateCanvas();
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

        getApp().setRoot("VarianteMenu1");
    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Vous pouvez réduire ou augmenter la taille du plateau en modifiant les valeurs dans les encarts.\nLes valeurs doivent être strictement supérieur à 0.\n\nEffectuer un clique gauche sur une case, la rendra inacessible pour cette variante.\nSi une case est déja inaccessible, faire un clique gauche dessus la rendra de nouveau accessible pour cette variante. \n");//TODO description
    }
}
