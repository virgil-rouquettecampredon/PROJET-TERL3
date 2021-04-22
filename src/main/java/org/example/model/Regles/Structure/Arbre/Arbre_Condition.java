package org.example.model.Regles.Structure.Arbre;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

import java.util.List;

public class Arbre_Condition extends Arbre_Formule<Condition> {

    class Noeud_Condition extends Noeud<Condition> {
        private Jeton jeton;
        private boolean inverser;

        public Noeud_Condition(Condition c,Jeton j,boolean inverser){
            super(c);
            this.jeton = j;
            this.inverser = inverser;
        }

        public boolean evaluer(){
            if(this.getFilsG() != null && this.getFilsD() != null) {
                    if (jeton.equals(Jeton.ET)) {
                        if(inverser){
                            //Si on doit appliquer un ET logique mais sous l'emprise d'un non
                            //(NON(..ET..) -> (NON.. OU NON..))
                            return getFilsG().evaluer() || getFilsD().evaluer();
                        }
                        return getFilsG().evaluer() && getFilsD().evaluer();
                    } else if (jeton.equals(Jeton.OU)) {
                        if(inverser){
                            //Si on doit appliquer un OU logique mais sous l'emprise d'un non
                            //(NON(..OU..) -> (NON.. ET NON..))
                            return getFilsG().evaluer() && getFilsD().evaluer();
                        }
                        return getFilsG().evaluer() || getFilsD().evaluer();
                    }
                }
            //Si le noeud à évaluer n'a ni fils gauche ou/et ni fils droit
            //Comme on n'autorise aucun connecteur logique unaire, le noeud est donc une feuille
            return inverser != getElem().evaluer();
        }

        public Jeton inverserJeton(Jeton j){
            return (j.equals(Jeton.OU)? Jeton.ET: Jeton.OU);
        }

        @Override
        public String toString() {
            if(estFeuille()){
                return (inverser)?(getElem().evaluer())? "¬T": "¬F":(getElem().evaluer())? "T": "F";
            }
            Jeton j = (inverser)?inverserJeton(jeton):jeton;
            return  j + "<" + getFilsG().toString() + "," + getFilsD().toString() + ">";
        }
    }

    //Listes utiles pour le traitement des Jetons et Conditions de notre modèle
    private List<Jeton> jetons;
    private List<Condition> conditions;
    private int indiceJeton;
    private int indiceCondition;

    private Jeton curJeton;
    private Condition curCondition;

    public Arbre_Condition(List<Jeton> jetons, List<Condition> conditions){
        super(null);
        this.jetons = jetons;
        this.conditions = conditions;
        this.indiceJeton = 0;
        this.indiceCondition = 0;
        curJeton = jetons.get(indiceJeton);
    }


    /*Getter et Setter*/

    public List<Condition> getListeConditions(){
        return this.conditions;
    }

    /**===============================================================================
     * ================= Analyse descendante récursive de la formule =================
     * ===============================================================================**/

    /**Méthode utiles pour la construction de l'arbre représentant une formule sous forme de Jetons.
     * D'une liste représentant la formule conditionnelle sous forme de Jeton particuliers (ET,OU,NON,PARENTHESEOUVRANTE,PARENTHESEFERMANTE,CONDITION)
     * et d'une liste des différentes conditions reconnus, on va pouvoir construire l'Arbre modélisant la formule conditionnelle.
     *
     * Cette construction particulière va permettre de conserver les priorités liées au parenthésage ainsi que la précédence des connecteurs logiques.**/

    //Méthode permettant d'avancer (si possible) dans la liste des Jetons
    public void avancer() throws EvaluableException {
        try {
            indiceJeton++;
            curJeton = jetons.get(indiceJeton);
        } catch (IndexOutOfBoundsException e) {
            curJeton = null;
            //throw new ArbreConditionException("Erreur : Impossible d'avancer : " + curJeton + " (" + indiceJeton + ")");
        }
    }

    //Méthode permettant de tester s'il est possible d'avancer dans la liste des Jetons
    public void testAvancer(Jeton attendu) throws EvaluableException{
        if (curJeton != attendu) {
            throw new ArbreConditionException("Erreur : élément non reconnu : [attendu : " + attendu + " | recu : " + curJeton + "] (" + indiceJeton + ")");
        }
        avancer();
    }

