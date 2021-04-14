

package org.example.model.Regles;

import java.util.ArrayList;
import java.util.List;

import org.example.model.GroupCases;
import org.example.model.Piece;
import org.example.model.Case;
import org.example.model.Joueur;

public class Automate_Interface_Condition extends Automate_Interface<Jeton_Interface> {

    private List<Piece> pieces;
    private List<GroupCases> cases;
    private List<Joueur> joueurs;

    private int curEtat;

    public Automate_Interface_Condition(List<Piece> pieces, List<GroupCases> cases, List<Joueur> joueurs) {
        super(17, 0);

        this.pieces = pieces;
        this.cases = cases;
        this.joueurs = joueurs;

        curEtat = 0;
    }

    public void initialiserAutomate() {
        //GESTION DES ETATS TERMINAUX
        //ETAT INITIAL
        this.ajouterUneTransition(0,Jeton_Interface.JOUEUR,1);

    }

    public List<ElementRegle> elementSelectionnables()throws MauvaiseDefinitionRegleException {
        List<ElementRegle> elements = new ArrayList<>();
        Etat e = this.recupererEtat(curEtat);
        if(e == null){
            throw new MauvaiseDefinitionRegleException("Etat courant inconnu : " + curEtat);
        }
        if (e.getTransitions().isEmpty()) {
            elements.add(new ElementRegle(Jeton_Interface.OU,"OU", "OU"));
            elements.add(new ElementRegle(Jeton_Interface.ET,"ET", "ET"));
            elements.add(new ElementRegle(Jeton_Interface.ALORS,"ALORS", "alors"));
        } else {
            for (TransitionSortante t : e.getTransitions()) {
                int ind = 0;
                switch (t.getEtiquetteArete()) {
                    case CASE -> {
                        elements.add(new ElementRegle(Jeton_Interface.CASE,"Toutes les cases", "tous-typecase"));
                        for (GroupCases gc : cases) {
                            elements.add(new ElementRegle(Jeton_Interface.CASE,gc.getName(), "C" + ind));
                            ind++;
                        }
                    }
                    case JOUEUR -> {
                        elements.add(new ElementRegle(Jeton_Interface.JOUEUR,"Tous les joueurs", "tous-joueur"));
                        for (Joueur j : joueurs) {
                            elements.add(new ElementRegle(Jeton_Interface.JOUEUR,j.getName(), "J" + ind));
                            ind++;
                        }
                    }
                    case PIECE -> {
                        elements.add(new ElementRegle(Jeton_Interface.PIECE,"Toutes les pieces", "tous-piece"));
                        for (Piece p : pieces) {
                            elements.add(new ElementRegle(Jeton_Interface.PIECE,p.getName(), "P" + ind));
                            ind++;
                        }
                    }
                    default -> {
                        for (String s : t.getEtiquetteArete().getElementsReconnaissables()) {
                            elements.add(new ElementRegle(t.getEtiquetteArete(),s, s));
                        }
                    }
                }
            }
        }
        return elements;
    }

    public void selectionnerElement(ElementRegle elR) throws MauvaiseDefinitionRegleException{
        int etat = this.etatSuivant(curEtat, elR.getJetonAssocie());
        if(etat == -1){
            throw new MauvaiseDefinitionRegleException("Transition inconnue : " + curEtat + " --" + elR.getJetonAssocie() + "-> ?");
        }
        curEtat = etat;
    }

}
