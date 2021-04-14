package org.example.model.Regles;

import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;
import org.example.model.Case;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Function;
import java.util.function.BiFunction;

public class Fonctions_Comportements {

/*------------------------------------------------------------------------------------------------
 * ---------------------------------------CONDITION-----------------------------------------------
 * -----------------------------------------------------------------------------------------------*/

    /*--------------------------------CONDITION ETAT------------------------------*/
    /** Condition : PIECE+ESTPROMU
     * @param listPieces : liste des pieces possibles
     * @return vrai si au moins une pièces dans la liste est promu ce tour*/
    public static final Function<List<Piece>,Boolean> estPromu = (listPieces) -> {
        for (Piece piece: listPieces) {
            if (piece.aEtePromu()) {
                return true;
            }
        }
        return false;
    };

    /*-----------------------------CONDITION ACTION CIBLE-------------------------*/
    /** Condition : PIECE+PREND+PIECE
     * @param pieces_a : liste des pièces attaquante possible
     * @param pieces_v : liste des pièces victime possible
     * @return vrai si au moins une pièces dans la liste des pièces attaquante à pris une pièce dans la liste des pièces victime*/
    public static final BiFunction<List<Piece>,List<Piece>,Boolean> prend_Par_Piece = (pieces_a, pieces_v) -> {
        for (Piece piece_a : pieces_a) {
            if (pieces_v.contains(piece_a.getPieceMange())) {
                return true;
            }
        }
        return false;
    };

    /** Condition : PIECE+PREND+CASE
     * @param listCases des pièces attaquante possible
     * @param listPiecesVictime : liste des pièces victime possible
     * @return vrai si au moins une pièces dans la liste des pièces attaquante à pris une pièce situé sur la case dans la liste des case*/
    public static final BiFunction<List<Piece>, List<Case>, Boolean> prend_Par_Case = (listPiecesAttaquante, listCases) -> {
        ArrayList<Piece> listPieceSurCases = new ArrayList<>();
        for (Case c : listCases) {
            if (c.getPieceOnCase() != null) {
                listPieceSurCases.add(c.getPieceOnCase());
            }
        }
        return prend_Par_Piece.apply(listPiecesAttaquante, listPieceSurCases);
    };

    /** Condition : PIECE+ESTSUR+CASE
     * @param pieces : liste des pièces possible
     * @param cases : liste des cases possible
     * @return vrai si au moins une pièce dans la liste pieces est sur une case inclu dans la liste cases*/
    public static final BiFunction<List<Piece>, List<Case>, Boolean> est_Sur = (pieces, cases) -> {
        for (Case casePlateau: cases) {
            for (Piece piece: pieces) {
                if(piece.equals(casePlateau.getPieceOnCase())){
                    return true;
                }
            }
        }
        return false;
    };

    /** Condition : PIECE+ESTMENACE+PIECE
     * @param pieces_m : liste des pièces menacantes possible
     * @param pieces_v : liste des pièces victimes possible
     * @return vrai si au moins une pièce dans la liste des pièces menacante menace au moins une pièce dans la liste des pièces victime*/
    public static final BiFunction<List<Piece>, List<Piece>, Boolean> est_Menace = (pieces_m, pieces_v ) -> {
        for (Piece piece_m : pieces_m) {
            if (pieces_v.contains(piece_m.getPieceMenace())) {
                return true;
            }
        }
        return false;
    };

    /** Condition : PIECE+DEPLACE+CASE
     * @param pieces : liste des pièces possible
     * @param cases : liste des cases possible
     * @return vrai si au moins une pièce dans la liste des pièces se deplace sur une case dans la liste des cases*/
    public static final BiFunction<List<Piece>, List<Case>, Boolean> se_deplace = (pieces, cases) -> {
            for (Piece p : pieces) {
                if (cases.contains(p.getCaseDeplace())) {
                    return true;
                }
            }
        return false;
    };


    /*-----------------------------CONDITION COMPARAISON-------------------------*/
    /** Condition : PIECE+DEPINF+INTEGER
     * @param pieces : liste des pièces possible
     * @param valeur : valeur à comparer
     * @return vrai si au moins une pièce a un nombre de deplacement inferieur valeur*/
    public static final BiFunction<List<Piece>, Integer, Boolean> deplacement_inferieur_a = (pieces, valeur) -> {
        for (Piece piece: pieces) {
            if(piece.getNbMovement() < valeur) {
                return true;
            }
        }
        return false;
    };

