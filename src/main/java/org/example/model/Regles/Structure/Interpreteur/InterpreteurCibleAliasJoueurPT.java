package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurCibleAliasJoueurPT extends InterpreteurCible<Piece>{
    /*Classe Interpreteur permettant de modéliser un Interpreteur Cible Piece Token ayant un alias sur ses pieces*/
    private Interpreteur_Alias_Cible<Piece> interpreteurPiecesAlias;    //Interpreteur représentant l'alias sur la Piece
    private String joueur;                                              //Chaine de caractère représentant le Joueur de PieceToken

    public InterpreteurCibleAliasJoueurPT(Interpreteur_Alias_Cible<Piece> interpreteurPiecesAlias, String joueur){
        this.interpreteurPiecesAlias = interpreteurPiecesAlias;
        this.joueur = joueur;
    }

    @Override
    public List<Piece> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        InterpreteurSujet<Joueur> interpreteur = new InterpreteurSujetJoueur(joueur);
        List<Joueur> listeJoueurs = interpreteur.recupererTout(ord);

        List<Piece> piecesARetourner = new ArrayList<>();
        for (Piece p : interpreteurPiecesAlias.recupererTout(ord)){
            for (Joueur j : listeJoueurs){
                if(p.getJoueur().equals(j)){
                    piecesARetourner.add(p);
                }
            }
        }
        return piecesARetourner;
    }

    @Override
    public String toString(){
        return "[InterpreteurCibleAliasJoueurPT: interpret Piece:" + this.interpreteurPiecesAlias
                + " String joueur : " + this.joueur + "]";
    }
}
