package org.example.model.Regles;

import org.example.model.Regles.Structure.Arbre.ArbreException;
import org.example.model.Regles.Structure.Arbre.Arbre_Condition;
import org.example.model.Regles.Structure.Arbre.Arbre_Formule;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Regle {
    /*Classe permettant de modéliser une Regle de jeu*/
    private Arbre_Condition arbre_conditions;               //Arbre représentant la formule formée par la définition des condtions de la Regle
    private List<Consequence> consequences;                 //Liste des conséquences à exécuter si les conditions précédement définies sont respectées

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
        } catch (ArbreException e) {
            throw new MauvaiseDefinitionRegleException("Regle : Impossible de générer l'arbre de Condition : " + e.getMessage());
        }
        this.consequences = consequences;
    }

    /**Méthode permettant d'ajouter une conséquence supplémentaire à une Regle
     * @param cons : consequence à ajouter aux conséquences de notre Regle**/
    public void ajouterUneConsequence(Consequence cons) {
        consequences.add(cons);
    }

    /**Méthode permettant de générer un Arbre des condtions pour  notre Regle
     * @param conditions : liste des condtions nécessaire à la génération de l'arbre
     * @param jetons  : liste des jetons necessaires à la gnération de l'arbre (va fournir le squelette à analyser pour reconstituer l'arbre)**/
    public void genererArbreCondition(List<Condition> conditions, List<Jeton> jetons) throws MauvaiseDefinitionRegleException {
        try {
            arbre_conditions = new Arbre_Condition(jetons, conditions);
            arbre_conditions.construire();
        } catch (ArbreException e) {
            throw new MauvaiseDefinitionRegleException("Regle : Impossible de générer l'arbre de Condition : " + e.getMessage());
        }
        this.consequences = new ArrayList<>();
    }

    /**Méthode permettant d'analyser une règle.
     * Si les conditions sont réunies (que l'évaluation de l'arbre est positive), alors on peut exécuter les conséquences adéquates**/
    public void analyser(OrdonnanceurDeJeu ord) throws MauvaiseDefinitionRegleException{
        //verifierElements pour chaque conditions de l'arbre
        int i = 0;
        for (Condition c : arbre_conditions.getListeConditions()){
            try{
                c.verifierElements(ord);
                i++;
            }catch (MauvaiseInterpretationObjetRegleException e){
                throw new MauvaiseDefinitionRegleException("Impossible d'interpréter le ou les élément(s) de la condition" + i + ": " + e.getMessage());
            }
        }
        if (arbre_conditions.evaluer()) {
            for (Consequence cons : consequences) {
                cons.comportement();
            }
        }
    }

    /*Getter et Setter*/
    public void setArbreCondition(Arbre_Condition cond) {
        this.arbre_conditions = cond;
    }
}
