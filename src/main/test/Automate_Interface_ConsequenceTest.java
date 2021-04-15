import org.example.model.GroupCases;
import org.example.model.Joueur;
import org.example.model.Piece;
import org.example.model.Regles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
            assertEquals("Transition inconnue : 1 --JOUEUR-> ?",ex.getMessage());
        }
    }
}
