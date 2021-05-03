package org.example.model.Regles;

import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Arbre.ArbreException;
import org.example.model.Regles.Structure.Arbre.Arbre_Condition;
import org.example.model.Regles.Structure.Arbre.Arbre_Formule;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;
import org.example.model.Regles.Structure.Alias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Regle est un objet qui représente une règle créée, càd un arbre de Condition et une liste de Consequence associées
 * Règle fait également le lien avec les alias créé et associé dans les Conditions et Consequence
 * Pour s'exécuter, la règle va interpreter les alias associé, puis vérifier l'évaluation de l'arbre de Condition pour
 * enfin exécuter la liste des Consequence si l'arbre des Conditions retourne vrai. */

public class Regle {
    /*Classe permettant de modéliser une Regle de jeu*/
    private Arbre_Condition arbre_conditions;               //Arbre représentant la formule formée par la définition des condtions de la Regle
    private List<Consequence> consequences;                 //Liste des conséquences à exécuter si les conditions précédement définies sont respectées

    private  List<Alias<Jeton,?>> listeAlias;                 //Liste des alias définis dans la Regle

    public Regle() {
        arbre_conditions = null;
        consequences = new ArrayList<>();
        listeAlias = new ArrayList<>();
    }

    public Regle(List<Consequence> consequences) throws MauvaiseDefinitionRegleException {
        arbre_conditions = null;
        this.consequences = consequences;
        listeAlias = new ArrayList<>();
    }

    public Regle(List<Consequence> consequences, List<Condition> conditions, List<Jeton> jetons) throws MauvaiseDefinitionRegleException {
        try {
            arbre_conditions = new Arbre_Condition(jetons, conditions);
            arbre_conditions.construire();
        } catch (ArbreException e) {
            throw new MauvaiseDefinitionRegleException("Regle : Impossible de générer l'arbre de Condition : " + e.getMessage());
        }
        this.consequences = consequences;
        listeAlias = new ArrayList<>();
    }

    /**Méthode permettant d'ajouter un Alias supplémentaire à une Regle
     * @param alias : Aliaas à ajouter à notre Regle**/
    public void ajouterUnAlias(Alias<Jeton,?> alias) {
        listeAlias.add(alias);
    }

    /**Méthode permettant d'éditer les liens pour les Alias
     * Cela va permettre de mettre à jour les informations importantes pour pouvoir les réutiliser après.**/
    public void editerLesLiens(OrdonnanceurDeJeu ord) throws MauvaiseDefinitionRegleException{
        System.out.println("\033[0;37;0;101m" + "EDITION DES LIENS DE LA REGLE" + "\033[0m");
        for (Alias<Jeton,?> alias: listeAlias) {
            alias.editionDesLiens(ord);
        }
        System.out.println("\033[0;37;0;101m" + "FIN EDITION DES LIENS DE LA REGLE" + "\033[0m");
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
        //Si l'évaluation de l'arbre condition est positive, on peut exécuter les Consequences
        if (arbre_conditions.evaluer()) {
            for (Consequence cons : consequences) {
                i = 0;
                try{
                    cons.verifierElements(ord);
                    i++;
                }catch (MauvaiseInterpretationObjetRegleException e){
                    throw new MauvaiseDefinitionRegleException("Impossible d'interpréter le ou les élément(s) de la consequence" + i + ": " + e.getMessage());
                }
                cons.comportement(ord);
            }
        }
    }

    /*Getter et Setter*/
    public Arbre_Condition getArbreCondition(){
        return arbre_conditions;
    }

    public List<Consequence> getConsequences(){
        return consequences;
    }

    public void setArbreCondition(Arbre_Condition cond) {
        this.arbre_conditions = cond;
    }

    public List<Alias<Jeton, ?>> getListeAlias() {
        return listeAlias;
    }

    @Override
    public String toString() {
        return "Regle{" +
                "arbre_conditions=" + arbre_conditions +
                "\nconsequences=" + consequences +
                "\nlisteAlias=" + listeAlias +
                '}';
    }
}