    //Méthode permettant de lever une erreur si besoin
    public void erreurSyntaxe() throws EvaluableException{
        throw new ArbreConditionException("Erreur : élément non reconnu : " + curJeton + " (" + indiceJeton + ")");
    }

    //Regle : construireOU -> construireET-construireOUDroite
    public Noeud_Condition construireOU(boolean inverser) throws EvaluableException{
        //System.out.println("OU");
        return construireOUDroite(construireET(inverser),inverser);
    }

    //Regle : construireOUDroite -> OU construireET-construireOUDroite
    public Noeud_Condition construireOUDroite(Noeud_Condition nGauche,boolean inverser) throws EvaluableException{
        //System.out.println("OUD");
        if(curJeton!=null && curJeton.equals(Jeton.OU)){
            avancer();
            Noeud_Condition n = new Noeud_Condition(curCondition,Jeton.OU,inverser);
            n.setFilsG(nGauche);
            n.setFilsD(construireET(inverser));
            return construireOUDroite(n,inverser);
        }
        return nGauche; //Regle : construireOUDroite -> epsilon
    }

    //Regle : construireET -> construireCondEtParenthese-construireETDroite
    public Noeud_Condition construireET(boolean inverser) throws EvaluableException{
        //System.out.println("ET");
        return construireETDroite(construireCondEtParenthese(inverser),inverser);
    }

    //Regle : construireETDroite -> ET construireCondEtParenthese-construireETDroite
    public Noeud_Condition construireETDroite(Noeud_Condition nGauche,boolean inverser) throws EvaluableException{
        //System.out.println("ETD");
        if(curJeton!=null && curJeton.equals(Jeton.ET)){
            avancer();
            Noeud_Condition result = new Noeud_Condition(curCondition, Jeton.ET,inverser);
            result.setFilsG(nGauche);
            result.setFilsD(construireCondEtParenthese(inverser));
            return construireETDroite(result,inverser);
        }
        return nGauche; //Regle : construireETDroite -> epsilon
    }

    //Regle : construireCondEtParenthese -> INTERPRETATION
    public Noeud_Condition construireCondEtParenthese(boolean inverser) throws EvaluableException{
        //System.out.println("COND PARENTH");
        Noeud_Condition valeur = null;
        if(curJeton!=null && curJeton.equals(Jeton.PARENTHESEOUVRANTE)) { //Regle : construireCondEtParenthese -> ( construireOU )
            //System.out.println("PARENTH");
            avancer();
            //System.out.println("ON RECOMMENCE");
            valeur = construireOU(inverser);
            testAvancer(Jeton.PARENTHESEFERMANTE);
        }
        else if (curJeton!=null && curJeton.equals(Jeton.CONDITION)) { //Regle : construireCondEtParenthese -> CONDITION
            //System.out.println("COND");
            try{
                curCondition = conditions.get(indiceCondition);
                indiceCondition++;
            }catch (IndexOutOfBoundsException e){
                throw new ArbreConditionException("Erreur : Condition manquante à : " + indiceCondition + " (" + indiceJeton + ")");
            }
            valeur = new Noeud_Condition(curCondition, curJeton,inverser);
            avancer();
        }
        else if(curJeton!=null && curJeton.equals(Jeton.NON)){ //Regle : construireCondEtParenthese -> NON (construireOU)
            avancer();
            testAvancer(Jeton.PARENTHESEOUVRANTE);
            //On inverse ici (condition sous l'emprise d'un non)
            valeur = construireOU(!inverser);
            testAvancer(Jeton.PARENTHESEFERMANTE);
        }else{
            //Rien ne correspond à ce qui peut être lu, donc erreur
            erreurSyntaxe();
        }
        return valeur;
    }

    /**Méthode permettant d'initialiser puis de déclancher l'analyse descendante récursive de la formule conditionnelle
     * @return Noeud Condition racine de l'arbre.**/
    public Noeud_Condition construireArbre() throws ArbreConditionException{
        try{
            Noeud_Condition a = construireOU(false);
            if(indiceCondition != this.conditions.size()){
                warning = "Vous n'avez pas utilisé l'ensemble des conditions définies à la construction de votre Arbre_Condition.";
            }
            if(curJeton != null){
                erreurSyntaxe();
            }
            return a;
        }catch(EvaluableException e){
            throw new ArbreConditionException("Construction error : " + e.getMessage());
        }
    }

    public void construire()throws ArbreConditionException{
        this.setRacine(construireArbre());
    }
}
