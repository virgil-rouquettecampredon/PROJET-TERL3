import org.example.model.*;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurCibleCase;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.example.model.Regles.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class InterpreteurCase_Test {

    private InterpreteurCibleCase interpretc;
    private OrdonnanceurDeJeu ord;
    private Plateau p;

    @BeforeEach
    public final void init_allgroupescases() {
        p = new Plateau(2, 3);
        ArrayList<GroupCases> lg = new ArrayList<>();
        Jeton.CASE.setBorne(5);

        ArrayList<Case> lc = new ArrayList<>();
        lc.add(p.getCase(new Position(0,1)));
        lc.add(p.getCase(new Position(1,1)));
        lc.add(p.getCase(new Position(2,1)));
        GroupCases g1 = new GroupCases("Cases du bas", p);
        g1.setCasesAbsolue(lc);
        lg.add(g1);

        ArrayList<Case> lc2 = new ArrayList<>();
        lc2.add(p.getCase(new Position(0,0)));
        lc2.add(p.getCase(new Position(1,0)));
        lc2.add(p.getCase(new Position(2,0)));
        GroupCases g2 = new GroupCases("Cases du haut", p);
        g2.setCasesAbsolue(lc2);
        lg.add(g2);

        ArrayList<Case> lc3 = new ArrayList<>();
        lc3.add(p.getCase(new Position(1, 0)));
        GroupCases g3 = new GroupCases("Case n1", p);
        g3.setCasesAbsolue(lc3);
        lg.add(g3);


        VarianteBuilder vb = new VarianteBuilder();
        vb.setListGroupCases(lg);
        vb.setPlateau(p);
        ord = new OrdonnanceurDeJeu(vb.createVariante());
    }

    public final void init_groupecases_vide(){
        VarianteBuilder vb = new VarianteBuilder();
        vb.setPlateau(p);
        ord = null;
        ord = new OrdonnanceurDeJeu(vb.createVariante());
        Jeton.CASE.setBorne(5);
    }

    /**
     * ==================================================
     * ===============| TESTS MAUVAIS |==================
     * ==================================================
     */

    @Test
    public final void testX_testMauvais() {
        //Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)
        interpretc = new InterpreteurCibleCase("X");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: GroupCases: 'X': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)", m.getMessage());
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
            assertEquals("Interpreteur objet de regle: GroupCases: 'C': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)", m.getMessage());
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
            assertEquals("Interpreteur objet de regle: GroupCases: 'CA': Entier imparsable (NumberFormatException)", m.getMessage());
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
            assertEquals("Interpreteur objet de regle: GroupCases: 'CALLL': Entier imparsable (NumberFormatException)", m.getMessage());
        }
    }

    @Test
    public final void testC4_testMauvais() {
        //numéro case inconnu
        interpretc = new InterpreteurCibleCase("C4");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: GroupCases: 'C4': numéro case inconnu (IndexOutOfBoundsException)", m.getMessage());
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
            assertEquals("Interpreteur objet de regle: GroupCases: 'C-2': numéro case inconnu", m.getMessage());
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
            assertEquals("Interpreteur objet de regle: GroupCases: 'C6': numéro case inconnu", m.getMessage());
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
            assertEquals("Interpreteur objet de regle: GroupCases: 'C2abc': Entier imparsable (NumberFormatException)", m.getMessage());
        }
    }//hahaha le nul

    /**
     * ==================================================
     * ==================| TESTS BONS |==================
     * ==================================================
     */

    @Test
    public final void testCALL_testBon() {
        interpretc = new InterpreteurCibleCase("CALL");
        try {
            assertEquals(4, interpretc.recupererTout(ord).size());
            assertTrue(interpretc.recupererTout(ord).get(0).getName().equals("Toutes les cases"));
            assertTrue(interpretc.recupererTout(ord).get(1).getName().equals("Cases du bas"));
            assertTrue(interpretc.recupererTout(ord).get(2).getName().equals("Cases du haut"));
            assertTrue(interpretc.recupererTout(ord).get(3).getName().equals("Case n1"));

        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testC0_testBon() {
        interpretc = new InterpreteurCibleCase("C0");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals(6, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
            //assertEquals(7, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
            System.out.println(interpretc.recupererTout(ord));

            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(0).getPosition().equals(new Position(0,0)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(1).getPosition().equals(new Position(1,0)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(2).getPosition().equals(new Position(2,0)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(3).getPosition().equals(new Position(0,1)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(4).getPosition().equals(new Position(1,1)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(5).getPosition().equals(new Position(2,1)));

        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testC1_testBon() {
        interpretc = new InterpreteurCibleCase("C1");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals(3, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(0).getPosition().equals(new Position(0,1)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(1).getPosition().equals(new Position(1,1)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(2).getPosition().equals(new Position(2,1)));

        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testC3_testBon() {
        interpretc = new InterpreteurCibleCase("C3");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals(1, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(0).getPosition().equals(new Position(1,0)));

        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

    @Test
    public final void testInitGroupesCasesVide_testC0_testBon() {
        init_groupecases_vide();
        interpretc = new InterpreteurCibleCase("C0");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals(6, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
            assertTrue(interpretc.recupererTout(ord).get(0).getName().equals("Toutes les cases"));

        } catch (MauvaiseInterpretationObjetRegleException m) {
            fail("Exception détectée: " + m.getMessage());
        }
    }

}