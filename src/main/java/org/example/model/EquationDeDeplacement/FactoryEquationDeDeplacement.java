package org.example.model.EquationDeDeplacement;

public interface FactoryEquationDeDeplacement {
    //Séparateur autorisé
    String separateur = "###";

    //Fonction permettant la création d'une équation de déplacement
    EquationDeDeplacement createEquationDeDeplacement() throws MauvaiseImplementationEquationDeDeplacementException;
}
