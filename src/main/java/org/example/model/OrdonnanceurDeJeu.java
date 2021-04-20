package org.example.model;

import java.util.List;

public class OrdonnanceurDeJeu {

    //private int nbJoueur;
    private List<Joueur> joueurs;
    //private List<List<Joueur>> equipes;

    private List<String> types_pieces;
    private List<Piece> pieces;
    private Plateau plateau;

    public OrdonnanceurDeJeu(List<Joueur> joueurs, List<Piece> pieces, Plateau plateau){
        this.joueurs = joueurs;
        this.pieces = pieces;
        this.plateau = plateau;
    }

    public List<Joueur> getJoueurs() { return this.joueurs; }

    public Joueur getJoueur(int i) {
        try { return this.joueurs.get(i); }
        catch (ArrayIndexOutOfBoundsException aioobe){ return null; }
    }

    public List<Piece> getPieces() { return this.pieces; }

    public String getTypePiece(int i) {
        try { return this.types_pieces.get(i); }
        catch (ArrayIndexOutOfBoundsException aioobe){ return null; }
    }

    public Plateau getPlateau() { return this.plateau; }


}
