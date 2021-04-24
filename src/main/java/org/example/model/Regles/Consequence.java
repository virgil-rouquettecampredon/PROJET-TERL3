package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;

import java.io.Serializable;

public abstract class Consequence implements BlocDeRegle {
    public abstract void comportement(OrdonnanceurDeJeu ord);
}
