import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import org.example.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import org.example.model.Regles.*;

public class OrdonnanceurDeJeuTest {
    public OrdonnanceurDeJeu ordonnanceurDeJeu;
    public Variante<Jeton> varianteTest;

    @BeforeEach
    public void initializeTest() {
        VarianteManager vm = new VarianteManager();
        varianteTest = vm.createVarianteClassique();
        ordonnanceurDeJeu = new OrdonnanceurDeJeu(varianteTest);
    }

    private void deplacer(Position p1, int idJoueur, Position p2) throws DeplacementException{
        Case origine = varianteTest.getPlateau().getCase(p1);
        Joueur j = varianteTest.getOrdrejoueur().get(idJoueur);
        Case destination = varianteTest.getPlateau().getCase(p2);
        ordonnanceurDeJeu.deplacerPiece(origine, j, destination);
    }

    /**==================================================
     * ================| TESTS MAUVAIS |=================
     * ==================================================*/
    @Test
    public void test_deplacer_pas_bon_joueur(){
        try{
            deplacer(new Position(0, 1), 0, new Position(0, 2));
            Assertions.fail("Exception non reconnue");
        }catch (DeplacementException e){
            Assertions.assertEquals("Pas le bon joueur",e.getMessage());
        }
    }

    @Test
    public void test_deplacer_deplacement_inexistant(){
        try{
            deplacer(new Position(0, 6), 0, new Position(1, 5));
            Assertions.fail("Exception non reconnue");
        }catch (DeplacementException e){
            Assertions.assertEquals("Deplacement inexistant",e.getMessage());
        }
    }

    @Test
    public void test_deplacer_met_en_echec() {
        try {
            deplacer(new Position(4, 6), 0, new Position(4, 5));
            deplacer(new Position(4, 1), 1, new Position(4, 2));
            deplacer(new Position(3, 7), 0, new Position(6, 4));
            deplacer(new Position(4, 0), 1, new Position(4, 1));
            deplacer(new Position(6, 4), 0, new Position(6, 3));
        } catch(DeplacementException e) {
            Assertions.fail("Exception trop tôt");
        }
        try {
            deplacer(new Position(4, 2), 1, new Position(4, 3));
            Assertions.fail("Exception non reconnue");
        }catch (DeplacementException e){
            Assertions.assertEquals("Mit en echec",e.getMessage());
        }
    }

    /**==================================================
     * ================| TESTS CONDITIONS |==============
     * ==================================================*/
    @Test
    public void test_deplacer_met_en_echec_et_mat(){
        try{
            deplacer(new Position(4, 6), 0, new Position(4, 5));
            deplacer(new Position(0, 1), 1, new Position(0, 2));
            deplacer(new Position(3, 7), 0, new Position(5, 5));
            deplacer(new Position(0, 2), 1, new Position(0, 3));
            deplacer(new Position(5, 7), 0, new Position(2, 4));
            deplacer(new Position(7, 1), 1, new Position(7, 2));
            deplacer(new Position(5, 5), 0, new Position(5, 1));
            Assertions.assertTrue(ordonnanceurDeJeu.echecEtMat(varianteTest.getOrdrejoueur().get(1)));
        }catch (DeplacementException e){
            Assertions.fail("Exception levée"+e.getMessage());
            e.printStackTrace();
        }
    }

    private void viderPlateau() {
        for (List<Case> ligne: varianteTest.getPlateau().getEchiquier()) {
            for (Case c : ligne) {
                c.setPieceOnCase(null);
            }
        }
        for (Joueur j : varianteTest.getJoueurs()) {
            j.getPawnList().clear();
        }
    }

