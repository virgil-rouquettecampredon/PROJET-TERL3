import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Automate.Automate_Interface_Condition;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujetJoueur;
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

    private List<Joueur> alljoueurs;
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


        this.ord = new OrdonnanceurDeJeu(alljoueurs, null, null);
    }

    /**==================================================
     * ===============| TESTS MAUVAIS |==================
     * ==================================================*/

    /*JOUEUR*/
    @Test
    public final void testStringInvalideX_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("X");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testStringInvalideJ_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("J");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testStringInvalideJA_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("JA");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testStringInvalideJAL_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("JAL");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testJoueurNumInvalideJ20_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("J20");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testJoueurNumInvalideJ14abc_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("J14abc");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testJoueurNumInvalideJNnegatif_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("J-6");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testJoueurNumNonDécrit_testMauvais() {
        Jeton.JOUEUR.setBorne(21, 0);
        interpretj = new InterpreteurSujetJoueur("J20");
        assertEquals(null, interpretj.recupererTout(ord));
    }


    /*EQUIPE*/
    @Test
    public final void testStringInvalideE_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("E");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testStringInvalideEA_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("EA");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testEquipeNumInvalideE8_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("E8");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testEquipeNumInvalideENnegatif_testMauvais() {
        interpretj = new InterpreteurSujetJoueur("E-2");
        assertEquals(null, interpretj.recupererTout(ord));
    }

    @Test
    public final void testEquipeNumNonDécrit_testMauvais() {
        Jeton.JOUEUR.setBorne(10, 1);
        interpretj = new InterpreteurSujetJoueur("E9");
        assertEquals(0, interpretj.recupererTout(ord).size());
    }

    /**==================================================
     * =================| TESTS BONS |===================
     * ==================================================*/

    /*JOUEUR*/
    @Test
    public final void testJALL_testBon() {
        interpretj = new InterpreteurSujetJoueur("JALL");
        assertTrue(interpretj.recupererTout(ord).equals(alljoueurs));
    }

    @Test
    public final void testJ0_testBon() {
        interpretj = new InterpreteurSujetJoueur("J0");
        assertTrue((interpretj.recupererTout(ord).size()==1)
                && interpretj.recupererTout(ord).get(0).getName().equals("stinky"));
    }

    @Test
    public final void testJ19_testBon() {
        interpretj = new InterpreteurSujetJoueur("J19");
        assertTrue((interpretj.recupererTout(ord).size()==1)
                && interpretj.recupererTout(ord).get(0).getName().equals("Sans"));
    }

    @Test
    public final void testJ16_testBon() {
        interpretj = new InterpreteurSujetJoueur("J16");
        assertTrue((interpretj.recupererTout(ord).size()==1)
                && interpretj.recupererTout(ord).get(0).getName().equals("Virgil croquette"));
    }


    /*EQUIPE*/
    @Test
    public final void testE3_testBon() {
        interpretj = new InterpreteurSujetJoueur("E3");
        assertTrue((interpretj.recupererTout(ord).size()==2)
                && interpretj.recupererTout(ord).get(0).getName().equals("Meynard")
                && interpretj.recupererTout(ord).get(1).getName().equals("Yumi"));
    }

    @Test
    public final void testE6NonDecritListeVide_testBon() {
        Jeton.JOUEUR.setBorne(10, 1);
        interpretj = new InterpreteurSujetJoueur("E8");
        assertTrue(interpretj.recupererTout(ord).size()==0);
    }

}
