import org.example.model.GroupCases;
import org.example.model.Joueur;
import org.example.model.Piece;
import org.example.model.Regles.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Automate_Interface_ConditionTest {
    private List<Joueur> joueurs;
    private List<GroupCases> cases;
    private List<Piece> pieces;
    private Automate_Interface<Jeton_Interface> auto;


    @BeforeEach
    public void initialiser_Automate(){
        joueurs = new ArrayList<>();
        cases = new ArrayList<>();
        pieces = new ArrayList<>();

        auto = new Automate_Interface_Condition(pieces,cases,joueurs);
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


    //TEST PARCOURS PIECE
    @Test
    public final void test_selectionnerElement_P_Phase1_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface. PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.ALORS,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 2 --ALORS-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_P_Phase2_COMPTEURDEP_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ALORS,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 4 --ALORS-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_P_Phase2_PROMU_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PROMU,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.FIN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 5 --FIN-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_P_Phase2_PREND_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PREND,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.FIN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 7 --FIN-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_P_Phase2_PLACE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PLACE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.FIN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 9 --FIN-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_P_Phase3_PRENDPIECE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PREND,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 12 --PIECE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_P_Phase3_PRENDPIECETOKEN_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PREND,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 14 --CASE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_P_Phase3_PRENDCASE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PREND,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 15 --JOUEUR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_P_Phase3_PLACE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PLACE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.FIN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 15 --FIN-> ?",ex.getMessage());
        }
    }


    //TEST PARCOURS PIECE+JOUEUR
    @Test
    public final void test_selectionnerElement_PT_Phase1_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 3 --JOUEUR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PJ_Phase2_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ALORS,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 3 --ALORS-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PJ_Phase3_DEPLACE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.DEPLACE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e1);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 8 --PIECE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PJ_Phase3_EST_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.EST,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e1);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 10 --PIECE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PJ_Phase3_MENACE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ESTMENACE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 6 --JOUEUR-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PJ_Phase4_DEPLACE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.DEPLACE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e4);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 15 --CASE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PJ_Phase4_EST_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.EST,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 15 --EST-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PJ_Phase4_MENACEPIECETOKEN_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ESTMENACE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e1);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 14 --PIECE-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PJ_Phase4_MENACEPIECE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ESTMENACE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.FIN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 12 --FIN-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PJ_Phase5_MENACEPIECE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ESTMENACE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.FIN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 14 --FIN-> ?",ex.getMessage());
        }
    }


    //TEST PARCOURS JOUEUR
    @Test
    public final void test_selectionnerElement_J_Phase1_Mauvais(){
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
    public final void test_selectionnerElement_J_Phase2_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ALORS,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 4 --ALORS-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_J_Phase3_NON_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.ET,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 11 --ET-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_J_Phase3_COMPARATEUR_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.ET,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 13 --ET-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_J_Phase4_NON_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.OU,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 13 --OU-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_J_Phase4_NOMBRE_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.FIN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 16 --FIN-> ?",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_J_Phase5_NON_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e6 = new ElementRegle(Jeton_Interface.FIN,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            auto.selectionnerElement(e6);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 16 --FIN-> ?",ex.getMessage());
        }
    }


    //TEST PARENTHESAGE
    @Test
    public final void test_selectionnerElement_PARENTHESAGE_1_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ESTMENACE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.PARENTHESE_FERMANTE,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer une parenthèse fermante ici",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PARENTHESAGE_2_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PARENTHESE_OUVRANTE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.ESTMENACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e6 = new ElementRegle(Jeton_Interface.PARENTHESE_FERMANTE,"test","test");
        ElementRegle e7 = new ElementRegle(Jeton_Interface.ALORS,"test","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e7);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("",ex.getMessage());
        }
    }

}
