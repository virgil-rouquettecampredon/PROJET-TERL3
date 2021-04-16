package org.example.model.Regles;

import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujet;

import java.util.List;
import java.util.function.Function;

public class ConditionEtat<A extends SujetDeRegle> extends Condition {

    private Function<List<A>,Boolean> comportement;
    private InterpreteurSujet<A> interpreteurSujet;

    public ConditionEtat(InterpreteurSujet interpreteurSujet,Function<List<A>,Boolean> comportement){
        this.interpreteurSujet = interpreteurSujet;
        this.comportement = comportement;
    }

    public boolean evaluer(){
        return comportement.apply(this.interpreteurSujet.recupererTout());
    }
}
