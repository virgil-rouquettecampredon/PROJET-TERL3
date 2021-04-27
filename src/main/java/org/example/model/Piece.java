package org.example.model;

import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.FactoryEquationDeDeplacement;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;
import org.example.model.Regles.CibleDeRegle;
import org.example.model.Regles.SujetDeRegle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Piece implements SujetDeRegle, CibleDeRegle, Serializable, Cloneable {
    private String name;                                            // Nom de la pièce
    private String sprite;                                          // Image qui sera afficher sur le plateau pour cette pièce
    private int nbMovement;                                         // Le nombre total de mouvement qu'a effectué la pièce au cours de la partie jouée
    private int nbLife;                                             // Le nombre de vie qu'a la pièce si elle est condition de victoire
    private Joueur joueur;                                          // Le joueur auquelle appartient la pièce
    private ArrayList<PositionDeDeplacement> posDeplacements;       // Les deplacement fixes de la pièce (ex :x+2; y+2)
    private ArrayList<VecteurDeDeplacement> vecDeplacements;        // Les déplacement horizontaux de la pièce
    private Boolean[] comportementPiece;                            // Donne les comportements de pièces (ex: conditionDeVictoire, Traitre, ...)
    private Boolean[] etatPiece;                                    // Ce sont des conditions utilisé pour les règles (ex: aEtePromu)
    private Piece pieceMange = null;                                // Retourne la pièce qui vient d'être mangé, sinon retourne null
    private Piece pieceMenace = null;                               // Retourne la pièce qu'il menace

    private List<Position> deplacementsSpecialRegles;               //Liste des déplacements spéciaux d'une pièce, MAJ d'après les Regles

    public Piece(String name, String sprite, int nbMovement, int nbLife, Joueur joueur, ArrayList<PositionDeDeplacement> posDeplacements, ArrayList<VecteurDeDeplacement> vecDeplacements, List<Position> deplacementsSpecialRegles) {
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

        this.deplacementsSpecialRegles = deplacementsSpecialRegles;
    }

    public Piece(String name, String sprite, Joueur joueur) {
        this(name, sprite, 0, -1, joueur, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Piece(String name, String sprite) {
        this(name, sprite, null);
    }


    /**@param piece: La pèce utilisé afin de la dupliquer.
     * La fonction duplique une pièce */
    public Piece(Piece piece) {
        this(piece.name, piece.sprite, piece.nbMovement, piece.nbLife, piece.joueur, piece.posDeplacements, piece.vecDeplacements, piece.deplacementsSpecialRegles);

        comportementPiece = new Boolean[3];
        System.arraycopy(piece.comportementPiece, 0, comportementPiece, 0, 3);
    }

    public Piece clone() throws CloneNotSupportedException {
        Piece p = (Piece)super.clone();
        p.joueur = null;

        p.posDeplacements = new ArrayList<>();
        p.posDeplacements.addAll(posDeplacements);
        p.vecDeplacements = new ArrayList<>();
        p.vecDeplacements.addAll(vecDeplacements);
        p.comportementPiece = new Boolean[3];
        for (int i = 0; i < 3; i++){
            p.comportementPiece[i] = comportementPiece[i];
        }
        p.etatPiece = new Boolean[42];
        for (int i = 0; i < 42; i++) {
            p.etatPiece[i] = etatPiece[i];
        }
        pieceMange = null;
        pieceMenace = null;
        p.deplacementsSpecialRegles = new ArrayList<>();
        for (Position pos : deplacementsSpecialRegles) {
            p.deplacementsSpecialRegles.add(new Position(pos.getX(), pos.getY()));
        }
        return p;
    }

    public void ajouterDeplacementsSpecialRegles(Position p){
        deplacementsSpecialRegles.add(p);
    }

    public boolean equalsRegle(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return name.equals(piece.name) && posDeplacements.equals(piece.posDeplacements) && vecDeplacements.equals(piece.vecDeplacements) && Arrays.equals(comportementPiece, piece.comportementPiece);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return name.equals(piece.name) && sprite.equals(piece.sprite) && posDeplacements.equals(piece.posDeplacements) && vecDeplacements.equals(piece.vecDeplacements) && Arrays.equals(comportementPiece, piece.comportementPiece);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, sprite, posDeplacements, vecDeplacements);
        result = 31 * result + Arrays.hashCode(comportementPiece);
        return result;
    }

    @Override
    public String toString() {
        return "["+name + " joueur="+joueur+"]";
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

    public void setDeplaceCeTour(boolean bool) {
        etatPiece[1] = bool;
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

    public void setPosDeplacements(ArrayList<PositionDeDeplacement> posDeplacements) {
        this.posDeplacements = posDeplacements;
    }
    public void setVecDeplacements(ArrayList<VecteurDeDeplacement> vecDeplacements) {
        this.vecDeplacements = vecDeplacements;
    }

    public List<Position> getDeplacementsSpecialRegles(){
        return deplacementsSpecialRegles;
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

    public boolean getDeplaceCeTour() {
        return etatPiece[1];
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