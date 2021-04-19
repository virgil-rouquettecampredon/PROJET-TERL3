package org.example.model.Regles;

import java.io.Serializable;

public abstract class Consequence implements BlocDeRegle, Serializable {
    /*Classe permettant de modéliser une Consequence au sein du système (sous ensemble d'une Regle)*/

    /**Méthode permettant à un objet de type Consequence d'avoir un comportement et de l'exécuter.**/
    public abstract void comportement();
}
