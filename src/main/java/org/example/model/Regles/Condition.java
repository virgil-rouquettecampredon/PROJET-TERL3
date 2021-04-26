package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Interpreteur.MauvaiseInterpretationObjetRegleException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Condition implements BlocDeRegle, EstEvaluable {
    /**Méthode permettant de récupérer la liste de Sujet qui rendent la Condition vrai**/
    public abstract <A extends SujetDeRegle> List<A> getSujets();

    /**Méthode permettant de récupérer la liste de Cible qui rendent la Condition vrai**/
    public <A extends CibleDeRegle> List<A> getCibles(){
        return new ArrayList<>();
    }
}
