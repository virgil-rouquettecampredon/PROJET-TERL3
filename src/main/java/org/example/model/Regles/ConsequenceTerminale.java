

package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Interpreteur.Interpreteur_Objet_Regle;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConsequenceTerminale<A extends SujetDeRegle> extends Consequence{

    private Function<List<A>,Void> comportement;
    private Interpreteur_Objet_Regle<A> interpretSujet;

    private List<A> sujets;

    public ConsequenceTerminale(Interpreteur_Objet_Regle<A> listSujet,Function<List<A>,Void> comportement){
        this.interpretSujet = listSujet;
        this.comportement = comportement;

        sujets = new ArrayList<>();
    }

    @Override
    public void verifierElements(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        System.out.println("\033[0;103m" + "verifierElements CONSEQUENCE TERMINALE" + "\033[0m");
        System.out.println("-------> INTSUJET : " + interpretSujet);
        sujets = interpretSujet.recupererTout(ord);
        System.out.println("-------> SUJET : " + sujets);
    }

    public void comportement(OrdonnanceurDeJeu ord){
        comportement.apply(sujets);
    }

    @Override
    public String toString(){
        return "[CONSEQUENCE " + sujets.getClass() + " TERMINALE"
                + "\ninterpreteur sujet: " + this.interpretSujet
                + "\ncomportement: " + this.comportement
                + "\nliste sujets: " + this.sujets + "]";
    }
}
