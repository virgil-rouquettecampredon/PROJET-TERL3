package org.example.model;

import org.example.model.Regles.CibleDeRegle;

import java.io.Serializable;
import java.util.Objects;

public class Case implements Serializable, Cloneable {
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

    public Case clone() throws CloneNotSupportedException {
        Case c = (Case) super.clone();
        c.position = new Position(position.getX(), position.getY());
        if (pieceOnCase != null) {
            c.pieceOnCase = pieceOnCase.clone();
        }
        return c;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return accessible == aCase.accessible && position.equals(aCase.position) && Objects.equals(pieceOnCase, aCase.pieceOnCase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, pieceOnCase, accessible);
    }

    @Override
    public String toString() {
        return "Case : [" +
                "position=" + position +
                ", pieceOnCase=" + pieceOnCase +
                ", accessible=" + accessible +
                ']';
    }
}