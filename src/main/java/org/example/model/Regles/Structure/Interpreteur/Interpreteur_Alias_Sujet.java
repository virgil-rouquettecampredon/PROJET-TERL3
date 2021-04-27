package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.Structure.Alias;
import org.example.model.Regles.SujetDeRegle;


import java.util.List;

public class Interpreteur_Alias_Sujet<T extends SujetDeRegle> extends InterpreteurSujet<T> {

    private Alias<Jeton,T> alias;

    public Interpreteur_Alias_Sujet(Alias<Jeton,T> alias){
        this.alias = alias;
    }

    public List<T> recupererTout(OrdonnanceurDeJeu ord){
        return alias.getObjetsDeRegle();
    }
}
