package org.example.model.Regles;

public class ConditionEtat<A extends SujetDeRegle> extends Condition {

    private String etat;

    public boolean evaluer(){
        //A IMPLEMENTER
        return false;
    }
}