    @Test
    public void test_met_pat(){
        try{
            viderPlateau();
            Piece king = varianteTest.getJoueurs().get(1).getTypePawnList().stream().filter(p -> p.getName().equals("Roi")).findAny().get();
            Piece nKing = new Piece(king);
            varianteTest.getPlateau().getEchiquier().get(0).get(0).setPieceOnCase(nKing);
            varianteTest.getJoueurs().get(1).getPawnList().add(nKing);

            Piece tour = varianteTest.getJoueurs().get(0).getTypePawnList().stream().filter(p -> p.getName().equals("Tour")).findAny().get();
            Piece nTour = new Piece(tour);
            varianteTest.getPlateau().getEchiquier().get(1).get(7).setPieceOnCase(nTour);
            varianteTest.getJoueurs().get(0).getPawnList().add(nTour);
            nTour = new Piece(tour);
            varianteTest.getPlateau().getEchiquier().get(7).get(1).setPieceOnCase(nTour);
            varianteTest.getJoueurs().get(0).getPawnList().add(nTour);

            Assertions.assertTrue(ordonnanceurDeJeu.pat(varianteTest.getOrdrejoueur().get(1)));
        } catch (DeplacementException e){
            Assertions.fail("Exception levée"+e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void test_zero_pieces(){
        try{
            viderPlateau();

            Piece king = varianteTest.getJoueurs().get(1).getTypePawnList().stream().filter(p -> p.getName().equals("Roi")).findAny().get();
            Piece nKing = new Piece(king);
            varianteTest.getPlateau().getEchiquier().get(0).get(0).setPieceOnCase(nKing);
            varianteTest.getJoueurs().get(1).getPawnList().add(nKing);

            Assertions.assertTrue(ordonnanceurDeJeu.zeroPiece(varianteTest.getOrdrejoueur().get(0)));
        } catch (DeplacementException e){
            Assertions.fail("Exception levée"+e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void test_zero_vie(){
        try{
            viderPlateau();

            Piece king = varianteTest.getJoueurs().get(1).getTypePawnList().stream().filter(p -> p.getName().equals("Roi")).findAny().get();
            king.setNbLife(1);
            Piece nKing = new Piece(king);
            varianteTest.getPlateau().getEchiquier().get(0).get(0).setPieceOnCase(nKing);
            varianteTest.getJoueurs().get(1).getPawnList().add(nKing);

            Piece tour = varianteTest.getJoueurs().get(0).getTypePawnList().stream().filter(p -> p.getName().equals("Tour")).findAny().get();
            Piece nTour = new Piece(tour);
            varianteTest.getPlateau().getEchiquier().get(1).get(7).setPieceOnCase(nTour);
            varianteTest.getJoueurs().get(0).getPawnList().add(nTour);

            deplacer(new Position(7, 1), 0, new Position(7, 0));

            Assertions.assertTrue(ordonnanceurDeJeu.conditionVictoireZeroVie(varianteTest.getOrdrejoueur().get(1)));
        } catch (DeplacementException e){
            Assertions.fail("Exception levée : "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**==================================================
     * ================| TESTS BONS |====================
     * ==================================================*/
    @Test
    public void test_ensembles_deplacement(){
        try{
            Set<Case> oracleCase = new LinkedHashSet<>();
            oracleCase.add(varianteTest.getPlateau().getCase(new Position(3, 5)));
            Assertions.assertEquals(oracleCase.toString(), ordonnanceurDeJeu.deplacementsValide(varianteTest.getPlateau().getCase(new Position(3, 6)), varianteTest.getJoueurs().get(0)).toString());
        }catch (DeplacementException e){
            Assertions.fail("Exception levée : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void test_deplacer_deplace_la_piece(){
        try{
            deplacer(new Position(1, 7), 0, new Position(3, 5));
            Assertions.assertNull(varianteTest.getPlateau().getCase(new Position(1, 7)));
            Assertions.assertNotNull(varianteTest.getPlateau().getCase(new Position(3, 5)));
        }catch (DeplacementException e){
            Assertions.fail("Exception levée : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void test_map_lol_xd() {
        Map<Joueur, Label> map = new HashMap<>();
        Label l = new Label("test");
        Label l2 = new Label("test2");
        Joueur j1 = varianteTest.getJoueurs().get(0);
        map.put(j1, l);
        map.put(varianteTest.getJoueurs().get(1), l2);
        Assertions.assertEquals(l2, map.get(varianteTest.getOrdrejoueur().get(1)));
        Assertions.assertEquals(l, map.get(varianteTest.getOrdrejoueur().get(0)));

        System.out.println(j1 + " iugykafehgbuyilk "+ varianteTest.getOrdrejoueur().get(0));
        j1.setName("BITE");
        System.out.println(j1 + " iugykafehgbuyilk "+ varianteTest.getOrdrejoueur().get(0));
        System.out.println(map);

        Assertions.assertEquals(l, map.get(varianteTest.getOrdrejoueur().get(0)));
        Assertions.assertEquals(l2, map.get(varianteTest.getOrdrejoueur().get(1)));
    }

    /*==================================================
     * ================| TESTS REGLES |==================
     * ==================================================*/
    //todo tests regles
}
