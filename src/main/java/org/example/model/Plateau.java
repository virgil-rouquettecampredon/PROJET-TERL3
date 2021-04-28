package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plateau implements Serializable, Cloneable{
    private int heightY;                                // Hauteur du plateau
    private int witdhX;                                 // Largeur du plateau
    private ArrayList<ArrayList<Case>> echiquier;       // Liste des case du plateau

    /**
     * Constructeur par défault
     */
    public Plateau() {
        this.heightY = 8;
        this.witdhX = 8;
        updateSize();
    }


    public Plateau(int heightY, int witdhX) {
        this.heightY = heightY;
        this.witdhX = witdhX;
        updateSize();
    }

    public Plateau(Plateau plateauACopie){
        this.heightY = plateauACopie.heightY;
        this.witdhX = plateauACopie.witdhX;
        updateSize();

        for (int i = 0; i < this.heightY; i++) {
            for (int j = 0; j < this.witdhX; j++){
                Case casePlateau = this.echiquier.get(i).get(j);
                Case caseACopie = plateauACopie.getEchiquier().get(i).get(j);
                casePlateau.setPieceOnCase(caseACopie.getPieceOnCase());
                casePlateau.setAccessible(caseACopie.isAccessible());
            }
        }
    }

    /**
     * Vide le plateau de toutes ses pièces
     */
    public void clear() {
        for (int i = 0; i < heightY; i++) {
            for (int j = 0; j < witdhX; j++) {
                echiquier.get(i).get(j).setPieceOnCase(null);
            }
        }
    }

    /**
     * Clone le plateau en profondeur et met à jour les pieces avec le joueur correspondant
     * @param joueurs la lise des joueurs à mettre dans les pièces
     * @return un plateau clone de this
     * @throws CloneNotSupportedException
     */
    public Plateau clone(ArrayList<Joueur> joueurs) throws CloneNotSupportedException {
        Plateau p = (Plateau)super.clone();
        p.echiquier = new ArrayList<>();
        //Pour toutes les cases :
        for (ArrayList<Case> ligne : echiquier) {
            ArrayList<Case> ligneP = new ArrayList<>();
            for (Case c : ligne) {
                //On reconstruit le plateau avec les clones de cases
                Case nc = c.clone();
                ligneP.add(nc);

                //On met le joueur correspondant dans la piece de la nouvelle case
                Case myCase = getCase(c.getPosition());
                if (myCase.getPieceOnCase() != null) {
                    Joueur monJoueur = myCase.getPieceOnCase().getJoueur();
                    Joueur jCorrespondant = null;
                    for (Joueur vj : joueurs) {
                        if (vj.getName().equals(monJoueur.getName()) && vj.getEquipe() == monJoueur.getEquipe()) {
                            jCorrespondant = vj;
                            break;
                        }
                    }
                    if (jCorrespondant == null) {
                        System.err.println("Pas possible");
                    }
                    else {
                        nc.getPieceOnCase().setJoueur(jCorrespondant);
                        jCorrespondant.getPawnList().add(nc.getPieceOnCase());
                    }
                }
            }
            p.echiquier.add(ligneP);
        }

        return p;
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

    public Case getCase(Position p) {
        if (p.getX() >= witdhX || p.getX() < 0 || p.getY() >= heightY || p.getY() < 0) {
            return null;
        }
        return echiquier.get(p.getY()).get(p.getX());
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


    public Position getPiecePosition(Piece p){
        Position pos;
        for (List<Case> listCase: echiquier) {
            for (Case c: listCase){
                Piece piece;
                if((piece = c.getPieceOnCase()) != null){
                    if (piece == p){
                        return c.getPosition();
                    }
                }
            }
        }
        return null;
    }

    public void reinitialiserComportementLieAunTour(Joueur j){
        for (List<Case> listCase: echiquier) {
            for (Case casePlateau: listCase) {
                if (casePlateau.getPieceOnCase() != null) {
                    if (casePlateau.getPieceOnCase().getJoueur() != j) {
                        casePlateau.getPieceOnCase().setPieceMange(null);
                        casePlateau.getPieceOnCase().setDeplaceCeTour(false);
                    }
                    casePlateau.getPieceOnCase().setEstApromouvoir(false);
                    casePlateau.getPieceOnCase().getDeplacementsSpecialRegles().clear();
                }
            }
        }
    }
}
