package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurSujetAliasAliasPT extends InterpreteurSujet<Piece>{
    /*Classe Interpreteur permettant de modéliser un Interpreteur Sujet Piece Token ayant un alias sur ses pieces et ses joueurs*/
    private Interpreteur_Alias_Sujet<Piece> interpreteurPiecesAlias;        //Interpreteur représentant l'alias sur la Piece
    private Interpreteur_Alias_Sujet<Joueur> interpreteurJoueursAlias;;     //Interpreteur représentant l'alias sur le Joueur

    public InterpreteurSujetAliasAliasPT(Interpreteur_Alias_Sujet<Piece> interpreteurPiecesAlias, Interpreteur_Alias_Sujet<Joueur> interpreteurJoueursAlias){
        this.interpreteurPiecesAlias = interpreteurPiecesAlias;
        this.interpreteurJoueursAlias = interpreteurJoueursAlias;
    }

    @Override
    public List<Piece> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        System.out.println("\033[42m" + "RECUPERER TOUT INTERPRETEUR SUJET PIECE ALIAS + JOUEUR ALIAS" + "\033[0m ");

        List<Piece> piecesARetourner = new ArrayList<>();
        for (Piece p : interpreteurPiecesAlias.recupererTout(ord)){
            for (Joueur j : interpreteurJoueursAlias.recupererTout(ord)){
                if(p.getJoueur().equals(j)){
                    piecesARetourner.add(p);
                }
            }
        }
        return piecesARetourner;
    }

    @Override
    public String toString(){
        return "[InterpreteurSujetAliasAliasPT: interpret piece:" + this.interpreteurPiecesAlias
                + " interpret joueur:" + this.interpreteurJoueursAlias + "]";
    }
}
