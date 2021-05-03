package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujet;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConditionEtat<A extends SujetDeRegle> extends Condition {

    private Function<List<A>,Boolean> comportement;
    private InterpreteurSujet<A> interpreteurSujet;

    private List<A> sujets;

    public ConditionEtat(InterpreteurSujet<A> interpreteurSujet,Function<List<A>,Boolean> comportement){
        this.interpreteurSujet = interpreteurSujet;
        this.comportement = comportement;

        sujets = new ArrayList<>();
    }

    @Override
    public List<A> getSujets(){
        return sujets;
    }

    @Override
    public void verifierElements(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        System.out.println("\033[0;103m" + "verifierElements CONDITION ETAT" + "\033[0m");
        System.out.println("-------> INTSUJET : " + interpreteurSujet);
        sujets = interpreteurSujet.recupererTout(ord);
        System.out.println("-------> SUJET : " + sujets);
    }

    public boolean evaluer(){
        return comportement.apply(sujets);
    }

    @Override
    public String toString(){
        return "[CONDITION " + sujets.getClass() + " ETAT"
                + "\ninterpreteur sujet: " + this.interpreteurSujet
                + "\ncomportement: " + this.comportement
                + "\nliste sujets: " + this.sujets + "]";
    }
}
