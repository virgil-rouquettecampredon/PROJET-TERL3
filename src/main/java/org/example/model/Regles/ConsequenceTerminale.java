

package org.example.model.Regles;

import org.example.model.Regles.Structure.Interpreteur.Interpreteur_Objet_Regle;

import java.util.List;
import java.util.function.Function;

public class ConsequenceTerminale<A extends SujetDeRegle> extends Consequence{

    private Function<List<A>,Void> comportement;
    private Interpreteur_Objet_Regle interpretSujet;

    public ConsequenceTerminale(Interpreteur_Objet_Regle listSujet,Function<List<A>,Void> comportement){
        this.interpretSujet = listSujet;
        this.comportement = comportement;
    }

    public void comportement(){
        comportement.apply(this.interpretSujet.recupererTout());
    }
}