    /** Condition : PIECE+DEPEGAL+INTEGER
     * @param pieces : liste des pièces possible
     * @param valeur : valeur à comparer
     * @return vrai si au moins une pièce a un nombre de deplacement egal valeur*/
    public static final BiFunction<List<Piece>, Integer, Boolean> deplacement_egal_a = (pieces, valeur) -> {
        for (Piece piece: pieces) {
            if(piece.getNbMovement() == valeur) {
                return true;
            }
        }
        return false;
    };

    /** Condition : PIECE+DEPSUP+INTEGER
     * @param pieces : liste des pièces possible
     * @param valeur : valeur à comparer
     * @return vrai si au moins une pièce a un nombre de deplacement superieur valeur*/
    public static final BiFunction<List<Piece>, Integer, Boolean> deplacement_superieur_a = (pieces, valeur) -> {
        for (Piece piece: pieces) {
            if(piece.getNbMovement() > valeur) {
                return true;
            }
        }
            return false;
    };



/* --------------------------------------------------------------------------------------------------
 * -------------------------------------------CONSEQUENCE--------------------------------------------
 * --------------------------------------------------------------------------------------------------*/

    /** Consequence : JOUEUR + GAGNE
     * @param ordonnanceur : ordonnanceur de jeu
     * @param joueur : joueur gagnant
     * @fn désigne j comme gagnant et termine la partie*/
    public static final BiFunction<OrdonnanceurDeJeu, List<Joueur>, Void> victoire = (ordonnanceur, joueurs) -> { return null; };

    /** Consequence : JOUEUR + PERD
     * @param ordonnanceur : ordonnanceur de jeu
     * @param joueur : joeur perdant
     * @fn désigne j comme perdant et termine la partie*/
    public static final BiFunction<OrdonnanceurDeJeu, List<Joueur>, Void> defaite = (ordonnanceur, joueurs) -> { return null; };

    /** Consequence : JOUEUR + PAT
     * @param ordonnanceur : ordonnanceur de jeu
     * @fn désigne pat et termine la partie*/
    public static final Function<OrdonnanceurDeJeu, Void> pat = (ordonnanceur) -> { return null; };

    /** Consequence : PIECE + PREND + PIECE
     * @param pieces_a : liste des pieces attaquantes possible
     * @param pieces_v : liste des pieces victimes possible
     * @fn le joueur choisi la pièce attaquante parmis la liste pièces_a et choisi la pièce que celle-ci va prendre
     * parmis la liste des pièces victime dans pieces_v  */
    public static final BiFunction<List<Piece>, List<Piece>, Void> prendre_pîece = (pieces_a, pieces_v) -> { return null; };

    /** Consequence : PIECE + PREND + CASE
     * @param pieces : liste des pieces attaquantes possible
     * @param cases : liste des cases victimes possible
     * @fn le joueur choisi la pièce attaquante parmis la liste pièces_a et choisi la pièce que celle-ci va prendre
     * parmis la liste des pièces se trouvant sur les cases dans cases */
    public static final BiFunction<List<Piece>, List<Case>, Void> prendre_par_case = (pieces, cases) -> { return null; };

    /** Consequence : PIECE + SE_PROMET_EN + PIECE
     * @param pieces : liste des pieces promouvable
     * @param cases : liste des cases sur lequel les pièces peuvent être promu
     * @fn si au moins une pièce dans la liste des pices est sur une case dans la liste des cases,
     * le joueur choisi parmis ces pièces à promouvoir*/
    public static final BiFunction<List<Piece>, List<Case>, Void> promouvoir = (pieces, cases) -> { return null; };

    /** Consequence : JOUEUR + PLACE + PIECE
     * @param pieces : liste des pieces plaçable
     * @param cases : liste des cases sur lequel les pièces peuvent être placé
     * @fn si au moins une pièce dans la liste des pièces est dans la defausse,
     * le joueur choisi parmis une de ces pièces pour la placer sur le terrain*/
    public static final BiFunction<List<Piece>, List<Case>, Void> placer = (pieces, cases) -> { return null; };



}
