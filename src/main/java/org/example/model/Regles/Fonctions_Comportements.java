package org.example.model.Regles;

import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Function;
import java.util.function.BiFunction;

public class Fonctions_Comportements {
    
    public static List<SujetDeRegle> sujetDeLaConditionVrai = new ArrayList<>();            //Liste des SujetDeRegle rendant une Condition vrai (Alias)
    public static List<CibleDeRegle> cibleDeLaConditionVrai = new ArrayList<>();            //Liste des CibleDeRegle rendant une Condition vrai (Alias)

/*------------------------------------------------------------------------------------------------
 * ---------------------------------------CONDITION-----------------------------------------------
 * -----------------------------------------------------------------------------------------------*/

    /*--------------------------------CONDITION ETAT------------------------------*/
    /** Condition : PIECE+ESTPROMU
     * @param listPieces : liste des pieces possibles
     * @return vrai si au moins une pièces dans la liste est promu ce tour*/
    public static final Function<List<Piece>,Boolean> estPromu = (listPieces) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        boolean valeurDeRetour = false;
        for (Piece piece: listPieces) {
            if (piece.aEtePromu()) {
                sujetDeLaConditionVrai.add(piece);
                valeurDeRetour = true;
            }
        }
        return valeurDeRetour;
    };

    /*-----------------------------CONDITION ACTION CIBLE-------------------------*/
    /** Condition : PIECE+PREND+PIECE
     * @param pieces_a : liste des pièces attaquante possible
     * @param pieces_v : liste des pièces victime possible
     * @return vrai si au moins une pièces dans la liste des pièces attaquante à pris une pièce dans la liste des pièces victime*/
    public static final BiFunction<List<Piece>,List<Piece>,Boolean> prend_Par_Piece = (pieces_a, pieces_v) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        boolean valeurDeRetour = false;
        for (Piece piece_a : pieces_a) {
            for (Piece piece_v: pieces_v){
                if (piece_v.equals(piece_a.getPieceMange())) {
                    sujetDeLaConditionVrai.add(piece_a);
                    cibleDeLaConditionVrai.add(piece_v);
                    valeurDeRetour = true;
                }
            }
        }
        return valeurDeRetour;
    };

    /** Condition : PIECE+PREND+CASE
     * @param listCases des pièces attaquante possible
     * @param listPiecesVictime : liste des pièces victime possible
     * @return vrai si au moins une pièces dans la liste des pièces attaquante à pris une pièce situé sur la case dans la liste des case*/
    public static final BiFunction<List<Piece>, List<GroupCases>, Boolean> prend_Par_Case = (listPiecesAttaquantes, listGroupCases) -> {
        sujetDeLaConditionVrai.clear(); //piece
        cibleDeLaConditionVrai.clear(); //case

        for (GroupCases g: listGroupCases) {
            ArrayList<Case> listCases_return = new ArrayList<>();
            for (Case c : g.getCasesAbsolue()) {
                //if (c.getPieceOnCase() != null && c.getPieceOnCase().getPieceMange() != null && listPiecesAttaquantes.contains(c.getPieceOnCase())) { return true; }
                for (Piece p: listPiecesAttaquantes) {

                }
            }
        }

        return false;
    };

    /** Condition : PIECE+ESTSUR+CASE
     * @param pieces : liste des pièces possible
     * @param cases : liste des cases possible
     * @return vrai si au moins une pièce dans la liste pieces est sur une case inclu dans la liste cases*/
    public static final BiFunction<List<Piece>, List<GroupCases>, Boolean> est_Sur = (pieces, groupescases) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        GroupCases groupe = new GroupCases("groupeAliasEstSur",null);
        boolean valeurDeRetour = false;
        Plateau plateau = null;

        for (GroupCases g: groupescases) {
            for (Piece p: pieces) {
                for (Case c : g.getCasesAbsolue()) {
                    if (p.equals(c.getPieceOnCase())) {
                        plateau = g.getPlateau();
                        sujetDeLaConditionVrai.add(p);
                        groupe.addCasesAbsolue(c);
                        valeurDeRetour = true;
                    }
                }
            }
        }
        if(valeurDeRetour){
            groupe.setPlateau(plateau);
            cibleDeLaConditionVrai.add(groupe);
        }
        return valeurDeRetour;
    };

    /** Condition : PIECE+ESTMENACE+PIECE
     * @param pieces_m : liste des pièces menacantes possible
     * @param pieces_v : liste des pièces victimes possible
     * @return vrai si au moins une pièce dans la liste des pièces menacante menace au moins une pièce dans la liste des pièces victime*/
    public static final BiFunction<List<Piece>, List<Piece>, Boolean> est_Menace = (pieces_m, pieces_v ) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        boolean valeurDeRetour = false;

        for (Piece piece_m : pieces_m) {
            for (Piece piece_v: pieces_v){
                if (piece_m.equals(piece_v.getPieceMenace())) {
                    sujetDeLaConditionVrai.add(piece_m);
                    cibleDeLaConditionVrai.add(piece_m);
                    valeurDeRetour = true;
                }
            }
        }
        return valeurDeRetour;
    };

    /** Condition : PIECE+DEPLACE+CASE
     * @param pieces : liste des pièces possible
     * @param cases : liste des cases possible
     * @return vrai si au moins une pièce dans la liste des pièces se deplace sur une case dans la liste des cases*/
    public static final BiFunction<List<Piece>, List<GroupCases>, Boolean> se_deplace = (pieces, groupcases) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        GroupCases groupe = new GroupCases("groupeAliasSeDeplace",null);
        boolean valeurDeRetour = false;
        Plateau plateau = null;

        for (GroupCases g: groupcases){
            for (Piece p: pieces) {
                for (Case c : g.getCasesAbsolue()) {
                    if (p.equals(c.getPieceOnCase()) && p.getDeplaceCeTour()){
                        plateau = g.getPlateau();
                        sujetDeLaConditionVrai.add(p);
                        groupe.addCasesAbsolue(c);
                        valeurDeRetour = true;
                    }
                }
            }
        }
        if(valeurDeRetour){
            groupe.setPlateau(plateau);
            cibleDeLaConditionVrai.add(groupe);
        }
        return valeurDeRetour;
    };

    /** Condition : PIECE+ESTPLACE+CASE
     * @param pieces : liste des pièces possible
     * @param cases : liste des cases possible
     * @return vrai si au moins une pièce dans la liste des pièces est placé sur une case dans la liste des cases ce tour-ci*/
    public static final BiFunction<List<Piece>, List<GroupCases>, Boolean> est_place = (pieces, groupcases) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        GroupCases groupe = new GroupCases("groupeAliasSeDeplace",null);
        boolean valeurDeRetour = false;
        Plateau plateau = null;

        for (GroupCases g: groupcases){
            for (Piece p: pieces) {
                for (Case c: g.getCasesAbsolue()) {
                    if (c.getPieceOnCase().equals(p)){
                        plateau = g.getPlateau();
                        sujetDeLaConditionVrai.add(p);
                        groupe.addCasesAbsolue(c);
                        valeurDeRetour = true;
                    }
                }
            }
        }
        if(valeurDeRetour){
            groupe.setPlateau(plateau);
            cibleDeLaConditionVrai.add(groupe);
        }
        return valeurDeRetour;
    };


    /*-----------------------------CONDITION COMPARAISON-------------------------*/

        /*-----DEPLACEMENT-----*/
    /** Condition : PIECE+DEPINF+INTEGER
     * @param pieces : liste des pièces possible
     * @param valeur : valeur à comparer sous forme de liste IntegerRegle à un element
     * @return vrai si au moins une pièce a un nombre de deplacement inferieur valeur*/
    public static final BiFunction<List<Piece>, List<IntegerRegle>, Boolean> deplacement_inferieur_a = (pieces, lvaleur) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        boolean valeurDeRetour = false;
        if (lvaleur != null) {
            for (Piece piece : pieces) {
                if (piece.getNbMovement() < lvaleur.get(0).getVal()) {
                    sujetDeLaConditionVrai.add(piece);
                    valeurDeRetour = true;
                }
            }
        }
        return valeurDeRetour;
    };

    /** Condition : PIECE+DEPEGAL+INTEGER
     * @param pieces : liste des pièces possible
     * @param valeur : valeur à comparer sous forme de liste IntegerRegle à un element
     * @return vrai si au moins une pièce a un nombre de deplacement egal valeur*/
    public static final BiFunction<List<Piece>, List<IntegerRegle>, Boolean> deplacement_egal_a = (pieces, lvaleur) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        boolean valeurDeRetour = false;
        if (lvaleur != null) {
            for (Piece piece : pieces) {
                if (piece.getNbMovement() == lvaleur.get(0).getVal()) {
                    sujetDeLaConditionVrai.add(piece);
                    valeurDeRetour = true;
                }
            }
        }
        return valeurDeRetour;
    };

    /** Condition : PIECE+DEPSUP+INTEGER
     * @param pieces : liste des pièces possible
     * @param lvaleur : valeur à comparer sous forme de liste IntegerRegle à un element
     * @return vrai si au moins une pièce a un nombre de deplacement superieur valeur*/
    public static final BiFunction<List<Piece>, List<IntegerRegle>, Boolean> deplacement_superieur_a = (pieces, lvaleur) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        boolean valeurDeRetour = false;
        if (lvaleur != null) {
            for (Piece piece : pieces) {
                if (piece.getNbMovement() > lvaleur.get(0).getVal()) {
                    sujetDeLaConditionVrai.add(piece);
                    valeurDeRetour = true;
                }
            }
        }
        return valeurDeRetour;
    };


    /*-----TIMER-----*/

    /** Condition : JOUEUR+DEPSUP+INTEGER
     * @param joueurs : liste des joueurs possible
     * @param lvaleur : valeur à comparer sous forme de liste IntegerRegle à un element
     * @return vrai si au moins un joueur a un timer inférieur valeur*/
    public static final BiFunction<List<Joueur>, List<IntegerRegle>, Boolean> timer_inferieur_a = (joueurs, lvaleur) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        boolean valeurDeRetour = false;
        if (lvaleur != null) {
            for (Joueur joueur : joueurs) {
                int secondeTotal = 60 * joueur.getMinute() + joueur.getSeconde();
                if (secondeTotal < lvaleur.get(0).getVal()) { //TODO VOIR AVEC HUGO
                    sujetDeLaConditionVrai.add(joueur);
                    valeurDeRetour = true;
                }
            }
        }
        return valeurDeRetour;
    };

    /** Condition : JOUEUR+DEPSUP+INTEGER
     * @param joueurs : liste des joueurs possible
     * @param lvaleur : valeur à comparer sous forme de liste IntegerRegle à un element
     * @return vrai si au moins un joueur a un timer égal valeur*/
    public static final BiFunction<List<Joueur>, List<IntegerRegle>, Boolean> timer_egal_a = (joueurs, lvaleur) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        boolean valeurDeRetour = false;
        if (lvaleur != null) {
            for (Joueur joueur : joueurs) {
                int secondeTotal = 60 * joueur.getMinute() + joueur.getSeconde();
                if (secondeTotal == lvaleur.get(0).getVal()) {
                    sujetDeLaConditionVrai.add(joueur);
                    valeurDeRetour = true;
                }
            }
        }
        return valeurDeRetour;
    };

    /** Condition : JOUEUR+DEPSUP+INTEGER
     * @param joueurs : liste des joueurs possible
     * @param lvaleur : valeur à comparer sous forme de liste IntegerRegle à un element
     * @return vrai si au moins un joueur a un timer supérieur valeur*/
    public static final BiFunction<List<Joueur>, List<IntegerRegle>, Boolean> timer_superieur_a = (joueurs, lvaleur) -> {
        sujetDeLaConditionVrai.clear();
        cibleDeLaConditionVrai.clear();

        boolean valeurDeRetour = false;
        if (lvaleur != null) {
            for (Joueur joueur : joueurs) {
                int secondeTotal = 60 * joueur.getMinute() + joueur.getSeconde();
                if (secondeTotal > lvaleur.get(0).getVal()) {
                    sujetDeLaConditionVrai.add(joueur);
                    valeurDeRetour = true;
                }
            }
        }
        return valeurDeRetour;
    };
