package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Regles.SujetDeRegle;

import java.util.List;

public interface Interpreteur_Objet_Regle<T extends SujetDeRegle> {

    List<T> recupererTout();
}
