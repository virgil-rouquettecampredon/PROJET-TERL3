package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plateau implements Serializable {
    private int heightY;                                // Hauteur du plateau
    private int witdhX;                                 // Largeur du plateau
    private ArrayList<ArrayList<Case>> echiquier;       // Liste des case du plateau

    /**
     * Constructeur par défault
     */
    public Plateau() {
        this.heightY = 8;
        this.witdhX = 8;
        updateSize();
    }


    public Plateau(int heightY, int witdhX) {
        this.heightY = heightY;
        this.witdhX = witdhX;
        updateSize();
    }

    public Plateau(Plateau plateauACopie){
        this.heightY = plateauACopie.heightY;
        this.witdhX = plateauACopie.witdhX;
        updateSize();

        for (int i = 0; i < this.heightY; i++) {
            for (int j = 0; j < this.witdhX; j++){
                Case casePlateau = this.echiquier.get(i).get(j);
                Case caseACopie = plateauACopie.getEchiquier().get(i).get(j);
                casePlateau.setPieceOnCase(caseACopie.getPieceOnCase());
                casePlateau.setAccessible(caseACopie.isAccessible());
            }
        }
    }

    /**
     * Vide le plateau de toutes ses pièces
     */
    public void clear() {
        for (int i = 0; i < heightY; i++) {
            for (int j = 0; j < witdhX; j++) {
                echiquier.get(i).get(j).setPieceOnCase(null);
            }
        }
    }

    /*DEBUT GETTER SETTER*/

    public int getHeightY() {
        return heightY;
    }

    public int getWitdhX() {
        return witdhX;
    }

    public ArrayList<ArrayList<Case>> getEchiquier() {
        return echiquier;
    }

    public Case getCase(Position p) {
        return echiquier.get(p.getY()).get(p.getX());
    }

    public void setHeightY(int heightY) {
        this.heightY = heightY;
        updateSize();
    }

    public void setWitdhX(int witdhX) {
        this.witdhX = witdhX;
        updateSize();
    }

    private void updateSize() {
        echiquier = new ArrayList<>();
        for (int i = 0; i < heightY; i++) {
            echiquier.add(new ArrayList<>());
            for (int j = 0; j < witdhX; j++) {
                echiquier.get(i).add(new Case(j, i));
            }
        }
    }

    /*FIN GETTER SETTER*/


    public Position getPiecePosition(Piece p){
        Position pos;
        for (List<Case> listCase: echiquier) {
            for (Case c: listCase){
                Piece piece;
                if((piece = c.getPieceOnCase()) != null){
                    if (piece == p){
                        return c.getPosition();
                    }
                }
            }
        }
        return null;
    }

    public void reinitialiserComportementLieAunTour(Joueur j){
        for (List<Case> listCase: echiquier) {
            for (Case casePlateau: listCase) {
                if (casePlateau.getPieceOnCase() != null && casePlateau.getPieceOnCase().getJoueur() != j){
                    casePlateau.getPieceOnCase().setPieceMange(null);
                    casePlateau.getPieceOnCase().setDeplaceCeTour(false);
                }
            }
        }
    }
}
