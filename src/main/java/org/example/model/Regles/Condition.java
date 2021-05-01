package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Condition est un objet représentant une condition de règle. Il est créé sous forme de ConditionEtat ou ConditionAction
 * Chaque objet Condition contient une Fonction_Comportement Boolean qu'elle va exécuter selon la liste de SujetDeRegle donné grâce à ses Interpreteurs
 * Dans l'objet Règle, il est stocké dans un arbre binaire de type Arbre_Condition et sera évaluer à l'interieur.*/

public abstract class Condition implements BlocDeRegle, EstEvaluable {
    /**Méthode permettant de récupérer la liste de Sujet qui rendent la Condition vrai**/
    public abstract <A extends SujetDeRegle> List<A> getSujets();

    /**Méthode permettant de récupérer la liste de Cible qui rendent la Condition vrai**/
    public <A extends CibleDeRegle> List<A> getCibles(){
        return new ArrayList<>();
    }
}
