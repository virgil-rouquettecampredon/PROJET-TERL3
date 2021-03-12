import org.example.model.OrdreDesJoueurs;
import org.example.model.OrdreDesJoueursException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;

public class OrdreDesJoueursTest {
    @Test
    public final void testConstructeur() {
        try {
            OrdreDesJoueurs.verifierOrdre("123", 3);
        } catch (OrdreDesJoueursException oe) {
            fail("OrdreDesJoueurs exception AAAAAAAAAAA");
        }
    }
}