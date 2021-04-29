package org.example.model.Regles.Structure;

import org.example.model.Regles.*;
import org.example.model.*;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

import java.util.ArrayList;
import java.util.List;


/**
 * Alias permet de représenter sous forme d'objet un alias créé durant la création d'un règle.
 * Elle contient la condition qui l'a défini et les bloc de règles sur lesquelles elle intéragit.
 * Elle communique avec les blocs de règles par l'intermédiaire des Interpréteurs alias.*/
public class Alias<A extends EstToken,T extends ObjetsDeRegle> {
    /*Classe modélisant un Alias*/

    private A jetonAssocie;                                 //Jeton permettant de définir ce à quoi l'Alias fait référence  (type)
    protected Condition conditionDeDefinition;              //Condition dans laquelle l'alias est définie

    private boolean estSurSujet;

    private List<T> objetsDeRegle;                          //Liste d'objet de Regle récupérée depuis l'analyse de la Condition associée

    public Alias(A jeton, boolean estSurSujet){
        this.jetonAssocie = jeton;
        conditionDeDefinition = null;
        this.estSurSujet = estSurSujet;

        objetsDeRegle = new ArrayList<>();
    }

    public A getJetonAssocie(){return jetonAssocie;}
    
    public void setConditionDeDefinition(Condition c){
        this.conditionDeDefinition = c;
    }

    public List<T> getObjetsDeRegle(){
        return objetsDeRegle;
    }

    /**Méthode permettant d'effectuer l'édition des liens.
     * Va regarder les valeurs pour lesquelles la conditionDeDefinition vaudra vrai, et va les récupérer pour les redonner ensuite aux condOuConsDeReutilisation.**/
    public void editionDesLiens(OrdonnanceurDeJeu ord) throws MauvaiseDefinitionRegleException{
        System.out.println("ALIAS : ");
        conditionDeDefinition.verifierElements(ord);

        if(conditionDeDefinition.evaluer()){
            System.out.println("OK ALIAS");
            if(estSurSujet){
                System.out.println("SUR SUJET");
                objetsDeRegle = (List<T>) Fonctions_Comportements.sujetDeLaConditionVrai;
            }else{
                System.out.println("SUR REGLE");
                objetsDeRegle = (List<T>) Fonctions_Comportements.cibleDeLaConditionVrai;
            }
        }
    }
}
