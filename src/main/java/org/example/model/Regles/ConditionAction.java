package org.example.model.Regles;

import org.example.model.Case;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujet;
import org.example.model.Regles.Structure.Interpreteur.Interpreteur_Objet_Regle;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class ConditionAction<A extends SujetDeRegle,B extends CibleDeRegle> extends Condition {

    private BiFunction<List<A>, List<B>,Boolean> comportement;
    private InterpreteurSujet<A> interpretSujet;
    private Interpreteur_Objet_Regle<B> interpretCible;

    private List<A> sujets;
    private List<B> cibles;

    public ConditionAction(InterpreteurSujet<A> interpretSujet, Interpreteur_Objet_Regle<B> interpretCible, BiFunction<List<A>, List<B>,Boolean> comportement){
        this.interpretSujet = interpretSujet;
        this.interpretCible = interpretCible;
        this.comportement = comportement;

        this.cibles = new ArrayList<>();
        this.sujets = new ArrayList<>();
    }

    @Override
    public List<A> getSujets(){
        return sujets;
    }

    @Override
    public List<B> getCibles(){
        return cibles;
    }

    @Override
    public void verifierElements(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException{
        sujets = interpretSujet.recupererTout(ord);
        cibles = interpretCible.recupererTout(ord);
    }

    @Override
    public boolean evaluer(){
        return comportement.apply(sujets, cibles);
    }

    @Override
    public String toString(){
        return "[CONSEQUENCE " + sujets.getClass() + " ACTION " + cibles.getClass()
                + ": interpreteur sujet: " + this.interpretSujet
                + ", interpreteur cible: " + this.interpretCible
                + ", comportement: " + this.comportement
                + ", liste sujets: " + this.sujets
                + ", liste cibles: " + this.cibles + "]";
    }
}
