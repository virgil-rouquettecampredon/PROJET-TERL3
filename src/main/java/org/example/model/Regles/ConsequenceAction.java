

package org.example.model.Regles;

import java.util.List;
import java.util.function.BiFunction;

public class ConsequenceAction<A extends SujetDeRegle,B extends CibleDeRegle> extends Consequence{
    private BiFunction<List<A>, List<B>,Void> comportement;
    private List<A> listSujet;
    private List<B> listCible;

    public ConsequenceAction(List<A> listSujet,List<B> listCible,BiFunction<List<A>, List<B>,Void> comportement){
        this.listSujet = listSujet;
        this.listCible = listCible;
        this.comportement = comportement;
    }

    public void comportement(){
        comportement.apply(this.listSujet,this.listCible);
    }
}
