package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.CibleDeRegle;
import org.example.model.Regles.Structure.Alias_Cible;
import org.example.model.Regles.SujetDeRegle;

import java.util.List;

public class Interpreteur_Alias_Cible extends Interpreteur_Alias<CibleDeRegle> {

    private Alias_Cible alias;

    public Interpreteur_Alias_Cible(Alias_Cible cibles){
        this.alias = cibles;
    }

    public List<CibleDeRegle> recupererTout(OrdonnanceurDeJeu ord){
        return this.alias.getCiblesDeRegle();
    }
}
