import org.example.model.Regles.Etat;
import org.example.model.Regles.GenerateurDeRegle;
import org.example.model.Regles.MauvaiseDefinitionRegleException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GenerateurDeRegleSyntaxeTest {

    /**==================================================
     * ================TESTS BONS Joueur=================
     * ==================================================*/
    @Test
    public final void testBlocJoueurALL_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JALL",0);
            assertEquals(Etat.JOUEUR, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurChiffre_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("J1",2);
            assertEquals(Etat.JOUEUR, etat);
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
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("CALL",0);
            assertEquals(Etat.CASE, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseChiffre_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("C2",3);
            assertEquals(Etat.CASE, etat);
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
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("PALL",0);
            assertEquals(Etat.PIECE, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceChiffre_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("P0",4);
            assertEquals(Etat.PIECE, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }

    /**==================================================
     * ==============TESTS BONS PieceToken===============
     * ==================================================*/
    @Test
    public final void testBlocPieceToken_ALL_ALL_ALL_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#JALL#ALL",0,0);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_ALL_ALL_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P1#JALL#ALL",0,2);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_ALL_Chiffre_ALL_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#J2#ALL",4,2);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_ALL_ALL_P_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#JALL#P",4,0);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_ALL_ALL_D_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#JALL#D",4,0);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_Chiffre_ALL_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P2#J1#ALL",3,3);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_ALL_P_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P2#JALL#P",2,3);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_ALL_D_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P1#JALL#D",2,3);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_ALL_Chiffre_P_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#J1#P",2,3);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_ALL_Chiffre_D_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#J0#D",2,3);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_Chiffre_D_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P2#J1#D",2,3);
            assertEquals(Etat.PIECETOKEN, etat);
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceToken_Chiffre_Chiffre_P_testBon() {
        try{
            Etat etat = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P0#J0#P",2,3);
            assertEquals(Etat.PIECETOKEN, etat);
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
            "mange", "sedeplace", "estpromu", "estsur", "estechec", "nb_deplacement", "estplace", "timer",   /*conditions*/
                "=", "<", ">",                                                                               /*comparaisons*/
                "tous-piece", "tous-joueur", "tous-typecase",                                                /*tokens nimporte*/
                "victoire", "defaite", "pat", "manger", "placer", "promouvoir", "deplacer",                    /*consequences*/
        };

        Etat[] tabEtatAxiome = {Etat.MANGE,Etat.SEDEPLACE,Etat.PROMU,Etat.SURCASE,Etat.ESTMENACE,Etat.NBDEPLACEMENT,Etat.ESTPLACEE,Etat.TIMER,
                Etat.COMPARAISON,Etat.COMPARAISON,Etat.COMPARAISON,Etat.PIECE,Etat.JOUEUR,Etat.CASE,
                Etat.CONSEQUENCE,Etat.CONSEQUENCE,Etat.CONSEQUENCE,Etat.CONSEQUENCE,Etat.CONSEQUENCE,Etat.CONSEQUENCE,Etat.CONSEQUENCE,
        };
        for (int i = 0; i < tabAxiome.length; i++) {
            assertTrue(GenerateurDeRegle.estAxiome(tabAxiome[i]));
            assertEquals(tabEtatAxiome[i],GenerateurDeRegle.getAxiome(tabAxiome[i]));
        }
        String fauxAxiome = "Un_Axiome_Faux";
        assertFalse(GenerateurDeRegle.estAxiome(fauxAxiome));
        assertEquals(Etat.AUCUN,GenerateurDeRegle.getAxiome(fauxAxiome));
    }

    /**==================================================
     * ================TEST BON Scenario=================
     * ==================================================*/
    @Test
    public final void testScenario1_testBon() {
        ArrayList<String> regle = new ArrayList<>();
        regle.add("0"); //filtré
        regle.add("P0#J0#P");
        regle.add("sedeplace");
        regle.add("tous-typecase");
        regle.add("victoire");

        ArrayList<Etat> regleEtat = new ArrayList<>();
        regleEtat.add(Etat.PIECETOKEN);
        regleEtat.add(Etat.SEDEPLACE);
        regleEtat.add(Etat.CASE);
        regleEtat.add(Etat.CONSEQUENCE);

        try{
            ArrayList<Etat> etats = GenerateurDeRegle.estSyntaxiquementCorrecte(regle,10,10,10);
            for (int i = 0; i < etats.size(); i++) {
                assertEquals(etats.get(i),regleEtat.get(i));
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
        regle.add("mange");
        regle.add("P0#J2#D");
        regle.add("OU");
        regle.add("tous-joueur");
        regle.add("ET");
        regle.add("tous-typecase");
        regle.add("estechec");
        regle.add("promouvoir");

        ArrayList<Etat> regleEtat = new ArrayList<>();
        regleEtat.add(Etat.PIECETOKEN);
        regleEtat.add(Etat.MANGE);
        regleEtat.add(Etat.PIECETOKEN);
        regleEtat.add(Etat.OU);
        regleEtat.add(Etat.JOUEUR);
        regleEtat.add(Etat.ET);
        regleEtat.add(Etat.CASE);
        regleEtat.add(Etat.ESTMENACE);
        regleEtat.add(Etat.CONSEQUENCE);

        try{
            ArrayList<Etat> etats = GenerateurDeRegle.estSyntaxiquementCorrecte(regle,10,10,10);
            for (int i = 0; i < etats.size(); i++) {
                assertEquals(etats.get(i),regleEtat.get(i));
            }
            assertEquals("PALL#JALL#ALL",regle.get(1));
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
        regle.add("P0#J0#P");
        regle.add("sedeplace");
        regle.add("C2");
        regle.add("ET");
        regle.add("J3");
        regle.add("timer");
        regle.add("N=");
        regle.add("33");
        regle.add("victoire");

        ArrayList<Etat> regleEtat = new ArrayList<>();
        regleEtat.add(Etat.PIECETOKEN);
        regleEtat.add(Etat.SEDEPLACE);
        regleEtat.add(Etat.CASE);
        regleEtat.add(Etat.ET);
        regleEtat.add(Etat.JOUEUR);
        regleEtat.add(Etat.TIMER);
        regleEtat.add(Etat.NON);
        regleEtat.add(Etat.COMPARAISON);
        regleEtat.add(Etat.NOMBRE);
        regleEtat.add(Etat.CONSEQUENCE);

        try{
            ArrayList<Etat> etats = GenerateurDeRegle.estSyntaxiquementCorrecte(regle,10,10,10);
            for (int i = 0; i < etats.size(); i++) {
                assertEquals(etats.get(i),regleEtat.get(i));
            }
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }
    }
}