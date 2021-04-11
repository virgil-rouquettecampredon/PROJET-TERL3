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

public class ArbreTest {

    private Arbre_Formule<Condition> arbre;
    private List<Jeton> jetons;
    private List<Condition> conditions;

    private Condition condVrai;
    private Condition condFaux;

    @BeforeEach
    public void initialiser_Automate() {
        condVrai = new Condition() {
            @Override
            public boolean evaluer(){
                return true;
            }
        };
        condFaux = new Condition() {
            @Override
            public boolean evaluer(){
                return false;
            }
        };
    }


    /**==============================================
     * ======== TESTS Mauvais CONSTRUCTION ==========
     * ==============================================*/

    @Test
    public final void test_Construction_ManqueCond_Mauvais(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : Condition manquante à : 1 (3)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_Mauvais(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertEquals("Vous n'avez pas utilisé l'ensemble des conditions définies à la construction de votre Arbre_Condition.", arbre.getWarning());
        }catch (ArbreException e){
            fail("Aucune Exception détectée");
        }
    }

    @Test
    public final void test_Construction_ParentheseOuvrantePasFermante_Mauvais(){
        jetons = Arrays.asList(Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : Impossible d'avancer : condition (4)",e.getMessage());
        }
    }
}
