import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Automate.Automate_Regles;
import org.example.model.Regles.Structure.Automate.Automate_Regles_Semantique;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class GenerateurDeRegleSyntaxeTest {

    private GenerateurDeRegle_Jeton generateur;
    private Automate_Regles<Jeton> auto;

    @BeforeEach
    public void initialiser_Generateur(){
        auto = new Automate_Regles_Semantique();
        auto.initialiserAutomate();
        generateur = new GenerateurDeRegle_Jeton(auto);
        Jeton.PIECE.setBorne(20, 0);
        Jeton.CASE.setBorne(20, 0);
        Jeton.JOUEUR.setBorne(20, 0);
    }

    /**==================================================
     * ===============|TESTS BONS Joueur|================
     * ==================================================*/
    @Test
    public final void testBlocJoueurALL_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("JALL"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.JOUEUR, jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurChiffre_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("J1"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.JOUEUR, jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    /**==================================================
     * ================|TESTS BONS Case|=================
     * ==================================================*/
    @Test
    public final void testBlocCaseALL_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("CALL"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.CASE,jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseChiffre_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("C2"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.CASE, jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    /**==================================================
     * ================|TESTS BONS Piece|================
     * ==================================================*/
    @Test
    public final void testBlocPieceALL_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.PIECE, jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceChiffre_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("P0"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.PIECE, jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    /**==================================================
     * =============|TESTS BONS PieceToken|==============
     * ==================================================*/
    @Test
    public final void testBlocPieceToken_ALL_ALL_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#JALL"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.PIECETOKEN, jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_ALL_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("P1#JALL"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.PIECETOKEN, jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_ALL_Chiffre_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#J2"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.PIECETOKEN, jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_Chiffre_testBon() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("P2#J1"));
            assertEquals(1,jetons.size());
            assertEquals(Jeton.PIECETOKEN, jetons.get(0));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    /**==================================================
     * ================|TEST BON Axiome|=================
     * ==================================================*/
    @Test
    public final void testBlocAxiomes_testBon() {
        String[] tabAxiome = {
                "prend", "sedeplace", "estpromu", "estsur", "estechec", "nb_deplacement", "estplace", "timer",   /*conditions*/
                "=", "<", ">",                                                                               /*comparaisons*/
                "tous-piece", "tous-joueur", "tous-typecase",                                                /*tokens nimporte*/
                "victoire", "defaite", "pat", "prendre", "placer", "promouvoir", "deplacer",                    /*consequences*/
                "as","testAlias","testAlias"
        };

        Jeton[] tabJetonAxiome = {Jeton.ACTION, Jeton.ACTION, Jeton.ETAT, Jeton.ACTION, Jeton.ACTION, Jeton.COMPTEUR, Jeton.ACTION, Jeton.COMPTEUR,
                Jeton.COMPARAISON, Jeton.COMPARAISON, Jeton.COMPARAISON, Jeton.TOUS, Jeton.TOUS, Jeton.TOUS,
                Jeton.CONSEQUENCETERMINALE, Jeton.CONSEQUENCETERMINALE, Jeton.CONSEQUENCETERMINALE, Jeton.CONSEQUENCEACTION, Jeton.CONSEQUENCEACTION, Jeton.CONSEQUENCEACTION, Jeton.CONSEQUENCEACTION,
                Jeton.ALIAS
        };

        Jeton[] tabJetonAxiomeBis = {Jeton.ACTION, Jeton.ACTION, Jeton.ETAT, Jeton.ACTION, Jeton.ACTION, Jeton.COMPTEUR, Jeton.ACTION, Jeton.COMPTEUR,
                Jeton.COMPARAISON, Jeton.COMPARAISON, Jeton.COMPARAISON, Jeton.PIECE, Jeton.JOUEUR, Jeton.CASE,
                Jeton.CONSEQUENCETERMINALE, Jeton.CONSEQUENCETERMINALE, Jeton.CONSEQUENCETERMINALE, Jeton.CONSEQUENCEACTION, Jeton.CONSEQUENCEACTION, Jeton.CONSEQUENCEACTION, Jeton.CONSEQUENCEACTION,
                Jeton.ALIAS,Jeton.ALIASREUTILISATION
        };

        int i = 0;
        for (Jeton j : tabJetonAxiome) {
            assertTrue(j.estReconnu(tabAxiome[i]));
            i++;
        }

        try {
            List<Jeton> jetons = generateur.analyseSyntaxique(new ArrayList<String>(Arrays.asList(tabAxiome)));
            int y = 0;
            for (Jeton jet : tabJetonAxiomeBis) {
                assertEquals(jet, jetons.get(y));
                y++;
            }
        } catch (MauvaiseDefinitionRegleException e) {
            fail("Exception Levée : " + e.getMessage());
        }

        try {
            List<Jeton> jetonsBis = generateur.analyseSyntaxique(Arrays.asList("Un_Axiome_Faux"));
            fail("Exception non détecté");
        } catch (MauvaiseDefinitionRegleException e) {
            assertEquals("Axiome inconnu au bloc de regle numero : [0]", e.getMessage());
        }
    }

    /**==================================================
     * ================TEST BON Scenario=================
     * ==================================================*/
    @Test
    public final void testScenario1_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("P0#J0");
        regle.add("sedeplace");
        regle.add("tous-typecase");
        regle.add("NNNalors");
        regle.add("victoire");

        ArrayList<String> regleFinale = new ArrayList<>();
        regleFinale.add("P0#J0");
        regleFinale.add("sedeplace");
        regleFinale.add("CALL");
        regleFinale.add("NON");
        regleFinale.add("NON");
        regleFinale.add("NON");
        regleFinale.add("alors");
        regleFinale.add("victoire");

        ArrayList<Jeton> regleJeton = new ArrayList<>();
        regleJeton.add(Jeton.PIECETOKEN);
        regleJeton.add(Jeton.ACTION);
        regleJeton.add(Jeton.CASE);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.ALORS);
        regleJeton.add(Jeton.CONSEQUENCETERMINALE);

        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(regle);
            for (int i = 0; i < jetons.size(); i++) {
                assertEquals(jetons.get(i), regleJeton.get(i));
            }

            assertEquals(regleFinale.size(),regle.size());

            for (int j = 0; j<regle.size();j++) {
                assertEquals(regleFinale.get(j),regle.get(j));
            }
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testScenario2_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("tous-piece");
        regle.add("prend");
        regle.add("P0#J2");
        regle.add("OU");
        regle.add("tous-joueur");
        regle.add("et");
        regle.add("tous-typecase");
        regle.add("estechec");
        regle.add("promouvoir");

        ArrayList<Jeton> regleJeton = new ArrayList<>();
        regleJeton.add(Jeton.PIECE);
        regleJeton.add(Jeton.ACTION);
        regleJeton.add(Jeton.PIECETOKEN);
        regleJeton.add(Jeton.OU);
        regleJeton.add(Jeton.JOUEUR);
        regleJeton.add(Jeton.ET);
        regleJeton.add(Jeton.CASE);
        regleJeton.add(Jeton.ACTION);
        regleJeton.add(Jeton.CONSEQUENCEACTION);

        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(regle);
            for (int i = 0; i < jetons.size(); i++) {
                assertEquals(jetons.get(i), regleJeton.get(i));
            }
            assertEquals("PALL",regle.get(0));
            assertEquals("JALL",regle.get(4));
            assertEquals("CALL",regle.get(6));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testScenario3_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("P0#J0");
        regle.add("sedeplace");
        regle.add("C2");
        regle.add("OU");
        regle.add("J3");
        regle.add("timer");
        regle.add("N=");
        regle.add("33");
        regle.add("victoire");

        ArrayList<String> regleFinale = new ArrayList<>();
        regleFinale.add("P0#J0");
        regleFinale.add("sedeplace");
        regleFinale.add("C2");
        regleFinale.add("OU");
        regleFinale.add("J3");
        regleFinale.add("timer");
        regleFinale.add("NON");
        regleFinale.add("=");
        regleFinale.add("33");
        regleFinale.add("victoire");

        ArrayList<Jeton> regleJeton = new ArrayList<>();
        regleJeton.add(Jeton.PIECETOKEN);
        regleJeton.add(Jeton.ACTION);
        regleJeton.add(Jeton.CASE);
        regleJeton.add(Jeton.OU);
        regleJeton.add(Jeton.JOUEUR);
        regleJeton.add(Jeton.COMPTEUR);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.COMPARAISON);
        regleJeton.add(Jeton.NOMBRE);
        regleJeton.add(Jeton.CONSEQUENCETERMINALE);

        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(regle);
            for (int i = 0; i < jetons.size(); i++) {
                assertEquals(regleJeton.get(i),jetons.get(i));
            }

            assertEquals(regleFinale.size(),regle.size());

            for (int j = 0; j<regle.size();j++) {
                assertEquals(regleFinale.get(j),regle.get(j));
            }
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testScenario4_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("P0#J0");
        regle.add("sedeplace");
        regle.add("C2");
        regle.add("et");
        regle.add("NNC10");
        regle.add("as");
        regle.add("masupercase");
        regle.add("alors");
        regle.add("pat");
        regle.add("et");
        regle.add("N102");

        ArrayList<String> regleFinale = new ArrayList<>();
        regleFinale.add("P0#J0");
        regleFinale.add("sedeplace");
        regleFinale.add("C2");
        regleFinale.add("et");
        regleFinale.add("NON");
        regleFinale.add("NON");
        regleFinale.add("C10");
        regleFinale.add("masupercase");
        regleFinale.add("alors");
        regleFinale.add("pat");
        regleFinale.add("et");
        regleFinale.add("NON");
        regleFinale.add("102");

        ArrayList<Jeton> regleJeton = new ArrayList<>();
        regleJeton.add(Jeton.PIECETOKEN);
        regleJeton.add(Jeton.ACTION);
        regleJeton.add(Jeton.CASE);
        regleJeton.add(Jeton.ET);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.CASE);
        regleJeton.add(Jeton.ALIAS);
        regleJeton.add(Jeton.ALORS);
        regleJeton.add(Jeton.CONSEQUENCETERMINALE);
        regleJeton.add(Jeton.ET);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.NOMBRE);

        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(regle);
            for (int i = 0; i < jetons.size(); i++) {
                assertEquals(regleJeton.get(i),jetons.get(i));
            }

            assertEquals("{ nom : masupercase }",generateur.toStringAlias());


            assertEquals(regleFinale.size(),regle.size());
            for (int j = 0; j<regle.size();j++) {
                assertEquals(regleFinale.get(j),regle.get(j));
            }
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    @Test
    public final void testScenario5_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("NNP0#J0");
        regle.add("Nestpromu");
        regle.add("as");
        regle.add("nonAliasTest");
        regle.add("nonAliasTest");
        regle.add("Nnon");
        regle.add("Nas");
        regle.add("NnonAliasTest");
        regle.add("N10");
        regle.add("=");
        regle.add("Net");
        regle.add("timer");
        regle.add("NnonAliasTest");

        ArrayList<String> regleFinale = new ArrayList<>();
        regleFinale.add("NON");
        regleFinale.add("NON");
        regleFinale.add("P0#J0");
        regleFinale.add("NON");
        regleFinale.add("estpromu");
        regleFinale.add("nonAliasTest");
        regleFinale.add("nonAliasTest");
        regleFinale.add("NON");
        regleFinale.add("non");
        regleFinale.add("NON");
        regleFinale.add("NnonAliasTest");
        regleFinale.add("NON");
        regleFinale.add("10");
        regleFinale.add("=");
        regleFinale.add("NON");
        regleFinale.add("et");
        regleFinale.add("timer");
        regleFinale.add("NON");
        regleFinale.add("nonAliasTest");

        ArrayList<Jeton> regleJeton = new ArrayList<>();
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.PIECETOKEN);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.ETAT);
        regleJeton.add(Jeton.ALIAS);
        regleJeton.add(Jeton.ALIASREUTILISATION);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.ALIAS);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.NOMBRE);
        regleJeton.add(Jeton.COMPARAISON);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.ET);
        regleJeton.add(Jeton.COMPTEUR);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.ALIASREUTILISATION);

        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(regle);
            for (int i = 0; i < jetons.size(); i++){
                assertEquals(regleJeton.get(i),jetons.get(i));
            }

            assertEquals("{ nom : nonAliasTest | nom : NnonAliasTest }",generateur.toStringAlias());

            assertEquals(regleFinale.size(),regle.size());
            for (int j = 0; j<regle.size();j++) {
                assertEquals(regleFinale.get(j),regle.get(j));
            }
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
}