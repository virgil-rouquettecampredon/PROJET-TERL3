

package org.example.model.Regles;

import java.util.List;
import java.util.function.BiFunction;

public class ConditionComportement<A extends SujetDeRegle> extends Condition{
    /*private BiFunction<List<A>,Integer,Boolean> comportement;
    private List<A> listSujet;
    private int valeur;

    private List<A> sujets;

    public ConditionComportement(List<A> listSujet,int valeur,BiFunction<List<A>,Integer,Boolean> comportement){
        this.listSujet = listSujet;
        this.comportement = comportement;
        this.valeur = valeur;

        this.sujets = new ArrayList<>();
    }*/
    //TODO: PU LA MERDE

    @Override
    public void verifierElements(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        //sujets = interpretSujet.recupererTout(ord);
    }

    public boolean evaluer(){
        return comportement.apply(this.listSujet,this.valeur);
    }
}
