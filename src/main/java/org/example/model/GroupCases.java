package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupCases implements Serializable {
    private String name;
    private ArrayList<Case> casesAbsolue;
    private ArrayList<Position> positionsRelatives;
    private Plateau plateau;

    public GroupCases(String name, Plateau plateau) {
        this.name = name;
        this.casesAbsolue = new ArrayList<>();
        this.positionsRelatives = new ArrayList<>();
        this.plateau = plateau;
    }

    /*GETTER ET SETTER*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Case> getCasesAbsolue() {
        return casesAbsolue;
    }

    public void setCasesAbsolue(ArrayList<Case> casesAbsolue) {
        this.casesAbsolue = casesAbsolue;
    }

    public ArrayList<Position> getPositionsRelatives() {
        return positionsRelatives;
    }

    public void setPositionsRelatives(ArrayList<Position> positionsRelatives) {
        this.positionsRelatives = positionsRelatives;
    }


    /**Méthode qui retourne l'ensemble des cases du groupe
     * @param posX : indice X de la position servant au calculs des cases en position relative.
     * @param posY : indice Y de la position servant au calculs des cases en position relative.
     * **/
    public ArrayList<Case> getAllCases(int posX, int posY) {
        ArrayList<Case> allCases = new ArrayList<>(casesAbsolue);
        allCases.addAll(getCasesRelatives(posX, posY));
        return allCases;
    }

    /**Méthode qui retourne l'ensemble des cases du groupe
     * @param p : Position servant au calculs des cases en position relative.**/
    public ArrayList<Case> getAllCases(Position p) {
        return getAllCases(p.getX(), p.getY());
    }

    /**Méthode permettant de récupérer l'ensemble des cases du groupe avec comme position relative (0,0).**/
    public ArrayList<Case> getAllCases() {
        return getAllCases(0, 0);
    }

    /**Méthode qui retourne l'ensemble des cases relatives du groupe
     * @param posX : indice X de la position servant au calculs des cases en position relative.
     * @param posY : indice Y de la position servant au calculs des cases en position relative.
     * **/
    public ArrayList<Case> getCasesRelatives(int posX, int posY) {
        ArrayList<Case> casesRelatives = new ArrayList<>();
        for (Position p : positionsRelatives) {
            if (p.getY() + posY < plateau.getHeightY() && p.getX() + posX < plateau.getWitdhX()) {
                casesRelatives.add(plateau.getEchiquier().get(p.getY() + posY).get(p.getX() + posX));
            }
        }
        return casesRelatives;
    }
}
