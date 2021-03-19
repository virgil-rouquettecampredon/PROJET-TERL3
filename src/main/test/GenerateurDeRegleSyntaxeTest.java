import org.example.model.Regles.GenerateurDeRegle;
import org.example.model.Regles.MauvaiseDefinitionRegleException;
import org.junit.Test;

import static org.junit.Assert.fail;

public class GenerateurDeRegleSyntaxeTest {
    @Test
    public final void testBlocJoueurALL_testBon() {
        try{
            GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JALL",0);
                
        }catch (MauvaiseDefinitionRegleException m){
            fail("Exception Levée : " + m.getMessage());
        }

    }
}
