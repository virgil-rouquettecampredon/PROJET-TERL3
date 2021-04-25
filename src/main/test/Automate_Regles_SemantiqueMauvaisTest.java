import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Automate.Automate_Regles_Semantique;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Automate_Regles_SemantiqueMauvaisTest {

    private Automate_Regles_Semantique automate;
    private Regle regle;

    @BeforeEach
    public void initialiser_Automate(){
        automate = new Automate_Regles_Semantique();
        automate.initialiserAutomate();
    }

    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 0 6----------------------------------------------
    //------------------------------------------------------------------------------------------------
    //test etat terminal 306
    @Test
    public final void testCase306_ExceptionTropPeuArguments(){
        List<String> reS = Arrays.asList("test");
        List<Jeton> reJ = Arrays.asList(Jeton.ETAT);

        //afin de pouvoir tester les cas erreur particuliers
        automate.setEtatDeDepart(2);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Piece(T)-Etat [0]",e.getMessage());
        }

    }

    @Test
    public final void testCase306_026_PasDetectePiece(){
        List<String> reS = Arrays.asList("P1","test");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE, Jeton.ETAT);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Etat inconnu [1]",e.getMessage());
        }
    }

    @Test
    public final void testCase306_0236_PasDetectePieceJoueur(){
        List<String> reS = Arrays.asList("P1","J1","test");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE, Jeton.JOUEUR, Jeton.ETAT);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-Etat inconnu [2]",e.getMessage());
        }
    }

    @Test
    public final void testCase306_036_PasDetectePieceToken(){
        List<String> reS = Arrays.asList("P1#J2","test");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECETOKEN, Jeton.ETAT);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc PieceToken-Etat inconnu [1]",e.getMessage());
        }
    }

    @Test
    public final void testCase306_PasDetecte(){
        List<String> reS = Arrays.asList("ET","P1#J2","test");
        List<Jeton> reJ = Arrays.asList(Jeton.ET, Jeton.PIECETOKEN, Jeton.ETAT);

        automate.setEtatDeDepart(14);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Etat OU Piece-Joueur-Etat OU PieceToken-Etat inconnu [2]",e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 0 9----------------------------------------------
    //------------------------------------------------------------------------------------------------
    //test etat terminal 309
    @Test
    public final void testCase309_ExceptionTropPeuArguments(){
        List<String> reS = Arrays.asList("P1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE);

        //afin de pouvoir tester les cas erreur particuliers
        automate.setEtatDeDepart(5);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Piece(T)-Action-Piece [0]",e.getMessage());
        }

    }

    @Test
    public final void testCase309_0259_PasDetectePiece(){
        List<String> reS = Arrays.asList("P1","test","P2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE, Jeton.ACTION,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Action-Piece inconnu [2]",e.getMessage());
        }
    }

    @Test
    public final void testCase309_0359_PasDetectePieceToken(){
        List<String> reS = Arrays.asList("P1#J2","test","P1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECETOKEN, Jeton.ACTION,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc PieceToken-Action-Piece inconnu [2]",e.getMessage());
        }
    }

    @Test
    public final void testCase306_02359_PasDetectePieceJoueur(){
        List<String> reS = Arrays.asList("P1","J1","test","P1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.JOUEUR, Jeton.ACTION,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-Action-Piece inconnu [3]",e.getMessage());
        }
    }

    @Test
    public final void testCase309_PasDetecte(){
        List<String> reS = Arrays.asList("ET","P1#J2","test","P2");
        List<Jeton> reJ = Arrays.asList(Jeton.ET, Jeton.PIECETOKEN, Jeton.ACTION,Jeton.PIECE);

        automate.setEtatDeDepart(14);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Action-Piece OU Piece-Joueur-Action-Piece OU PieceToken-Action-Piece inconnu [3]",e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 1 0----------------------------------------------
    //------------------------------------------------------------------------------------------------
    //test etat terminal 310
    @Test
    public final void testCase310_ExceptionTropPeuArguments(){
        List<String> reS = Arrays.asList("P1","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.JOUEUR);

        //afin de pouvoir tester les cas erreur particuliers
        automate.setEtatDeDepart(5);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Piece(T)-Action-Piece(J|T) [1]",e.getMessage());
        }

    }

    @Test
    public final void testCase310_025910_PasDetectePiece_PieceJoueur(){
        List<String> reS = Arrays.asList("P1","test","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE, Jeton.ACTION,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Action-Piece-Joueur inconnu [3]",e.getMessage());
        }
    }

    @Test
    public final void testCase310_02510_PasDetectePiece_PieceToken(){
        List<String> reS = Arrays.asList("P2","test","P1#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ACTION,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Action-PieceToken inconnu [2]",e.getMessage());
        }
    }

    @Test
    public final void testCase310_035910_PasDetectePieceToken_PieceJoueur(){
        List<String> reS = Arrays.asList("P1#J1","test","P1","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECETOKEN, Jeton.ACTION,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc PieceToken-Action-Piece-Joueur inconnu [3]",e.getMessage());
        }
    }

    @Test
    public final void testCase310_03510_PasDetectePieceToken_PieceToken(){
        List<String> reS = Arrays.asList("P1#J1","test","P1#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECETOKEN, Jeton.ACTION,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc PieceToken-Action-PieceToken inconnu [2]",e.getMessage());
        }
    }

    @Test
    public final void testCase310_0235910_PasDetectePieceJoueur_PieceJoueur(){
        List<String> reS = Arrays.asList("P1","J1","test","P1","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.JOUEUR, Jeton.ACTION,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-Action-Piece-Joueur inconnu [4]",e.getMessage());
        }
    }

    @Test
    public final void testCase310_023510_PasDetectePieceJoueur_PieceToken(){
        List<String> reS = Arrays.asList("P1","J1","test","P1#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.JOUEUR, Jeton.ACTION,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-Action-PieceToken inconnu [3]",e.getMessage());
        }
    }

    @Test
    public final void testCase310_PasDetecte(){
        List<String> reS = Arrays.asList("ET","P1#J2","test","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.ET, Jeton.PIECETOKEN, Jeton.ACTION,Jeton.PIECE,Jeton.JOUEUR);

        automate.setEtatDeDepart(14);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Action-Piece-Joueur OU Piece-Action-PieceToken OU Piece-Joueur-Action-Piece-Joueur OU Piece-Joueur-Action-PieceToken OU PieceToken-Action-Piece-Joueur OU PieceToken-Action-PieceToken inconnu [4]",e.getMessage());
        }
    }


    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 1 1----------------------------------------------
    //------------------------------------------------------------------------------------------------
    //test etat terminal 311
    @Test
    public final void testCase311_ExceptionTropPeuArguments(){
        List<String> reS = Arrays.asList("C1");
        List<Jeton> reJ = Arrays.asList(Jeton.CASE);

        //afin de pouvoir tester les cas erreur particuliers
        automate.setEtatDeDepart(5);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Piece(T)-Action-Case [0]",e.getMessage());
        }

    }

    @Test
    public final void testCase311_02511_PasDetectePiece(){
        List<String> reS = Arrays.asList("P1","test","C2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE, Jeton.ACTION,Jeton.CASE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Action-Case inconnu [2]",e.getMessage());
        }
    }

    @Test
    public final void testCase311_03511_PasDetectePieceToken(){
        List<String> reS = Arrays.asList("P1#J2","test","C1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECETOKEN, Jeton.ACTION,Jeton.CASE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc PieceToken-Action-Case inconnu [2]",e.getMessage());
        }
    }

    @Test
    public final void testCase311_023511_PasDetectePieceJoueur(){
        List<String> reS = Arrays.asList("P1","J1","test","C1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.JOUEUR, Jeton.ACTION,Jeton.CASE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-Action-Case inconnu [3]",e.getMessage());
        }
    }

    @Test
    public final void testCase311_PasDetecte(){
        List<String> reS = Arrays.asList("ET","P1#J2","test","C2");
        List<Jeton> reJ = Arrays.asList(Jeton.ET, Jeton.PIECETOKEN, Jeton.ACTION,Jeton.CASE);

        automate.setEtatDeDepart(14);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Action-Case OU Piece-Joueur-Action-Case OU PieceToken-Action-Case inconnu [3]",e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 1 3----------------------------------------------
    //------------------------------------------------------------------------------------------------
    //test etat terminal 313
    @Test
    public final void testCase313_ExceptionTropPeuArguments(){
        List<String> reS = Arrays.asList("=","12");
        List<Jeton> reJ = Arrays.asList(Jeton.COMPARAISON,Jeton.NOMBRE);

        //afin de pouvoir tester les cas erreur particuliers
        automate.setEtatDeDepart(4);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Joueur-Compteur-Comparaison-Nombre [1]",e.getMessage());
        }

    }

    @Test
    public final void testCase313_014813_PasDetecteJoueur(){
        List<String> reS = Arrays.asList("J1","test","=","12");
        List<Jeton> reJ = Arrays.asList(Jeton.JOUEUR, Jeton.COMPTEUR,Jeton.COMPARAISON,Jeton.NOMBRE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-Timer inconnu [3]",e.getMessage());
        }
    }

    @Test
    public final void testCase313_PasDetecte(){
        List<String> reS = Arrays.asList("ET","J3","test","C2","12");
        List<Jeton> reJ = Arrays.asList(Jeton.ET, Jeton.JOUEUR, Jeton.COMPTEUR,Jeton.COMPARAISON,Jeton.NOMBRE);

        automate.setEtatDeDepart(14);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-Compteur-Comparaison-Nombre inconnu [4]",e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 1 4----------------------------------------------
    //------------------------------------------------------------------------------------------------
    //test etat terminal 314
    @Test
    public final void testCase314_ExceptionTropPeuArguments(){
        List<String> reS = Arrays.asList("1");
        List<Jeton> reJ = Arrays.asList(Jeton.NOMBRE);

        //afin de pouvoir tester les cas erreur particuliers
        automate.setEtatDeDepart(12);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Piece(T)-Compteur-Comparaison-Nombre [0]",e.getMessage());
        }

    }

    @Test
    public final void testCase314_0271214_PasDetectePiece(){
        List<String> reS = Arrays.asList("P1","test","=","10");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE, Jeton.COMPTEUR,Jeton.COMPARAISON,Jeton.NOMBRE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Compteur-Comparaison-Nombre inconnu [3]",e.getMessage());
        }
    }

    @Test
    public final void testCase314_02371214_PasDetectePieceJoueur(){
        List<String> reS = Arrays.asList("P1","J2","test",">","12");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.JOUEUR, Jeton.COMPTEUR,Jeton.COMPARAISON,Jeton.NOMBRE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-Compteur-Comparaison-Nombre inconnu [4]",e.getMessage());
        }
    }

    @Test
    public final void testCase314_0371214_PasDetectePieceToken(){
        List<String> reS = Arrays.asList("P1#J1","test","<","12");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECETOKEN,Jeton.COMPTEUR,Jeton.COMPARAISON,Jeton.NOMBRE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc PieceToken-Compteur-Comparaison-Nombre inconnu [3]",e.getMessage());
        }
    }

    @Test
    public final void testCase314_PasDetecte(){
        List<String> reS = Arrays.asList("ET","P1","J3","test","=","23");
        List<Jeton> reJ = Arrays.asList(Jeton.ET, Jeton.PIECE,Jeton.JOUEUR, Jeton.COMPTEUR,Jeton.COMPARAISON,Jeton.NOMBRE);

        automate.setEtatDeDepart(14);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Compteur-Comparaison-Nombre OU Piece-Joueur-Compteur-Comparaison-Nombre OU PieceToken-Compteur-Comparaison-Nombre inconnu [5]",e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 1 9----------------------------------------------
    //------------------------------------------------------------------------------------------------
    //test etat terminal 319
    @Test
    public final void testCase319_ExceptionTropPeuArguments(){
        List<String> reS = Arrays.asList("1");
        List<Jeton> reJ = Arrays.asList(Jeton.CONSEQUENCETERMINALE);

        //afin de pouvoir tester les cas erreur particuliers
        automate.setEtatDeDepart(16);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Sujet-ConsequenceTerminale [0]",e.getMessage());
        }

    }

    @Test
    public final void testCase319_151619_PasDetecte(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","J2","test");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCETERMINALE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-ConsequenceTerminale inconnu [4]",e.getMessage());
        }

    }

    @Test
    public final void testCase319_PasDetecte(){
        List<String> reS = Arrays.asList("J1","test");
        List<Jeton> reJ = Arrays.asList(Jeton.JOUEUR,Jeton.CONSEQUENCETERMINALE);

        automate.setEtatDeDepart(15);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-ConsequenceTerminale inconnu [1]",e.getMessage());
        }
    }


    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 2 1----------------------------------------------
    //------------------------------------------------------------------------------------------------
    //test etat terminal 321
    @Test
    public final void testCase321_ExceptionTropPeuArguments(){
        List<String> reS = Arrays.asList("P1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE);

        //afin de pouvoir tester les cas erreur particuliers
        automate.setEtatDeDepart(20);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Sujet-ConsequenceAction-(Case)-Piece [0]",e.getMessage());
        }

    }

    @Test
    public final void testCase321_15162021_PasDetecteJoueur_Piece(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","J2","test","P2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-ConsequenceAction-Piece inconnu [5]",e.getMessage());
        }

    }

    @Test
    public final void testCase321_15172021_PasDetectePiece_Piece(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P2","test","P2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.CONSEQUENCEACTION,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-ConsequenceAction-Piece inconnu [5]",e.getMessage());
        }

    }

    @Test
    public final void testCase321_1517182021_PasDetectePieceJoueur_Piece(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P2","J2","test","P2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE, Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-ConsequenceAction-Piece inconnu [6]",e.getMessage());
        }

    }

    @Test
    public final void testCase321_15182021_PasDetectePieceToken_Piece(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P2#J2","test","P2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECETOKEN,Jeton.CONSEQUENCEACTION,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piecetoken-ConsequenceAction-Piece inconnu [5]",e.getMessage());
        }

    }

    @Test
    public final void testCase321_1516202321_PasDetecteJoueur_CasePiece(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","J2","test","C2","P6");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-ConsequenceAction-Case-Piece inconnu [6]",e.getMessage());
        }

    }

    @Test
    public final void testCase321_1517202321_PasDetectePiece_CasePiece(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P2","test","C2","P6");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-ConsequenceAction-Case-Piece inconnu [6]",e.getMessage());
        }

    }

    @Test
    public final void testCase321_151718202321_PasDetectePieceJoueur_CasePiece(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P2","J5","test","C2","P6");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-ConsequenceAction-Case-Piece inconnu [7]",e.getMessage());
        }

    }

    @Test
    public final void testCase321_1518202321_PasDetectePieceToken_CasePiece(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P2#J5","test","C2","P6");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECETOKEN,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piecetoken-ConsequenceAction-Case-Piece inconnu [6]",e.getMessage());
        }

    }

    @Test
    public final void testCase321_PasDetecte(){
        List<String> reS = Arrays.asList("J1","test");
        List<Jeton> reJ = Arrays.asList(Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECE);

        automate.setEtatDeDepart(17);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Sujet-ConsequenceAction-Piece ou Sujet-ConsequenceAction-Case-Piece inconnu [3]",e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 2 2----------------------------------------------
    //------------------------------------------------------------------------------------------------
    @Test
    public final void testCase322_ExceptionTropPeuArguments(){
        List<String> reS = Arrays.asList("P2#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECETOKEN);

        automate.setEtatDeDepart(20);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Sujet-ConsequenceAction-(Case)-Piece(T,J) [0]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_15162022_PasDetecteJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","J5","placer","P2#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-ConsequenceAction-PieceToken inconnu [5]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_15172022_PasDetectePiece(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P2","test","P2#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.CONSEQUENCEACTION,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-ConsequenceAction-PieceToken inconnu [5]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_1517182022_PasDetectePieceJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P2","J1","test","P2#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-ConsequenceAction-PieceToken inconnu [6]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_15182022_PasDetectePiecetoken(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P2#J1","test","P2#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECETOKEN,Jeton.CONSEQUENCEACTION,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc PieceToken-ConsequenceAction-PieceToken inconnu [5]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_1516202122_PasDetecteJoueur_PieceJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","J1","test","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-ConsequenceAction-Piece-Joueur inconnu [6]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_1517202122_PasDetectePiece_PieceJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1","test","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.CONSEQUENCEACTION,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-ConsequenceAction-Piece-Joueur inconnu [6]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_151718202122_PasDetectePieceJoueur_PieceJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1","J1","test","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-ConsequenceAction-Piece-Joueur inconnu [7]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_1518202122_PasDetectePiecetoken_PieceJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1#J1","test","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECETOKEN,Jeton.CONSEQUENCEACTION,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piecetoken-ConsequenceAction-Piece-Joueur inconnu [6]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_151620232122_PasDetecteJoueur_CasePieceJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","J1","test","C1","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-ConsequenceAction-Case-Piece-Joueur inconnu [7]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_151720232122_PasDetectePiece_CasePieceJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1","test","C1","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-ConsequenceAction-Case-Piece-Joueur inconnu [7]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_15171820232122_PasDetectePieceJoueur_CasePieceJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1","J1","test","C1","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-ConsequenceAction-Case-Piece-Joueur inconnu [8]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_151820232122_PasDetectePiecetoken_CasePieceJoueur(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1#J1","test","C1","P2","J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECETOKEN,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECE,Jeton.JOUEUR);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc PieceToken-ConsequenceAction-Case-Piece-Joueur inconnu [7]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_1516202322_PasDetecteJoueur_CasePiecetoken(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","J1","test","C1","P2#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-ConsequenceAction-Case-PieceToken inconnu [6]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_1517202322_PasDetectePiece_CasePiecetoken(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1","test","C1","P2#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-ConsequenceAction-Case-PieceToken inconnu [6]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_151718202322_PasDetectePieceJoueur_CasePiecetoken(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1","J1","test","C1","P2#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-ConsequenceAction-Case-PieceToken inconnu [7]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_1518202322_PasDetectePiecetoken_CasePiecetoken(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1#J1","test","C1","P2#J2");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECETOKEN,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECETOKEN);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc PieceToken-ConsequenceAction-Case-PieceToken inconnu [6]",e.getMessage());
        }
    }

    @Test
    public final void testCase322_PasDetecte(){
        List<String> reS = Arrays.asList("J1","test");
        List<Jeton> reJ = Arrays.asList(Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE,Jeton.PIECETOKEN);

        automate.setEtatDeDepart(17);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Sujet-ConsequenceAction-PieceToken OU Sujet-ConsequenceAction-Piece-Joueur OU Sujet-ConsequenceAction-Case-PieceToken OU Sujet-ConsequenceAction-Case-Piece-Joueur inconnu [3]",e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------
    //---------------------------------------------3 2 3----------------------------------------------
    //------------------------------------------------------------------------------------------------

    @Test
    public final void testCase323_PasAssezArguments(){
        List<String> reS = Arrays.asList("test");
        List<Jeton> reJ = Arrays.asList(Jeton.CASE);
        automate.setEtatDeDepart(20);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Pas assez d'argument pour Sujet-ConsequenceAction-Case(T,J) [0]",e.getMessage());
        }
    }

    @Test
    public final void testCase323_15162023_PasDetecteJoueur_Case(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","J1","test","C1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Joueur-ConsequenceAction-Case inconnu [5]",e.getMessage());
        }
    }

    @Test
    public final void testCase323_15172023_PasDetectePiece_Case(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1","test","C1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.CONSEQUENCEACTION,Jeton.CASE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-ConsequenceAction-Case inconnu [5]",e.getMessage());
        }
    }

    @Test
    public final void testCase323_1517182023_PasDetectePieceJoueur_Case(){
        List<String> reS = Arrays.asList("P2","estpromu","alors","P1","J1","test","C1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.PIECE,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Piece-Joueur-ConsequenceAction-Case inconnu [6]",e.getMessage());
        }
    }

    @Test
    public final void testCase323_15182023_PasDetectePiecetoken_Case() {
        List<String> reS = Arrays.asList("P2", "estpromu", "alors", "P1#J1", "test", "C1");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE, Jeton.ETAT, Jeton.ALORS, Jeton.PIECETOKEN, Jeton.CONSEQUENCEACTION, Jeton.CASE);

        try {
            regle = automate.analyserUneRegle(reJ, reS);
            fail("Aucune Exception détectée");
        } catch (MauvaiseDefinitionRegleException e) {
            assertEquals("Bloc PieceToken-ConsequenceAction-Case inconnu [5]", e.getMessage());

        }
    }

    @Test
    public final void testCase323_PasDetecte(){
        List<String> reS = Arrays.asList("J1","test","test");
        List<Jeton> reJ = Arrays.asList(Jeton.JOUEUR,Jeton.CONSEQUENCEACTION,Jeton.CASE);

        automate.setEtatDeDepart(17);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc Sujet-ConsequenceAction-Case inconnu [2]",e.getMessage());
        }
    }


    //----------------------------------------------------------------------------------------------
    //----------------------------------------Tests Autres------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Test
    public final void testCase_EstPasTerminal(){
        List<String> reS = Arrays.asList("P1","estpromu","alors", "J1", "placer");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCEACTION);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        } catch (MauvaiseDefinitionRegleException e){
            assertEquals("Bloc incomplet: ne fini pas par un jeton terminal [4]",e.getMessage());
        }
    }

    @Test
    public final void testCase_EstInconnu(){
        List<String> reS = Arrays.asList("J1","estpromu");
        List<Jeton> reJ = Arrays.asList(Jeton.JOUEUR,Jeton.ETAT);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        } catch (MauvaiseDefinitionRegleException e){
            assertEquals("Transition inconnue (etat == null) : etat à l'état: 1(1)",e.getMessage());
        }
    }

    @Test
    public final void testCase_DoubleAlors(){
        List<String> reS = Arrays.asList("P1","estpromu", "alors", "J1", "gagner", "alors", "J2", "gagner");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ALORS,Jeton.JOUEUR,Jeton.CONSEQUENCETERMINALE,Jeton.ALORS, Jeton.JOUEUR,Jeton.CONSEQUENCETERMINALE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        } catch (MauvaiseDefinitionRegleException e){
            assertEquals("Double alors [5]",e.getMessage());
        }
    }

    @Test
    public final void testCase_DoubleConditionSansAlors(){
        List<String> reS = Arrays.asList("P1","estpromu","et","P2#J1","estpromu");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE,Jeton.ETAT,Jeton.ET,Jeton.PIECETOKEN,Jeton.ETAT);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        } catch (MauvaiseDefinitionRegleException e){
            assertEquals("Il faut définir au moins une consequence pour créer une règle",e.getMessage());
        }
    }

    @Test
    public final void testCase_PasDeCondition(){
        List<String> reS = Arrays.asList("J15","victoire");
        List<Jeton> reJ = Arrays.asList(Jeton.JOUEUR,Jeton.CONSEQUENCETERMINALE);

        automate.setEtatDeDepart(15);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        } catch (MauvaiseDefinitionRegleException e){
            assertEquals("Il faut définir au moins une condition pour créer une règle",e.getMessage());
        }
    }

    @Test
    public final void testCase_ManqueParenthese_Fermante(){
        List<String> reS = Arrays.asList("(","P2","estpromu","et","P2#J1","estpromu","alors");
        List<Jeton> reJ = Arrays.asList(Jeton.PARENTHESEOUVRANTE, Jeton.PIECE, Jeton.ETAT, Jeton.ET, Jeton.PIECETOKEN, Jeton.ETAT, Jeton.ALORS);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        } catch (MauvaiseDefinitionRegleException e){
            assertEquals("Probleme de paranthesage, trop de parenthèses ouvrante : 6",e.getMessage());
        }
    }

    @Test
    public final void testCase_ManqueParenthese_Ouvrante(){
        List<String> reS = Arrays.asList("P2","estpromu","et","P2#J1","estpromu",")");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE, Jeton.ETAT, Jeton.ET, Jeton.PIECETOKEN, Jeton.ETAT, Jeton.PARENTHESEFERMANTE);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        } catch (MauvaiseDefinitionRegleException e){
            assertEquals("Probleme de paranthesage, une paranthese fermante est présente alors qu'il n'existe pas de paranthèse ouvrante pour celle-ci : 5",e.getMessage());
        }
    }

    @Test
    public final void testCase_EtatDeDepartInvalide(){
        List<String> reS = Arrays.asList("test");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE);

        automate.setEtatDeDepart(200);
        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        } catch (MauvaiseDefinitionRegleException e){
            assertEquals("Impossible de commencer une analyse en partant de l'état de départ : 200",e.getMessage());
        }
    }
}
