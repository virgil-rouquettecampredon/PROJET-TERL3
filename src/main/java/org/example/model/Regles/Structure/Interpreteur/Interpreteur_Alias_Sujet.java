package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.SujetDeRegle;
import org.example.model.Regles.Structure.Alias_Sujet;


import java.util.List;

public class Interpreteur_Alias_Sujet extends Interpreteur_Alias<SujetDeRegle> {

    private Alias_Sujet alias;

    public Interpreteur_Alias_Sujet(Alias_Sujet alias){
        this.alias = alias;
    }

    public List<SujetDeRegle> recupererTout(OrdonnanceurDeJeu ord){
        return alias.getSujetsDeRegle();
    }
}
