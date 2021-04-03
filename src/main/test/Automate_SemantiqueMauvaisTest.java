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
    List<BlocDeRegle> regle;

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
    public final void testCase306_026_PasDetecte(){
        List<String> reS = Arrays.asList("P1","test");
        List<Jeton> reJ = Arrays.asList(Jeton.PIECE, Jeton.ETAT);

        try {
            regle = automate.analyserUneRegle(reJ,reS);
            fail("Aucune Exception détectée");
        }catch (MauvaiseSemantiqueRegleException e){
            assertEquals("Bloc Piece-Etat inconnu [1]",e.getMessage());
        }

    }
}
