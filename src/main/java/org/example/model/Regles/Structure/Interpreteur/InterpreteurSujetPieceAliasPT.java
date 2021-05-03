package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurSujetPieceAliasPT extends InterpreteurSujet<Piece>{
    /*Classe Interpreteur permettant de modéliser un Interpreteur Sujet Piece Token ayant un alias sur ses joueurs*/
    private Interpreteur_Alias_Sujet<Joueur> interpreteurJoueursAlias;    //Interpreteur représentant l'alias sur la Piece
    private String pieces;                                                //Chaine de caractère représentant la Piece de PieceToken

    public InterpreteurSujetPieceAliasPT(Interpreteur_Alias_Sujet<Joueur> interpreteurJoueursAlias, String pieces) {
        this.interpreteurJoueursAlias = interpreteurJoueursAlias;
        this.pieces = pieces;
    }

    @Override
    public List<Piece> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        System.out.println("\033[42m" + "RECUPERER TOUT INTERPRETEUR SUJET PIECE + JOUEUR ALIAS :" + "\033[0m "+ pieces);

        InterpreteurSujet<Piece> interpreteur = new InterpreteurSujetPiece(pieces);
        List<Piece> listePieces = interpreteur.recupererTout(ord);

        List<Piece> piecesARetourner = new ArrayList<>();
        for (Piece p : listePieces){
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
        return "[InterpreteurSujetPieceAliasPT: interpret joueur" + this.interpreteurJoueursAlias
                + "String piece:" + this.pieces + "]";
    }
}
