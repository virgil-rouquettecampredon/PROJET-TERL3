package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Regles.CibleDeRegle;

import java.util.List;

public abstract class InterpreteurCible<T extends CibleDeRegle> implements Interpreteur_Objet_Regle {

    public List<T> recupererTout(/*List<T> list*/) {/*A IMPLEMENTER*/ return null; }
}
