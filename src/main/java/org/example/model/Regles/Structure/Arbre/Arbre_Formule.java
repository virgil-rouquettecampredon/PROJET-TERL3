package org.example.model.Regles.Structure.Arbre;

import org.example.model.Regles.EstEvaluable;

public abstract class Arbre_Formule<A extends EstEvaluable> implements EstEvaluable{
    /**Méthode permettant de modéliser un arbre d'évaluation de formule de logique.**/

    //Noeud racine de l'arbre
    private Noeud<A> racine;

    //Chaine pour les warnings
    protected String warning;

    public Arbre_Formule(Noeud<A> racine){
        this.racine = racine;
        this.warning = "";
    }

    /**Méthode permettant d'évaluer une formule logique mise sous forme d'un arbre.**/
    @Override
    public boolean evaluer(){
        return racine.evaluer();
    }

    /**Getter et Setter**/
    public Noeud<A> getRacine() {
        return racine;
    }

    public void setRacine(Noeud<A> racine) {
        this.racine = racine;
    }

    /**Méthode abstraite à implémanter renseignant la manière dont l'arbre va être construit.
     * C'est cette méthode qui va définir les règles de construction de l'arbre à partir d'une formule logique.
     * Va construire l'arbre par embriquement de Noeuds, ou lever une Exception sinon.**/
    public abstract void construire() throws ArbreException;

    // ===== Modélisation d'un Noeud de l'arbre
    public static abstract class Noeud<A> implements EstEvaluable{
        //Elément du Noeud à évaluer
        private A elem;

        //Pointeurs vers la suite de l'arbre
        //(Deux maximums car connecteurs de logique binaire {ET,OU,=>,<=>} ou unaire {NON})
        private Noeud<A> filsG;
        private Noeud<A> filsD;

        public Noeud(A elem){
            this.elem = elem;
            filsG = null;
            filsD = null;
        }

        /**Getter et Setter**/
        public Noeud<A> getFilsD() { return filsD; }
        public Noeud<A> getFilsG() { return filsG; }
        public void setFilsG(Noeud<A> filsG) { this.filsG = filsG; }
        public void setFilsD(Noeud<A> filsD) { this.filsD = filsD; }
        public A getElem() { return elem;}

        public boolean estFeuille(){
            return this.filsD == null && this.filsG == null;
        }

        @Override
        public String toString() {
            return "<" + elem + "," + filsG + "," + filsD + ">";
        }
    }

    public String getWarning(){
        return this.warning;
    }

    @Override
    public String toString() {
        return racine.toString();
    }
}
