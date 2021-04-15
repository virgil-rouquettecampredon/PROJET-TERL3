

package org.example.model.Regles;

import java.util.List;
import java.util.function.BiFunction;

public class ConsequenceAction<A extends SujetDeRegle,B extends CibleDeRegle> extends Consequence{
    private BiFunction<List<A>, List<B>,Void> comportement;
    private Sujet sujet;
    private Traducteur_Objet_Regle cible;

    public ConsequenceAction(Sujet sujet,Traducteur_Objet_Regle cible, BiFunction<List<A>, List<B>,Void> comportement){
        this.sujet = sujet;
        this.cible = cible;
        this.comportement = comportement;
    }

    public void comportement(){
        comportement.apply(this.sujet.recupererTout(), this.cible.recupererTout());
    }
}
