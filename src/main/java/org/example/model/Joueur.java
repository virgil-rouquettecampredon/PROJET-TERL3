package org.example.model;

import org.example.model.Regles.CibleDeRegle;
import org.example.model.Regles.SujetDeRegle;

import java.io.Serializable;
import java.util.ArrayList;

public class Joueur implements CibleDeRegle, SujetDeRegle, Serializable {

    private String name;                        // Nom du joueur
    private int equipe;                         // Équipe du joueur
    private ArrayList<Piece> graveyard;         // Liste des pièce dans la défausse du joueur
    private ArrayList<Piece> pawnList;          // Liste de toutes les pièces du joueur (le nombre de pièces total du joueur)
    private ArrayList<Piece> typePawnList;      // Liste de tous les types de pièces du joueur
    private int timer;                          // Temps du joueur utilisé depuis le début de la partie


    public Joueur(String name, int equipe) {
        this.name = name;
        this.equipe = equipe;
        this.graveyard = new ArrayList<Piece>();
        this.pawnList = new ArrayList<Piece>();
        this.typePawnList = new ArrayList<Piece>();
        this.timer = -1;
    }

    public void movePawn(Piece pawn, Position position){
        // A completer

    }

    public void revive(Piece pawn, Case emplacement){
        // A completer

    }

    /*DEBUT GETTER SETTER*/

    public void setName(String name) {
        this.name = name;
    }

    public void setPawnList(ArrayList<Piece> pawnList) {
        this.pawnList = pawnList;
    }

    public void setEquipe(int equipe) {
        this.equipe = equipe;
    }

    public void setGraveyard(ArrayList<Piece> graveyard) {
        this.graveyard = graveyard;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public int getEquipe() {
        return equipe;
    }

    public ArrayList<Piece> getGraveyard() {
        return graveyard;
    }

    public ArrayList<Piece> getPawnList() {
        return pawnList;
    }

    public ArrayList<Piece> getTypePawnList() {
        return typePawnList;
    }

    public int getTimer() {
        return timer;
    }

    /*FIN GETTER SETTER*/
}
