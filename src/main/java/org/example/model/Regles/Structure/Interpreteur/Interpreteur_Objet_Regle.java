package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.MauvaiseSemantiqueRegleException;
import org.example.model.Regles.ObjetsDeRegle;
import org.example.model.Regles.SujetDeRegle;

import java.util.List;

public interface Interpreteur_Objet_Regle<T extends ObjetsDeRegle> {

    List<T> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException;
}
