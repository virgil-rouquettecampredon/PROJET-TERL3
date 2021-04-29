

package org.example.model.Regles.Structure.Automate;

import org.example.EditRuleController;
import org.example.model.Regles.*;

import java.util.*;

public abstract class Automate_Interface<A extends EstToken> extends Automate<A> {
    //Indice renseignant le jeton courant dans lequel on se trouve à un instant t du parcours de l'automate
    //Si curEtat vaut -1, fin du parcours.
    protected int curEtat;
    //Listes permettant de connaitre le parcous effectué dans l'automate afin de pouvoir revenir en arrière
    //Mécanique importante à intégrer dans l'interface (meilleur accompagnement et guidage)
    protected Deque<Integer> etatsParcourus;
    protected Deque<A> jetonsReconnus;

    protected Map<String, Jeton_Interface> alias;

    public Automate_Interface(int nbEtat,int etatDeDepart){
        super(nbEtat,etatDeDepart);
        curEtat = 0;
        etatsParcourus = new ArrayDeque<>();
        jetonsReconnus = new ArrayDeque<>();
        alias = new HashMap<>();
    }

    public Automate_Interface(int nbEtat,int etatDeDepart, List<String> valEtat){
        super(nbEtat,etatDeDepart,valEtat);
        curEtat = 0;
        etatsParcourus = new ArrayDeque<>();
        jetonsReconnus = new ArrayDeque<>();
        alias = new HashMap<>();
    }

    /*Getter et Setter*/
    public void setAlias(Map<String, Jeton_Interface> alias){
        this.alias = alias;
    }

    public Map<String, Jeton_Interface> getAlias(){
        return this.alias;
    }

    public int getCurEtat(){
        return curEtat;
    }

    public void setCurEtat(int curEtat) {
        this.curEtat = curEtat;
    }

    /**Méthode permettant de revenir en arrière dans l'automate (backtrack)**/
    public abstract void revenirEnArriere();

    /**Méthode permettant d'indiquer si une chaine de caractère peut être renseignée sans risque d'être interprétée par le système ensuite
     * @param s : chaine de caractère dont on veut déterminer si elle est renseignable ou non.**/
    public boolean peutEtreRenseigne(String s){
        Jeton[] jetons = Jeton.values();
        for (Jeton j: jetons) {
            if(j.estReconnu(s)){
                return false;
            }
        }
        //Traitements supplémentaires pour la bonne création ensuite
        if(s.length()>=1){
            if(s.charAt(0) == 'P' || s.charAt(0) == 'E' || s.charAt(0) == 'C' || s.charAt(0) == 'J'){
                return false;
            }
        }
        return alias.get(s) == null;
    }

    /** Méthode abstraite permettant de générer les éléments sélectionnables depuis l'interface.
     * @return une liste d'ElementRegle représentant les éléments sélectionnables depuis l'interface, et leur sémantique de jeton et de valeur associée.**/
    public abstract List<ElementRegle> elementSelectionnables() throws MauvaiseDefinitionRegleException;

    /**Méthode abstraite permettant de mettre à jour l'automate en fonction du choix fait à la définition de la règle
     * @param elR: element choisi par l'utilisateur de l'interface qui est en train de définir une règle.**/
    public abstract void selectionnerElement(ElementRegle elR) throws MauvaiseDefinitionRegleException;
}
