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
        updateSize();
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
