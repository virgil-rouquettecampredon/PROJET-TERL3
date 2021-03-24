package org.example.model;

import java.util.ArrayList;

public class Plateau {
    private int heightY;
    private int witdhX;
    private ArrayList<ArrayList<Case>> echiquier;


    public Plateau() {
        this.heightY = 10;
        this.witdhX = 10;
        updateSize();
    }

    public Plateau(int heightY, int witdhX) {
        this.heightY = heightY;
        this.witdhX = witdhX;
        echiquier = new ArrayList<ArrayList<Case>>();
    }

    public void afficherPlateau(){
        // A completer

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
    }

    public void setWitdhX(int witdhX) {
        this.witdhX = witdhX;
    }

    /*FIN GETTER SETTER*/
}
