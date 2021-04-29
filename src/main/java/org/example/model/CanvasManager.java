package org.example.model;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CanvasManager {
    private Canvas canvas;              // Le canvas graphique
    private GraphicsContext context;    // La zone de dessin sur le canvas
    private double rectSize;            // La taille d'une case en pixels
    private Plateau plateau;            // Le plateau à afficher

    private Color lightBoardColor = Color.PINK;     // La couleur du plateau sur les cases sombres
    private Color darkBoardColor = Color.PURPLE;    // La couleur du plateau sur les cases claires
    private Color strokeColor = Color.BLACK;        // La couleur par defaut pour les traits

    // Les couleurs des cases non accessibles
    private Color lightNonAccessibleCaseColor = Color.DARKGRAY;
    private Color darkNonAccessibleCaseColor = Color.GRAY;

    // Les couleurs des déplacements uniquement
    private Color lightMoveColor = Color.LIGHTGREEN;
    private Color darkMoveColor = Color.DARKGREEN;
    private Color arrowMoveColor = Color.GREEN;

    // Les couleurs pour prendre uniquement
    private Color lightTakeColor = Color.PALEVIOLETRED;
    private Color darkTakeColor = Color.DARKRED;
    private Color arrowTakeColor = Color.RED;

    // Les couleurs des déplacements et pour prendre
    private Color lightBothColor = Color.color(68/255.0, 167/255.0, 242/255.0);
    private Color darkBothColor = Color.MEDIUMBLUE;
    private Color arrowBothColor = Color.SKYBLUE;

    public CanvasManager(Canvas canvas, Plateau plateau) {
        this.canvas = canvas;
        context = canvas.getGraphicsContext2D();
        rectSize = canvas.getWidth()/plateau.getWitdhX();
        this.plateau = plateau;
    }

    /**
     * Dessine le plateau de sorte que :
     *  -   Les cases sont des carres
     *  -   Le canvas fait au maximum 300 pixels (de large ou de hauteur)
     *  -   Les cases non accessibles sont grisées
     */
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

        context.setFill(lightBoardColor);
        context.fillRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight());


        context.setFill(darkBoardColor);
        for (int i = 0; i < nbSquareY; i++) {
            for (int j = 0; j < nbSquareX; j++) {
                if (plateau.getEchiquier().get(i).get(j).isAccessible()) {
                    if ((j+i)%2==0) {
                        context.setFill(lightBoardColor);
                    }
                    else {
                        context.setFill(darkBoardColor);
                    }
                }
                else {
                    if ((j+i)%2==0) {
                        context.setFill(lightNonAccessibleCaseColor);
                    }
                    else {
                        context.setFill(darkNonAccessibleCaseColor);
                    }
                }
                context.fillRect(j * rectSize, i * rectSize, rectSize, rectSize);
            }
        }
    }

    public void hideCasesExcept(List<Position> listPos) {
        for (int i = 0; i < plateau.getHeightY(); i++) {
            for (int j = 0; j < plateau.getWitdhX(); j++) {
                Position p = new Position(j, i);
                if (!listPos.contains(p)) {
                    drawCase(p, lightNonAccessibleCaseColor, darkNonAccessibleCaseColor);
                }
            }
        }
    }

    public void hideCasesExceptCase(List<Case> listCase) {
        ArrayList<Position> pos = new ArrayList<>();
        for (Case c: listCase) {
            pos.add(c.getPosition());
        }
        hideCasesExcept(pos);
    }

    public void hideAllCases() {
        hideCasesExcept(new ArrayList<Position>());
    }

    /**
     * Dessine les pieces sur le plateau
     */
    public void drawPawn() {
        for (ArrayList<Case> ligne: plateau.getEchiquier()) {
            for (Case c: ligne) {
                if (c.getPieceOnCase() != null) {
                    putPiece(c.getPosition().getX(), c.getPosition().getY(), new Image(c.getPieceOnCase().getSprite()));
                }
            }
        }
    }

    /**
     * Dessine les deplacements d'une piece à partir d'une position
     * @param image L'image de la piece
     * @param posX  La position X de la piece
     * @param posY  La position Y de la piece
     * @param posDeplacements   La liste des positions de déplacements
     * @param vecDeplacements   La liste des vecteurs de déplacements
     */
    public void drawDeplacement(Image image, int posX, int posY, List<PositionDeDeplacement> posDeplacements, List<VecteurDeDeplacement> vecDeplacements) {
        for (PositionDeDeplacement e:
                posDeplacements) {

            double bPosEX = (e.getX()+posX) * rectSize;
            double bPosEY = (e.getY()+posY) * rectSize;
            if ((e.getX()+posX + e.getY()+posY)%2==0) {
                switch (e.getTypeDeplacement()) {
                    case DEPLACER -> context.setFill(lightMoveColor);
                    case PRENDRE -> context.setFill(lightTakeColor);
                    case BOTH -> context.setFill(lightBothColor);
                }
            }
            else {
                switch (e.getTypeDeplacement()) {
                    case DEPLACER -> context.setFill(darkMoveColor);
                    case PRENDRE -> context.setFill(darkTakeColor);
                    case BOTH -> context.setFill(darkBothColor);
                }
            }
            context.fillRect(bPosEX, bPosEY, rectSize, rectSize);
        }

        for (VecteurDeDeplacement e:
                vecDeplacements) {
            switch (e.getTypeDeplacement()) {
                case DEPLACER -> context.setStroke(arrowMoveColor);
                case PRENDRE -> context.setStroke(arrowTakeColor);
                case BOTH -> context.setStroke(arrowBothColor);
            }
            context.setLineWidth(5);

            double bEX = (e.getX()+posX);
            double bEY = (e.getY()+posY);

            double cPosX = (posX+.5) * rectSize;
            double cPosY = (posY+.5) * rectSize;
            double ceX = (bEX+.5) * rectSize;
            double ceY =  (bEY+.5) * rectSize;
            context.strokeLine(cPosX, cPosY, ceX, ceY);

            context.strokeOval(ceX-(rectSize/4), ceY-(rectSize/4), (rectSize/2), (rectSize/2));
        }
        context.setStroke(strokeColor);
        putPiece(posX, posY, image);
    }

    /**
     * Dessine une piece sur le plateau
     * @param x La position X de la case
     * @param y La position Y de la case
     * @param img L'image de la piece
     */
    private void putPiece(int x, int y, Image img) {
        double cx = rectSize*(x+0.5);
        double cy = rectSize*(y+0.5);
        double w;
        double h;
        if (img.getHeight() > img.getWidth()) {
            h = rectSize*0.9;
            w = img.getWidth()/img.getHeight() * rectSize*0.9;
        }
        else {
            w = rectSize*0.9;
            h = img.getHeight()/img.getWidth() * rectSize*0.9;
        }
        context.drawImage(img, cx-w/2, cy-h/2, w, h);
    }

    /**
     * Dessine un groupe de case à partir d'une position
     * @param posX  La position X de la case pour les positions relatives
     * @param posY  La position Y de la case pour les positions relatives
     * @param group Le groupe de case
     */
    public void drawGroupCases(int posX, int posY, GroupCases group) {
        for (Case c : group.getCasesAbsolue()) {
            Position p = c.getPosition();
            drawCase(p, lightMoveColor, darkMoveColor);
        }

        for (Position relP : group.getPositionsRelatives()) {
            Position p = new Position(relP.getX() + posX, relP.getY() + posY);
            drawCase(p, lightBothColor, darkBothColor);
        }

        context.setFill(arrowTakeColor);
        context.fillOval((posX * rectSize)+(rectSize/4), (posY * rectSize)+(rectSize/4), (rectSize/2), (rectSize/2));
    }

    /**
     * Donne une couleur sombre aléatoire en fonction d'une seed sous forme de String
     * @param s La seed
     * @return  Une couleur aléatoire
     */
    private Color darkColorFromString(String s) {
        Random rand = new Random(s.hashCode());
        double r = rand.nextDouble()/2;
        double g = rand.nextDouble()/2;
        double b = rand.nextDouble()/2;

        while (lightBoardColor.getRed() - r < .1 && lightBoardColor.getGreen() - g < .1 && lightBoardColor.getBlue() - b < .1) {
            r = rand.nextDouble()/2;
            g = rand.nextDouble()/2;
            b = rand.nextDouble()/2;
        }

        return Color.color(r, g, b);
    }

    /**
     * Donne une couleur claire aléatoire en fonction d'une seed sous forme de String
     * @param s La seed
     * @return  Une couleur aléatoire
     */
    private Color lightColorFromString(String s) {
        Random rand = new Random(s.hashCode());
        double r = rand.nextDouble()/2+.5;
        double g = rand.nextDouble()/2+.5;
        double b = rand.nextDouble()/2+.5;

        while (darkBoardColor.getRed() - r < .1 && darkBoardColor.getGreen() - g < .1 && darkBoardColor.getBlue() - b < .1) {
            r = rand.nextDouble()/2+.5;
            g = rand.nextDouble()/2+.5;
            b = rand.nextDouble()/2+.5;
        }

        return Color.color(r, g, b);
    }

    /**
     * Permet d'avoir la case du plateau à partir d'une coordonée en pixels
     * @param mouseX    Coordonée X
     * @param mouseY    Coordonée Y
     * @return  La case sous la coordonée
     */
    public Case getCase(double mouseX, double mouseY) {
        int x = (int) (mouseX / rectSize);
        int y = (int) (mouseY / rectSize);
        return plateau.getCase(new Position(x, y));
    }

    private void drawCase(Position p, Color cLight, Color cDark) {
        if ((p.getX()+p.getY())%2==0) {
            context.setFill(cLight);
        }
        else {
            context.setFill(cDark);
        }
        context.fillRect(p.getX() * rectSize, p.getY() * rectSize, rectSize, rectSize);
    }

    public void drawCase(Position position) {
        drawCase(position, lightTakeColor, darkTakeColor);
    }

    public void drawSelectionCase(Position pos) {
        drawCase(pos, lightBothColor, darkBothColor);
    }

    public double getRectSize() {return rectSize;}

    public void drawCoupPossibles(Set<Case> coupPossibles) {
        for (Case c: coupPossibles) {
            Position position = c.getPosition();
            drawCase(position, lightMoveColor, darkMoveColor);
        }
    }
}
