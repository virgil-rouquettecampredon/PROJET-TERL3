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
    @Test
    public final void test(){

    }
}
