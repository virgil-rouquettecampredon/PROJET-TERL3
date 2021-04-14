
package org.example.model.Regles;

import java.util.ArrayList;
import java.util.List;

public abstract class Automate_Regles<A extends EstToken> extends Automate<A>{

    public Automate_Regles(int nbEtat, int etatDeDepart){
        super(nbEtat,etatDeDepart);
    }

    public Automate_Regles(int nbEtat, int etatDeDepart, List<String> valEtat){
        super(nbEtat,etatDeDepart,valEtat);
    }

    /** Méthode abstraite permettant l'analyse de l'automate
     * @return une liste de BlocDeRegle représantant la règle bien former sémantiquement sous d'objets manipulables par le système.
     * @param regleString : Regle sous forme de liste de mots
     * @param regleSyntaxe : Regle sous forme de Jeton, issue de l'analyse syntaxique.**/
    public abstract Regle analyserUneRegle(List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException;
}
