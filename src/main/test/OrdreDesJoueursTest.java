import org.example.model.OrdreDesJoueurs;
import org.example.model.OrdreDesJoueursException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OrdreDesJoueursTest {

    /**==================================================
     * ================| TESTS MAUVAIS |=================
     * ==================================================*/
    @Test
    public void test_verifierOrdre_chainevide(){
        try{
            OrdreDesJoueurs.verifierOrdre("",5);
            fail("Exception non reconnue");
        }catch (OrdreDesJoueursException e){
            assertEquals("Joueur non renseigné dans la chaine : 1",e.getMessage());
        }
    }
    @Test
    public void test_verifierOrdre_invalide_mauvais(){
        try{
            OrdreDesJoueurs.verifierOrdre("J7J2J3J5J1J1J2",5);
            fail("Exception non reconnue");
        }catch (OrdreDesJoueursException e){
            assertEquals("Joueur renseigné invalide : 7",e.getMessage());
        }
    }
    @Test
    public void test_verifierOrdre_identificateur_invalide(){
        try{
            OrdreDesJoueurs.verifierOrdre("J2J3J5J1JHugolepasboJ1J2",5);
            fail("Exception non reconnue");
        }catch (OrdreDesJoueursException e){
            assertEquals("Identificateur de Joueur invalide : Hugolepasbo",e.getMessage());
        }
    }
    @Test
    public void test_verifierOrdre_identificateur_invalide_JJ(){
        try{
            OrdreDesJoueurs.verifierOrdre("J2J3J5J1JJJ1J2",5);
            fail("Exception non reconnue");
        }catch (OrdreDesJoueursException e){
            assertEquals("Identificateur de Joueur invalide : ",e.getMessage());
        }
    }
    @Test
    public void test_verifierOrdre_joueur_non_renseigne(){
        try{
            OrdreDesJoueurs.verifierOrdre("J3J5J1J1J1J2",5);
            fail("Exception non reconnue");
        }catch (OrdreDesJoueursException e){
            assertEquals("Joueur non renseigné dans la chaine : 4",e.getMessage());
        }
    }


    /**==================================================
     * =================| TESTS BONS |===================
     * ==================================================*/
    @Test
    public void test_verifierOrdre_valide(){
        try{
            OrdreDesJoueurs.verifierOrdre("J3J5J1J4J1J2",5);
        }catch (OrdreDesJoueursException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }

    @Test
    public void test_get_valide(){
        String ordreStr = "J3J5J1J4J1J2";
        int nbJoueur = 5;

        OrdreDesJoueurs ordre = new OrdreDesJoueurs(ordreStr, nbJoueur);
        List<Integer> test = new ArrayList(Arrays.asList(3,5,1,4,1,2,3,5,1,4,1,2,3,5,1,4,1,2));
        try{
            OrdreDesJoueurs.verifierOrdre(ordreStr,nbJoueur);
            for(int i = 0;i<15;i++){
                assertEquals(ordre.get(),test.get(i));
            }
        }catch (OrdreDesJoueursException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
}