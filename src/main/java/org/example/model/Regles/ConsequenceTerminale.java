

package org.example.model.Regles;

import java.util.List;
import java.util.function.Function;

public class ConsequenceTerminale<A extends SujetDeRegle> extends Consequence{

    private Function<List<A>,Void> comportement;
    private List<A> listSujet;

    public ConsequenceTerminale(List<A> listSujet,Function<List<A>,Void> comportement){
        this.listSujet = listSujet;
        this.comportement = comportement;
    }

    public void comportement(){
        comportement.apply(this.listSujet);
    }
}
