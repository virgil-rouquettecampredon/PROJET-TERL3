package org.example.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrdreDesJoueursTest {

    @Test
    public final void testConstructeur() {
        try {
            OrdreDesJoueurs o = new OrdreDesJoueurs("123", 3);
        } catch (OrdreDesJoueursException oe) {
            fail("OrdreDesJoueurs exception");
        }

    }

}
