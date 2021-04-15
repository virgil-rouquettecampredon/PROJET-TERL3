import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Automate.Automate_Regles;
import org.example.model.Regles.Structure.Automate.Automate_Regles_Semantique;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GenerateurDeRegleSyntaxeMauvaiseTest {

    private GenerateurDeRegle_Jeton generateur;
    private Automate_Regles<Jeton> auto;

    @BeforeEach
    public void initialiser_Generateur(){
        auto = new Automate_Regles_Semantique();
        auto.initialiserAutomate();
        generateur = new GenerateurDeRegle_Jeton(auto);
    }

    @Test
    public final void testBlocVide_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList(""));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Axiome inconnu au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocInconnu_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("AALL"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Axiome inconnu au bloc de regle numero : [0]",m.getMessage());
        }
    }

    /**==================================================
     * ===============TESTS Mauvais Joueur===============
     * ==================================================*/
    @Test
    public final void testBlocJoueurVide_testMauvais() {
        Jeton j = Jeton.JOUEUR;
        assertFalse(j.estReconnu(""));
        assertEquals("Syntaxe du joueur incorrecte",j.getMessageErreur());
    }
    @Test
    public final void testBlocJoueur_testMauvais() {
        Jeton j = Jeton.JOUEUR;
        assertFalse(j.estReconnu("ffrf"));
        assertEquals("Syntaxe du joueur incorrecte (doit commencer par J)",j.getMessageErreur());
    }
    @Test
    public final void testBlocJoueurJ_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("J"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe du joueur incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurJA_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("JA"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe du joueur (numero) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurJAL_testMauvais() {
        try {
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("JAL"));
            fail("Exception non détectée");
        } catch (MauvaiseDefinitionRegleException m) {
            assertEquals("Syntaxe du joueur (numero) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurJAaa_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("JAaa"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe du joueur (JA + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurJALa_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("JALa"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe du joueur (JAL + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurJALLL_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("JALLl"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe du joueur (JALL + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurJChiffreBas_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("J-1"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe du joueur (numero trop petit) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocJoueurJChiffreHaut_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("J1"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe du joueur (numero trop grand) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }

    /**==================================================
     * ================TESTS Mauvais Case================
     * ==================================================*/
    @Test
    public final void testBlocCaseVide_testMauvais() {
        Jeton j = Jeton.CASE;
        assertFalse(j.estReconnu(""));
        assertEquals("Syntaxe de case incorrecte",j.getMessageErreur());
    }
    @Test
    public final void testBlocCase_testMauvais() {
        Jeton j = Jeton.CASE;
        assertFalse(j.estReconnu("ffrf"));
        assertEquals("Syntaxe de case incorrecte (doit commencer par C)",j.getMessageErreur());
    }
    @Test
    public final void testBlocCaseC_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("C"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de case incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseCA_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("CA"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de case (numero) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseCAL_testMauvais() {
        try {
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("CAL"));
            fail("Exception non détectée");
        } catch (MauvaiseDefinitionRegleException m) {
            assertEquals("Syntaxe de case (numero) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseCAaa_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("CAaa"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de case (CA + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseCALa_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("CALa"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de case (CAL + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseCALLL_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("CALLl"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de case (CALL + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseCChiffreBas_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("C-1"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de case (numero trop petit) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocCaseCChiffreHaut_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("C1"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de case (numero trop grand) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }

    /**==================================================
     * ===============TESTS Mauvais Piece================
     * ==================================================*/
    @Test
    public final void testBlocPieceVide_testMauvais() {
        Jeton j = Jeton.PIECE;
        assertFalse(j.estReconnu(""));
        assertEquals("Syntaxe de piece incorrecte",j.getMessageErreur());
    }
    @Test
    public final void testBlocPiece_testMauvais() {
        Jeton j = Jeton.PIECE;
        assertFalse(j.estReconnu("ffrf"));
        assertEquals("Syntaxe de piece incorrecte (doit commencer par P)",j.getMessageErreur());
    }
    @Test
    public final void testBlocPieceP_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("P"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de piece incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPiecePA_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PA"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de piece (numero) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPiecePAL_testMauvais() {
        try {
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PAL"));
            fail("Exception non détectée");
        } catch (MauvaiseDefinitionRegleException m) {
            assertEquals("Syntaxe de piece (numero) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPiecePAaa_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PAaa"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de piece (PA + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPiecePALa_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALa"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de piece (PAL + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPiecePALLL_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALLl"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de piece (PALL + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPiecePChiffreBas_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("P-1"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de piece (numero trop petit) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPiecePChiffreHaut_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("P1"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de piece (numero trop grand) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }

    /**==================================================
     * ============TESTS Mauvais PieceToken==============
     * ==================================================*/
    @Test
    public final void testBlocPieceTokenVide_testMauvais() {
        Jeton j = Jeton.PIECETOKEN;
        assertFalse(j.estReconnu(""));
        assertEquals("Pas assez de parametre de définition d'une pieceToken",j.getMessageErreur());
    }
    @Test
    public final void testBlocPieceTokenUnParam_testMauvais() {
        Jeton j = Jeton.PIECETOKEN;
        assertFalse(j.estReconnu("salut"));
        assertEquals("Pas assez de parametre de définition d'une pieceToken",j.getMessageErreur());
    }
    @Test
    public final void testBlocPieceTokenDieseDiese_testMauvais() {
        Jeton j = Jeton.PIECETOKEN;
        assertFalse(j.estReconnu("a##a"));
        assertEquals("Trop de parametre de définition d'une pieceToken",j.getMessageErreur());
    }
    @Test
    public final void testBlocPieceTokenTropArg_testMauvais() {
        Jeton j = Jeton.PIECETOKEN;
        assertFalse(j.estReconnu("a#a##a#a"));
        assertEquals("Trop de parametre de définition d'une pieceToken",j.getMessageErreur());
    }
    @Test
    public final void testBlocPieceTokenPDiese_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("P#_"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe de piece incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenAALLDiese_testMauvais() {
        Jeton j = Jeton.PIECETOKEN;
        assertFalse(j.estReconnu("AALL#_"));
        assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe de piece incorrecte (doit commencer par P)]",j.getMessageErreur());
    }
    @Test
    public final void testBlocPieceTokenPADiese_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PA#_"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe de piece (numero) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenPAaaDiese_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PAaa#_"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe de piece (PA + mauvais carac) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenPALaDiese_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALa#_"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe de piece (PAL + mauvais carac) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenPALLLDiese_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALLl#_"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe de piece (PALL + mauvais carac) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenPChiffreBasDiese_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("P-1#_"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe de piece (numero trop petit) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenPChiffreHaut_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("P1#_"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe de piece (numero trop grand) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseVide_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de piece (PALL + mauvais carac) incorrecte au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJ_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#J"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe du joueur incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }

    @Test
    public final void testBlocPieceTokenBonDieseJA_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#JA"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe du joueur (numero) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseAALL_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#AALL"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe du joueur incorrecte (doit commencer par J)] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJAaa_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#JAaa"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe du joueur (JA + mauvais carac) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJALaDiese_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#JALa"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe du joueur (JAL + mauvais carac) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJALLL_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#JALLl"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe du joueur (JALL + mauvais carac) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJChiffreBas_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#J-1"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe du joueur (numero trop petit) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
    @Test
    public final void testBlocPieceTokenBonDieseJChiffreHaut_testMauvais() {
        try{
            List<Jeton> jetons = generateur.analyseSyntaxique(Arrays.asList("PALL#J1"));
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals("Syntaxe de pieceToken incorrecte [Syntaxe du joueur (numero trop grand) incorrecte] au bloc de regle numero : [0]",m.getMessage());
        }
    }
}
