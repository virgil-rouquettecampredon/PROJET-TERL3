

package org.example.model.Regles;

import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujet;
import org.example.model.Regles.Structure.Interpreteur.Interpreteur_Objet_Regle;

import java.util.List;
import java.util.function.BiFunction;

public class ConsequenceAction<A extends SujetDeRegle,B extends CibleDeRegle> extends Consequence{
    private BiFunction<List<A>, List<B>,Void> comportement;
    private InterpreteurSujet interpreteurSujet;
    private Interpreteur_Objet_Regle interpreteurCible;

    public ConsequenceAction(InterpreteurSujet interpreteurSujet, Interpreteur_Objet_Regle interpreteurCible, BiFunction<List<A>, List<B>,Void> comportement){
        this.interpreteurSujet = interpreteurSujet;
        this.interpreteurCible = interpreteurCible;
        this.comportement = comportement;
    }

    @Override
    public void verifierElements(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        sujets = interpreteurSujet.recupererTout(ord);
        cibles = interpreteurCible.recupererTout(ord);
    }

    public void comportement(OrdonnanceurDeJeu ord){
        comportement.apply(sujets, cibles);
    }
}
