package org.example.model;

import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.FactoryEquationDeDeplacement;
import org.example.model.Regles.CibleDeRegle;
import org.example.model.Regles.SujetDeRegle;

import java.util.ArrayList;

public class Piece implements SujetDeRegle, CibleDeRegle {
    private String name;
    private String sprite;
    private int nbMovement;
    private int nbLife;
    private Joueur joueur;
    private ArrayList<EquationDeDeplacement> deplacements;
    private ArrayList<Boolean> comportementPiece;

    public Piece(String name, String sprite, Joueur joueur) {
        this.name = name;
        this.sprite = sprite;
        this.nbMovement = 0;
        this.nbLife = -1;
        this.joueur = joueur;
        this.deplacements = new ArrayList<EquationDeDeplacement>();
        this.comportementPiece = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            comportementPiece.add(i, false);
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
        deplacements = new ArrayList<>();
        deplacements.addAll(piece.deplacements);
        comportementPiece = new ArrayList<>();
        comportementPiece.addAll(piece.comportementPiece);
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

    public void setDeplacements(ArrayList<EquationDeDeplacement> deplacements) {
        this.deplacements = deplacements;
    }

    public void setEstConditionDeVictoire(boolean comportement){
        comportementPiece.set(0, comportement);
    }

    public void setEstSauteuse(boolean comportement){
        comportementPiece.set(1, comportement);
    }

    public void setEstPromouvable(boolean comportement){
        comportementPiece.set(2, comportement);
    }

    public void setEstTraitre(boolean comportement){
        comportementPiece.set(3, comportement);
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

    public ArrayList<EquationDeDeplacement> getDeplacements() {
        return deplacements;
    }

    public boolean estConditionDeVictoire(){
        return comportementPiece.get(0);
    }

    public boolean estSauteuse(){
        return comportementPiece.get(1);
    }

    public boolean estPromouvable(){
        return comportementPiece.get(2);
    }

    public boolean estTraitre(){
        return comportementPiece.get(3);
    }

    /*FIN GETTER SETTER*/
}