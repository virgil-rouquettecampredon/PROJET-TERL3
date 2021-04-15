package org.example.model.Regles;

import org.example.model.Regles.Structure.Arbre.ArbreException;
import org.example.model.Regles.Structure.Arbre.Arbre_Condition;
import org.example.model.Regles.Structure.Arbre.Arbre_Formule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Regle implements Serializable {
    private Arbre_Formule<Condition> arbre_conditions;
    private List<Consequence> consequences;


    public Regle() {
        arbre_conditions = null;
        consequences = new ArrayList<>();
    }

    public Regle(List<Consequence> consequences) throws MauvaiseDefinitionRegleException {
        arbre_conditions = null;
        this.consequences = consequences;
    }


    public Regle(List<Consequence> consequences, List<Condition> conditions, List<Jeton> jetons) throws MauvaiseDefinitionRegleException {
        try {
            arbre_conditions = new Arbre_Condition(jetons, conditions);
            arbre_conditions.construire();
            arbre_conditions.evaluer();
        } catch (ArbreException e) {
            throw new MauvaiseDefinitionRegleException("Regle : Impossible de générer l'arbre de Condition : " + e.getMessage());
        }
        this.consequences = consequences;
    }

    public void ajouterUneConsequence(Consequence cons) {
        consequences.add(cons);
    }


    public void genererArbreCondition(List<Condition> conditions, List<Jeton> jetons) throws MauvaiseDefinitionRegleException {
        try {
            arbre_conditions = new Arbre_Condition(jetons, conditions);
            arbre_conditions.construire();
            arbre_conditions.evaluer();
        } catch (ArbreException e) {
            throw new MauvaiseDefinitionRegleException("Regle : Impossible de générer l'arbre de Condition : " + e.getMessage());
        }
    }

    public void analyser() {
        if (arbre_conditions.evaluer()) {
            for (Consequence cons : consequences) {
                cons.comportement();
            }

        }
    }
}
