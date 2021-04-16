package org.example.model.Regles;

import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujet;
import org.example.model.Regles.Structure.Interpreteur.Interpreteur_Objet_Regle;

import java.util.List;
import java.util.function.BiFunction;

public class ConditionAction<A extends SujetDeRegle,B extends CibleDeRegle> extends Condition {

    private BiFunction<List<A>, List<B>,Boolean> comportement;
    private InterpreteurSujet interpretSujet;
    private Interpreteur_Objet_Regle interpretCible;

    public ConditionAction(InterpreteurSujet interpretSujet, Interpreteur_Objet_Regle interpretCible, BiFunction<List<A>, List<B>,Boolean> comportement){
        this.interpretSujet = interpretSujet;
        this.interpretCible = interpretCible;
        this.comportement = comportement;
    }

    public boolean evaluer(){
        return comportement.apply(interpretSujet.recupererTout(), interpretCible.recupererTout()); //a changer par cible
    }
}
