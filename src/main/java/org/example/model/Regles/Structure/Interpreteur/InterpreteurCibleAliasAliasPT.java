package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurCibleAliasAliasPT extends InterpreteurCible<Piece> {
    /*Classe Interpreteur permettant de modéliser un Interpreteur Cible Piece Token ayant un alias sur ses pieces et ses joueurs*/
    private Interpreteur_Alias_Cible<Piece> interpreteurPiecesAlias;        //Interpreteur représentant l'alias sur la Piece
    private Interpreteur_Alias_Cible<Joueur> interpreteurJoueursAlias;      //Interpreteur représentant l'alias sur le Joueur

    public InterpreteurCibleAliasAliasPT(Interpreteur_Alias_Cible<Piece> interpreteurPiecesAlias, Interpreteur_Alias_Cible<Joueur> interpreteurJoueursAlias) {
        this.interpreteurPiecesAlias = interpreteurPiecesAlias;
        this.interpreteurJoueursAlias = interpreteurJoueursAlias;
    }

    @Override
    public List<Piece> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        List<Piece> piecesARetourner = new ArrayList<>();
        for (Piece p : interpreteurPiecesAlias.recupererTout(ord)) {
            for (Joueur j : interpreteurJoueursAlias.recupererTout(ord)) {
                if (p.getJoueur().equals(j)) {
                    piecesARetourner.add(p);
                }
            }
        }
        return piecesARetourner;
    }

    @Override
    public String toString(){
        return "[InterpreteurCibleAliasAliasPT: interpret Piece:" + this.interpreteurPiecesAlias
                + "interpret Joueur: " + this.interpreteurJoueursAlias + "]";
    }
}
