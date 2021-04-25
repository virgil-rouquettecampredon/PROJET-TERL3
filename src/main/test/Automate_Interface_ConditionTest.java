import org.example.model.GroupCases;
import org.example.model.Joueur;
import org.example.model.Piece;
import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Automate.Automate_Interface;
import org.example.model.Regles.Structure.Automate.Automate_Interface_Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Transition inconnue : 15 --JOUEUR-> ?",ex.getMessage());
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
            assertEquals("Impossible de terminer les conséquences, il manque encore une parenthèse fermante",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PARENTHESAGE_3_Mauvais(){
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
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e7);
            fail("Excepion non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer une parenthèse fermante ici",ex.getMessage());
        }
    }

    @Test
    public final void test_selectionnerElement_PARENTHESAGE_4_Mauvais(){
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
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e7);

            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible de terminer les conséquences, il manque encore 2 parenthèses fermantes",ex.getMessage());
        }
    }

    //TEST ALIAS
    @Test
    public final void test_AliasParentheseOuvrante_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PARENTHESE_OUVRANTE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");
        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 0",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasNON_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 18",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasNON_ParentheseOuvrante_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PARENTHESE_OUVRANTE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 0",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasJoueurCompteur_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 4",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasJoueurCompteurNON_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 11",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasJoueurCompteurComparateur_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 13",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasJoueurCompteurNONComparateur_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 13",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasJoueurCompteurComparateurNombre_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 16",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasJoueurCompteurNONComparateurNombre_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_TEMPSRESTANT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e6 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 16",ex.getMessage());
        }
    }

    @Test
    public final void test_AliasPieceCompteurDeplacement_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 4",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceCompteurDeplacementNON_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 11",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceCompteurDeplacementComparateur_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 13",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceCompteurDeplacementNONComparateur_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 13",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceCompteurDeplacementComparateurNombre_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 16",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceCompteurDeplacementNONComparateurNombre_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e6 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 16",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPiecePromu_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PROMU,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 5",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceEstMenace_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.ESTMENACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 6",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPiecePrend_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PREND,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 7",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceDeplace_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.DEPLACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 8",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPiecePlace_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PLACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 9",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceEst_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.EST,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 10",ex.getMessage());
        }
    }

    @Test
    public final void test_AliasPieceJoueurCompteurDeplacement_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 4",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurCompteurDeplacementNON_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 11",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurCompteurDeplacementComparateur_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 13",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurCompteurDeplacementNONComparateur_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e6 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 13",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurCompteurDeplacementComparateurNombre_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e6 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 16",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurCompteurDeplacementNONComparateurNombre_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e7 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e6 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e7);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 16",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurPromu_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PROMU,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 5",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurEstMenace_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.ESTMENACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 6",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurPrend_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PREND,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 7",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurDeplace_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.DEPLACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 8",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurPlace_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PLACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 9",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceJoueurEst_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECE,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.JOUEUR,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.EST,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 10",ex.getMessage());
        }
    }

    @Test
    public final void test_AliasPieceTokenCompteurDeplacement_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 4",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenCompteurDeplacementNON_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 11",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenCompteurDeplacementComparateur_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 13",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenCompteurDeplacementNONComparateur_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 13",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenCompteurDeplacementComparateurNombre_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 16",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenCompteurDeplacementNONComparateurNombre_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.COMPTEUR_DEPLACEMENT,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.NON,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.COMPARATEUR,"test","test");
        ElementRegle e6 = new ElementRegle(Jeton_Interface.NOMBRE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 16",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenPromu_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PROMU,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 5",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenEstMenace_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.ESTMENACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 6",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenPrend_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PREND,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 7",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenDeplace_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.DEPLACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 8",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenPlace_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.PLACE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 9",ex.getMessage());
        }
    }
    @Test
    public final void test_AliasPieceTokenEst_Mauvais(){
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.EST,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 10",ex.getMessage());
        }
    }

    @Test
    public final void test_AliasSujetActionCibleParentheseFermante_Mauvais(){
        ElementRegle e6 = new ElementRegle(Jeton_Interface.PARENTHESE_OUVRANTE,"test","test");
        ElementRegle e1 = new ElementRegle(Jeton_Interface.PIECETOKEN,"test","test");
        ElementRegle e2 = new ElementRegle(Jeton_Interface.EST,"test","test");
        ElementRegle e3 = new ElementRegle(Jeton_Interface.CASE,"test","test");
        ElementRegle e4 = new ElementRegle(Jeton_Interface.PARENTHESE_FERMANTE,"test","test");
        ElementRegle e5 = new ElementRegle(Jeton_Interface.ALIAS,"as","test");

        try {
            auto.selectionnerElement(e6);
            auto.selectionnerElement(e1);
            auto.selectionnerElement(e2);
            auto.selectionnerElement(e3);
            auto.selectionnerElement(e4);
            auto.selectionnerElement(e5);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException ex){
            assertEquals("Impossible d'appliquer un ALIAS ici : 17",ex.getMessage());
        }
    }
}
