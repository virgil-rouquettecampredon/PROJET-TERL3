import org.example.model.*;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurCibleCase;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujetPiece;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class InterpreteurCaseRelative_Test {

    private InterpreteurCibleCase interpretc;
    private OrdonnanceurDeJeu ord;
    private Plateau p;


    @BeforeEach
    public final void init_allcasesgroupes(){
        p = new Plateau(6,6);
        ArrayList lg = new ArrayList();
        Jeton.CASE.setBorne(6);

        //Cases absolues
        ArrayList<Case> lc_abs1 = new ArrayList<>();
        lc_abs1.add(p.getCase(new Position(0,0)));
        lc_abs1.add(p.getCase(new Position(1,0)));
        lc_abs1.add(p.getCase(new Position(2,0)));
        lc_abs1.add(p.getCase(new Position(3,0)));
        lc_abs1.add(p.getCase(new Position(4,0)));
        lc_abs1.add(p.getCase(new Position(5,0)));
        GroupCases g = new GroupCases("Cases du haut", p);
        g.setCasesAbsolue(lc_abs1);
        lg.add(g);

        ArrayList<Case> lc_abs2 = new ArrayList<>();
        lc_abs2.add(p.getCase(new Position(3,3)));
        lc_abs2.add(p.getCase(new Position(4,3)));
        lc_abs2.add(p.getCase(new Position(3,4)));
        lc_abs2.add(p.getCase(new Position(4,4)));
        GroupCases g2 = new GroupCases("Cases centrales", p);
        g2.setCasesAbsolue(lc_abs2);
        lg.add(g2);

        //Cases relatives
        ArrayList<Position> lc_rel1 = new ArrayList<>();
        lc_rel1.add(new Position(0, -2));
        GroupCases g3 = new GroupCases("Case -2y", p);
        g3.setPositionsRelatives(lc_rel1);
        lg.add(g3);

        ArrayList<Position> lc_rel2 = new ArrayList<>();
        lc_rel2.add(new Position(0, -5));
        GroupCases g4 = new GroupCases("Case -5y", p);
        g4.setPositionsRelatives(lc_rel2);
        lg.add(g4);

        ArrayList<Position> lc_rel3 = new ArrayList<>();
        lc_rel3.add(new Position(1, 0));
        lc_rel3.add(new Position(1, 1));
        GroupCases g5 = new GroupCases("Cases 1x et 1xy", p);
        g5.setPositionsRelatives(lc_rel3);
        lg.add(g5);

        //Groupe vide
        ArrayList<Position> lc_rel4 = new ArrayList<>();
        GroupCases g6 = new GroupCases("Groupe vide", p);
        g6.setPositionsRelatives(lc_rel4);
        lg.add(g6);

        //create variante + ordonnanceur
        VarianteBuilder vb = new VarianteBuilder();
        vb.setListGroupCases(lg);
        vb.setPlateau(p);
        ord = new OrdonnanceurDeJeu(vb.createVariante());

    }

    /**
     * ==================================================
     * ===============| TESTS MAUVAIS |==================
     * ==================================================
     */

    @Test
    public final void testDiese_testMauvais(){
        interpretc = new InterpreteurCibleCase("#");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: '#': Interpreteur objet de regle: Format: '#' vide",m.getMessage());
        }
    }

    @Test
    public final void testXDiese_testMauvais(){
        interpretc = new InterpreteurCibleCase("X#");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: 'X#': Interpreteur objet de regle: Format: '#' vide",m.getMessage());
        }
    }

    @Test
    public final void testXDieseX_testMauvais(){
        interpretc = new InterpreteurCibleCase("X#X");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: 'X#X': Interpreteur objet de regle: GroupCases: 'X': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testC0DieseX_testMauvais(){
        interpretc = new InterpreteurCibleCase("C0#X");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: 'C0#X': Interpreteur objet de regle: GroupCases: 'X': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testXDieseC0_testMauvais(){
        interpretc = new InterpreteurCibleCase("X#C0");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: 'X#C0': Interpreteur objet de regle: GroupCases: 'X': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testDieseC0_testMauvais(){
        interpretc = new InterpreteurCibleCase("#C0");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: '#C0': Interpreteur objet de regle: GroupCases: '': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testDieseDiese_testMauvais(){
        interpretc = new InterpreteurCibleCase("##");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: '##': Interpreteur objet de regle: Format: '#' vide",m.getMessage());
        }
    }

    @Test
    public final void testDieseC0Diese_testMauvais(){
        interpretc = new InterpreteurCibleCase("#C0#");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: '#C0#': Interpreteur objet de regle: GroupCases: '': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testDieseC0DieseC0_testMauvais(){
        interpretc = new InterpreteurCibleCase("#C0#C0");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: '#C0#C0': Interpreteur objet de regle: GroupCases: '': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testC0DieseDieseC0_testMauvais(){
        interpretc = new InterpreteurCibleCase("C0##C0");
        try {
            interpretc.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Cases absolu + Cases relatives: 'C0##C0': Interpreteur objet de regle: GroupCases: '': Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)",m.getMessage());
        }
    }

    /**
     * ==================================================
     * ==================| TESTS BONS |==================
     * ==================================================
     */

    @Test
    public final void testC1DieseC3_testBon(){
        //Case du Haut X Cases -2y
        interpretc = new InterpreteurCibleCase("C1#C3");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals("[Case -2y] relatif par rapport à [Cases du haut]", interpretc.recupererTout(ord).get(0).getName());
            assertEquals(0, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
            //System.out.println("Result C1#C3" + interpretc.recupererTout(ord));
        } catch (MauvaiseInterpretationObjetRegleException m) {
            System.out.println("Erreur message: " + m.getMessage());
            fail("Exception détectée");
        }
    }

    @Test
    public final void testC2DieseC3_testBon(){
        interpretc = new InterpreteurCibleCase("C2#C3");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals("[Case -2y] relatif par rapport à [Cases centrales]", interpretc.recupererTout(ord).get(0).getName());
            assertEquals(4, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(0).getPosition().equals(new Position(3,1)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(1).getPosition().equals(new Position(4,1)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(2).getPosition().equals(new Position(3,2)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(3).getPosition().equals(new Position(4,2)));
        } catch (MauvaiseInterpretationObjetRegleException m) {
            System.out.println("Erreur message: " + m.getMessage());
            fail("Exception détectée");
        }
    }

    @Test
    public final void testC1DieseC2_testBon(){
        interpretc = new InterpreteurCibleCase("C1#C2");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals("[Cases centrales] relatif par rapport à [Cases du haut]", interpretc.recupererTout(ord).get(0).getName());
            assertEquals(0, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
        } catch (MauvaiseInterpretationObjetRegleException m) {
            System.out.println("Erreur message: " + m.getMessage());
            fail("Exception détectée");
        }
    }

    @Test
    public final void testC3DieseC5_testBon(){
        interpretc = new InterpreteurCibleCase("C3#C5");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals("[Cases 1x et 1xy] relatif par rapport à [Case -2y]", interpretc.recupererTout(ord).get(0).getName());
            assertEquals(0, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
        } catch (MauvaiseInterpretationObjetRegleException m) {
            System.out.println("Erreur message: " + m.getMessage());
            fail("Exception détectée");
        }
    }

    @Test
    public final void testC4DieseC0_testBon(){
        interpretc = new InterpreteurCibleCase("C0#C4");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals("[Case -5y] relatif par rapport à [Toutes les cases]", interpretc.recupererTout(ord).get(0).getName());
            assertEquals(6, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(0).getPosition().equals(new Position(0,0)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(1).getPosition().equals(new Position(1,0)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(2).getPosition().equals(new Position(2,0)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(3).getPosition().equals(new Position(3,0)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(4).getPosition().equals(new Position(4,0)));
            assertTrue(interpretc.recupererTout(ord).get(0).getCasesAbsolue().get(5).getPosition().equals(new Position(5,0)));
        } catch (MauvaiseInterpretationObjetRegleException m) {
            System.out.println("Erreur message: " + m.getMessage());
            fail("Exception détectée");
        }
    }

    @Test
    public final void testC6DieseC3_testBon(){
        interpretc = new InterpreteurCibleCase("C6#C3");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals("[Case -2y] relatif par rapport à [Groupe vide]", interpretc.recupererTout(ord).get(0).getName());
            assertEquals(0, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
        } catch (MauvaiseInterpretationObjetRegleException m) {
            System.out.println("Erreur message: " + m.getMessage());
            fail("Exception détectée");
        }
    }

    @Test
    public final void testC0DieseC6_testBon(){
        interpretc = new InterpreteurCibleCase("C0#C6");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals("[Groupe vide] relatif par rapport à [Toutes les cases]", interpretc.recupererTout(ord).get(0).getName());
            assertEquals(0, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
        } catch (MauvaiseInterpretationObjetRegleException m) {
            System.out.println("Erreur message: " + m.getMessage());
            fail("Exception détectée");
        }
    }

    @Test
    public final void testC6DieseC6_testBon(){
        interpretc = new InterpreteurCibleCase("C6#C6");
        try {
            assertEquals(1, interpretc.recupererTout(ord).size());
            assertEquals("[Groupe vide] relatif par rapport à [Groupe vide]", interpretc.recupererTout(ord).get(0).getName());
            assertEquals(0, interpretc.recupererTout(ord).get(0).getCasesAbsolue().size());
        } catch (MauvaiseInterpretationObjetRegleException m) {
            System.out.println("Erreur message: " + m.getMessage());
            fail("Exception détectée");
        }
    }

}
