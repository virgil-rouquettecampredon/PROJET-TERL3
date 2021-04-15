

package org.example.model.Regles;

import java.util.List;

public abstract class Automate_Interface<A extends EstToken> extends Automate<A> {
    protected int curEtat;

    public Automate_Interface(int nbEtat,int etatDeDepart){
        super(nbEtat,etatDeDepart);
        curEtat = 0;
    }

    public Automate_Interface(int nbEtat,int etatDeDepart, List<String> valEtat){
        super(nbEtat,etatDeDepart,valEtat);
        curEtat = 0;
    }

    public int getCurEtat(){
        return curEtat;
    }

    /** Méthode abstraite permettant de générer les éléments sélectionnables depuis l'interface.
     * @return une liste d'ElementRegle représentant les éléments sélectionnables depuis l'interface, et leur sémantique de jeton et de valeur associée.**/
    public abstract List<ElementRegle> elementSelectionnables() throws MauvaiseDefinitionRegleException;

    /**Méthode abstraite permettant de mettre à jour l'automate en fonction du choix fait à la définition de la règle
     * @param elR: element choisi par l'utilisateur de l'interface qui est en train de définir une règle.**/
    public abstract void selectionnerElement(ElementRegle elR) throws MauvaiseDefinitionRegleException;
}
