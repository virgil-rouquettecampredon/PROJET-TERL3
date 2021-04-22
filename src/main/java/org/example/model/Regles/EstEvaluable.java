package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

public interface EstEvaluable {
    /**Méthode permettant à un élément implémentant EstEvaluable et la redéfinissant d'être évalué.
     * @return booléen renseignant l'évaluation de l'élément.**/
    boolean evaluer();
}