/* --------------------------------------------------------------------------------------------------
 * -------------------------------------------CONSEQUENCE--------------------------------------------
 * --------------------------------------------------------------------------------------------------*/

    /** Consequence : JOUEUR + GAGNE
     * @param ordonnanceur : ordonnanceur de jeu
     * @param joueur : joueur gagnant
     * @fn désigne j comme gagnant et termine la partie*/
    public static final Function<List<Joueur>, Void> victoire = (joueurs) -> {
        System.out.println("VICTOIRE EXECUTE");
        for (Joueur j : joueurs) {
            j.setAGagne(true);
        }
        return null;
    };

    /** Consequence : JOUEUR + PERD
     * @param ordonnanceur : ordonnanceur de jeu
     * @param joueur : joeur perdant
     * @fn désigne j comme perdant et termine la partie*/
    public static final Function<List<Joueur>, Void> defaite = (joueurs) -> {
        for (Joueur j: joueurs) {
            j.setAPerdu(true);
        }
        return null;
    };

    /** Consequence : JOUEUR + PAT
     * @param ordonnanceur : ordonnanceur de jeu
     * @fn désigne pat et termine la partie*/
    public static final Function<List<Joueur>, Void> pat = (joueurs) -> {
        for (Joueur j: joueurs) {
            j.setAPat(true);
        }
        return null;
    };

    /** Consequence : PIECE + PREND + PIECE
     * @param pieces_a : liste des pieces attaquantes possible
     * @param pieces_v : liste des pieces victimes possible
     * @fn le joueur choisi la pièce attaquante parmis la liste pièces_a et choisi la pièce que celle-ci va prendre
     * parmis la liste des pièces victime dans pieces_v  */
    public static final BiFunction<List<Piece>, List<Piece>, Void> prendre_piece = (pieces_a, pieces_v) -> { return null; };

    /** Consequence : PIECE + PREND + CASE
     * @param pieces : liste des pieces attaquantes possible
     * @param cases : liste des cases victimes possible
     * @fn le joueur choisi la pièce attaquante parmis la liste pièces_a et choisi la pièce que celle-ci va prendre
     * parmis la liste des pièces se trouvant sur les cases dans cases */
    public static final BiFunction<List<Piece>, List<GroupCases>, Void> prendre_par_case = (pieces, cases) -> { return null; };

    /** Consequence : PIECE + SE_PROMET_EN + PIECE
     * @param pieces : liste des pieces promouvable
     * @param cases : liste des cases sur lequel les pièces peuvent être promu
     * @fn si au moins une pièce dans la liste des pieces est sur une case dans la liste des cases,
     * le joueur choisi parmis ces pièces à promouvoir*/
    public static final BiFunction<List<Piece>, List<GroupCases>, Void> promouvoir = (pieces, cases) -> {
        for (GroupCases gc : cases) {
            for (Case c : gc.getCasesAbsolue()) {
                if (c.getPieceOnCase() != null && pieces.contains(c.getPieceOnCase())) {
                    c.getPieceOnCase().setEstApromouvoir(true);
                }
            }
        }
        return null;
    };

    /** Consequence : JOUEUR + PLACE + PIECE
     * @param pieces : liste des pieces plaçable
     * @param cases : liste des cases sur lequel les pièces peuvent être placé
     * @fn si au moins une pièce dans la liste des pièces est dans la defausse,
     * le joueur choisi parmis une de ces pièces pour la placer sur le terrain*/
    public static final BiFunction<List<Piece>, List<GroupCases>, Void> placer = (pieces, cases) -> { return null; };

    /** Consequence : PIECE + DEPLACE + CASE
     * @param pieces : liste des pieces plaçable
     * @param groupcases : liste des cases sur lequel les pièces peuvent être placé
     * @fn Le joueur effectue un choix de déplacement selon les cases proposé par groupcases pour chacune des pièces.
     * Le groupe de case peut comporter des cases absolu du plateau comme des cases relatives qui seront lu en fonction de la position de la pièce*/
    public static final BiFunction<List<Piece>, List<GroupCases>, Void> deplacer = (pieces, groupcases) -> { return null; };//todo 2



}
