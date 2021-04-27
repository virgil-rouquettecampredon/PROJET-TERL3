package org.example.model;

import javafx.geometry.Pos;
import javafx.scene.Group;
import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;
import org.example.model.Regles.ElementRegle;
import org.example.model.Regles.Jeton_Interface;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.Regle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Classe de transition entre l'interface et les variantes en mémoire
public class VarianteManager {
    private VarianteBuilder current;        // La variante que l'on modifie à souhait par l'interface
    private List<Variante<Jeton>> variantes;       // Liste des variantes importées depuis le démarrage de l'application

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

        Joueur j2 = j.get(0);
        if (j.size() > 1) {
            j2 = j.get(1);
        }

        //LES PIECES
        //LES PAWN
        Piece pawnP1 = new Piece("Pion", "file:" + pawnFile.getAbsolutePath(), j.get(0));
        pawnP1.getPosDeplacements().add(new PositionDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.DEPLACER));
        pawnP1.getPosDeplacements().add(new PositionDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.PRENDRE));
        pawnP1.getPosDeplacements().add(new PositionDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.PRENDRE));
        pawnP1.setEstPromouvable(true);
        j.get(0).getTypePawnList().add(pawnP1);

        Piece pawnP2 = null;
        if (j.size() > 1) {
            pawnP2 = new Piece("Pion", "file:" + pawnBlackFile.getAbsolutePath(), j2);
            pawnP2.getPosDeplacements().add(new PositionDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.DEPLACER));
            pawnP2.getPosDeplacements().add(new PositionDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.PRENDRE));
            pawnP2.getPosDeplacements().add(new PositionDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.PRENDRE));
            pawnP2.setEstPromouvable(true);
            j2.getTypePawnList().add(pawnP2);
        }

        //LES ROIS
        Piece kingP1 = new Piece("Roi", "file:" + kingFile.getAbsolutePath(), j.get(0));
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
            kingP2 = new Piece("Roi", "file:" + kingBlackFile.getAbsolutePath(), j2);
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

        //LES TOURS
        Piece tourP1 = new Piece("Tour", "file:"+tourFile.getAbsolutePath(), j.get(0));
        tourP1.getVecDeplacements().add(new VecteurDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        tourP1.getVecDeplacements().add(new VecteurDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        tourP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        tourP1.getVecDeplacements().add(new VecteurDeDeplacement(1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        j.get(0).getTypePawnList().add(tourP1);

        Piece tourP2 = null;
        if (j.size() > 1) {
            tourP2 = new Piece("Tour", "file:" + tourBlackFile.getAbsolutePath(), j2);
            tourP2.getVecDeplacements().add(new VecteurDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            tourP2.getVecDeplacements().add(new VecteurDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            tourP2.getVecDeplacements().add(new VecteurDeDeplacement(-1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
            tourP2.getVecDeplacements().add(new VecteurDeDeplacement(1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
            j2.getTypePawnList().add(tourP2);
        }

        //LES FOUS
        Piece fouP1 = new Piece("Fou", "file:"+fouFile.getAbsolutePath(), j.get(0));
        fouP1.getVecDeplacements().add(new VecteurDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        fouP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        fouP1.getVecDeplacements().add(new VecteurDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        fouP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        j.get(0).getTypePawnList().add(fouP1);

        Piece fouP2 = null;
        if (j.size() > 1) {
            fouP2 = new Piece("Fou", "file:"+fouBlackFile.getAbsolutePath(), j2);
            fouP2.getVecDeplacements().add(new VecteurDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            fouP2.getVecDeplacements().add(new VecteurDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            fouP2.getVecDeplacements().add(new VecteurDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            fouP2.getVecDeplacements().add(new VecteurDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            j2.getTypePawnList().add(fouP2);
        }

        //LES CAVALIERS
        Piece cavalierP1 = new Piece("Cavalier", "file:"+cavalierFile.getAbsolutePath(), j.get(0));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(-1, -2, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(1, -2, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(-1, 2, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(1, 2, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(-2, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(2, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(-2, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        cavalierP1.getPosDeplacements().add(new PositionDeDeplacement(2, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        j.get(0).getTypePawnList().add(cavalierP1);

        Piece cavalierP2 = null;
        if (j.size() > 1) {
            cavalierP2 = new Piece("Cavalier", "file:"+cavalierBlackFile.getAbsolutePath(), j2);
            cavalierP2.getPosDeplacements().add(new PositionDeDeplacement(-1, -2, EquationDeDeplacement.TypeDeplacement.BOTH));
            cavalierP2.getPosDeplacements().add(new PositionDeDeplacement(1, -2, EquationDeDeplacement.TypeDeplacement.BOTH));
            cavalierP2.getPosDeplacements().add(new PositionDeDeplacement(-1, 2, EquationDeDeplacement.TypeDeplacement.BOTH));
            cavalierP2.getPosDeplacements().add(new PositionDeDeplacement(1, 2, EquationDeDeplacement.TypeDeplacement.BOTH));
            cavalierP2.getPosDeplacements().add(new PositionDeDeplacement(-2, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            cavalierP2.getPosDeplacements().add(new PositionDeDeplacement(2, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            cavalierP2.getPosDeplacements().add(new PositionDeDeplacement(-2, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            cavalierP2.getPosDeplacements().add(new PositionDeDeplacement(2, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            j2.getTypePawnList().add(cavalierP2);
        }

        //LES DAMES
        Piece dameP1 = new Piece("Dame", "file:"+dameFile.getAbsolutePath(), j.get(0));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        dameP1.getVecDeplacements().add(new VecteurDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
        j.get(0).getTypePawnList().add(dameP1);

        Piece dameP2 = null;
        if (j.size() > 1) {
            dameP2 = new Piece("Dame", "file:"+dameBlackFile.getAbsolutePath(), j2);
            dameP2.getVecDeplacements().add(new VecteurDeDeplacement(0, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            dameP2.getVecDeplacements().add(new VecteurDeDeplacement(0, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            dameP2.getVecDeplacements().add(new VecteurDeDeplacement(-1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
            dameP2.getVecDeplacements().add(new VecteurDeDeplacement(1, 0, EquationDeDeplacement.TypeDeplacement.BOTH));
            dameP2.getVecDeplacements().add(new VecteurDeDeplacement(1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            dameP2.getVecDeplacements().add(new VecteurDeDeplacement(-1, -1, EquationDeDeplacement.TypeDeplacement.BOTH));
            dameP2.getVecDeplacements().add(new VecteurDeDeplacement(1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            dameP2.getVecDeplacements().add(new VecteurDeDeplacement(-1, 1, EquationDeDeplacement.TypeDeplacement.BOTH));
            j2.getTypePawnList().add(dameP2);
        }

        //LES PLACER SUR LE PLATEAU
        for (int i = 0; i < 8; i++) {
            putPieceOnPlateau(pawnP1, plateau, j.get(0), i, 6);
            if (j.size() > 1) {
                putPieceOnPlateau(pawnP2, plateau, j2, i, 1);
            }
        }

        putPieceOnPlateau(kingP1, plateau, j.get(0), 4, 7);
        if (j.size() > 1) {
            putPieceOnPlateau(kingP2, plateau, j2, 4,0);
        }

        putPieceOnPlateau(tourP1, plateau, j.get(0), 0, 7);
        putPieceOnPlateau(tourP1, plateau, j.get(0), 7, 7);

        if (j.size() > 1) {
            putPieceOnPlateau(tourP2, plateau, j2, 0, 0);
            putPieceOnPlateau(tourP2, plateau, j2, 7, 0);
        }

        putPieceOnPlateau(fouP1, plateau, j.get(0), 2, 7);
        putPieceOnPlateau(fouP1, plateau, j.get(0), 5, 7);

        if (j.size() > 1) {
            putPieceOnPlateau(fouP2, plateau, j2, 2, 0);
            putPieceOnPlateau(fouP2, plateau, j2, 5, 0);
        }

        putPieceOnPlateau(cavalierP1, plateau, j.get(0), 1, 7);
        putPieceOnPlateau(cavalierP1, plateau, j.get(0), 6, 7);

        if (j.size() > 1) {
            putPieceOnPlateau(cavalierP2, plateau, j2, 1, 0);
            putPieceOnPlateau(cavalierP2, plateau, j2, 6, 0);
        }

        putPieceOnPlateau(dameP1, plateau, j.get(0), 3, 7);
        if (j.size() > 1) {
            putPieceOnPlateau(dameP2, plateau, j2, 3,0);
        }
    }

    private void putPieceOnPlateau(Piece piece, Plateau plateau, Joueur j, int x, int y) {
        if (plateau.getHeightY() > y && plateau.getWitdhX() > x && plateau.getEchiquier().get(y).get(x).isAccessible()) {
            Piece newPiece = new Piece(piece);
            plateau.getEchiquier().get(y).get(x).setPieceOnCase(newPiece);
            j.getPawnList().add(newPiece);
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
     * Ajoute les groupe des cases classique dans le groupe de case
     * @param listGroupCases Liste des groupe de case où mettre les groupes
     */
    public void addClassiqueGroupeCases(ArrayList<GroupCases> listGroupCases, Plateau plateau) {
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
    }

    /**
     *  Genère une variante classique
     * @return  La variante classique
     */
    public Variante<Jeton> createVarianteClassique() {
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

        ArrayList<GroupCases> listGroupCases = new ArrayList<>();
        addClassiqueGroupeCases(listGroupCases, plateau);

        return new VarianteJeton("Classique", plateau, joueurs, ordreJoueur, regles, listGroupCases);
    }

    public void setCurrent(VarianteBuilder current) {
        this.current = current;
    }

    public VarianteBuilder getCurrent() {
        return current;
    }

    public List<Variante<Jeton>> getVariantes() {
        return variantes;
    }

    /**
     * Cree la variante courrante et l'ajoute dans la liste des variante
     * @return la variante cree
     */
    public Variante<Jeton> applyCurrent() {
        Variante<Jeton> vr = current.createVariante();
        variantes.add(vr);
        return vr;
    }

    /**
     * Sauvegarde dans un fichier la variante courrante
     * @param path  Le chemin vers le fichier à creer
     * @throws IOException Si le chemin n'est pas valide
     */
    public void saveCurrent(String path) throws IOException{
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(current);
        oos.close();
    }

    /**
     * Importe une variante depuis un fichier et l'ajoute dans la liste de variante
     * @param path  le chemin vers le fichier
     * @return  La variante importée
     * @throws IOException  Si le chemin n'est pas valide
     */
    public Variante<Jeton> importFile(String path) throws IOException{
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            VarianteBuilder vb = (VarianteBuilder) ois.readObject();
            ois.close();
            return applyCurrent();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new IOException(e);
        }
    }
}
