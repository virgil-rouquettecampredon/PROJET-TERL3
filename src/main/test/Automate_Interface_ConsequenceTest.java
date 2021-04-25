import org.example.model.GroupCases;
import org.example.model.Joueur;
import org.example.model.Piece;
import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Automate.Automate_Interface;
import org.example.model.Regles.Structure.Automate.Automate_Interface_Consequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Automate_Interface_ConsequenceTest {
    private List<Joueur> joueurs;
    private List<GroupCases> cases;
    private List<Piece> pieces;
    private Automate_Interface<Jeton_Interface> auto;


    @BeforeEach
    public void initialiser_Automate(){
        joueurs = new ArrayList<>();
        cases = new ArrayList<>();
        pieces = new ArrayList<>();

        auto = new Automate_Interface_Consequence(pieces,cases,joueurs);
        auto.initialiserAutomate();
    }


    /*=================================================================
    * ========================= TESTS MAUVAIS =========================
    * =================================================================*/


    //TEST ETAT INITIAL
    @Test
    public final void test_selectionnerElement_Phase0_Mauvais(){
        ElementRegle e = new ElementRegle(Jeton_Interface.ALORS,"test","test");
        try {
            auto.selectionnerElement(e);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 0 --ALORS-> ?",ex.getMessage());
        }
    }

    //TEST JOUEUR
    @Test
    public final void test_selectionnerElement_Phase1_JOUEUR_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.ALORS,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 1 --ALORS-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase2_JOUEURTER_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_TERMINALE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e1);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 4 --JOUEUR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase2_JOUEURPLACER_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PLACER,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e1);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 5 --JOUEUR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase3_JOUEURPLACERPIECE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PLACER,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 9 --PIECE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase3_JOUEURPLACERPIECETOKEN_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PLACER,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e1);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 10 --JOUEUR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase4_JOUEURPLACERPIECEJOUEUR_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PLACER,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 10 --PIECE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase4_JOUEURPLACERPIECECASE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PLACER,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 13 --PIECE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase4_JOUEURPLACERPIECETOKEN_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PLACER,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 13 --PIECETOKEN-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase5_JOUEURPLACERPIECE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PLACER,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 13 --CONSEQUENCE_PLACER-> ?",ex.getMessage());
        }
    }


    //TEST PIECE
    @Test
    public final void test_selectionnerElement_Phase1_PIECE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_TERMINALE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 2 --CONSEQUENCE_TERMINALE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase2_PIECEPRENDRE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PRENDRE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 6 --CONSEQUENCE_PRENDRE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase3_PIECEPRENDREPIECE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PRENDRE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 12 --PIECE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase3_PIECEPRENDREPIECETOKEN_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PRENDRE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 11 --PIECETOKEN-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase4_PIECEPRENDREPIECEJOUEUR_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PRENDRE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 11 --JOUEUR-> ?",ex.getMessage());
        }
    }


    //TEST PIECE+JOUEUR
    @Test
    public final void test_selectionnerElement_Phase2_PIECEJOUEUR_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 3 --JOUEUR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase3_PIECEJOUEURPROMOUVOIR_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PROMOUVOIR,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 7 --CONSEQUENCE_PROMOUVOIR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase4_PIECEJOUEURPROMOUVOIR_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.CONSEQUENCE_PROMOUVOIR,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 13 --CONSEQUENCE_PROMOUVOIR-> ?",ex.getMessage());
        }
    }


    //TEST PIECETOKEN
    @Test
    public final void test_selectionnerElement_Phase1_PIECETOKEN_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 3 --JOUEUR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase2_PIECETOKENDEPLACER_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.CONSEQUENCE_DEPLACER,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 8 --JOUEUR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_Phase3_PIECETOKENDEPLACER_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.CONSEQUENCE_DEPLACER,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 13 --CONSEQUENCE_DEPLACER-> ?",ex.getMessage());
        }
    }
}
