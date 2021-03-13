import org.example.model.EquationDeDeplacement.*;
import org.example.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static org.junit.Assert.*;

public class EquationDeDeplacementTest {

    /*
    =========================================
    TEST POUR FACTORY_POSITION_DE_DEPLACEMENT
    =========================================
    */

    @Test
    @TestFactory
    public final void testFactoryPositionDeDeplacement_testBon() {
        FactoryEquationDeDeplacement facto = new FactoryPositionDeDeplacement("3###2");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
        }catch(MauvaiseImplementationPositionDeDeplacementException op){
            fail("FactoryPositionDeDeplacement exception : " + op.getMessage());
        }catch (MauvaiseImplementationEquationDeDeplacementException oe) {
            fail("FactoryEquationDeDeplacement exception : " + oe.getMessage());
        }
    }

    @Test
    @TestFactory
    public final void testFactoryPositionDeDeplacement_erreurSeparateurInvalide() {
        FactoryEquationDeDeplacement facto = new FactoryPositionDeDeplacement("3##2");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Pas assez d'arguments de définition (x###y)",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    @Test
    @TestFactory
    public final void testFactoryPositionDeDeplacement_erreurTropDeSeparateur() {
        FactoryEquationDeDeplacement facto = new FactoryPositionDeDeplacement("3###2###3");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Trop d'arguments de définition (x###y)",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    @Test
    @TestFactory
    public final void testFactoryPositionDeDeplacement_erreurSeparateurInvelideMaisDeuxCoordonnees() {
        FactoryEquationDeDeplacement facto = new FactoryPositionDeDeplacement("3###2##3");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Valeur non numérique pour y : 2##3",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    @Test
    @TestFactory
    public final void testFactoryPositionDeDeplacement_erreurCoordonneeInvalide() {
        FactoryEquationDeDeplacement facto = new FactoryPositionDeDeplacement("a###23");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Valeur non numérique pour x : a",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    @Test
    @TestFactory
    public final void testFactoryPositionDeDeplacement_erreurChaineVide() {
        FactoryEquationDeDeplacement facto = new FactoryPositionDeDeplacement("");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Pas assez d'arguments de définition (x###y)",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    /*
    ========================================
    TEST POUR FACTORY_VECTEUR_DE_DEPLACEMENT
    ========================================
    */

    @Test
    @TestFactory
    public final void testFactoryVecteurDeDeplacement_testBon() {
        FactoryEquationDeDeplacement facto = new FactoryVecteurDeDeplacement("3###2");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
        }catch(MauvaiseImplementationVecteurDeDeplacementException op){
            fail("FactoryVecteurDeDeplacement exception : " + op.getMessage());
        }catch (MauvaiseImplementationEquationDeDeplacementException oe) {
            fail("FactoryEquationDeDeplacement exception : " + oe.getMessage());
        }
    }

    @Test
    @TestFactory
    public final void testFactoryVecteurDeDeplacement_erreurSeparateurInvalide() {
        FactoryEquationDeDeplacement facto = new FactoryVecteurDeDeplacement("3##2");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Pas assez d'arguments de définition (x###y)",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    @Test
    @TestFactory
    public final void testFactoryVecteurDeDeplacement_erreurTropDeSeparateur() {
        FactoryEquationDeDeplacement facto = new FactoryVecteurDeDeplacement("3###2###3");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Trop d'arguments de définition (x###y)",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    @Test
    @TestFactory
    public final void testFactoryVecteurDeDeplacement_erreurSeparateurInvelideMaisDeuxCoordonnees() {
        FactoryEquationDeDeplacement facto = new FactoryVecteurDeDeplacement("3###2##3");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Valeur non numérique pour y : 2##3",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    @Test
    @TestFactory
    public final void testFactoryVecteurDeDeplacement_erreurCoordonneeInvalide() {
        FactoryEquationDeDeplacement facto = new FactoryVecteurDeDeplacement("a###23");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Valeur non numérique pour x : a",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    @Test
    @TestFactory
    public final void testFactoryVecteurDeDeplacement_erreurChaineVide() {
        FactoryEquationDeDeplacement facto = new FactoryVecteurDeDeplacement("");
        try {
            EquationDeDeplacement eq = facto.createEquationDeDeplacement();
            fail("Exception non détectée : EQ : X=" + eq.getX() + " | Y="+eq.getY());
        }catch(MauvaiseImplementationEquationDeDeplacementException op){
            assertEquals("Pas assez d'arguments de définition (x###y)",op.getMessage());
            return;
        }
        fail("Aucune Exception détectée");
    }

    /*
    ============================
    TEST POSITION_DE_DEPLACEMENT
    ============================
    */

    @Test
    public final void testPositionDeDeplacement_bonneCreation() {
        FactoryEquationDeDeplacement facto = new FactoryPositionDeDeplacement("2###23");
        EquationDeDeplacement eq;
        try {
             eq = facto.createEquationDeDeplacement();
        }
        catch(MauvaiseImplementationPositionDeDeplacementException op){
            fail("FactoryPositionDeDeplacement exception : " + op.getMessage());
            return;
        }catch (MauvaiseImplementationEquationDeDeplacementException oe) {
            fail("FactoryEquationDeDeplacement exception : " + oe.getMessage());
            return;
        }
        assertEquals(2,eq.getX());
        assertEquals(23,eq.getY());
    }

    @Test
    public final void testPositionDeDeplacement_bonneEvaluation() {
        FactoryEquationDeDeplacement facto = new FactoryPositionDeDeplacement("2###23");
        EquationDeDeplacement eq;
        try {
            eq = facto.createEquationDeDeplacement();
        }
        catch(MauvaiseImplementationPositionDeDeplacementException op){
            fail("FactoryPositionDeDeplacement exception : " + op.getMessage());
            return;
        }catch (MauvaiseImplementationEquationDeDeplacementException oe) {
            fail("FactoryEquationDeDeplacement exception : " + oe.getMessage());
            return;
        }
        Position pos = new Position(4,2);
        Position pos2 = eq.evaluate(pos);
        assertEquals(6,pos2.getX());
        assertEquals(25,pos2.getY());
        assertFalse(eq.canEvaluate());
    }

    /*
    ============================
    TEST VECTEUR_DE_DEPLACEMENT
    ============================
    */

    @Test
    public final void testVecteurDeDeplacement_bonneCreation() {
        FactoryEquationDeDeplacement facto = new FactoryVecteurDeDeplacement("2###23");
        EquationDeDeplacement eq;
        try {
            eq = facto.createEquationDeDeplacement();
        }
        catch(MauvaiseImplementationVecteurDeDeplacementException op){
            fail("FactoryVecteurDeDeplacement exception : " + op.getMessage());
            return;
        }catch (MauvaiseImplementationEquationDeDeplacementException oe) {
            fail("FactoryEquationDeDeplacement exception : " + oe.getMessage());
            return;
        }
        assertEquals(2,eq.getX());
        assertEquals(23,eq.getY());
    }

    @Test
    public final void testVecteurDeDeplacement_bonneEvaluation() {
        FactoryEquationDeDeplacement facto = new FactoryVecteurDeDeplacement("2###23");
        EquationDeDeplacement eq;
        try {
            eq = facto.createEquationDeDeplacement();
        }
        catch(MauvaiseImplementationVecteurDeDeplacementException op){
            fail("FactoryVecteurDeDeplacement exception : " + op.getMessage());
            return;
        }catch (MauvaiseImplementationEquationDeDeplacementException oe) {
            fail("FactoryEquationDeDeplacement exception : " + oe.getMessage());
            return;
        }
        Position pos = new Position(4,2);
        Position pos2 = eq.evaluate(pos);
        assertEquals(6,pos2.getX());
        assertEquals(25,pos2.getY());
        assertTrue(eq.canEvaluate());
    }

}