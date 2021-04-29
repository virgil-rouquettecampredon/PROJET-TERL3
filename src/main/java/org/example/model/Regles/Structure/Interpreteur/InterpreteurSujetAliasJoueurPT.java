package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.Structure.Alias;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurSujetAliasJoueurPT extends InterpreteurSujet<Piece>{
    /*Classe Interpreteur permettant de modéliser un Interpreteur Sujet Piece Token ayant un alias sur ses pieces*/
    private Interpreteur_Alias_Sujet<Piece> interpreteurPiecesAlias;    //Interpreteur représentant l'alias sur la Piece
    private String joueur;                                              //Chaine de caractère représentant le Joueur de PieceToken

    public InterpreteurSujetAliasJoueurPT(Interpreteur_Alias_Sujet<Piece> interpreteurPiecesAlias, String joueur){
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
        return "[InterpreteurSujetAliasJoueurPT: interpret piece:" + this.interpreteurPiecesAlias
                + " String joueur:" + this.joueur + "]";
    }
}
