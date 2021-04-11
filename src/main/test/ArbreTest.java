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
            public boolean evaluer() throws EvaluableException {
                return true;
            }
        };
        condFaux = new Condition() {
            @Override
            public boolean evaluer() throws EvaluableException {
                return false;
            }
        };
    }


    /**==============================================
     * ==============TESTS Mauvais===================
     * ==============================================*/

    @Test
    public final void test_(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condVrai);

    }

}
