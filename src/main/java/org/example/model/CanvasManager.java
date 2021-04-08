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

        if (nbSquareX > nbSquareY) {
            canvas.setHeight(300*((float)nbSquareY/nbSquareX));
            canvas.setWidth(300);
        }
        else {
            canvas.setHeight(300);
            canvas.setWidth(300*((float)nbSquareX/nbSquareY));
        }

        rectSize = canvas.getWidth()/nbSquareX;

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
            if ((e.getX()+posX + e.getY()+posY)%2==0) {
                switch (e.getTypeDeplacement()) {
                    case DEPLACER -> context.setFill(Color.DARKGREEN);
                    case PRENDRE -> context.setFill(Color.DARKRED);
                    case BOTH -> context.setFill(Color.MEDIUMBLUE);
                }
            }
            else {
                switch (e.getTypeDeplacement()) {
                    case DEPLACER -> context.setFill(Color.LIGHTGREEN);
                    case PRENDRE -> context.setFill(Color.PALEVIOLETRED);
                    case BOTH -> context.setFill(Color.color(68/255.0, 167/255.0, 242/255.0));
                }
            }
            context.fillRect(bPosEX, bPosEY, rectSize, rectSize);
        }

        for (VecteurDeDeplacement e:
                vecDeplacements) {
            switch (e.getTypeDeplacement()) {
                case DEPLACER -> context.setStroke(Color.GREEN);
                case PRENDRE -> context.setStroke(Color.RED);
                case BOTH -> context.setStroke(Color.SKYBLUE);
            }
            context.setLineWidth(5);

            double bEX = (e.getX()+posX);
            double bEY = (e.getY()+posY);

            double cPosX = (posX+.5) * rectSize;
            double cPosY = (posY+.5) * rectSize;
            double ceX = (bEX+.5) * rectSize;
            double ceY =  (bEY+.5) * rectSize;
            context.strokeLine(cPosX, cPosY, ceX, ceY);

            /*
            double angle = Math.atan(e.getY()/((double)(e.getX())));
            if (e.getX() < 0) {
                angle = Math.PI - angle;
            }

            System.out.println(e.getX()+ "x" + e.getY() + " : " + angle);

            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            double p1X = bEX + (cos*(-1) - sin*(-1));
            double p1Y = bEY + (sin*(-1) + cos*(-1));
            double p2X = bEX + (cos*(-1) - sin*(1));
            double p2Y = bEY + (sin*(-1) + cos*(1));

            context.strokeLine(ceX, ceY, p1X * rectSize, p1Y * rectSize); //TODO Faire des vrai fleches 1/2
            context.strokeLine(ceX, ceY, p2X * rectSize, p2Y * rectSize); //TODO Faire des vrai fleches 2/2
            */
            context.strokeOval(ceX-(rectSize/4), ceY-(rectSize/4), (rectSize/2), (rectSize/2));
        }
        context.setStroke(Color.BLACK);
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
