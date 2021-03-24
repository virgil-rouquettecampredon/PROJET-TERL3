package org.example.model;

import org.example.model.Regles.CibleDeRegle;

public class Case implements CibleDeRegle {
    private Position position;
    private Piece pieceOnCase;

    public Case(int x, int y) {
        position = new Position(x, y);
        this.pieceOnCase = null;
    }

    public void afficherCase(){
        // A completer

    }

    /*DEBUT GETTER SETTER*/


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