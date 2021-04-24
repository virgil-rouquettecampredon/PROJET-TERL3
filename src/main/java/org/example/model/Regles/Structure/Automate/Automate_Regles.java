
package org.example.model.Regles.Structure.Automate;

import org.example.model.Regles.EstToken;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.MauvaiseDefinitionRegleException;
import org.example.model.Regles.Regle;

import java.io.Serializable;
import java.util.List;


/** Automate_Regles est un type d'Automate particulier à la création de règles,
 * Cela permet de pouvoir créer notre système de règle et d'en créer d'autre si besoin
 * */
public abstract class Automate_Regles<A extends EstToken> extends Automate<A> {

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
