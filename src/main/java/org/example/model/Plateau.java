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

    public void afficherPlateau(){
        // A completer

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
}
