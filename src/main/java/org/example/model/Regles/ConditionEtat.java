package org.example.model.Regles;

public class ConditionEtat<A extends SujetDeRegle> implements Condition {

    public boolean verification(){
        //A IMPLEMENTER
        return false;
    }
    public void consequence(){
        //A IMPLEMENTER
    }
}
