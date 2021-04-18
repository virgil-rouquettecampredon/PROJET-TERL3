

package org.example.model.Regles.Structure.Automate;

import org.example.EditRuleController;
import org.example.model.Regles.ElementRegle;
import org.example.model.Regles.EstToken;
import org.example.model.Regles.MauvaiseDefinitionRegleException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public abstract class Automate_Interface<A extends EstToken> extends Automate<A> {
    //Indice renseignant le jeton courant dans lequel on se trouve à un instant t du parcours de l'automate
    //Si curEtat vaut -1, fin du parcours.
    protected int curEtat;
    //Listes permettant de connaitre le parcous effectué dans l'automate afin de pouvoir revenir en arrière
    //Mécanique importante à intégrer dans l'interface (meilleur accompagnement et guidage)
    protected Deque<Integer> etatsParcourus;
    protected Deque<A> jetonsReconnus;

    public Automate_Interface(int nbEtat,int etatDeDepart){
        super(nbEtat,etatDeDepart);
        curEtat = 0;
        etatsParcourus = new ArrayDeque<>();
        jetonsReconnus = new ArrayDeque<>();
    }

    public Automate_Interface(int nbEtat,int etatDeDepart, List<String> valEtat){
        super(nbEtat,etatDeDepart,valEtat);
        curEtat = 0;
        etatsParcourus = new ArrayDeque<>();
        jetonsReconnus = new ArrayDeque<>();
    }

    public int getCurEtat(){
        return curEtat;
    }

    public void setCurEtat(int curEtat) {
        this.curEtat = curEtat;
    }

    /**Méthode permettante de revenir en arrière dans l'automate (backtrack)**/
    public abstract void revenirEnArriere();

    /** Méthode abstraite permettant de générer les éléments sélectionnables depuis l'interface.
     * @return une liste d'ElementRegle représentant les éléments sélectionnables depuis l'interface, et leur sémantique de jeton et de valeur associée.**/
    public abstract List<ElementRegle> elementSelectionnables() throws MauvaiseDefinitionRegleException;

    /**Méthode abstraite permettant de mettre à jour l'automate en fonction du choix fait à la définition de la règle
     * @param elR: element choisi par l'utilisateur de l'interface qui est en train de définir une règle.**/
    public abstract void selectionnerElement(ElementRegle elR) throws MauvaiseDefinitionRegleException;
}
