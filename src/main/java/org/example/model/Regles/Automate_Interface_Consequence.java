

package org.example.model.Regles;

import org.example.model.GroupCases;
import org.example.model.Joueur;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class Automate_Interface_Consequence extends Automate_Interface<Jeton_Interface> {

    private List<Piece> pieces;
    private List<GroupCases> cases;
    private List<Joueur> joueurs;

    private int curEtat;

    public Automate_Interface_Consequence(List<Piece> pieces, List<GroupCases> cases, List<Joueur> joueurs) {
        super(13, 0);

        this.pieces = pieces;
        this.cases = cases;
        this.joueurs = joueurs;

        curEtat = 0;
    }

    public void initialiserAutomate() {
        //GESTION DES ETATS TERMINAUX
        this.ajouterUnEtatTerminal(4,301);
        this.ajouterUnEtatTerminal(11,300);
        this.ajouterUnEtatTerminal(12,300);
        this.ajouterUnEtatTerminal(13,300);

        //ETAT 0
        this.ajouterUneTransition(0,Jeton_Interface.JOUEUR,1);
        this.ajouterUneTransition(0,Jeton_Interface.PIECE,2);
        this.ajouterUneTransition(0,Jeton_Interface.PIECETOKEN,3);

        //ETAT 1
        this.ajouterUneTransition(1,Jeton_Interface.CONSEQUENCE_TERMINALE,4);
        this.ajouterUneTransition(1,Jeton_Interface.CONSEQUENCE_PLACER,5);

        //ETAT 2
        this.ajouterUneTransition(2,Jeton_Interface.JOUEUR,3);
        this.ajouterUneTransition(2,Jeton_Interface.CONSEQUENCE_PRENDRE,6);
        this.ajouterUneTransition(2,Jeton_Interface.CONSEQUENCE_PROMOUVOIR,7);
        this.ajouterUneTransition(2,Jeton_Interface.CONSEQUENCE_DEPLACER,8);

        //ETAT 3
        this.ajouterUneTransition(3,Jeton_Interface.CONSEQUENCE_PRENDRE,6);
        this.ajouterUneTransition(3,Jeton_Interface.CONSEQUENCE_PROMOUVOIR,7);
        this.ajouterUneTransition(3,Jeton_Interface.CONSEQUENCE_DEPLACER,8);

        //ETAT 4

        //ETAT 5
        this.ajouterUneTransition(5,Jeton_Interface.PIECE,9);
        this.ajouterUneTransition(5,Jeton_Interface.PIECETOKEN,10);

        //ETAT 6
        this.ajouterUneTransition(6,Jeton_Interface.PIECETOKEN,11);
        this.ajouterUneTransition(6,Jeton_Interface.PIECE,12);
        this.ajouterUneTransition(6,Jeton_Interface.CASE,13);

        //ETAT 7
        this.ajouterUneTransition(7,Jeton_Interface.CASE,13);

        //ETAT 8
        this.ajouterUneTransition(8,Jeton_Interface.CASE,13);

        //ETAT 9
        this.ajouterUneTransition(9,Jeton_Interface.JOUEUR,10);
        this.ajouterUneTransition(9,Jeton_Interface.CASE,13);

        //ETAT 10
        this.ajouterUneTransition(10,Jeton_Interface.CASE,13);

        //ETAT 11

        //ETAT 12
        this.ajouterUneTransition(12,Jeton_Interface.JOUEUR,11);

        //ETAT 13
    }

    public List<ElementRegle> elementSelectionnables() throws MauvaiseDefinitionRegleException{
        List<ElementRegle> elements = new ArrayList<>();
        Etat e = this.recupererEtat(curEtat);
        if(e == null){
            throw new MauvaiseDefinitionRegleException("Etat courant inconnu : " + curEtat);
        }
        if (e.estTerminal()) {
            if(e.getCodeDeRetour() != 301){
                elements.add(new ElementRegle(Jeton_Interface.ET,"ET", "ET"));
            }
            elements.add(new ElementRegle(Jeton_Interface.FIN,"FIN", ""));
        }
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
        return elements;
    }

    public void selectionnerElement(ElementRegle elR) throws MauvaiseDefinitionRegleException{
        if(elR.getJetonAssocie() == Jeton_Interface.ET){
            curEtat = 0;
        }else{
            int etat = this.etatSuivant(curEtat, elR.getJetonAssocie());
            if(etat == -1){
                throw new MauvaiseDefinitionRegleException("Transition inconnue : " + curEtat + " --" + elR.getJetonAssocie() + "-> ?");
            }
            curEtat = etat;
        }
    }
}
