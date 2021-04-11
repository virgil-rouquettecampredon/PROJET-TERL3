package org.example.model;

import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.FactoryEquationDeDeplacement;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;
import org.example.model.Regles.CibleDeRegle;
import org.example.model.Regles.SujetDeRegle;

import java.io.Serializable;
import java.util.ArrayList;

public class Piece implements SujetDeRegle, CibleDeRegle, Serializable {
    private String name;
    private String sprite;
    private int nbMovement;
    private int nbLife;
    private Joueur joueur;
    private ArrayList<PositionDeDeplacement> posDeplacements;
    private ArrayList<VecteurDeDeplacement> vecDeplacements;
    private Boolean[] comportementPiece;

    public Piece(String name, String sprite, Joueur joueur) {
        this.name = name;
        this.sprite = sprite;
        this.nbMovement = 0;
        this.nbLife = -1;
        this.joueur = joueur;
        this.posDeplacements = new ArrayList<>();
        this.vecDeplacements = new ArrayList<>();
        this.comportementPiece = new Boolean[3];
        for (int i = 0; i < 3; i++){
            comportementPiece[i] = false;
        }
    }

    public Piece(String name, String sprite) {
        this(name, sprite, null);
    }

    public Piece(Piece piece) {
        name = piece.name;
        sprite = piece.sprite;
        nbMovement = piece.nbMovement;
        nbLife = piece.nbLife;
        joueur = piece.joueur;

        posDeplacements = new ArrayList<>();
        posDeplacements.addAll(piece.posDeplacements);
        vecDeplacements = new ArrayList<>();
        vecDeplacements.addAll(piece.vecDeplacements);

        comportementPiece = new Boolean[3];
        for (int i = 0; i < 3; i++) {
            comportementPiece[i] = piece.comportementPiece[i];
        }
    }

    public Position[] deplacementTheoriques(){
        // A completer

        return null;
    }

    /*DEBUT GETTER SETTER*/

    public void setName(String name) {
        this.name = name;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setNbMovement(int nbMovement) {
        this.nbMovement = nbMovement;
    }

    public void setNbLife(int nbLife) {
        this.nbLife = nbLife;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void setEstConditionDeVictoire(boolean comportement){
        comportementPiece[0] = comportement;
    }

    public void setEstPromouvable(boolean comportement){
        comportementPiece[1] = comportement;
    }

    public void setEstTraitre(boolean comportement){
        comportementPiece[2] = comportement;
    }

    public String getName() {
        return name;
    }

    public String getSprite() {
        return sprite;
    }

    public int getNbMovement() {
        return nbMovement;
    }

    public int getNbLife() {
        return nbLife;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<PositionDeDeplacement> getPosDeplacements() {
        return posDeplacements;
    }

    public ArrayList<VecteurDeDeplacement> getVecDeplacements() {
        return vecDeplacements;
    }

    public boolean estConditionDeVictoire(){
        return comportementPiece[0];
    }

    public boolean estPromouvable(){
        return comportementPiece[1];
    }

    public boolean estTraitre(){
        return comportementPiece[2];
    }

    /*FIN GETTER SETTER*/
}