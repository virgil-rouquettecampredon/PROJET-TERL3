

package org.example.model.Regles;

import org.example.model.Regles.Structure.Interpreteur.Interpreteur_Objet_Regle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConsequenceTerminale<A extends SujetDeRegle> extends Consequence{

    private Function<List<A>,Void> comportement;
    private Interpreteur_Objet_Regle interpretSujet;

    public ConsequenceTerminale(Interpreteur_Objet_Regle<A> listSujet,Function<List<A>,Void> comportement){
        this.interpretSujet = listSujet;
        this.comportement = comportement;

        sujets = new ArrayList<>();
    }

    @Override
    public void verifierElements(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        sujets = interpretSujet.recupererTout(ord);
    }

    public void comportement(){
        comportement.apply(this.interpretSujet.recupererTout());
    }
}
