package org.example.model.Regles;

import org.example.model.Regles.Structure.Automate.Automate_Regles;

import java.io.Serializable;
import java.util.*;

public abstract class GenerateurDeRegle<A extends EstToken>{

    //Liste des règles données sous forme de chaine de caractère (liste de mots) à la construction du GenerateurDeRegle
    private List<List<String>> reglesSousFormeDeChaine;

    //Liste d'objets Tokken utile pour l'analyse syntaxique
    protected List<A> objetsSyntaxiques;
    //Automate utilisé pour l'analyse semantique
    protected Automate_Regles<A> automate;

    //Listes de Regle
    private List<Regle> regleAvantCoup;
    private List<Regle> regleApresCoup;

    public GenerateurDeRegle(List<A> objetsSemantiques, Automate_Regles<A> automate){
        this.automate = automate;
        this.objetsSyntaxiques = objetsSemantiques;

        this.regleAvantCoup = new ArrayList<>();
        this.regleApresCoup = new ArrayList<>();
        reglesSousFormeDeChaine = new ArrayList<>();
    }

    public List<Regle> getRegleAvantCoup() {
        return regleAvantCoup;
    }

    public List<Regle> getRegleApresCoup() {
        return regleApresCoup;
    }

    /**Méthode permettant d'ajouter une règle avant coup.
    * @param r: Regle à ajouter.**/
   public void ajouterRegleAvantCoup(Regle r){
       this.regleAvantCoup.add(r);
   }

    /**Méthode permettant d'ajouter une règle après coup.
     * @param r: Regle à ajouter.**/
    public void ajouterRegleApresCoup(Regle r){
        this.regleApresCoup.add(r);
    }

    /**Méthode permettant de renseigner une nouvelle règle (sous forme de suite de mot) à faire
     * analyser par le GenerateurDeRegle.
     * @param regles : règle sous forme de liste de mots.**/
    public void addRegleSousFormeDeChaine(List<String> regles){
        this.reglesSousFormeDeChaine.add(regles);
    }

    /**Méthode permettant de récupérer le nombre de règles sous forme de liste de mots à analyser.**/
    public int getNBReglesAEvaluer(){
        return this.reglesSousFormeDeChaine.size();
    }

    /**Méthode permettant de récupérer une règle à l'indice passé en paramètre
     * @param indice : indice de l'élément de objetsSyntaxiques à retourner.
     * @return La règle sous forme de liste de mots à l'indice indice si elle existe, null sinon **/
    public List<String> getRegle(int indice){
        try {
            return reglesSousFormeDeChaine.get(indice);
        }catch(IndexOutOfBoundsException  e){
            return null;
        }
    }

    /**Méthode permettant de récupérer un Token de objetsSyntaxiques, s'il existe
     * @param token: Token de objetsSyntaxiques à récupérer.
     * @return : Un Token de objetsSyntaxiques égal à token s'il existe, null sinon.**/
    public A getToken(A token){
        for (A t: objetsSyntaxiques) {
            if(t.equals(token)){
                return t;
            }
        }
        return null;
    }

    /**Méthode permettant de récupérer un Token de objetsSyntaxiques s'il reconnait la String s
     * @param s: String à tester pour savoir si un élément de objetsSyntaxiques (Liste de Token) la reconnait.
     * @return : Le premier Token de objetsSyntaxiques qui reconnait la chaine s, null sinon.**/
    public A estReconnu(String s){
        for (A t: objetsSyntaxiques) {
            if(t.estReconnu(s)){
                return t;
            }
        }
        return null;
    }

    /**Méthode permettant de générer les règles qui seront ajoutées dans les deux listes de r**/
    public abstract void genererRegles() throws MauvaiseDefinitionRegleException;
}
