import org.example.model.*;
import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

//Attention creation de fichier dans /AppData/Local/Temp
public class SauvegardeVarianteTest {
    public String filePath;
    public VarianteManager vm;

    @BeforeEach
    public void initializeTest() {
        filePath = System.getProperty("user.home") + "/AppData/Local/Temp/" + "Test.cbvr";
        vm = new VarianteManager();
    }

    @Test
    public void testEnregistrerVariante() throws IOException {
        VarianteBuilder vr = new VarianteBuilder();
        vr.setName("TestVariante");

        vm.setCurrent(vr);
        vm.saveCurrent(filePath);

        Assertions.assertTrue(new File(filePath).isFile());
    }

    @Test
    public void testImporterVariante() throws IOException{
        VarianteBuilder vrSave = new VarianteBuilder();
        vrSave.setName("TestVariante");

        vm.setCurrent(vrSave);
        vm.saveCurrent(filePath);

        Variante vr = vm.importFile(filePath);

        VarianteBuilder vrToCompare = new VarianteBuilder();
        vrToCompare.setName("TestVariante");

        Assertions.assertEquals(vr, vrToCompare.createVariante());
    }

    @Test
    public void test_importer_variante_piece_platea_meme_list_de_piece_du_joueur() throws IOException{
        VarianteBuilder vrSave = new VarianteBuilder();
        vm.setCurrent(vrSave);

        vm.addClassiquePlayers();

        addUniquePawn(vrSave.getJoueurs(), vrSave.getPlateau());

        vm.saveCurrent(filePath);

        Variante vr = vm.importFile(filePath);

        for (ArrayList<Case> ligne : vr.getPlateau().getEchiquier()) {
            for (Case c : ligne) {
                Piece piece = c.getPieceOnCase();
                if (piece != null) {
                    Assertions.assertSame(piece, piece.getJoueur().getPawnList().stream().filter(p -> p.equals(piece)).findAny().get());
                }
            }
        }
    }

    private void addUniquePawn(ArrayList<Joueur> j, Plateau plateau) {
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
        putPieceOnPlateau(pawnP1, plateau, j.get(0), 0, 6);
        if (j.size() > 1) {
            putPieceOnPlateau(pawnP2, plateau, j2, 0, 1);
        }

        putPieceOnPlateau(kingP1, plateau, j.get(0), 4, 7);
        if (j.size() > 1) {
            putPieceOnPlateau(kingP2, plateau, j2, 4,0);
        }

        putPieceOnPlateau(tourP1, plateau, j.get(0), 0, 7);

        if (j.size() > 1) {
            putPieceOnPlateau(tourP2, plateau, j2, 0, 0);
        }

        putPieceOnPlateau(fouP1, plateau, j.get(0), 2, 7);

        if (j.size() > 1) {
            putPieceOnPlateau(fouP2, plateau, j2, 2, 0);
        }

        putPieceOnPlateau(cavalierP1, plateau, j.get(0), 1, 7);

        if (j.size() > 1) {
            putPieceOnPlateau(cavalierP2, plateau, j2, 1, 0);
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
}
