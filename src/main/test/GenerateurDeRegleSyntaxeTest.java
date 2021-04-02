import org.example.model.Regles.Jeton;
import org.example.model.Regles.GenerateurDeRegle;
import org.example.model.Regles.MauvaiseDefinitionRegleException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GenerateurDeRegleSyntaxeTest {

    /**==================================================
     * ================TESTS BONS Joueur=================
     * ==================================================*/
    @Test
    public final void testBlocJoueurALL_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JALL",0);
            assertEquals(Jeton.JOUEUR, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurChiffre_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("J1",2);
            assertEquals(Jeton.JOUEUR, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    /**==================================================
     * =================TESTS BONS Case==================
     * ==================================================*/
    @Test
    public final void testBlocCaseALL_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("CALL",0);
            assertEquals(Jeton.CASE, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseChiffre_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("C2",3);
            assertEquals(Jeton.CASE, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    /**==================================================
     * =================TESTS BONS Piece=================
     * ==================================================*/
    @Test
    public final void testBlocPieceALL_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("PALL",0);
            assertEquals(Jeton.PIECE, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceChiffre_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("P0",4);
            assertEquals(Jeton.PIECE, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    /**==================================================
     * ==============TESTS BONS PieceToken===============
     * ==================================================*/
    @Test
    public final void testBlocPieceToken_ALL_ALL_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#JALL",0,0);
            assertEquals(Jeton.PIECETOKEN, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_ALL_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P1#JALL",0,2);
            assertEquals(Jeton.PIECETOKEN, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_ALL_Chiffre_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#J2",4,2);
            assertEquals(Jeton.PIECETOKEN, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_Chiffre_testBon() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P2#J1",3,3);
            assertEquals(Jeton.PIECETOKEN, jeton);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    /**==================================================
     * =================TEST BON Axiome==================
     * ==================================================*/
    @Test
    public final void testBlocAxiomes_testBon() {
        String[] tabAxiome = {
            "prend", "sedeplace", "estpromu", "estsur", "estechec", "nb_deplacement", "estplace", "timer",   /*conditions*/
                "=", "<", ">",                                                                               /*comparaisons*/
                "tous-piece", "tous-joueur", "tous-typecase",                                                /*tokens nimporte*/
                "victoire", "defaite", "pat", "prendre", "placer", "promouvoir", "deplacer",                    /*consequences*/
                "as"
        };

        Jeton[] tabJetonAxiome = {Jeton.ACTION, Jeton.ACTION, Jeton.ETAT, Jeton.ACTION, Jeton.ACTION, Jeton.COMPTEUR, Jeton.ACTION, Jeton.COMPTEUR,
                Jeton.COMPARAISON, Jeton.COMPARAISON, Jeton.COMPARAISON, Jeton.PIECE, Jeton.JOUEUR, Jeton.CASE,
            Jeton.CONSEQUENCETERMINALE, Jeton.CONSEQUENCETERMINALE, Jeton.CONSEQUENCETERMINALE, Jeton.CONSEQUENCEACTION, Jeton.CONSEQUENCEACTION, Jeton.CONSEQUENCEACTION, Jeton.CONSEQUENCEACTION,
                Jeton.ALIAS
        };
        for (int i = 0; i < tabAxiome.length; i++) {
            assertTrue(GenerateurDeRegle.estAxiome(tabAxiome[i]));
            assertEquals(tabJetonAxiome[i],GenerateurDeRegle.getAxiome(tabAxiome[i]));
        }
        String fauxAxiome = "Un_Axiome_Faux";
        assertFalse(GenerateurDeRegle.estAxiome(fauxAxiome));
        assertEquals(Jeton.AUCUN,GenerateurDeRegle.getAxiome(fauxAxiome));
    }

    /**==================================================
     * ================TEST BON Scenario=================
     * ==================================================*/
    @Test
    public final void testScenario1_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("0"); //filtré
        regle.add("P0#J0");
        regle.add("sedeplace");
        regle.add("tous-typecase");
        regle.add("NNNalors");
        regle.add("victoire");

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
            ArrayList<Jeton> jetons = GenerateurDeRegle.estSyntaxiquementCorrecte(regle,10,10,10);
            for (int i = 0; i < jetons.size(); i++) {
                assertEquals(jetons.get(i), regleJeton.get(i));
            }
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testScenario2_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("1"); //filtré
        regle.add("tous-piece");
        regle.add("prend");
        regle.add("P0#J2");
        regle.add("OU");
        regle.add("tous-joueur");
        regle.add("ET");
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
            ArrayList<Jeton> jetons = GenerateurDeRegle.estSyntaxiquementCorrecte(regle,10,10,10);
            for (int i = 0; i < jetons.size(); i++) {
                assertEquals(jetons.get(i), regleJeton.get(i));
            }
            assertEquals("PALL",regle.get(1));
            assertEquals("JALL",regle.get(5));
            assertEquals("CALL",regle.get(7));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testScenario3_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("1"); //filtré
        regle.add("P0#J0");
        regle.add("sedeplace");
        regle.add("C2");
        regle.add("ET");
        regle.add("J3");
        regle.add("timer");
        regle.add("N=");
        regle.add("33");
        regle.add("victoire");

        ArrayList<Jeton> regleJeton = new ArrayList<>();
        regleJeton.add(Jeton.PIECETOKEN);
        regleJeton.add(Jeton.ACTION);
        regleJeton.add(Jeton.CASE);
        regleJeton.add(Jeton.ET);
        regleJeton.add(Jeton.JOUEUR);
        regleJeton.add(Jeton.COMPTEUR);
        regleJeton.add(Jeton.NON);
        regleJeton.add(Jeton.COMPARAISON);
        regleJeton.add(Jeton.NOMBRE);
        regleJeton.add(Jeton.CONSEQUENCETERMINALE);

        try{
            ArrayList<Jeton> jetons = GenerateurDeRegle.estSyntaxiquementCorrecte(regle,10,10,10);
            for (int i = 0; i < jetons.size(); i++) {
                assertEquals(regleJeton.get(i),jetons.get(i));
            }
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testScenario4_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("1"); //filtré
        regle.add("P0#J0");
        regle.add("sedeplace");
        regle.add("C2");
        regle.add("ET");
        regle.add("NNC10");
        regle.add("as");
        regle.add("masupercase");
        regle.add("alors");
        regle.add("pat");
        regle.add("ET");
        regle.add("N102");

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
            ArrayList<Jeton> jetons = GenerateurDeRegle.estSyntaxiquementCorrecte(regle,10,20,10);
            for (int i = 0; i < jetons.size(); i++) {
                assertEquals(regleJeton.get(i),jetons.get(i));
            }
            assertEquals("masupercase",regle.get(6));
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }

    }
}