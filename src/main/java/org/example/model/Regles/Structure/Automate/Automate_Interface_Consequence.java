

package org.example.model.Regles.Structure.Automate;

import org.example.model.GroupCases;
import org.example.model.Joueur;
import org.example.model.Piece;
import org.example.model.Regles.ElementRegle;
import org.example.model.Regles.Jeton_Interface;
import org.example.model.Regles.MauvaiseDefinitionRegleException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Automate_Interface_Consequence extends Automate_Interface<Jeton_Interface> {

    private List<Piece> pieces;
    private List<GroupCases> cases;
    private List<Joueur> joueurs;


    public Automate_Interface_Consequence(List<Piece> pieces, List<GroupCases> cases, List<Joueur> joueurs) {
        super(14, 0);

        this.pieces = pieces;
        this.cases = cases;
        this.joueurs = joueurs;
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

    public List<ElementRegle> elementSelectionnables() throws MauvaiseDefinitionRegleException {
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
        int etat = this.etatSuivant(curEtat, elR.getJetonAssocie());
        Etat e = this.recupererEtat(curEtat);
        if(e == null){
            throw new MauvaiseDefinitionRegleException("Etat courant inconnu : " + curEtat);
        }
        if(e.estTerminal()){
            if(elR.getJetonAssocie() == Jeton_Interface.ET) {
                //Si on se trouve sur un état terminal et que l'on cherche à lire un ET
                //On a finit de traiter une consequence, on retourne donc à l'état initial
                curEtat = 0;
                return;
            }else{
                if(elR.getJetonAssocie() == Jeton_Interface.FIN){
                    //Si on se trouve sur un état terminal et que l'on cherche à lire un FIN
                    //On a finit de définir les conséquences
                    curEtat = -1;
                    return;
                }
            }
        }
        //Si on est pas sur un etat terminal et que la transition que l'on veut lire n'existe pas (etatSuivant = -1), on lève une erreur
        if(etat == -1){
            throw new MauvaiseDefinitionRegleException("Transition inconnue : " + curEtat + " --" + elR.getJetonAssocie() + "-> ?");
        }
        curEtat = etat;
    }

    public static void main(String[] args) {
        //GESTION COULEUR
        String COLOR_RESET = "\u001B[0m";
        String COLOR_BLACK = "\u001B[30m";
        String COLOR_RED = "\u001B[31m";
        String COLOR_GREEN = "\u001B[32m";
        String COLOR_YELLOW = "\u001B[33m";
        String COLOR_BLUE = "\u001B[34m";
        String COLOR_PURPLE = "\u001B[35m";
        String COLOR_CYAN = "\u001B[36m";
        String COLOR_WHITE = "\u001B[37m";


        List<Joueur> joueurs = new ArrayList<>();
        List<GroupCases> cases = new ArrayList<>();
        List<Piece> pieces = new ArrayList<>();

        Automate_Interface<Jeton_Interface> auto = new Automate_Interface_Consequence(pieces,cases,joueurs);
        auto.initialiserAutomate();
        Scanner scan = new Scanner(System.in);
        String regle = "";

        List<ElementRegle> regleChoix = new ArrayList<>();
        try{
            while (auto.getCurEtat() != -1) {
                List<ElementRegle> elem = auto.elementSelectionnables();

                System.out.println("Elements possibles test (sélectionner " +COLOR_GREEN+"indice" +COLOR_RESET+ ") : ");
                int ind = 1;
                for (ElementRegle e : elem){
                    System.out.println(COLOR_GREEN + ind + COLOR_RESET+" -> " + e.getNomInterface());
                    ind++;
                }
                System.out.print("Choix : " + COLOR_GREEN);
                String rep = scan.next();
                System.out.println(rep + COLOR_RESET);

                int indRep = Integer.parseInt(rep);
                --indRep;
                if(indRep <0 || indRep>ind) {
                    throw new MauvaiseDefinitionRegleException("Seul un nombre valide est autorisé");
                }else {
                    ElementRegle choix = elem.get(indRep);
                    regleChoix.add(choix);
                    regle += "[" +COLOR_BLUE+choix.getNomInterface() +COLOR_RESET + "]";
                    System.out.println("Regle : " + regle);
                    auto.selectionnerElement(choix);
                }

            }

            //AFFICHAGE DE LA REGLE
            System.out.println("REGLE CREE : ");
            Iterator<ElementRegle> ite = regleChoix.iterator();
            while (ite.hasNext()){
                ElementRegle e = ite.next();
                if(e != null){
                    if(e.getJetonAssocie() != Jeton_Interface.ET && e.getJetonAssocie() != Jeton_Interface.FIN){
                        System.out.print(COLOR_PURPLE + "{" + COLOR_CYAN + e.getNomInterface() + COLOR_PURPLE+ "}" + COLOR_RESET);
                    }else{
                        if(e.getJetonAssocie() == Jeton_Interface.ET){
                            System.out.print(COLOR_YELLOW +e.getNomInterface()+ COLOR_RESET);
                        }
                    }
                }
            }

        }catch(MauvaiseDefinitionRegleException ex){
            System.err.println("Exception détectée : " + ex.getMessage());
        }
        catch(NumberFormatException nex){
            System.err.println("Seul un nombre est autorisé");
        }
    }
}
