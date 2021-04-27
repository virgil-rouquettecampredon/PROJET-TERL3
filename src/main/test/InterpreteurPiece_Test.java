import org.example.model.*;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;
import org.example.model.Regles.Structure.Interpreteur.*;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujetPiece;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.example.model.Regles.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class InterpreteurPiece_Test {

    private ArrayList<Joueur> alljoueurs;
    private Variante<Jeton> variante;
    private InterpreteurSujetPiece interpretp;
    private OrdonnanceurDeJeu ord;

    @BeforeEach
    public void init_allpieces(){

        /*Jeton.PIECE.setBorne(8);
        Jeton.JOUEUR.setBorne(5, 0);
        Jeton.JOUEUR.setBorne(4, 1);

        alljoueurs = new ArrayList<>();

        Joueur virgil = new Joueur("croquette", 0);
        Joueur franco = new Joueur("me gusta franco", 1);
        Joueur faritas = new Joueur("faritas", 1);
        Joueur chapeau_pointu = new Joueur("chapeau_pointu_le_mysterieux", 3);

        String s = "sprite";
        ArrayList<PositionDeDeplacement> pos = new ArrayList<>();
        pos.add(new PositionDeDeplacement(1,1,null));
        ArrayList<VecteurDeDeplacement> vect = new ArrayList<>();
        vect.add(new VecteurDeDeplacement(1,1,null));

        ArrayList<Piece> pieces1 = new ArrayList<>();
        pieces1.add(new Piece("shrek", s, 10, -1, virgil, pos, vect));
        pieces1.add(new Piece("shrek", s, 10, -1, virgil, pos, vect));
        pieces1.add(new Piece("chien", s, 10, -1, virgil, pos, vect));
        pieces1.add(new Piece("papa", s, 10, -1, virgil, pos, vect));
        virgil.setPawnList(pieces1);
        virgil.setTypePawnList(pieces1);

        ArrayList<Piece> pieces2 = new ArrayList<>();
        pieces2.add(new Piece("shrek", s, 10, -1, franco, pos, vect));
        pieces2.add(new Piece("chien", s, 10, -1, franco, pos, vect));
        franco.setPawnList(pieces2);
        franco.setTypePawnList(pieces2);

        ArrayList<Piece> pieces3 = new ArrayList<>();
        pieces3.add(new Piece("shrek", s, 10, -1, faritas, pos, vect));
        pieces3.add(new Piece("papa", s, 10, -1, faritas, pos, vect));
        faritas.setPawnList(pieces3);
        faritas.setTypePawnList(pieces3);

        ArrayList<Piece> pieces4 = new ArrayList<>();
        pieces4.add(new Piece("popo", s, 10, -1, chapeau_pointu, pos, vect));
        pieces4.add(new Piece("prout", s, 10, -1, chapeau_pointu, pos, vect));
        chapeau_pointu.setPawnList(pieces4);
        chapeau_pointu.setTypePawnList(pieces4);

        alljoueurs.add(virgil);
        alljoueurs.add(franco);
        alljoueurs.add(faritas);
        alljoueurs.add(chapeau_pointu);

        VarianteBuilder vb = new VarianteBuilder();
        vb.setJoueurs(alljoueurs);
        this.ord = new OrdonnanceurDeJeu(vb.createVariante());*/
    }

    /**==================================================
     * ===============| TESTS MAUVAIS |==================
     * ==================================================*/

    /*PIECE SIMPLE*/
    @Test
    public final void testX_testMauvais(){
        interpretp = new InterpreteurSujetPiece("X");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece: 'X': Format incorrect (syntaxe de la forme PALL ou PN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testP_testMauvais(){
        interpretp = new InterpreteurSujetPiece("P");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece: 'P': Format incorrect (syntaxe de la forme PALL ou PN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testPA_testMauvais(){
        interpretp = new InterpreteurSujetPiece("PA");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece: 'PA': Entier imparsable (NumberFormatException)",m.getMessage());
        }
    }

    @Test
    public final void testPAL_testMauvais(){
        interpretp = new InterpreteurSujetPiece("PAL");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece: 'PAL': Entier imparsable (NumberFormatException)",m.getMessage());
        }
    }

    @Test
    public final void testP10_testMauvais(){
        interpretp = new InterpreteurSujetPiece("P10");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece: 'P10': numéro piece inconnu",m.getMessage());
        }
    }


    @Test
    public final void testP7_testMauvais(){
        interpretp = new InterpreteurSujetPiece("P7");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece: 'P7': numéro piece inconnu",m.getMessage());
        }
    }

    @Test
    public final void testPMoins4_testMauvais(){
        interpretp = new InterpreteurSujetPiece("P-4");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece: 'P-4': numéro piece inconnu",m.getMessage());
        }
    }

    @Test
    public final void testP1abc_testMauvais(){
        interpretp = new InterpreteurSujetPiece("P1abc");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece: 'P1abc': Entier imparsable (NumberFormatException)",m.getMessage());
        }
    }




    /*----------PIECE + JOUEUR----------*/
    @Test
    public final void testDieseSimple_testMauvais(){
        interpretp = new InterpreteurSujetPiece("#");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece + Joueur: '#': Interpreteur objet de regle: Format Piece + Joueur: '#' vide",m.getMessage());
        }
    }

    @Test
    public final void testXDiese_testMauvais(){
        interpretp = new InterpreteurSujetPiece("X#");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece + Joueur: 'X#': Interpreteur objet de regle: Format Piece + Joueur: '#' vide",m.getMessage());
        }
    }

    @Test
    public final void testPDiese_testMauvais(){
        interpretp = new InterpreteurSujetPiece("P#");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece + Joueur: 'P#': Interpreteur objet de regle: Format Piece + Joueur: '#' vide",m.getMessage());
        }
    }

    @Test
    public final void testPDoubleDiese_testMauvais(){
        interpretp = new InterpreteurSujetPiece("P##");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece + Joueur: 'P##': Interpreteur objet de regle: Format Piece + Joueur: '#' vide",m.getMessage());
        }
    }

    @Test
    public final void testDiesePDiese_testMauvais(){
        interpretp = new InterpreteurSujetPiece("#P#");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece + Joueur: '#P#': Interpreteur objet de regle: Piece: '': Format incorrect (syntaxe de la forme PALL ou PN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testP1Diese_testMauvais(){
        interpretp = new InterpreteurSujetPiece("P1#");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece + Joueur: 'P1#': Interpreteur objet de regle: Format Piece + Joueur: '#' vide",m.getMessage());
        }
    }

    @Test
    public final void testP1DoubleDiese_testMauvais(){
        interpretp = new InterpreteurSujetPiece("P1##");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece + Joueur: 'P1##': Interpreteur objet de regle: Format Piece + Joueur: '#' vide",m.getMessage());
        }
    }

    @Test
    public final void testDieseJ1_testMauvais(){
        interpretp = new InterpreteurSujetPiece("#J1");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece + Joueur: '#J1': Interpreteur objet de regle: Piece: '': Format incorrect (syntaxe de la forme PALL ou PN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testDieseDieseJ1_testMauvais(){
        interpretp = new InterpreteurSujetPiece("##J1");
        try {
            interpretp.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Piece + Joueur: '##J1': Interpreteur objet de regle: Piece: '': Format incorrect (syntaxe de la forme PALL ou PN où N est un entier positif)",m.getMessage());
        }
    }

    /**==================================================
     * ==================| TESTS BONS |==================
     * ==================================================*/

    @Test
    public final void testP0_testBon(){
        interpretp = new InterpreteurSujetPiece("P0");
        try {
            assertTrue(interpretp.recupererTout(ord).size() == 4
                    && interpretp.recupererTout(ord).get(0).getName().equals("shrek") && interpretp.recupererTout(ord).get(0).getJoueur().getName().equals("croquette")
                    && interpretp.recupererTout(ord).get(1).getName().equals("shrek") && interpretp.recupererTout(ord).get(1).getJoueur().getName().equals("croquette")
                    && interpretp.recupererTout(ord).get(2).getName().equals("shrek") && interpretp.recupererTout(ord).get(2).getJoueur().getName().equals("me gusta franco")
                    && interpretp.recupererTout(ord).get(3).getName().equals("shrek") && interpretp.recupererTout(ord).get(3).getJoueur().getName().equals("faritas")
            );
        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testP4_testBon(){
        interpretp = new InterpreteurSujetPiece("P4");
        try {
            assertTrue(interpretp.recupererTout(ord).size() == 1
                    && interpretp.recupererTout(ord).get(0).getName().equals("prout")
                    && interpretp.recupererTout(ord).get(0).getJoueur().getName().equals("chapeau_pointu_le_mysterieux")
            );
        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testP0DieseJ0_testBon(){
        interpretp = new InterpreteurSujetPiece("P0#J0");
        try {
            assertTrue(interpretp.recupererTout(ord).size() == 2
                    && interpretp.recupererTout(ord).get(0).getName().equals("shrek") && interpretp.recupererTout(ord).get(0).getJoueur().getName().equals("croquette")
                    && interpretp.recupererTout(ord).get(1).getName().equals("shrek") && interpretp.recupererTout(ord).get(0).getJoueur().getName().equals("croquette")
            );
        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testP0DieseJALL_testBon(){
        interpretp = new InterpreteurSujetPiece("P0#JALL");
        try {
            assertTrue(interpretp.recupererTout(ord).size() == 4
                    && interpretp.recupererTout(ord).get(0).getName().equals("shrek") && interpretp.recupererTout(ord).get(0).getJoueur().getName().equals("croquette")
                    && interpretp.recupererTout(ord).get(1).getName().equals("shrek") && interpretp.recupererTout(ord).get(1).getJoueur().getName().equals("croquette")
                    && interpretp.recupererTout(ord).get(2).getName().equals("shrek") && interpretp.recupererTout(ord).get(2).getJoueur().getName().equals("me gusta franco")
                    && interpretp.recupererTout(ord).get(3).getName().equals("shrek") && interpretp.recupererTout(ord).get(3).getJoueur().getName().equals("faritas")
            );
        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testPALLDieseJ0_testBon(){
        interpretp = new InterpreteurSujetPiece("PALL#J0");
        try {
            assertTrue(interpretp.recupererTout(ord).size() == 4
                    && interpretp.recupererTout(ord).get(0).getName().equals("shrek") && interpretp.recupererTout(ord).get(0).getJoueur().getName().equals("croquette")
                    && interpretp.recupererTout(ord).get(1).getName().equals("shrek") && interpretp.recupererTout(ord).get(1).getJoueur().getName().equals("croquette")
                    && interpretp.recupererTout(ord).get(2).getName().equals("chien") && interpretp.recupererTout(ord).get(2).getJoueur().getName().equals("croquette")
                    && interpretp.recupererTout(ord).get(3).getName().equals("papa") && interpretp.recupererTout(ord).get(3).getJoueur().getName().equals("croquette")
            );
        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

}
