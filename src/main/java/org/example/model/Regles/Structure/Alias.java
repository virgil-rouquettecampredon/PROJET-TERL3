package org.example.model.Regles.Structure;

import org.example.model.Regles.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Alias permet de représenter sous forme d'objet un alias créé durant la création d'un règle.
 * Elle contient la condition qui l'a défini et les bloc de règles sur lesquelles elle intéragit.
 * Elle communique avec les blocs de règles par l'intermédiaire des Interpréteurs alias.*/
public abstract class Alias<A extends EstToken> {
    /*Classe modélisant un Alias*/

    private A jetonAssocie;                                 //Jeton permettant de définir ce à quoi l'Alias fait référence  (type)
    protected Condition conditionDeDefinition;              //Condition dans laquelle l'alias est définie

    public Alias(A jeton){
        this.jetonAssocie = jeton;
        conditionDeDefinition = null;
    }

    public A getJetonAssocie(){return jetonAssocie;}
    
    public void setConditionDeDefinition(Condition c){
        this.conditionDeDefinition = c;
    }

    /**Méthode permettant d'effectuer l'édition des liens.
     * Va regarder les valeurs pour lesquelles la conditionDeDefinition vaudra vrai, et va les récupérer pour les redonner ensuite aux condOuConsDeReutilisation.**/
    public abstract void editionDesLiens();
}
