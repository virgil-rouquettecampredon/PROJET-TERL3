import org.example.model.Regles.Jeton;
import org.example.model.Regles.GenerateurDeRegle;
import org.example.model.Regles.MauvaiseDefinitionRegleException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GenerateurDeRegleSyntaxeMauvaiseTest {

    /**==================================================
     * ================TESTS Mauvais Joueur=================
     * ==================================================*/
    @Test
    public final void testBlocJoueurVide_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur incorrecte");
        }
    }
    @Test
    public final void testBlocJoueur_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("AALL",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur incorrecte (doit commencer par J)");
        }
    }
    @Test
    public final void testBlocJoueurJ_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("J",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur incorrecte");
        }
    }
    @Test
    public final void testBlocJoueurJA_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JA",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur (numero) incorrecte");
        }
    }
    @Test
    public final void testBlocJoueurJAL_testMauvais() {
        try {
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JAL", 0);
            fail("Exception non détectée");
        } catch (MauvaiseDefinitionRegleException m) {
            assertEquals(m.getMessage(), "Syntaxe du joueur (numero) incorrecte");
        }
    }
    @Test
    public final void testBlocJoueurJAaa_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JAaa",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur (JA + mauvais carac) incorrecte");
        }
    }
    @Test
    public final void testBlocJoueurJALa_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JALa",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur (JAL + mauvais carac) incorrecte");
        }
    }
    @Test
    public final void testBlocJoueurJALLL_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JALLl",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur (JALL + mauvais carac) incorrecte");
        }
    }
    @Test
    public final void testBlocJoueurJChiffreBas_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("J-1",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur (numero trop petit) incorrecte");
        }
    }
    @Test
    public final void testBlocJoueurJChiffreHaut_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("J1",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur (numero trop grand) incorrecte");
        }
    }


    /**==================================================
     * ================TESTS Mauvais Case================
     * ==================================================*/
    @Test
    public final void testBlocCaseVide_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de case incorrecte");
        }
    }
    @Test
    public final void testBlocCase_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("AALL",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de case incorrecte (doit commencer par C)");
        }
    }
    @Test
    public final void testBlocCaseC_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("C",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de case incorrecte");
        }
    }
    @Test
    public final void testBlocCaseCA_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("CA",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de case (numero) incorrecte");
        }
    }
    @Test
    public final void testBlocCaseCAL_testMauvais() {
        try {
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("CAL", 0);
            fail("Exception non détectée");
        } catch (MauvaiseDefinitionRegleException m) {
            assertEquals(m.getMessage(), "Syntaxe de case (numero) incorrecte");
        }
    }
    @Test
    public final void testBlocCaseCAaa_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("CAaa",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de case (CA + mauvais carac) incorrecte");
        }
    }
    @Test
    public final void testBlocCaseCALa_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("CALa",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de case (CAL + mauvais carac) incorrecte");
        }
    }
    @Test
    public final void testBlocCaseCALLL_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("CALLl",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de case (CALL + mauvais carac) incorrecte");
        }
    }
    @Test
    public final void testBlocCaseCChiffreBas_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("C-1",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de case (numero trop petit) incorrecte");
        }
    }
    @Test
    public final void testBlocCaseCChiffreHaut_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Case("C1",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de case (numero trop grand) incorrecte");
        }
    }

    /**==================================================
     * ===============TESTS Mauvais Piece================
     * ==================================================*/
    @Test
    public final void testBlocPieceVide_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de piece incorrecte");
        }
    }
    @Test
    public final void testBlocPiece_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("AALL",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de piece incorrecte (doit commencer par P)");
        }
    }
    @Test
    public final void testBlocPieceP_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("P",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de piece incorrecte");
        }
    }
    @Test
    public final void testBlocPiecePA_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("PA",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de piece (numero) incorrecte");
        }
    }
    @Test
    public final void testBlocPiecePAL_testMauvais() {
        try {
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("PAL", 0);
            fail("Exception non détectée");
        } catch (MauvaiseDefinitionRegleException m) {
            assertEquals(m.getMessage(), "Syntaxe de piece (numero) incorrecte");
        }
    }
    @Test
    public final void testBlocPiecePAaa_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("PAaa",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de piece (PA + mauvais carac) incorrecte");
        }
    }
    @Test
    public final void testBlocPiecePALa_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("PALa",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de piece (PAL + mauvais carac) incorrecte");
        }
    }
    @Test
    public final void testBlocPiecePALLL_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("PALLl",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de piece (PALL + mauvais carac) incorrecte");
        }
    }
    @Test
    public final void testBlocPiecePChiffreBas_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("P-1",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de piece (numero trop petit) incorrecte");
        }
    }
    @Test
    public final void testBlocPiecePChiffreHaut_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Piece("P1",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de piece (numero trop grand) incorrecte");
        }
    }

    /**==================================================
     * ============TESTS Mauvais PieceToken==============
     * ==================================================*/

    @Test
    public final void testBlocPieceTokenVide_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Mauvais nombre de parametre de définition d'une pieceToken");
        }
    }
    @Test
    public final void testBlocPieceTokenUnParam_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("salut",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Mauvais nombre de parametre de définition d'une pieceToken");
        }
    }
    @Test
    public final void testBlocPieceTokenDieseDiese_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P0##J2",5,5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Mauvais nombre de parametre de définition d'une pieceToken");
        }
    }
    @Test
    public final void testBlocPieceTokenTropArg_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("a#a##a#a",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Mauvais nombre de parametre de définition d'une pieceToken");
        }
    }
    @Test
    public final void testBlocPieceTokenPDiese_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P#_",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe de piece incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenAALLDiese_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("AALL#_",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe de piece incorrecte (doit commencer par P)]");
        }
    }
    @Test
    public final void testBlocPieceTokenPADiese_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PA#_",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe de piece (numero) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenPAaaDiese_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PAaa#_",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe de piece (PA + mauvais carac) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenPALaDiese_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALa#_",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe de piece (PAL + mauvais carac) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenPALLLDiese_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALLl#_",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe de piece (PALL + mauvais carac) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenPChiffreBasDiese_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P-1#_",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe de piece (numero trop petit) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenPChiffreHaut_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("P1#_",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe de piece (numero trop grand) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseVide_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Mauvais nombre de parametre de définition d'une pieceToken");
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJ_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#J",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe du joueur incorrecte]");
        }
    }

    @Test
    public final void testBlocPieceTokenBonDieseJA_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#JA",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe du joueur (numero) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseAALL_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#AALL",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe du joueur incorrecte (doit commencer par J)]");
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJAaa_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#JAaa",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe du joueur (JA + mauvais carac) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJALaDiese_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#JALa",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe du joueur (JAL + mauvais carac) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJALLL_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#JALLl",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe du joueur (JALL + mauvais carac) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJChiffreBas_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#J-1",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe du joueur (numero trop petit) incorrecte]");
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJChiffreHaut_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_PieceToken("PALL#J1",0,0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe de pieceToken incorrecte [Syntaxe du joueur (numero trop grand) incorrecte]");
        }
    }
}
