package org.example.model.Regles;

import java.util.List;

public class Arbre_Condition extends Arbre_Formule<Condition>{

    class Noeud_Condition extends Noeud<Condition> {
        private Jeton jeton;

        public Noeud_Condition(Condition c,Jeton j){
            super(c);
            this.jeton = j;
        }

        public boolean evaluer(){
            if(this.getFilsG() != null && this.getFilsD() != null) {
                    if (jeton.equals(Jeton.ET)) {
                        return getFilsG().evaluer() && getFilsD().evaluer();
                    } else if (jeton.equals(Jeton.OU)) {
                        return getFilsG().evaluer() || getFilsD().evaluer();
                    }
                }
            return this.getElem().evaluer();
        }

        @Override
        public String toString() {
            if(estFeuille()){
                return (getElem().evaluer())? "T": "F";
            }
            return  jeton + "<" + getFilsG().toString() + "," + getFilsD().toString() + ">";
        }
    }

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

    /**===============================================================================
     * ================= Analyse descendante récursive de la formule =================
     * ===============================================================================**/

    /**Méthode utiles pour la construction de l'arbre représentant une formule sous forme de Jetons.
     * D'une liste représentant la formule conditionnelle sous forme de Jeton particuliers (ET,OU,NON,PARENTHESEOUVRANTE,PARENTHESEFERMANTE,CONDITION)
     * et d'une liste des différentes conditions reconnus, on va pouvoir construire l'Arbre modélisant la formule conditionnelle.
     *
     * Cette construction particulière va permettre de conserver les priorités liées au parenthésage ainsi que la précédence des connecteurs logiques.**/

    //Méthode permettant d'avancer (si possible) dans la liste des Jetons
    public void avancer() throws EvaluableException{
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
    public Noeud_Condition construireOU() throws EvaluableException{
        //System.out.println("OU");
        return construireOUDroite(construireET());
    }

    //Regle : construireOUDroite -> OU construireET-construireOUDroite
    public Noeud_Condition construireOUDroite(Noeud_Condition nGauche) throws EvaluableException{
        //System.out.println("OUD");
        if(curJeton!=null && curJeton.equals(Jeton.OU)){
            avancer();
            Noeud_Condition n = new Noeud_Condition(curCondition,Jeton.OU);
            n.setFilsG(nGauche);
            n.setFilsD(construireET());
            return construireOUDroite(n);
        }
        return nGauche; //Regle : construireOUDroite -> epsilon
    }

    //Regle : construireET -> construireCondEtParenthese-construireETDroite
    public Noeud_Condition construireET() throws EvaluableException{
        //System.out.println("ET");
        return construireETDroite(construireCondEtParenthese());
    }

    //Regle : construireETDroite -> ET construireCondEtParenthese-construireETDroite
    public Noeud_Condition construireETDroite(Noeud_Condition nGauche) throws EvaluableException{
        //System.out.println("ETD");
        if(curJeton!=null && curJeton.equals(Jeton.ET)){
            avancer();
            Noeud_Condition result = new Noeud_Condition(curCondition, Jeton.ET);
            result.setFilsG(nGauche);
            result.setFilsD(construireCondEtParenthese());
            return construireETDroite(result);
        }
        return nGauche; //Regle : construireETDroite -> epsilon
    }

    //Regle : construireCondEtParenthese -> INTERPRETATION
    public Noeud_Condition construireCondEtParenthese() throws EvaluableException{
        //System.out.println("COND PARENTH");
        Noeud_Condition valeur = null;
        if(curJeton!=null && curJeton.equals(Jeton.PARENTHESEOUVRANTE)) { //Regle : construireCondEtParenthese -> ( construireOU )
            //System.out.println("PARENTH");
            avancer();
            //System.out.println("ON RECOMMENCE");
            valeur = construireOU();
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
            valeur = new Noeud_Condition(curCondition, curJeton);
            avancer();
        }
        else {
            erreurSyntaxe();
        }
        return valeur;
    }

    /**Méthode permettant d'initialiser puis de déclancher l'analyse descendante récursive de la formule conditionnelle
     * @return Noeud Condition racine de l'arbre.**/
    public Noeud_Condition construireArbre() throws ArbreConditionException{
        try{
            Noeud_Condition a = construireOU();
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
