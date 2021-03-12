import org.example.model.OrdreDesJoueurs;
import org.example.model.OrdreDesJoueursException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;

public class OrdreDesJoueursTest {
    @Test
    public final void testConstructeur() {
        try {
            OrdreDesJoueurs.verifierOrdre("123", 3);
        } catch (OrdreDesJoueursException oe) {
            fail("OrdreDesJoueurs exception : " + oe.getMessage());
        }
    }

    @Test
    public final void testVerifierOrdre_erreur123avec2j() {
        Assertions.assertThrows(OrdreDesJoueursException.class, ()-> {OrdreDesJoueurs.verifierOrdre("123", 2);});
    }

    @Test
    public final void testVerifierOrdre_erreur123avec4j() {
        try {
            OrdreDesJoueurs.verifierOrdre("123", 4);
            fail("Exception non détectée");
        } catch (OrdreDesJoueursException oe) {
            Assertions.assertEquals(oe.getMessage(), "Joueur non présent dans la séquence de jeu : 4");
        }
    }

    @Test
    public final void testVerifierOrdre_erreur13Et3J() {
        try {
            OrdreDesJoueurs.verifierOrdre("13", 3);
            fail("Exception non détectée");
        } catch (OrdreDesJoueursException oe) {
            Assertions.assertEquals(oe.getMessage(), "Joueur non présent dans la séquence de jeu : 2");
        }
    }

    @Test
    public final void testVerifierOrdre_erreur1203() {
        try {
            OrdreDesJoueurs.verifierOrdre("1203", 3);
            fail("Exception non détectée");
        } catch (OrdreDesJoueursException oe) {
            Assertions.assertEquals(oe.getMessage(), "Joueur renseigné invalide.");
        }
    }

    @Test
    public final void testVerifierOrdre_erreur156893avec4j() {
        try {
            OrdreDesJoueurs.verifierOrdre("156893", 4);
            fail("Exception non détectée");
        } catch (OrdreDesJoueursException oe) {
            Assertions.assertEquals(oe.getMessage(), "Joueur renseigné invalide.");
        }
    }

    @Test
    public final void testVerifierOrdre_erreur125avec3j() {
        try {
            OrdreDesJoueurs.verifierOrdre("125", 3);
            fail("Exception non détectée");
        } catch (OrdreDesJoueursException oe) {
            Assertions.assertEquals(oe.getMessage(), "Joueur renseigné invalide.");
        }
    }

}