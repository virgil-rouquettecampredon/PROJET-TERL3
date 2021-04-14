

package org.example.model.Regles;

import org.example.model.GroupCases;
import org.example.model.Joueur;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class Automate_Interface_Consequence extends Automate_Interface<Jeton> {

    private List<Piece> pieces;
    private List<GroupCases> cases;
    private List<Joueur> joueurs;

    private int curEtat;

    public Automate_Interface_Consequence(List<Piece> pieces, List<GroupCases> cases, List<Joueur> joueurs) {
        super(9, 0);

        this.pieces = pieces;
        this.cases = cases;
        this.joueurs = joueurs;

        curEtat = 0;
    }

    public void initialiserAutomate() {
        //ETAT 0
        this.ajouterUneTransition(0,Jeton.JOUEUR,1);
        this.ajouterUneTransition(0,Jeton.PIECE,2);
        //this.ajouterUneTransition(15,Jeton.PIECETOKEN,18);

        //ETAT 1
        this.ajouterUneTransition(1,Jeton.CONSEQUENCEACTION,5);
        this.ajouterUneTransition(1,Jeton.CONSEQUENCETERMINALE,4);

        //ETAT 2
        this.ajouterUneTransition(2,Jeton.JOUEUR,3);
        this.ajouterUneTransition(2,Jeton.CONSEQUENCEACTION,5);

        //ETAT 3
        this.ajouterUneTransition(3,Jeton.CONSEQUENCEACTION,5);

        //ETAT 4

        //ETAT 5
        this.ajouterUneTransition(5,Jeton.PIECE,6);
        //this.ajouterUneTransition(20,Jeton.PIECETOKEN,22);
        this.ajouterUneTransition(5,Jeton.CASE,8);

        //ETAT
        this.ajouterUneTransition(6,Jeton.JOUEUR,7);

        //ETAT 7

        //ETAT 8
        //this.ajouterUneTransition(23,Jeton.PIECETOKEN,22);
        this.ajouterUneTransition(8,Jeton.PIECE,6);
    }

    public List<ElementRegle> elementSelectionnables() throws MauvaiseDefinitionRegleException{
        /*
        List<ElementRegle> elements = new ArrayList<>();
        Etat e = this.recupererEtat(curEtat);
        if(e == null){
            throw new MauvaiseDefinitionRegleException("Etat courant inconnu : " + curEtat);
        }
        if (e.getTransitions().isEmpty()) {
            if(curEtat != 4){
                elements.add(new ElementRegle(Jeton.ET,"ET", "ET"));
            }
        } else {
            for (TransitionSortante t : e.getTransitions()) {
                int ind = 0;
                switch (t.getEtiquetteArete()) {
                    case CASE -> {
                        elements.add(new ElementRegle(Jeton.CASE,"Toutes les cases", "tous-typecase"));
                        for (GroupCases gc : cases) {
                            elements.add(new ElementRegle(Jeton.CASE,gc.getName(), "C" + ind));
                            ind++;
                        }
                    }
                    case JOUEUR -> {
                        elements.add(new ElementRegle(Jeton.JOUEUR,"Tous les joueurs", "tous-joueur"));
                        for (Joueur j : joueurs) {
                            elements.add(new ElementRegle(Jeton.JOUEUR,j.getName(), "J" + ind));
                            ind++;
                        }
                    }
                    case PIECE -> {
                        elements.add(new ElementRegle(Jeton.PIECE,"Toutes les pieces", "tous-piece"));
                        for (Piece p : pieces) {
                            elements.add(new ElementRegle(Jeton.PIECE,p.getName(), "P" + ind));
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

         */
        return null;
    }

    public void selectionnerElement(ElementRegle elR) throws MauvaiseDefinitionRegleException{
        /*
        int etat = this.etatSuivant(curEtat, elR.getJetonAssocie());
        if(etat == -1){
            throw new MauvaiseDefinitionRegleException("Transition inconnue : " + curEtat + " --" + elR.getJetonAssocie() + "-> ?");
        }
        curEtat = etat;

         */
    }
}
