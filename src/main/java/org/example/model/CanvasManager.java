package org.example.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;

import java.util.ArrayList;
import java.util.List;

public class CanvasManager {
    private Canvas canvas;
    private GraphicsContext context;
    private double rectSize;
    private Plateau plateau;

    public CanvasManager(Canvas canvas, Plateau plateau) {
        this.canvas = canvas;
        context = canvas.getGraphicsContext2D();
        rectSize = canvas.getWidth()/plateau.getWitdhX();
        this.plateau = plateau;
    }

    public void drawCanvas() {
        int nbSquareX = plateau.getWitdhX();
        int nbSquareY = plateau.getHeightY();
        rectSize = canvas.getWidth()/plateau.getWitdhX();

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


        context.setFill(Color.PINK);
        for (int i = 0; i < nbSquareY; i++) {
            for (int j = 0; j < nbSquareX; j++) {
                if (plateau.getEchiquier().get(i).get(j).isClickable()) {
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

    public void drawPawn() {
        for (ArrayList<Case> ligne: plateau.getEchiquier()) {
            for (Case c: ligne) {
                if (c.getPieceOnCase() != null) {
                    putPiece(c.getPosition().getX(), c.getPosition().getY(), new Image(c.getPieceOnCase().getSprite()));
                }
            }
        }
    }

    public void drawDeplacement(Image image, int posX, int posY, List<PositionDeDeplacement> posDeplacements, List<VecteurDeDeplacement> vecDeplacements) {
        for (PositionDeDeplacement e:
                posDeplacements) {

            double bPosEX = (e.getX()+posX) * rectSize;
            double bPosEY = (e.getY()+posY) * rectSize;
            if ((e.getX() + e.getY())%2==0) {
                context.setFill(Color.GREEN);
            }
            else {
                context.setFill(Color.LIGHTGREEN);
            }
            context.fillRect(bPosEX, bPosEY, rectSize, rectSize);
        }

        for (VecteurDeDeplacement e:
                vecDeplacements) {
            context.setStroke(Color.RED);
            context.setLineWidth(5);

            double bEX = (e.getX()+posX);
            double bEY = (e.getY()+posY);

            double cPosX = (posX+.5) * rectSize;
            double cPosY = (posY+.5) * rectSize;
            double ceX = (bEX+.5) * rectSize;
            double ceY =  (bEY+.5) * rectSize;
            context.strokeLine(cPosX, cPosY, ceX, ceY);

            double angle = Math.atan(Math.abs(posX - e.getX())/(double)Math.abs(posY - e.getY()));
            System.out.println(angle);//todo angle ici pas teste
            context.rotate(angle);
            context.strokeLine(ceX, ceY, bEX * rectSize, bEY * rectSize);
            context.strokeLine(ceX, ceY, bEX * rectSize, (bEY+1) * rectSize);
            context.rotate(-angle);

            context.setStroke(Color.BLACK);
        }
        putPiece(posX, posY, image);
    }


    private void putPiece(int x, int y, Image img) {
        double cx = rectSize*(x+0.5);
        double cy = rectSize*(y+0.5);
        double w;
        double h;
        if (img.getHeight() > img.getWidth()) {
            h = rectSize;
            w = img.getWidth()/img.getHeight() * rectSize;
        }
        else {
            w = rectSize;
            h = img.getHeight()/img.getWidth() * rectSize;
        }
        context.drawImage(img, cx-w/2, cy-h/2, w, h);
    }

    public Case getCase(double mouseX, double mouseY) {
        int x = (int) (mouseX / rectSize);
        int y = (int) (mouseY / rectSize);
        return plateau.getEchiquier().get(y).get(x);
    }
}
