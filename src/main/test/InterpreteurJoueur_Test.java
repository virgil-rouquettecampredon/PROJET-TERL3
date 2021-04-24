import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Automate.Automate_Interface_Condition;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujetJoueur;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;
import org.example.model.VarianteBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.example.model.Regles.*;
import org.example.model.Joueur;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class InterpreteurJoueur_Test{

    private ArrayList<Joueur> alljoueurs;
    private InterpreteurSujetJoueur interpretj;
    private OrdonnanceurDeJeu ord;

    @BeforeEach
    public void init_alljoueurs(){

        Jeton.JOUEUR.setBorne(20, 0);
        Jeton.JOUEUR.setBorne(6, 1);

        alljoueurs = new ArrayList<>();

        alljoueurs.add(new Joueur("stinky", 1));
        alljoueurs.add(new Joueur("Sona", 1));
        alljoueurs.add(new Joueur("Chollet", 1));
        alljoueurs.add(new Joueur("Morsay", 1));
        alljoueurs.add(new Joueur("Cortex", 1));
        alljoueurs.add(new Joueur("drogue", 1));
        alljoueurs.add(new Joueur("Scooby Snack", 1));
        alljoueurs.add(new Joueur("Kemar", 1));

        alljoueurs.add(new Joueur("Marex", 2));
        alljoueurs.add(new Joueur("Shrek", 2));
        alljoueurs.add(new Joueur("Reaubalhas", 2));
        alljoueurs.add(new Joueur("Norman", 2));

        alljoueurs.add(new Joueur("Meynard", 3));
        alljoueurs.add(new Joueur("Yumi", 3));

        alljoueurs.add(new Joueur("Erwan", 4));
        alljoueurs.add(new Joueur("Ange", 4));
        alljoueurs.add(new Joueur("Virgil croquette", 4));
        alljoueurs.add(new Joueur("Petru Valikov", 4));

        alljoueurs.add(new Joueur("Molnar", 5));

        alljoueurs.add(new Joueur("Sans", 6));

        VarianteBuilder vb = new VarianteBuilder();
        vb.setJoueurs(alljoueurs);
        this.ord = new OrdonnanceurDeJeu(vb.createVariante());
    }

    /**==================================================
     * ===============| TESTS MAUVAIS |==================
     * ==================================================*/

    /*JOUEUR*/
    @Test
    public final void testStringInvalideX_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("X");
        try {
            interpretj.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: 'X': Format incorrect (syntaxe de la forme JALL, JN ou EN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testStringInvalideJ_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("J");
        try {
            interpretj.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: 'J': Format incorrect (syntaxe de la forme JALL, JN ou EN où N est un entier positif)",m.getMessage());
        }
    }

    @Test
    public final void testStringInvalideJA_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("JA");
        try {
            interpretj.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Joueur: 'JA': Entier imparsable (NumberFormatException)",m.getMessage());
        }
    }

    @Test
    public final void testStringInvalideJAL_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("JAL");
        try {
            interpretj.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException m) {
            assertEquals("Interpreteur objet de regle: Joueur: 'JAL': Entier imparsable (NumberFormatException)",m.getMessage());
        }
    }

    @Test
    public final void testJoueurNumInvalideJ20_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("J20");
        try {
            interpretj.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException e) {
            assertEquals("Interpreteur objet de regle: Joueur: 'J20': numéro joueur inconnu",e.getMessage());
        }

    }

    @Test
    public final void testJoueurNumInvalideJ14abc_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("J14abc");
        try {
            interpretj.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException e) {
            assertEquals("Interpreteur objet de regle: Joueur: 'J14abc': Entier imparsable (NumberFormatException)",e.getMessage());
        }

    }

    @Test
    public final void testJoueurNumInvalideJNnegatif_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("J-6");
        try {
            interpretj.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException e) {
            assertEquals("Interpreteur objet de regle: Joueur: 'J-6': numéro joueur inconnu", e.getMessage());
        }
    }

    @Test
    public final void testJ20NumNonDécritListeVide_testBon() {
        //java.lang.IndexOutOfBoundsException: Index 20 out of bounds for length 20
        Jeton.JOUEUR.setBorne(21, 0);
        interpretj = new InterpreteurSujetJoueur("J20");
        try {
            interpretj.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException e) {
            assertEquals("Interpreteur objet de regle: Joueur: 'J20': numéro joueur inconnu (IndexOutOfBoundsException)", e.getMessage());
        }
    }


    /*EQUIPE*/
    @Test
    public final void testStringInvalideE_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("E");
        try {
            interpretj.recupererTout(ord);
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException e) {
            assertEquals("Interpreteur objet de regle: 'E': Format incorrect (syntaxe de la forme JALL, JN ou EN où N est un entier positif)",e.getMessage());
        }

    }

    @Test
    public final void testStringInvalideEA_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("EA");
        try {
            assertEquals(null, interpretj.recupererTout(ord));
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException e) {
            assertEquals("Interpreteur objet de regle: Equipe: 'EA': Entier imparsable (NumberFormatException)",e.getMessage());
        }

    }

    @Test
    public final void testEquipeNumInvalideE8_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("E8");
        try {
            assertEquals(null, interpretj.recupererTout(ord));
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException e) {
            assertEquals("Interpreteur objet de regle: Equipe: 'E8': numéro équipe inconnu",e.getMessage());
        }

    }

    @Test
    public final void testEquipeNumInvalideENnegatif_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("E-2");
        try {
            assertEquals(null, interpretj.recupererTout(ord));
            fail("Exception non détectée");
        } catch (MauvaiseInterpretationObjetRegleException e) {
            assertEquals("Interpreteur objet de regle: Equipe: 'E-2': numéro équipe inconnu",e.getMessage());
        }

    }



    /**==================================================
     * =================| TESTS BONS |===================
     * ==================================================

    /*JOUEUR*/
    @Test
    public final void testJALL_testBon() {
        interpretj = new InterpreteurSujetJoueur("JALL");
        try {
            assertTrue(interpretj.recupererTout(ord).equals(alljoueurs));
        } catch (MauvaiseInterpretationObjetRegleException e) {
            fail("Exception détectée: " + e.getMessage());
        }
    }

    @Test
    public final void testJ0_testBon() {
        interpretj = new InterpreteurSujetJoueur("J0");
        try {
            assertTrue((interpretj.recupererTout(ord).size()==1)
                    && interpretj.recupererTout(ord).get(0).getName().equals("stinky"));
        } catch (MauvaiseInterpretationObjetRegleException e) {
            fail("Exception détectée: " + e.getMessage());
        }
    }

    @Test
    public final void testJ19_testBon() {
        interpretj = new InterpreteurSujetJoueur("J19");
        try {
            assertTrue((interpretj.recupererTout(ord).size()==1)
                    && interpretj.recupererTout(ord).get(0).getName().equals("Sans"));
        } catch (MauvaiseInterpretationObjetRegleException e) {
            fail("Exception détectée: " + e.getMessage());
        }
    }

    @Test
    public final void testJ16_testBon() {
        interpretj = new InterpreteurSujetJoueur("J16");
        try {
            assertTrue((interpretj.recupererTout(ord).size()==1)
                    && interpretj.recupererTout(ord).get(0).getName().equals("Virgil croquette"));
        } catch (MauvaiseInterpretationObjetRegleException e) {
            fail("Exception détectée: " + e.getMessage());
        }
    }


    /*EQUIPE*/
    @Test
    public final void testE3_testBon() {
        interpretj = new InterpreteurSujetJoueur("E3");
        try {
            assertTrue((interpretj.recupererTout(ord).size()==2)
                    && interpretj.recupererTout(ord).get(0).getName().equals("Meynard")
                    && interpretj.recupererTout(ord).get(1).getName().equals("Yumi"));
        } catch (MauvaiseInterpretationObjetRegleException e) {
            fail("Exception détectée: " + e.getMessage());
        }
    }

    @Test
    public final void testE8NonDecritListeVide_testBon() {
        Jeton.JOUEUR.setBorne(10, 1);
        interpretj = new InterpreteurSujetJoueur("E8");
        try {
            assertTrue(interpretj.recupererTout(ord).size()==0);
        } catch (MauvaiseInterpretationObjetRegleException e) {
            fail("Exception détectée: " + e.getMessage());
        }
    }

    @Test
    public final void testE0NonDecritListeVide_testBon() {
        interpretj = new InterpreteurSujetJoueur("E0");
        try {
            assertTrue(interpretj.recupererTout(ord).size()==0);
        } catch (MauvaiseInterpretationObjetRegleException e) {
            fail("Exception détectée: " + e.getMessage());
        }
    }


}

