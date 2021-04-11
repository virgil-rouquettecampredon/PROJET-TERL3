package org.example.model;

import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;
import org.example.model.Regles.Regle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Classe de transition entre l'interface et les variantes en mémoire
public class VarianteManager {
    private VarianteBuilder current;
    private List<Variante> variantes;

    public VarianteManager() {
        variantes = new ArrayList<>();
        current = new VarianteBuilder();
        variantes.add(createVarianteClassique());
    }

    private Variante createVarianteClassique() {
        Plateau plateau = new Plateau(8, 8);

        Joueur j1 = new Joueur("Blanc", 0);
        Joueur j2 = new Joueur("Noir", 1);

        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(j1);
        joueurs.add(j2);

        ArrayList<Regle> regles = new ArrayList<>();


        Piece pawnP1 = new Piece("pawn", "file:src/main/resources/org/example/images/pawn.png", j1);
        pawnP1.getPosDeplacements().add(new PositionDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.DEPLACER));
        pawnP1.setEstPromouvable(true);
        j1.getTypePawnList().add(pawnP1);
        Piece pawnP2 = new Piece("pawn", "file:src/main/resources/org/example/images/pawnBlack.png", j2);
        pawnP2.getPosDeplacements().add(new PositionDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.DEPLACER));
        pawnP2.setEstPromouvable(true);
        j2.getTypePawnList().add(pawnP2);

        Piece kingP1 = new Piece("king", "file:src/main/resources/org/example/images/king.png", j1);
        kingP1.getVecDeplacements().add(new VecteurDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.setEstConditionDeVictoire(true);
        j1.getTypePawnList().add(kingP1);
        Piece kingP2 = new Piece("king", "file:src/main/resources/org/example/images/kingBlack.png", j2);
        kingP2.getVecDeplacements().add(new VecteurDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP2.setEstConditionDeVictoire(true);
        j2.getTypePawnList().add(kingP2);

        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(pawnP1);
        pieces.add(pawnP2);
        pieces.add(kingP1);
        pieces.add(kingP2);

        for (int i = 0; i < 8; i++) {
            Piece nPawnP1 = new Piece(pawnP1);
            plateau.getEchiquier().get(6).get(i).setPieceOnCase(nPawnP1);
            j1.getPawnList().add(nPawnP1);

            Piece nPawnP2 = new Piece(pawnP2);
            plateau.getEchiquier().get(1).get(i).setPieceOnCase(nPawnP2);
            j2.getPawnList().add(nPawnP2);
        }

        Piece nKingP1 = new Piece(kingP1);
        plateau.getEchiquier().get(7).get(4).setPieceOnCase(nKingP1);
        j1.getPawnList().add(nKingP1);

        Piece nKingP2 = new Piece(kingP2);
        plateau.getEchiquier().get(0).get(5).setPieceOnCase(nKingP2);
        j1.getPawnList().add(nKingP2);

        Variante classique = new Variante("Classique", plateau, joueurs, regles);
        return classique;
    }

    public void setCurrent(VarianteBuilder current) {
        this.current = current;
    }

    public VarianteBuilder getCurrent() {
        return current;
    }

    public List<Variante> getVariantes() {
        return variantes;
    }

    public Variante applyCurrent() {
        Variante vr = current.createVariante();
        variantes.add(vr);
        return vr;
    }

    public void saveCurrent(String path) throws IOException{
        Variante vrToSave = applyCurrent();
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(vrToSave);
        oos.close();
    }

    public Variante importFile(String path) throws IOException{
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Variante vr = (Variante) ois.readObject();
            ois.close();

            variantes.add(vr);
            return vr;
        }
        catch (IOException | ClassNotFoundException e) {
            throw new IOException();
        }
    }
}
