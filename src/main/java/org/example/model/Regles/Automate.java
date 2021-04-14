package org.example.model.Regles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//public abstract class Automate<A extends Comparable,B>
public abstract class Automate<A extends EstToken>{
    /**Classes utiles pour l'analyse sémantique
     * Permets une modélisation simple d'un automate reconnaissant des Token**/

    // ===== Modélisation d'une transition sortante d'un état de l'automate
    class TransitionSortante{
        private A etiquetteArete;
        private int etatArrive;
        public TransitionSortante(A etiquette,int arrive){
            this.etiquetteArete = etiquette;
            this.etatArrive = arrive;
        }

        public A getEtiquetteArete() {
            return etiquetteArete;
        }
    }

    // ===== Modélisation d'un état de l'automate
    class Etat{
        //Numéro de l'état (~numéro d'identification)
        private int num;
        //Liste des transitions sortantes d'un Etat
        //Ici, un automate est vu comme une liste d'Etat qui possèdent des transitions sortantes (~Graphe orienté valué aux arêtes par des Token)
        private List<TransitionSortante> transitions;
        //bool permettant de savoir si on se trouve dans un état terminal ou non
        //( -> utile ensuite pour le backtracking côté analyse sémantique)
        private boolean estTerminal;
        //Code de retour utile pour identifier l'état terminal dans lequel on se trouve
        //Peut permettre des traitements différents pour un même état terminal
        private int codeDeRetour;
        //Valeur d'un état terminal, utilisé pour construire des chaines de parcour dans l'automate par exemple
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

        /**Méthode permettant d'ajouter une transition sortante pour un état.
         * @param t : TransitionSortante à rajouter à la liste des transitions sortantes d'un Etat.**/
        private void ajouterTransitionSortante(TransitionSortante t){
            this.transitions.add(t);
        }

        /**Méthode permettant de savoir si un état est terminal ou non.
         * @return true si this est un Etat terminal, false sinon.**/
        public boolean estTerminal(){
            return estTerminal;
        }

        /**Méthode permettant de récupérer la valeur d'un Etat.
         * @return la valeur de this.**/
        public String getValeur(){
            return valeur;
        }

        /**Méthode permettant de récupérer le code de retour d'un Etat.
         * @return la code de retour de this.**/
        public int getCodeDeRetour(){
            return codeDeRetour;
        }

        public List<TransitionSortante> getTransitions() {
            return transitions;
        }
    }

    // ===== Modélisation de l'automate
    //Nombre d'états de l'automate
    private int nbEtat;
    //Nombre d'états terminaux de l'automate
    private int nbEtatTerminaux;
    //Liste des états de l'automate
    private List<Etat> etatsTr;

    //Indice de l'état de départ de l'automate
    //Automate déterministe ici (qu'un seul état de départ)
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

    /**Méthode permettant d'ajouter un état terminal à l'automate.
     * Va modifier un état de l'automate afin de le rendre terminal.
     * @param etat : numéro d'identification de l'état à rendre terminal.
     * @param codeDeRetour : entier rensiegnant le code de retour à retourner par l'Etat si terminal.**/
    protected void ajouterUnEtatTerminal(int etat, int codeDeRetour){
        Etat et =  recupererEtat(etat);
        if(et != null){
            et.estTerminal = true;
            et.codeDeRetour = codeDeRetour;
            this.nbEtatTerminaux++;
        }
    }

    /**Méthode permettant d'ajouter une transition à l'automate.
     * @param dep : numéro d'identifiaction de l'Etat de départ de la transition.
     * @param val : Token à reconnaitre depuis l'Etat dep pour aller ver l'Etat arrive.
     * @param arrive : numéro d'identifiaction de l'Etat d'arrivé de la transition.**/
    protected void ajouterUneTransition(int dep, A val, int arrive){
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

    /**Méthode permettant de récupérer le numéro d'identification de l'état d'arrivée
     * atteint depuis l'état de numéro d'identification etat par la transition valuée par val.
     * @param etat : numéro d'identifiaction de l'Etat de départ de la transition val à évaluer.
     * @param val : Token à évaluer pour savoir s'il fait partie d'une des transitions sortantes de l'Etat dont le numéro d'identification est etat.
     * @return : Le numéro d'identification de l'Etat atteint depuis l'Etat dont le numéro d'identification est etat par la transition valuée par le Token val, -1 sinon.**/
    public int etatSuivant(int etat,A val){
        for (Etat e: etatsTr) {
            if(e.num == etat){
                for(TransitionSortante t : e.transitions){
                    if(t.etiquetteArete.equals(val)){
                        return t.etatArrive;
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    /**Méthode permettant de récupérer L'Etat dont le numéro d'identifiaction est etat.
     * @param etat : numéro d'identifiaction de l'Etat à récupérer.
     * @return Etat de numéro d'identification etat, null sinon.**/
    public Etat recupererEtat(int etat) {
         for (Etat e : etatsTr) {
             if (e.num == etat) {
                 return e;
             }
         }return null;
    }

    /**Méthode permettant d'initialiser la valeur d'un Etat numéro d'identification etat.
     * @param etat : numéro d'identification de l'Etat dont il faut initialiser sa valeur.
     * @param valeur : valeur d'initialisation.**/
    protected void setValeurEtat(int etat, String valeur){
        for (Etat e : etatsTr) {
            if (e.num == etat) {
                e.valeur = valeur;
            }
        }
    }

    /**Getter et Setter**/
    public int getEtatDeDepart() {
        return etatDeDepart;
    }

    public void setEtatDeDepart(int etatDeDepart) {
        this.etatDeDepart = etatDeDepart;
    }


    /** Méthode abstraite permettant l'initialisation de l'automate (à définir dans les sous-classes)**/
    public abstract void initialiserAutomate();
}
