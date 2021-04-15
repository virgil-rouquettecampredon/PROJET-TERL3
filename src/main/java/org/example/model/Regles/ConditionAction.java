package org.example.model.Regles;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConditionAction<A extends SujetDeRegle,B extends CibleDeRegle> extends Condition {

    private BiFunction<List<A>, List<B>,Boolean> comportement;
    private Sujet sujet;
    private Traducteur_Objet_Regle cible;

    public ConditionAction(Sujet sujet, Traducteur_Objet_Regle cible, BiFunction<List<A>, List<B>,Boolean> comportement){
        this.sujet = sujet;
        this.cible = cible;
        this.comportement = comportement;
    }

    public boolean evaluer(){
        return comportement.apply(sujet.recupererTout(), cible.recupererTout()); //a changer par cible
    }
}
