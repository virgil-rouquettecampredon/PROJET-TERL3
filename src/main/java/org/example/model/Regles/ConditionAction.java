package org.example.model.Regles;

import java.util.List;
import java.util.function.BiFunction;

public class ConditionAction<A extends SujetDeRegle,B extends CibleDeRegle> extends Condition {

    private BiFunction<List<A>, List<B>,Boolean> comportement;
    private List<A> listSujet;
    private List<B> listCible;

    public ConditionAction(List<A> listSujet,List<B> listCible,BiFunction<List<A>, List<B>,Boolean> comportement){
        this.listSujet = listSujet;
        this.listCible = listCible;
        this.comportement = comportement;
    }

    public boolean evaluer(){
        return comportement.apply(this.listSujet,this.listCible);
    }
}
