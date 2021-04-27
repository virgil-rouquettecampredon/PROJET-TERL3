package org.example.model;

import org.example.model.Regles.CibleDeRegle;
import org.example.model.Regles.SujetDeRegle;

import java.io.Serializable;
import java.util.*;

public class Joueur implements CibleDeRegle, SujetDeRegle, Serializable, Cloneable {

    private String name;                        // Nom du joueur
    private int equipe;                         // Équipe du joueur
    private ArrayList<Piece> graveyard;         // Liste des pièce dans la défausse du joueur
    private ArrayList<Piece> pawnList;          // Liste des pieces sur le plateau
    private List<Piece> typePawnList;           // Liste de tous les types de pièces du joueur
    private int min;                          // Temps du joueur utilisé depuis le début de la partie
    private int sec;                          // Temps du joueur utilisé depuis le début de la partie


    public Joueur(String name, int equipe) {
        this.name = name;
        this.equipe = equipe;
        this.graveyard = new ArrayList<Piece>();
        this.pawnList = new ArrayList<Piece>();
        this.typePawnList = new ArrayList<Piece>();
        this.min = 10;
        this.sec = 0;
    }

    public Joueur(Joueur joueur) {
        this(joueur.name, joueur.equipe);
        min = joueur.min;
        sec = joueur.sec;
        pawnList.addAll(joueur.pawnList);
        typePawnList.addAll(joueur.typePawnList);
    }

    public Joueur clone() throws CloneNotSupportedException {
        Joueur j = (Joueur)super.clone();
        j.graveyard = new ArrayList<>();
        for (Piece p : graveyard) {
            j.graveyard.add(p.clone());
        }
        j.pawnList = new ArrayList<>();
        j.typePawnList = new ArrayList<>();
        for (Piece p : typePawnList) {
            j.typePawnList.add(p.clone());
        }
        return j;
    }

    public boolean decrementeTempsJoueur(){
        if(sec == 0 && min > 0){
            sec = 59;
            min --;
            return true;
        }
        else if(sec > 0){
            sec --;
            return true;
        }
        return false;
    }

    public String toStringTimer(){
        if(this.getSeconde() <= 9){
            return this.getMinute() + " : 0" + this.getSeconde();
        }
        return this.getMinute() + " : " + this.getSeconde();
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
        return name+" ("+equipe+")" +this.hashCode();
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

    public void setMinute(int minute) {
        this.min = minute;
    }

    public void setSec(int seconde) {
        this.sec = seconde;
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

    public Integer getMinute() {
        return min;
    }

    public Integer getSeconde() {
        return sec;
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
