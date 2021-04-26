package org.example.model;

import org.example.model.Regles.CibleDeRegle;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupCases implements CibleDeRegle, Serializable {
    /*Classe permettant de modéliser un regroupement de cases*/
    private String name;                                // Nom du groupe
    private ArrayList<Case> casesAbsolue;               // Liste des case en position Absolue
    private ArrayList<Position> positionsRelatives;     // Liste des positions du plateau correspondant à des cases relatifs à une chose
    private Plateau plateau;                            // Le plateau de jeu

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

    public Plateau getPlateau(){ return this.plateau; }

    public void setPlateau(Plateau p){ this.plateau = p; }


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

    public ArrayList<Case> getCasesRelatives(Position pos) {
        ArrayList<Case> casesRelatives = new ArrayList<>();
        for (Position p : positionsRelatives) {
            if (p.getY() + pos.getY() < plateau.getHeightY() && p.getX() + pos.getX() < plateau.getWitdhX()) {
                casesRelatives.add(plateau.getEchiquier().get(p.getY() + pos.getY()).get(p.getX() + pos.getX()));
            }
        }
        return casesRelatives;
    }

    /**Méthode permettant d'ajouter une CaseAbsolue au groupe
     * @param c : case à ajouter aux cases absolues d'un groupe.**/
    public void addCasesAbsolue(Case c){
        casesAbsolue.add(c);
    }

    @Override
    public String toString() {
        return "GroupCases{" +
                "name='" + name + '\'' +
                ", casesAbsolue=" + casesAbsolue +
                ", positionsRelatives=" + positionsRelatives +
                '}';
    }
}
