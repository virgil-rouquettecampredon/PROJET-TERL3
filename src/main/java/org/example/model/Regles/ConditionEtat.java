package org.example.model.Regles;

public class ConditionEtat<A extends SujetDeRegle> extends Condition {

    private Jeton etat;

    //public Condition

    public boolean evaluer(){
        //A IMPLEMENTER
        return false;
    }
}
