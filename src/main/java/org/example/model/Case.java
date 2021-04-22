package org.example.model;

import org.example.model.Regles.CibleDeRegle;

import java.io.Serializable;

public class Case implements CibleDeRegle, Serializable {
    private Position position;                          // Position sur le plateau
    private Piece pieceOnCase;                          // Pièce située sur la case, ou null si aucune pièce
    private boolean accessible;                          // Case accessible ou non

    public Case(int x, int y, boolean clickable) {
        position = new Position(x, y);
        this.pieceOnCase = null;
        this.accessible = true;
    }

    public Case(int x, int y) {
        this(x, y, true);
    }

    public void afficherCase(){
        // A completer

    }

    /*DEBUT GETTER SETTER*/
    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean value){
        this.accessible = value;
    }

    public void switchClickable() {
        accessible = !accessible;
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