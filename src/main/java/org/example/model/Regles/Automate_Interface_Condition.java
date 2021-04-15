

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
    private int nbParenthese;

    public Automate_Interface_Condition(List<Piece> pieces, List<GroupCases> cases, List<Joueur> joueurs) {
        super(18, 0);

        this.pieces = pieces;
        this.cases = cases;
        this.joueurs = joueurs;

        curEtat = 0;
        nbParenthese = 0;
    }

    public void initialiserAutomate() {
        //GESTION DES ETATS TERMINAUX
        this.ajouterUnEtatTerminal(5,300);
        this.ajouterUnEtatTerminal(16,300);
        this.ajouterUnEtatTerminal(17,300);
        this.ajouterUnEtatTerminal(14,300);
        this.ajouterUnEtatTerminal(12,300);
        this.ajouterUnEtatTerminal(15,300);

        //ETAT INITIAL
        this.ajouterUneTransition(0,Jeton_Interface.PARENTHESE_OUVRANTE,0);
        this.ajouterUneTransition(0,Jeton_Interface.JOUEUR,1);
        this.ajouterUneTransition(0,Jeton_Interface.PIECE,2);
        this.ajouterUneTransition(0,Jeton_Interface.PIECETOKEN,3);

        //ETAT 1
        this.ajouterUneTransition(1,Jeton_Interface.COMPTEUR_TEMPSRESTANT,4);

        //ETAT 2
        this.ajouterUneTransition(2,Jeton_Interface.JOUEUR,3);
        this.ajouterUneTransition(2,Jeton_Interface.COMPTEUR_DEPLACEMENT,4);
        this.ajouterUneTransition(2,Jeton_Interface.PROMU,5);
        this.ajouterUneTransition(2,Jeton_Interface.ESTMENACE,6);
        this.ajouterUneTransition(2,Jeton_Interface.PREND,7);
        this.ajouterUneTransition(2,Jeton_Interface.DEPLACE,8);
        this.ajouterUneTransition(2,Jeton_Interface.PLACE,9);
        this.ajouterUneTransition(2,Jeton_Interface.EST,10);

        //ETAT 3
        this.ajouterUneTransition(3,Jeton_Interface.COMPTEUR_DEPLACEMENT,4);
        this.ajouterUneTransition(3,Jeton_Interface.PROMU,5);
        this.ajouterUneTransition(3,Jeton_Interface.ESTMENACE,6);
        this.ajouterUneTransition(3,Jeton_Interface.PREND,7);
        this.ajouterUneTransition(3,Jeton_Interface.DEPLACE,8);
        this.ajouterUneTransition(3,Jeton_Interface.PLACE,9);
        this.ajouterUneTransition(3,Jeton_Interface.EST,10);

        //ETAT 4
        this.ajouterUneTransition(4,Jeton_Interface.NON,11);
        this.ajouterUneTransition(4,Jeton_Interface.COMPARATEUR,13);

        //ETAT 5
        this.ajouterUneTransition(5,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 6
        this.ajouterUneTransition(6,Jeton_Interface.PIECETOKEN,14);
        this.ajouterUneTransition(6,Jeton_Interface.PIECE,12);

        //ETAT 7
        this.ajouterUneTransition(7,Jeton_Interface.PIECE,12);
        this.ajouterUneTransition(7,Jeton_Interface.PIECETOKEN,14);
        this.ajouterUneTransition(7,Jeton_Interface.CASE,15);

        //ETAT 8
        this.ajouterUneTransition(8,Jeton_Interface.CASE,15);

        //ETAT 9
        this.ajouterUneTransition(9,Jeton_Interface.CASE,15);

        //ETAT 10
        this.ajouterUneTransition(10,Jeton_Interface.CASE,15);

        //ETAT 11
        this.ajouterUneTransition(11,Jeton_Interface.COMPARATEUR,13);

        //ETAT 12
        this.ajouterUneTransition(12,Jeton_Interface.JOUEUR,14);
        this.ajouterUneTransition(12,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 13
        this.ajouterUneTransition(13,Jeton_Interface.NOMBRE,16);

        //ETAT 14
        this.ajouterUneTransition(14,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 15
        this.ajouterUneTransition(15,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 16
        this.ajouterUneTransition(16,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 17
        this.ajouterUneTransition(17,Jeton_Interface.PARENTHESE_FERMANTE,17);
    }

    public List<ElementRegle> elementSelectionnables()throws MauvaiseDefinitionRegleException {
        List<ElementRegle> elements = new ArrayList<>();
        Etat e = this.recupererEtat(curEtat);
        if(e == null){
            throw new MauvaiseDefinitionRegleException("Etat courant inconnu : " + curEtat);
        }
        if (e.estTerminal()) {
            elements.add(new ElementRegle(Jeton_Interface.OU,"OU", "OU"));
            elements.add(new ElementRegle(Jeton_Interface.ET,"ET", "ET"));
            if(nbParenthese <= 0){
                elements.add(new ElementRegle(Jeton_Interface.ALORS,"ALORS", "alors"));
            }
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
                            if(nbParenthese > 0 || t.getEtiquetteArete() != Jeton_Interface.PARENTHESE_FERMANTE){
                                elements.add(new ElementRegle(t.getEtiquetteArete(),s, s));
                            }
                        }
                    }
                }
            }
        }
        return elements;
    }

    public void selectionnerElement(ElementRegle elR) throws MauvaiseDefinitionRegleException{
        if(elR.getJetonAssocie() == Jeton_Interface.PARENTHESE_OUVRANTE) ++nbParenthese;
        if(elR.getJetonAssocie() == Jeton_Interface.PARENTHESE_FERMANTE){
            if(nbParenthese == 0){
                throw new MauvaiseDefinitionRegleException("Impossible d'appliquer une parenthèse fermante ici");
            }
            --nbParenthese;
        }

        int etat = this.etatSuivant(curEtat, elR.getJetonAssocie());
        Etat e = this.recupererEtat(curEtat);
        if(e == null){
            throw new MauvaiseDefinitionRegleException("Etat courant inconnu : " + curEtat);
        }
        if(e.estTerminal() && (elR.getJetonAssocie() == Jeton_Interface.OU || elR.getJetonAssocie() == Jeton_Interface.ET)){
            curEtat = 0;
        }else{
            if(etat == -1){
                throw new MauvaiseDefinitionRegleException("Transition inconnue : " + curEtat + " --" + elR.getJetonAssocie() + "-> ?");
            }
        }
        curEtat = etat;
    }

}
