import org.example.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import org.example.model.Regles.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OrdonnanceurDeJeuTest {
    public OrdonnanceurDeJeu ordonnanceurDeJeu;
    public Variante<Jeton> varianteTest;

    @BeforeEach
    public void initializeTest() {
        VarianteManager vm = new VarianteManager();
        varianteTest = vm.createVarianteClassique();
        ordonnanceurDeJeu = new OrdonnanceurDeJeu(varianteTest.getJoueurs(), varianteTest.getPlateau());
    }

    /**==================================================
     * ================| TESTS MAUVAIS |=================
     * ==================================================*/
    @Test
    public void test_deplacer_pas_bon_joueur(){
        try{
            Case origine = varianteTest.getPlateau().getCase(new Position(0, 1));
            Joueur j = varianteTest.getOrdrejoueur().get(0);
            Case destination = varianteTest.getPlateau().getCase(new Position(0, 2));
            ordonnanceurDeJeu.deplacerPiece(origine, j, destination);
            fail("Exception non reconnue");
        }catch (DeplacementException e){
            assertEquals("Pas le bon joueur",e.getMessage());
        }
    }

}
