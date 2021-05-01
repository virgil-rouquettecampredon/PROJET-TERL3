package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;

import java.io.Serializable;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Consequence est un objet représentant une consequence de règle. Il est créé sous forme de ConsequenceTerminale ou ConsequenceAction
 * Chaque objet Consequence contient une Fonction_Comportement qu'elle va exécuter selon la liste de SujetDeRegle donné grâce à ses Interpreteurs
 * Dans l'objet Règle, il est stocké dans une liste et est exécuté si l'Arbre_Condition renvoie vrai*/

public abstract class Consequence implements BlocDeRegle {

    public abstract void comportement(OrdonnanceurDeJeu ord);
}
