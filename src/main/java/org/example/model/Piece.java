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

    private Boolean[] etatPiece;
    private Piece pieceMange = null;
    private Piece pieceMenace = null;
    private Case caseDeplace = null;

    public Piece(String name, String sprite, int nbMovement, int nbLife, Joueur joueur, ArrayList<PositionDeDeplacement> posDeplacements, ArrayList<VecteurDeDeplacement> vecDeplacements) {
        this.name = name;
        this.sprite = sprite;
        this.nbMovement = nbMovement;
        this.nbLife = nbLife;
        this.joueur = joueur;
        this.posDeplacements = new ArrayList<>();
        this.posDeplacements.addAll(posDeplacements);
        this.vecDeplacements = new ArrayList<>();
        this.vecDeplacements.addAll(vecDeplacements);

        this.comportementPiece = new Boolean[3];
        for (int i = 0; i < 3; i++){
            this.comportementPiece[i] = false;
        }
        this.etatPiece = new Boolean[42];
        for (int i = 0; i < 42; i++) {
            this.etatPiece[i] = false;
        }
    }

    public Piece(String name, String sprite, Joueur joueur) {
        this(name, sprite, 0, -1, joueur, new ArrayList<>(), new ArrayList<>());
    }

    public Piece(String name, String sprite) {
        this(name, sprite, null);
    }

    public Piece(Piece piece) {
        this(piece.name, piece.sprite, piece.nbMovement, piece.nbLife, piece.joueur, piece.posDeplacements, piece.vecDeplacements);

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


    public void setAEtePromu(boolean etat) {
        etatPiece[0] = etat;
    }

    public void setPieceMange(Piece p) {
        pieceMange = p;
    }

    public void setPieceMenace(Piece p) {
        pieceMenace = p;
    }

    public void setCaseDeplace(Case c) {
        caseDeplace = c;
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


    public boolean aEtePromu() {
        return etatPiece[0];
    }

    public Piece getPieceMange() {
        return pieceMange;
    }

    public Piece getPieceMenace() {
        return pieceMenace;
    }

    public Case getCaseDeplace() {
        return caseDeplace;
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