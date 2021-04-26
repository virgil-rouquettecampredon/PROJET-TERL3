package org.example.model;

import org.example.model.Regles.CibleDeRegle;
import org.example.model.Regles.SujetDeRegle;

import java.io.Serializable;
import java.util.*;

public class Joueur implements CibleDeRegle, SujetDeRegle, Serializable {

    private String name;                        // Nom du joueur
    private int equipe;                         // Équipe du joueur
    private ArrayList<Piece> graveyard;         // Liste des pièce dans la défausse du joueur
    private ArrayList<Piece> pawnList;          // Liste des pieces sur le plateau
    private List<Piece> typePawnList;           // Liste de tous les types de pièces du joueur
    private int timer;                          // Temps du joueur utilisé depuis le début de la partie


    public Joueur(String name, int equipe) {
        this.name = name;
        this.equipe = equipe;
        this.graveyard = new ArrayList<Piece>();
        this.pawnList = new ArrayList<Piece>();
        this.typePawnList = new ArrayList<Piece>();
        this.timer = 10;
    }

    public Joueur(Joueur joueur) {
        this(joueur.name, joueur.equipe);
        pawnList.addAll(joueur.pawnList);
        typePawnList.addAll(joueur.typePawnList);
    }

    public void movePawn(Piece pawn, Case casePlateau){

    }

    public void revive(Piece pawn, Case emplacement){
        // A completer

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return equipe == joueur.equipe && name.equals(joueur.name) && graveyard.equals(joueur.graveyard) && pawnList.equals(joueur.pawnList) && typePawnList.equals(joueur.typePawnList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, equipe, graveyard, pawnList, typePawnList);
    }

    @Override
    public String toString() {
        return name+" ("+equipe+")";
    }

    /*DEBUT GETTER SETTER*/

    public void setName(String name) {
        this.name = name;
    }

    public void setPawnList(ArrayList<Piece> pawnList) {
        this.pawnList = pawnList;
    }

    public void setTypePawnList(ArrayList<Piece> typePawnList) {
        LinkedHashSet<Piece> tmp = new LinkedHashSet<>();
        tmp.addAll(typePawnList);
        this.typePawnList.addAll(tmp);
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

    public List<Piece> getTypePawnList() {
        return typePawnList;
    }

    public int getTimer() {
        return timer;
    }

    public List<Piece> getPieceConditionDeVictoire(){
        List<Piece> pieces = new ArrayList<>();
        for (Piece piece: this.pawnList) {
            if (piece.estConditionDeVictoire()){
                pieces.add(piece);
            }
        }
        return pieces;
    }

    public List<Piece> getListPromotion() {
        List<Piece> promouvables = new ArrayList<>();
        for (Piece piece: this.getTypePawnList()) {
            if ((!piece.estConditionDeVictoire()) && (!piece.estPromouvable())){
                promouvables.add(piece);
            }
        }
        return promouvables;
    }

    /*FIN GETTER SETTER*/
}
