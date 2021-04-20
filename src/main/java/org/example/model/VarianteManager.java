package org.example.model;

import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;
import org.example.model.Regles.ElementRegle;
import org.example.model.Regles.Jeton_Interface;
import org.example.model.Regles.Regle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Classe de transition entre l'interface et les variantes en mémoire
public class VarianteManager {
    private VarianteBuilder current;        // La variante que l'on modifie à souhait par l'interface
    private List<Variante> variantes;       // Liste des variantes importées depuis le démarrage de l'application

    public VarianteManager() {
        variantes = new ArrayList<>();
        current = new VarianteBuilder();
        variantes.add(createVarianteClassique());
    }

    /**
     *  Ajoute les joueurs de la varainte classique dans la variante courrante
     */
    public void addClassiquePlayers() {
        Joueur j1 = new Joueur("Blanc", 0);
        Joueur j2 = new Joueur("Noir", 1);
        current.getJoueurs().add(j1);
        current.getJoueurs().add(j2);
    }

    /**
     *  Ajoute les règles classiques dans la liste des regles
     * @param regles    Liste des regles où mettre les regles
     * @param joueurs   Liste des joueurs
     */
    public void addClassiqueRules(ArrayList<RegleInterface> regles, ArrayList<Joueur> joueurs) {
        RegleInterface regle1 = new RegleInterface();
        String j1 = joueurs.get(0).getName();
        regle1.getRegle().add(new ElementRegle(Jeton_Interface.JOUEUR, j1, "J"+0));
        regle1.getRegle().add(new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT, "TIMER", "timer"));
        regle1.getRegle().add(new ElementRegle(Jeton_Interface.COMPARATEUR, "=", "="));
        regle1.getRegle().add(new ElementRegle(Jeton_Interface.NOMBRE, "0", "0"));
        regle1.getRegle().add(new ElementRegle(Jeton_Interface.ALORS, "ALORS", "alors"));

        regle1.getRegle().add(new ElementRegle(Jeton_Interface.JOUEUR, j1, "J"+0));
        regle1.getRegle().add(new ElementRegle(Jeton_Interface.CONSEQUENCE_TERMINALE, "PERT", "pert"));
        regle1.getRegle().add(new ElementRegle(Jeton_Interface.FIN, "FIN", "fin"));

        regles.add(regle1);
    }

    /**
     * Ajoute les pieces classiques dans les joueurs et sur le plateau
     * @param j Liste des joueurs où mettre les pieces
     * @param plateau   Le Plateau où mettre les pieces
     */
    public void addClassiquePawn(ArrayList<Joueur> j, Plateau plateau) {
        //mettre les pieces par defaut
        File pawnFile = new File("src/main/resources/org/example/images/pawn.png");
        File pawnBlackFile = new File("src/main/resources/org/example/images/pawnBlack.png");
        File kingFile = new File("src/main/resources/org/example/images/king.png");
        File kingBlackFile = new File("src/main/resources/org/example/images/kingBlack.png");
        Joueur j2 = j.get(0);
        if (j.size() > 1) {
            j2 = j.get(1);
        }
        Piece pawnP1 = new Piece("Paw", "file:" + pawnFile.getAbsolutePath(), j.get(0));
        pawnP1.getPosDeplacements().add(new PositionDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.DEPLACER));
        pawnP1.setEstPromouvable(true);
        j.get(0).getTypePawnList().add(pawnP1);

        Piece pawnP2 = null;
        if (j.size() > 1) {
            pawnP2 = new Piece("Paw", "file:" + pawnBlackFile.getAbsolutePath(), j2);
            pawnP2.getPosDeplacements().add(new PositionDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.DEPLACER));
            pawnP2.setEstPromouvable(true);
            j2.getTypePawnList().add(pawnP2);
        }

        Piece kingP1 = new Piece("King", "file:" + kingFile.getAbsolutePath(), j.get(0));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(-1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.getPosDeplacements().add(new PositionDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        kingP1.setEstConditionDeVictoire(true);
        j.get(0).getTypePawnList().add(kingP1);

        Piece kingP2 = null;
        if (j.size() > 1) {
            kingP2 = new Piece("King", "file:" + kingBlackFile.getAbsolutePath(), j2);
            kingP2.getPosDeplacements().add(new PositionDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            kingP2.getPosDeplacements().add(new PositionDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            kingP2.getPosDeplacements().add(new PositionDeDeplacement(1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
            kingP2.getPosDeplacements().add(new PositionDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            kingP2.getPosDeplacements().add(new PositionDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            kingP2.getPosDeplacements().add(new PositionDeDeplacement(-1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
            kingP2.getPosDeplacements().add(new PositionDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            kingP2.getPosDeplacements().add(new PositionDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            kingP2.setEstConditionDeVictoire(true);
            j2.getTypePawnList().add(kingP2);
        }


        for (int i = 0; i < 8; i++) {
            Piece nPawnP1 = new Piece(pawnP1);
            plateau.getEchiquier().get(6).get(i).setPieceOnCase(nPawnP1);
            j.get(0).getPawnList().add(nPawnP1);

            if (j.size() > 1) {
                Piece nPawnP2 = new Piece(pawnP2);
                plateau.getEchiquier().get(1).get(i).setPieceOnCase(nPawnP2);
                j2.getPawnList().add(nPawnP2);
            }
        }

        Piece nKingP1 = new Piece(kingP1);
        plateau.getEchiquier().get(7).get(3).setPieceOnCase(nKingP1);
        j.get(0).getPawnList().add(nKingP1);

        if (j.size() > 1) {
            Piece nKingP2 = new Piece(kingP2);
            plateau.getEchiquier().get(0).get(4).setPieceOnCase(nKingP2);
            j.get(0).getPawnList().add(nKingP2);
        }
    }

    /**
     * Ajoute l'ordre des joueur classique dans la variante courrante
     */
    public void addClassiqueOrderPlayer() {
        current.getOrdreJoueurs().clear();
        for (int i = 0; i < current.getJoueurs().size(); i++) {
            current.getOrdreJoueurs().add(current.getJoueurs().get(i));
        }
    }

    /**
     *  Genère une variante classique
     * @return  La variante classique
     */
    private Variante createVarianteClassique() {
        Plateau plateau = new Plateau(8, 8);

        Joueur j1 = new Joueur("Blanc", 0);
        Joueur j2 = new Joueur("Noir", 1);

        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(j1);
        joueurs.add(j2);

        ArrayList<Joueur> ordreJoueur = new ArrayList<>();
        ordreJoueur.add(j1);
        ordreJoueur.add(j2);

        addClassiquePawn(joueurs, plateau);

        ArrayList<RegleInterface> regles = new ArrayList<>();
        addClassiqueRules(regles, joueurs);

        Variante classique = new Variante("Classique", plateau, joueurs, ordreJoueur, regles, new ArrayList<>());
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
