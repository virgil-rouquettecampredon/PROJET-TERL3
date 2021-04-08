package org.example.model.Regles;

public abstract class Arbre_Formule<A extends EstEvaluable> implements EstEvaluable{
    private Noeud<A> racine;

    @Override
    public boolean evaluer() throws ArbreException{
        try {
            return racine.evaluer();
        }catch (EvaluableException e){
            throw new ArbreException("Arbre_Formule error : " + e.getMessage());
        }
    }

    public Arbre_Formule(Noeud<A> racine){
        this.racine = racine;
    }


    public Noeud<A> getRacine() {
        return racine;
    }

    public void setRacine(Noeud<A> racine) {
        this.racine = racine;
    }

    public abstract void construire() throws ArbreException;

    public static abstract class Noeud<A> implements EstEvaluable{
        private A elem;
        private Noeud<A> filsG;
        private Noeud<A> filsD;

        public Noeud(A elem){
            this.elem = elem;
            filsG = null;
            filsD = null;
        }

        public Noeud<A> getFilsD() { return filsD; }
        public Noeud<A> getFilsG() { return filsG; }
        public void setFilsG(Noeud<A> filsG) { this.filsG = filsG; }
        public void setFilsD(Noeud<A> filsD) { this.filsD = filsD; }
        public A getElem() { return elem;}
    }
}
