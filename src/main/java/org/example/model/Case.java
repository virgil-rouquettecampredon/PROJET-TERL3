package org.example.model;

import org.example.model.Regles.CibleDeRegle;

public class Case implements CibleDeRegle {
    private Position position;
    private Piece pieceOnCase;
    private boolean clickable;

    public Case(int x, int y, boolean clickable) {
        position = new Position(x, y);
        this.pieceOnCase = null;
        this.clickable = true;
    }

    public Case(int x, int y) {
        this(x, y, true);
    }

    public void afficherCase(){
        // A completer

    }

    /*DEBUT GETTER SETTER*/
    public boolean isClickable() {
        return clickable;
    }

    public void switchClickable() {
        clickable = !clickable;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPieceOnCase() {
        return pieceOnCase;
    }

    public void setPieceOnCase(Piece pieceOnCase) {
        this.pieceOnCase = pieceOnCase;
    }
    /*FIN GETTER SETTER*/
}