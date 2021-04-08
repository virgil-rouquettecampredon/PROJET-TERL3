package org.example.model.Regles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//public abstract class Automate<A extends Comparable,B>
public abstract class Automate{
    /**Classes utiles pour l'analyse sémantique
     * Permet une modélisation simple d'un automate reconnaissant des Jeton**/

    //Modélisation d'une transition sortante d'un état de l'automate
    class TransitionSortante{
        private Jeton etiquetteArete;
        private int etatArrive;
        public TransitionSortante(Jeton etiquette,int arrive){
            this.etiquetteArete = etiquette;
            this.etatArrive = arrive;
        }
    }

    //Modélisation d'un état de l'automate
    class Etat{
        private int num;
        private List<TransitionSortante> transitions;
        private boolean estTerminal;
        private int codeDeRetour;
        private String valeur;

        public Etat(int num){
            this.num = num;
            this.transitions = new ArrayList<>();
            this.estTerminal = false;
            this.codeDeRetour = 0;
            this.valeur = "" + this.num;
        }

        public Etat(int num, String valeur){
            this(num);
            this.valeur = valeur;
        }

        private void ajouterTransitionSortante(TransitionSortante t){
            this.transitions.add(t);
        }

        public boolean estTerminal(){
            return estTerminal;
        }

        public String getValeur(){
            return valeur;
        }

        public int getCodeDeRetour(){
            return codeDeRetour;
        }
    }

    //Modélisation de l'automate
    private int nbEtat;
    private int nbEtatTerminaux;
    private List<Etat> etatsTr;

    //Automate déterministe ici
    private int etatDeDepart;

    public Automate(int nbEtat, int etatDeDepart){
        etatsTr = new ArrayList<>();
        this.nbEtat = nbEtat;
        nbEtatTerminaux = 0;
        int i = 0;
        while (i<nbEtat){
            etatsTr.add(new Etat(i));
            i++;
        }
        this.etatDeDepart = etatDeDepart;
    }

    public Automate(int nbEtat,int etatDeDepart, List<String> valEtat){
        etatsTr = new ArrayList<>();
        this.nbEtat = nbEtat;
        nbEtatTerminaux = 0;
        int i = 0;
        while (i<nbEtat){
            etatsTr.add(new Etat(i, valEtat.get(i)));
            i++;
        }
        this.etatDeDepart = etatDeDepart;
    }

    protected void ajouterUnEtatTerminal(int etat, int codeDeRetour){
        Etat et =  recupererEtat(etat);
        if(et != null){
            et.estTerminal = true;
            et.codeDeRetour = codeDeRetour;
            this.nbEtatTerminaux++;
        }
    }

    protected void ajouterUneTransition(int dep, Jeton val, int arrive){
        for (Etat e: etatsTr) {
            if(e.num == dep){
                Iterator<TransitionSortante> t = e.transitions.iterator();
                while (t.hasNext()) {
                  if(t.next().etiquetteArete == val){
                        //Permet d'empécher les automates indeterministes
                        t.remove();
                    }
                }
                e.ajouterTransitionSortante(new TransitionSortante(val,arrive));
            }
        }
    }

    public int etatSuivant(int sommet,Jeton val){
        for (Etat e: etatsTr) {
            if(e.num == sommet){
                for(TransitionSortante t : e.transitions){
                    if(t.etiquetteArete == val){
                        return t.etatArrive;
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    public Etat recupererEtat(int etat) {
         for (Etat e : etatsTr) {
             if (e.num == etat) {
                 return e;
             }
         }return null;
    }

    protected void setValeurEtat(int etat, String valeur){
        for (Etat e : etatsTr) {
            if (e.num == etat) {
                e.valeur = valeur;
            }
        }
    }

    public int getEtatDeDepart() {
        return etatDeDepart;
    }

    public void setEtatDeDepart(int etatDeDepart) {
        this.etatDeDepart = etatDeDepart;
    }

    /** Méthode abstraite permettant l'initialisation de l'automate (à définir dans les sous-classes)**/
    public abstract void initialiserAutomate();

    /** Méthode abstraite permettant l'analyse de l'automate
     * @return une liste de BlocDeRegle représenatant la règle bien former sémantiquement sous d'objets manipulables par le système.
     * @param regleString : Regle sous forme de liste de mots
     * @param regleSyntaxe : Regle sous forme de Jeton, issue de l'analyse syntaxique.**/
    public abstract Regle analyserUneRegle(List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseSemantiqueRegleException;
}
