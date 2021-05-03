

package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Interpreteur.InterpreteurSujet;
import org.example.model.Regles.Structure.Interpreteur.Interpreteur_Objet_Regle;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class ConsequenceAction<A extends SujetDeRegle,B extends CibleDeRegle> extends Consequence{
    private BiFunction<List<A>, List<B>,Void> comportement;
    private InterpreteurSujet<A> interpreteurSujet;
    private Interpreteur_Objet_Regle<B> interpreteurCible;

    private List<A> sujets;
    private List<B> cibles;

    public ConsequenceAction(InterpreteurSujet<A> interpreteurSujet, Interpreteur_Objet_Regle<B> interpreteurCible, BiFunction<List<A>, List<B>,Void> comportement){
        this.interpreteurSujet = interpreteurSujet;
        this.interpreteurCible = interpreteurCible;
        this.comportement = comportement;

        sujets = new ArrayList<>();
        cibles = new ArrayList<>();
    }

    @Override
    public void verifierElements(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        System.out.println("\033[0;103m" + "verifierElements CONSEQUENCE ACTION" + "\033[0m");
        System.out.println("-------> INTSUJET : " + interpreteurSujet);
        System.out.println("-------> INTCIBLE : " + interpreteurCible);

        sujets = interpreteurSujet.recupererTout(ord);
        cibles = interpreteurCible.recupererTout(ord);

        System.out.println("-------> SUJET : " + sujets);
        System.out.println("-------> CIBLE : " + cibles);
    }

    public void comportement(OrdonnanceurDeJeu ord){
        comportement.apply(sujets, cibles);
    }

    @Override
    public String toString(){
        return "[CONSEQUENCE " + sujets.getClass() + " ACTION " + cibles.getClass()
                + "\ninterpreteur sujet: " + this.interpreteurSujet
                + "\ninterpreteur cible: " + this.interpreteurCible
                + "\ncomportement: " + this.comportement
                + "\nliste sujets: " + this.sujets
                + "\nliste cibles: " + this.cibles + "]";
    }
}
