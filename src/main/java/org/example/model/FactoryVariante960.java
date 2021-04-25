package org.example.model;

import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;
import org.example.model.Regles.Jeton;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FactoryVariante960 {

    public static Variante<Jeton> createVariante() {
        Plateau plateau = new Plateau(8, 8);

        Joueur j1 = new Joueur("Blanc", 0);
        Joueur j2 = new Joueur("Noir", 1);

        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(j1);
        joueurs.add(j2);

        ArrayList<Joueur> ordreJoueur = new ArrayList<>();
        ordreJoueur.add(j1);
        ordreJoueur.add(j2);

        //LES FICHIERS
        File pawnFile = new File("src/main/resources/org/example/images/pawn.png");
        File pawnBlackFile = new File("src/main/resources/org/example/images/pawnBlack.png");
        File kingFile = new File("src/main/resources/org/example/images/king.png");
        File kingBlackFile = new File("src/main/resources/org/example/images/kingBlack.png");
        File tourFile = new File("src/main/resources/org/example/images/tour.png");
        File tourBlackFile = new File("src/main/resources/org/example/images/tourBlack.png");
        File fouFile = new File("src/main/resources/org/example/images/fou.png");
        File fouBlackFile = new File("src/main/resources/org/example/images/fouBlack.png");
        File cavalierFile = new File("src/main/resources/org/example/images/cavalier.png");
        File cavalierBlackFile = new File("src/main/resources/org/example/images/cavalierBlack.png");
        File dameFile = new File("src/main/resources/org/example/images/dame.png");
        File dameBlackFile = new File("src/main/resources/org/example/images/dameBlack.png");

        //LES PIECES
        //LES PAWN
        Piece pawnP1 = new Piece("Pion", "file:" + pawnFile.getAbsolutePath(), j1);
        pawnP1.getPosDeplacements().add(new PositionDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.DEPLACER));
        pawnP1.getPosDeplacements().add(new PositionDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.PRENDRE));
        pawnP1.getPosDeplacements().add(new PositionDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.PRENDRE));
        pawnP1.setEstPromouvable(true);
        j1.getTypePawnList().add(pawnP1);

        Piece pawnP2  = new Piece("Pion", "file:" + pawnBlackFile.getAbsolutePath(), j2);
        pawnP2.getPosDeplacements().add(new PositionDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.DEPLACER));
        pawnP2.getPosDeplacements().add(new PositionDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.PRENDRE));
        pawnP2.getPosDeplacements().add(new PositionDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.PRENDRE));
        pawnP2.setEstPromouvable(true);
        j2.getTypePawnList().add(pawnP2);


        //LES ROIS
        Piece kingP1 = new Piece("Roi", "file:" + kingFile.getAbsolutePath(), j1);
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(-1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.setEstConditionDeVictoire(true);
        j1.getTypePawnList().add(kingP1);

        Piece kingP2 = new Piece(kingP1);
        kingP2.setSprite("file:" + kingBlackFile.getAbsolutePath());
        kingP2.setJoueur(j2);
        j2.getTypePawnList().add(kingP2);


        //LES TOURS
        Piece tourP1 = new Piece("Tour", "file:"+tourFile.getAbsolutePath(), j1);
        tourP1.getVecDeplacements().add(new VecteurDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        tourP1.getVecDeplacements().add(new VecteurDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        tourP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        tourP1.getVecDeplacements().add(new VecteurDeDeplacement(1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        j1.getTypePawnList().add(tourP1);

        Piece tourP2 = new Piece(tourP1);
        tourP2.setSprite("file:" + tourBlackFile.getAbsolutePath());
        tourP2.setJoueur(j2);
        j2.getTypePawnList().add(tourP2);


        //LES FOUS
        Piece fouP1 = new Piece("Fou", "file:"+fouFile.getAbsolutePath(), j1);
        fouP1.getVecDeplacements().add(new VecteurDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        fouP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        fouP1.getVecDeplacements().add(new VecteurDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        fouP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        j1.getTypePawnList().add(fouP1);

        Piece fouP2 = new Piece(fouP1);
        fouP2.setSprite("file:" + fouBlackFile.getAbsolutePath());
        fouP2.setJoueur(j2);
        j2.getTypePawnList().add(fouP2);


        //LES CAVALIERS
        Piece cavalierP1 = new Piece("Cavalier", "file:"+cavalierFile.getAbsolutePath(), j1);
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(-1, -2, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(1, -2, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(-1, 2, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(1, 2, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(-2, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(2, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(-2, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(2, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        j1.getTypePawnList().add(cavalierP1);

        Piece cavalierP2 = new Piece(cavalierP1);
        cavalierP2.setSprite("file:" + cavalierBlackFile.getAbsolutePath());
        cavalierP2.setJoueur(j2);
        j2.getTypePawnList().add(cavalierP2);


        //LES DAMES
        Piece dameP1 = new Piece("Dame", "file:"+dameFile.getAbsolutePath(), j1);
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        j1.getTypePawnList().add(dameP1);

        Piece dameP2 = new Piece(dameP1);
        dameP2.setSprite("file:" + dameBlackFile.getAbsolutePath());
        dameP2.setJoueur(j2);
        j2.getTypePawnList().add(dameP2);

        //METTRE LES PIECES SUR LE PLATEAU

        ArrayList<Piece[]> piecesAleatoires = new ArrayList<>();

        Piece[] kings = {kingP1, kingP2};
        piecesAleatoires.add(kings);

        Piece[] tours = {tourP1, tourP2};
        piecesAleatoires.add(tours);
        piecesAleatoires.add(tours);

        Piece[] fous = {fouP1, fouP2};
        piecesAleatoires.add(fous);
        piecesAleatoires.add(fous);

        Piece[] cavaliers = {cavalierP1, cavalierP2};
        piecesAleatoires.add(cavaliers);
        piecesAleatoires.add(cavaliers);

        Piece[] dames = {dameP1, dameP2};
        piecesAleatoires.add(dames);

        Collections.shuffle(piecesAleatoires);

        for (int i = 0; i < 8; i++) {
            Piece newPieceBlanc = new Piece(piecesAleatoires.get(i)[0]);
            plateau.getEchiquier().get(7).get(i).setPieceOnCase(newPieceBlanc);
            j1.getPawnList().add(newPieceBlanc);

            Piece newPieceNoir = new Piece(piecesAleatoires.get(i)[1]);
            plateau.getEchiquier().get(0).get(i).setPieceOnCase(newPieceNoir);
            j2.getPawnList().add(newPieceNoir);
        }
        for (int i = 0; i < 8; i++) {
            Piece newPawnP1 = new Piece(pawnP1);
            plateau.getEchiquier().get(6).get(i).setPieceOnCase(newPawnP1);
            j1.getPawnList().add(newPawnP1);

            Piece newPawnP2 = new Piece(pawnP2);
            plateau.getEchiquier().get(1).get(i).setPieceOnCase(newPawnP2);
            j2.getPawnList().add(newPawnP2);
        }

        //nul a chier
        ArrayList<RegleInterface> regles = new ArrayList<>();
        //addClassiqueRules(regles, joueurs);

        ArrayList<GroupCases> listGroupCases = new ArrayList<>();
        GroupCases gc;

        if (plateau.getHeightY() > 0) {
            gc = new GroupCases("Promotion Blanc", plateau);
            for (int i = 0; i < plateau.getWitdhX(); i++) {
                gc.getCasesAbsolue().add(plateau.getEchiquier().get(0).get(i));
            }
            listGroupCases.add(gc);
        }

        if (plateau.getHeightY() >= 8) {
            gc = new GroupCases("Promotion Noir", plateau);
            for (int i = 0; i < plateau.getWitdhX(); i++) {
                gc.getCasesAbsolue().add(plateau.getEchiquier().get(7).get(i));
            }
            listGroupCases.add(gc);
        }

        gc = new GroupCases("Deplacement Pion Blanc", plateau);
        gc.getPositionsRelatives().add(new Position(0, -2));
        listGroupCases.add(gc);

        gc = new GroupCases("Deplacement Pion Noir", plateau);
        gc.getPositionsRelatives().add(new Position(0, 2));
        listGroupCases.add(gc);

        return new VarianteJeton("Chess960", plateau, joueurs, ordreJoueur, regles, listGroupCases);
    }
}
