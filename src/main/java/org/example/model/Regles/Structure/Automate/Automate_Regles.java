
package org.example.model.Regles.Structure.Automate;

import org.example.model.Regles.EstToken;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.MauvaiseDefinitionRegleException;
import org.example.model.Regles.Regle;
import org.example.model.Regles.Structure.Alias;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/** Automate_Regles est un type d'Automate particulier à la création de règles,
 * Cela permet de pouvoir créer notre système de règle et d'en créer d'autre si besoin
 * */
public abstract class Automate_Regles<A extends EstToken> extends Automate<A> {

    protected Map<String, Alias<A>> aliasRegle;       //Liste des alias de la Regle (nom -> Alias)

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

    /**Méthode permettant d'ajouter un Alias dans la liste des Alias
     * @param nomAlias : nom de l'alias à ajouter à la table des alias
     * @param parcours : parcours de l'automate jusqu'à la rencontre d'une définition d'alias**/
    public abstract void ajouterAlias(String nomAlias, String parcours) throws MauvaiseDefinitionRegleException;

    /**Méthode permettant de récupérer un Alias de la liste des Alias
     * @param nomAlias : Nom de l'alias à récupérer
     * @return l'Alias correspondant au nom de l'alias, erreur sinon**/
    public abstract Alias<A> recupererAlias(String nomAlias) throws MauvaiseDefinitionRegleException;

    /*Getter et Setter*/
    public Map<String,Alias<A>> getAliasRegle(){
        return aliasRegle;
    }
}
