package org.example.model.Regles.Structure;

import org.example.model.Regles.*;

import java.util.ArrayList;
import java.util.List;

public class Alias_Cible extends Alias<Jeton>{

    private List<CibleDeRegle> ciblesDeRegle;

    public Alias_Cible(Jeton j){
        super(j);
    }

    public List<CibleDeRegle> getCiblesDeRegle(){
        return ciblesDeRegle;
    }

    public void editionDesLiens(){
        //Si la Condition vaut vrai
        if(conditionDeDefinition.evaluer()){
            //Liste des éléments à récupérer de la Condition
            ciblesDeRegle = new ArrayList<>(Fonctions_Comportements.cibleDeLaConditionVrai);
        }else{
            ciblesDeRegle = new ArrayList<>();
        }
    }
}
