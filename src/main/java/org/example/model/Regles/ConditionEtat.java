package org.example.model.Regles;

import java.util.List;
import java.util.function.Function;

public class ConditionEtat<A extends SujetDeRegle> extends Condition {

    private Function<List<A>,Boolean> comportement;
    //private List<A> listSujet;
    private Sujet<A> sujet;

    public ConditionEtat(Sujet sujet,Function<List<A>,Boolean> comportement){
        this.sujet = sujet;
        this.comportement = comportement;
    }

    public boolean evaluer(){
        return comportement.apply(this.sujet.recupererTout());
    }
}
