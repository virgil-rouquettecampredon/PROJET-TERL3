package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurCiblePieceAliasPT extends InterpreteurCible<Piece>{
    /*Classe Interpreteur permettant de modéliser un Interpreteur Cible Piece Token ayant un alias sur ses joueurs*/
    private Interpreteur_Alias_Cible<Joueur> interpreteurJoueursAlias;    //Interpreteur représentant l'alias sur la Piece
    private String pieces;                                                //Chaine de caractère représentant la Piece de PieceToken

    public InterpreteurCiblePieceAliasPT(Interpreteur_Alias_Cible<Joueur> interpreteurJoueursAlias, String pieces) {
        this.interpreteurJoueursAlias = interpreteurJoueursAlias;
        this.pieces = pieces;
    }

    @Override
    public List<Piece> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
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
}
