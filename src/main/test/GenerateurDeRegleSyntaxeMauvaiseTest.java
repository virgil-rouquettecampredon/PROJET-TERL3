import org.example.model.Regles.Jeton;
import org.example.model.Regles.GenerateurDeRegle;
import org.example.model.Regles.MauvaiseDefinitionRegleException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GenerateurDeRegleSyntaxeMauvaiseTest {

    /**==================================================
     * ================TESTS Mauvais Joueur=================
     * ==================================================*/
    @Test
    public final void testBlocJoueurVide_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur incorrecte");
        }
    }
    @Test
    public final void testBlocJoueur_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("A",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur incorrecte (doit commencer par J)");
        }
    }
    @Test
    public final void testBlocJoueurJ_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("J",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur incorrecte");
        }
    }
    @Test
    public final void testBlocJoueurJA_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JA",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur (numero) incorrecte");
        }
    }
    @Test
    public final void testBlocJoueurJAL_testMauvais() {
        try{
            Jeton jeton = GenerateurDeRegle.estSyntaxiquementCorrecte_Joueur("JAL",0);
            fail("Exception non détectée");
        }catch (MauvaiseDefinitionRegleException m){
            assertEquals(m.getMessage(),"Syntaxe du joueur (numero) incorrecte");
        }
    }
}
