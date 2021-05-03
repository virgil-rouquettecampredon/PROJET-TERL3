package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.CibleDeRegle;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.Structure.Alias;

import java.util.ArrayList;
import java.util.List;

public class Interpreteur_Alias_Cible<T extends CibleDeRegle> implements Interpreteur_Objet_Regle<T> {

    private Alias<Jeton,T> alias;

    public Interpreteur_Alias_Cible(Alias<Jeton,T> cibles){
        this.alias = cibles;
    }

    public List<T> recupererTout(OrdonnanceurDeJeu ord){
        System.out.println("\033[42m" + "RECUPERER TOUT INTERPRETEUR CIBLE ALIAS :" + "\033[0m "+ alias);
        return new ArrayList<>(alias.getObjetsDeRegle());
    }

    @Override
    public String toString(){
        return "[Interpreteur_Alias_Cible: " + this.alias + "]";
    }
}
