package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Structure.Interpreteur.*;

import java.util.List;

/** BlocDeRegle est une interface représentant un objet Condition ou Consequence. */

public interface BlocDeRegle {
    /**Méthode permettant à un BlocDeRegle de vérifier s'il PEUT récupérer les éléments dont il a besoin pour fonctionner
     * Théoriquement, l'interpreteur de règle ne sera jamais incorrect car une analyse syntaxique et sémantique est appliqué
     * avant sa construction. Une MIORE sera donc throw si il y a des bugs ce qui permettra de les corriger.
     * @param ord : contexte d'exécution du bloc de règle.**/
    public void verifierElements(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException;
}
