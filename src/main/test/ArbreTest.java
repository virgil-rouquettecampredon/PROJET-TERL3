import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Arbre.ArbreException;
import org.example.model.Regles.Structure.Arbre.Arbre_Condition;
import org.example.model.Regles.Structure.Arbre.Arbre_Formule;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void initialiser_Auto(){
        condVrai = new Condition() {
            @Override
            public void verifierElements(OrdonnanceurDeJeu ord){ }

            @Override
            public List getSujets(){
                return null;
            }

            @Override
            public boolean evaluer(){
                return true;
            }
        };
        condFaux = new Condition() {
            @Override
            public void verifierElements(OrdonnanceurDeJeu ord){}

            @Override
            public List<?> getSujets(){
                return null;
            }

            @Override
            public boolean evaluer(){
                return false;
            }
        };
    }

    /**==============================================
     * ======== TESTS Mauvais CONSTRUCTION ==========
     * ==============================================*/
    @Test
    public final void test_Construction_ManqueCond_Mauvais(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : Condition manquante à : 1 (2)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_Mauvais(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertEquals("Vous n'avez pas utilisé l'ensemble des conditions définies à la construction de votre Arbre_Condition.", arbre.getWarning());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Construction_ParentheseOuvrantePasFermante_Mauvais(){
        jetons = Arrays.asList(Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);

        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : élément non reconnu : [attendu : PARENTHESEFERMANTE | recu : null] (4)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_ParentheseOuvrantePasFermante_MultiParenthese_Mauvais(){
        jetons = Arrays.asList(Jeton.PARENTHESEOUVRANTE,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : élément non reconnu : [attendu : PARENTHESEFERMANTE | recu : null] (8)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_JetonNonReconnu_1_Mauvais(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.CONDITION,Jeton.OU);
        conditions = Arrays.asList(condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : élément non reconnu : CONDITION (1)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_JetonNonReconnu_2_Mauvais(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.ET,Jeton.OU,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : élément non reconnu : OU (4)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_JetonNonReconnu_3_Mauvais(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE,Jeton.OU,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : élément non reconnu : PARENTHESEFERMANTE (3)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_JetonNonReconnu_4_Mauvais(){
        jetons = Arrays.asList(Jeton.CASE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : élément non reconnu : CASE (0)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_JetonNonReconnu_NEGATION_1_Mauvais(){
        jetons = Arrays.asList(Jeton.NON,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condVrai,condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : élément non reconnu : [attendu : PARENTHESEOUVRANTE | recu : CONDITION] (1)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_JetonNonReconnu_NEGATION_2_Mauvais(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : élément non reconnu : [attendu : PARENTHESEFERMANTE | recu : null] (7)",e.getMessage());
        }
    }
    @Test
    public final void test_Construction_JetonNonReconnu_NEGATION_3_Mauvais(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.PARENTHESEFERMANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            fail("Aucune Exception détectée");
        }catch (ArbreException e){
            assertEquals("Construction error : Erreur : élément non reconnu : PARENTHESEFERMANTE (2)",e.getMessage());
        }
    }

    /**==============================================
     * ========== TESTS bon CONSTRUCTION ============
     * ==============================================*/
    @Test
    public final void test_Evaluation_VETV_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("ET<T,T>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONVETV_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condVrai,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("OU<¬T,¬T>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_VETF_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("ET<T,F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONVETF_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("OU<¬T,¬F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_FETV_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("ET<F,T>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONFETV_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("OU<¬F,¬T>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_FETF_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condFaux,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("ET<F,F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONFETF_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condFaux,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("OU<¬F,¬F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_VOUV_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.OU,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("OU<T,T>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONVOUV_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condVrai,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("ET<¬T,¬T>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_VOUF_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.OU,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("OU<T,F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONVOUF_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("ET<¬T,¬F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_FOUV_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.OU,Jeton.CONDITION);
        conditions = Arrays.asList(condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("OU<F,T>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONFOUV_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condFaux,condVrai);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("ET<¬F,¬T>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_FOUF_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.OU,Jeton.CONDITION);
        conditions = Arrays.asList(condFaux,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("OU<F,F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONFOUF_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condFaux,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("ET<¬F,¬F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_VOUVETF_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("OU<T,ET<T,F>>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONVOUVETF_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condVrai,condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("ET<¬T,OU<¬T,¬F>>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_ParentOVOUVParentFETF_Bon(){
        jetons = Arrays.asList(Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("ET<OU<T,T>,F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONParentOVOUVParentFETF_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condVrai,condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("OU<ET<¬T,¬T>,¬F>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_ParentO_FOUV_ParentF_ET_ParentO_VOUF_ParentF_Bon(){
        jetons = Arrays.asList(Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE,Jeton.ET,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condFaux,condVrai,condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("ET<OU<F,T>,OU<T,F>>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONParentO_FOUV_ParentF_ET_ParentO_VOUF_ParentF_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE,Jeton.ET,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condFaux,condVrai,condVrai,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("OU<ET<¬F,¬T>,ET<¬T,¬F>>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_VOUVET_ParentO_VOUVETF_ParentF_ETF_Bon(){
        jetons = Arrays.asList(Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.ET,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE,Jeton.ET,Jeton.CONDITION);
        conditions = Arrays.asList(condVrai,condVrai,condVrai,condVrai,condFaux,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertTrue(arbre.evaluer());
            assertEquals("OU<T,ET<ET<T,OU<T,ET<T,F>>>,F>>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
    @Test
    public final void test_Evaluation_NONVOUVET_ParentO_VOUVETF_ParentF_ETF_Bon(){
        jetons = Arrays.asList(Jeton.NON,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.ET,Jeton.PARENTHESEOUVRANTE,Jeton.CONDITION,Jeton.OU,Jeton.CONDITION,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE,Jeton.ET,Jeton.CONDITION,Jeton.PARENTHESEFERMANTE);
        conditions = Arrays.asList(condVrai,condVrai,condVrai,condVrai,condFaux,condFaux);
        arbre = new Arbre_Condition(jetons,conditions);
        try {
            arbre.construire();
            assertFalse(arbre.evaluer());
            assertEquals("ET<¬T,OU<OU<¬T,ET<¬T,OU<¬T,¬F>>>,¬F>>",arbre.toString());
        }catch (ArbreException e){
            fail("Exception détectée : " + e.getMessage());
        }
    }
}
