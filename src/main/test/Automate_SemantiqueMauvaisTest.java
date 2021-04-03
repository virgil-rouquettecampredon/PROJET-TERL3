import org.example.model.Piece;
import org.example.model.Regles.*;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Automate_SemantiqueMauvaisTest {

    private Automate_Semantique automate;
    private List<BlocDeRegle> regle;

    @BeforeEach
    public void initialiser_Automate(){
        automate = new Automate_Semantique();
        automate.initialiserAutomate();
        regle = new ArrayList<>();
    }

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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
            assertEquals("Bloc Piece-Etat OU Piece-Joueur-Etat OU PieceToken-Etat inconnu [2]",e.getMessage());
        }
    }

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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
            assertEquals("Bloc Piece-Action-Piece OU Piece-Joueur-Action-Piece OU PieceToken-Action-Piece inconnu [3]",e.getMessage());
        }
    }

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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
            assertEquals("Bloc Piece-Action-Piece-Joueur OU Piece-Action-PieceToken OU Piece-Joueur-Action-Piece-Joueur OU Piece-Joueur-Action-PieceToken OU PieceToken-Action-Piece-Joueur OU PieceToken-Action-PieceToken inconnu [4]",e.getMessage());
        }
    }

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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
            assertEquals("Bloc Piece-Action-Case OU Piece-Joueur-Action-Case OU PieceToken-Action-Case inconnu [3]",e.getMessage());
        }
    }

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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
            assertEquals("Bloc Joueur-Compteur-Comparaison-Nombre inconnu [4]",e.getMessage());
        }
    }

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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
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
        }catch (MauvaiseSemantiqueRegleException e){
            assertEquals("Bloc Piece-Compteur-Comparaison-Nombre OU Piece-Joueur-Compteur-Comparaison-Nombre OU PieceToken-Compteur-Comparaison-Nombre inconnu [5]",e.getMessage());
        }
    }
}
