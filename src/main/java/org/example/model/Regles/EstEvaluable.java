package org.example.model.Regles;

public interface EstEvaluable {
    /**Méthode permettant à un élément implémentant EstEvaluable et la redéfinissant d'être évalué.
     * @return booléen renseignant l'évaluation de l'élément.**/
    boolean evaluer() throws EvaluableException;
}
