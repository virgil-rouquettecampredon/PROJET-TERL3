package org.example.model.Regles;

import java.util.List;

public class Arbre_Condition extends Arbre_Formule<Condition>{

    class Noeud_Condition extends Noeud<Condition> {
        private Jeton jeton;

        public Noeud_Condition(Condition c,Jeton j){
            super(c);
            this.jeton = jeton;
        }

        public boolean evaluer() throws ArbreConditionException{
            try{
                if(this.getFilsG() != null && this.getFilsD() != null) {
                    if (jeton.equals(Jeton.ET)) {
                        return getFilsG().evaluer() && getFilsD().evaluer();
                    } else if (jeton.equals(Jeton.OU)) {
                        return getFilsG().evaluer() || getFilsD().evaluer();
                    }
                }
                return this.getElem().evaluer();
            }catch(EvaluableException e){
                throw new ArbreConditionException("Noeud_Condition error : " + e.getMessage());
            }
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
        curCondition = conditions.get(indiceCondition);
    }

    public void avancer() {
        try {
            indiceJeton++;
            curJeton = jetons.get(indiceJeton);
        } catch (IndexOutOfBoundsException e) {
            curJeton =  null;
        }
    }

    public void testAvancer(Jeton attendu) throws EvaluableException{
        avancer();
        if (curJeton != attendu) {
            erreurSyntaxe();
        }
    }

    public void erreurSyntaxe() throws EvaluableException{
        throw new ArbreConditionException("Erreur : élément non reconnu : " + curJeton);
    }

    public Noeud_Condition construireOU() throws EvaluableException{
        return construireOUDroite(construireET());
    }

    public Noeud_Condition construireOUDroite(Noeud_Condition nGauche) throws EvaluableException{
        if(curJeton.equals(Jeton.OU)){
            avancer();
            Noeud_Condition n = new Noeud_Condition(curCondition,Jeton.OU);
            n.setFilsG(nGauche);
            n.setFilsD(construireET());
            return construireOUDroite(n);
        }
        return nGauche;
    }

    public Noeud_Condition construireET() throws EvaluableException{
        return construireETDroite(construireCondEtParenthese());
    }

    public Noeud_Condition construireETDroite(Noeud_Condition nGauche) throws EvaluableException{
        if(curJeton.equals(Jeton.ET)){
            avancer();
            Noeud_Condition result = new Noeud_Condition(curCondition, Jeton.ET);
            result.setFilsG(nGauche);
            result.setFilsD(construireCondEtParenthese());
            return construireETDroite(result);
        }
        return nGauche;
    }

    public Noeud_Condition construireCondEtParenthese() throws EvaluableException{
        Noeud_Condition valeur = null;
        if(curJeton.equals(Jeton.PARENTHESEOUVRANTE)) {
            avancer();
            valeur = construireArbre();
            testAvancer(Jeton.PARENTHESEFERMANTE);
        }
        else if (curJeton.equals(Jeton.CONDITION)) {
            valeur = new Noeud_Condition(curCondition, curJeton);
            indiceCondition++;
            curCondition = conditions.get(indiceCondition);
        }
        else {
            erreurSyntaxe();
        }
        return valeur;
    }

    public Noeud_Condition construireArbre() throws ArbreConditionException{
        try{
            Noeud_Condition a = construireOU();
            return a;
        }catch(EvaluableException e){
            throw new ArbreConditionException("Construction error : " + e.getMessage());
        }
    }

    public void construire()throws ArbreConditionException{
        this.setRacine(construireArbre());
    }
}
