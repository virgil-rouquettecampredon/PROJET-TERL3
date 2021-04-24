import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Plateau;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurCibleCase;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.example.model.Regles.*;
import org.example.model.Joueur;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


public class InterpreteurCase_Test {

    private InterpreteurCibleCase interpretc;
    private OrdonnanceurDeJeu ord;


    /*@BeforeEach
    public final void init_allcases() {
        Plateau p = new Plateau(2, 3);
        Joueur virgil = new Joueur("croquette", 0);
        Joueur chapeau_pointu = new Joueur("chapeau_pointu_le_mysterieux", 3);
        ord = new OrdonnanceurDeJeu(Arrays.asList(virgil, chapeau_pointu), p);
        //java.lang.NullPointerException: Cannot invoke "java.util.List.size()" because "joueurs" is null
    }*/


    /**
     * ==================================================
     * ===============| TESTS MAUVAIS |==================
     * ==================================================
     */

    /*@Test
    public final void testX_testMauvais() {
        //Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)
        interpretc = new InterpreteurCibleCase("X");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Case: 'X': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)", m.getMessage());
        }
    }

    @Test
    public final void testC_testMauvais() {
        //Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)
        interpretc = new InterpreteurCibleCase("C");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Case: 'C': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)", m.getMessage());
        }
    }

    @Test
    public final void testCA_testMauvais() {
        //Entier imparsable (NumberFormatException)
        interpretc = new InterpreteurCibleCase("CA");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Case: 'CA': Entier imparsable (NumberFormatException)", m.getMessage());
        }
    }

    @Test
    public final void testCALLL_testMauvais() {
        //Entier imparsable (NumberFormatException)
        interpretc = new InterpreteurCibleCase("CALLL");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Case: 'CALLL': Entier imparsable (NumberFormatException)", m.getMessage());
        }
    }

    @Test
    public final void testC10_testMauvais() {
        //numéro case inconnu
        interpretc = new InterpreteurCibleCase("C10");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Case: 'C10': numéro case inconnu", m.getMessage());
        }
    }

    @Test
    public final void testCMoins2_testMauvais() {
        //numéro case inconnu
        interpretc = new InterpreteurCibleCase("C-2");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Case: 'C-2': numéro case inconnu", m.getMessage());
        }
    }

    @Test
    public final void testC6_testMauvais() {
        //numéro case inconnu
        interpretc = new InterpreteurCibleCase("C6");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Case: 'C6': numéro case inconnu", m.getMessage());
        }
    }

    @Test
    public final void testC2abc_testMauvais() {
        //Entier imparsable (NumberFormatException)
        interpretc = new InterpreteurCibleCase("C2abc");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Case: 'C2abc': Entier imparsable (NumberFormatException)", m.getMessage());
        }
    }*/

    /**
     * ==================================================
     * ==================| TESTS BONS |==================
     * ==================================================
     */

    /*@Test
    public final void testCALL_testBon() {
        interpretc = new InterpreteurCibleCase("CALL");
        try {
            assertTrue(interpretc.recupererTout(ord).size() == 6);
            assertEquals(0, interpretc.recupererTout(ord).get(0).getPosition().getX()); assertEquals(0, interpretc.recupererTout(ord).get(0).getPosition().getY());
            assertEquals(1, interpretc.recupererTout(ord).get(1).getPosition().getX()); assertEquals(0, interpretc.recupererTout(ord).get(1).getPosition().getY());
            assertEquals(2, interpretc.recupererTout(ord).get(2).getPosition().getX()); assertEquals(0, interpretc.recupererTout(ord).get(2).getPosition().getY());
            //assertEquals(1, interpretc.recupererTout(ord).get(3).getPosition().getX()); assertEquals(1, interpretc.recupererTout(ord).get(3).getPosition().getY());
            //assertEquals(0, interpretc.recupererTout(ord).get(4).getPosition().getX()); assertEquals(2, interpretc.recupererTout(ord).get(4).getPosition().getY());
            //assertEquals(1, interpretc.recupererTout(ord).get(5).getPosition().getX()); assertEquals(2, interpretc.recupererTout(ord).get(5).getPosition().getY());

        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testC0_testBon() {
        interpretc = new InterpreteurCibleCase("C0");
        try {
            assertTrue(interpretc.recupererTout(ord).size() == 1);
            assertTrue(interpretc.recupererTout(ord).get(0).getPosition().getX() == 0);
            assertTrue(interpretc.recupererTout(ord).get(0).getPosition().getY() == 0);
        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testC3_testBon() {
        interpretc = new InterpreteurCibleCase("C3");
        try {
            assertTrue(interpretc.recupererTout(ord).size() == 1);
            assertTrue(interpretc.recupererTout(ord).get(0).getPosition().getX() == 1);
            assertTrue(interpretc.recupererTout(ord).get(0).getPosition().getY() == 1);
        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testC5_testBon() {
        interpretc = new InterpreteurCibleCase("C5");
        try {
            assertTrue(interpretc.recupererTout(ord).size() == 1);
            assertTrue(interpretc.recupererTout(ord).get(0).getPosition().getX() == 1);
            assertTrue(interpretc.recupererTout(ord).get(0).getPosition().getY() == 2);
        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }*/

}