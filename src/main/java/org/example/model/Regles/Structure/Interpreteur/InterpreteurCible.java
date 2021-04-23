package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.CibleDeRegle;

import java.util.List;

public abstract class InterpreteurCible<T extends CibleDeRegle> implements Interpreteur_Objet_Regle<T> {

    @Override
    public List<T> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException { return null; }
}
