package org.example.model;

import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Automate.Automate_Regles;
import org.example.model.Regles.Structure.Automate.Automate_Regles_Semantique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class VarianteJeton extends Variante<Jeton> {
    /*Classe modélisant une Variante telle qu'implémentéée dans l'interface*/

    private ArrayList<RegleInterface> regles;           // La liste de Regles sous forme de RegleInterface

    public VarianteJeton(String name, Plateau plateau, ArrayList<Joueur> joueurs, ArrayList<Joueur> ordreJoueurs, ArrayList<RegleInterface> regles, ArrayList<GroupCases> listGroupCases) {
        super(name, plateau, joueurs, ordreJoueurs, listGroupCases);
        this.regles = regles;
        Automate_Regles<Jeton> auto = new Automate_Regles_Semantique();
        auto.initialiserAutomate();
        setGenerateurDeRegle(new GenerateurDeRegle_Jeton(auto));
    }

    public VarianteJeton(Variante<Jeton> variante) {
        super(variante);
        this.regles = new ArrayList<>();
        Automate_Regles<Jeton> auto = new Automate_Regles_Semantique();
        auto.initialiserAutomate();
        setGenerateurDeRegle(new GenerateurDeRegle_Jeton(auto));
    }

    public Variante<Jeton> clone() throws CloneNotSupportedException{
        Variante<Jeton> vj = super.clone();
        //rehinitialiser l'automate
        Automate_Regles<Jeton> auto = new Automate_Regles_Semantique();
        auto.initialiserAutomate();
        vj.setGenerateurDeRegle(new GenerateurDeRegle_Jeton(auto));
        return vj;
    }

    public void initialiser(List<Piece> typePieces) throws VarianteException{
        for(RegleInterface ri : getRegles()){
            //Récupération de la liste des éléments composants une Regle sur l'interface
            ArrayList<ElementRegle> regleSousFormeElemRegle = ri.getRegle();

            Jeton.CASE.setBorne(getListGroupCases().size());
            Jeton.JOUEUR.setBorne(getJoueurs().size());
            Jeton.PIECE.setBorne(typePieces.size());
            Set<Integer> equipes = new LinkedHashSet<>();
            for(Joueur j : getJoueurs()){
                equipes.add(j.getEquipe());
            }
            Jeton.PIECE.setBorne(equipes.size(), 1);

            //Instanciation de la liste traduisant la regle obtenue depuis l'interface en regle sous forme de liste de termes
            // 0 : Regle avant le coup d'un joueur
            // 1 : Regle après le coup d'un joueur
            String s  = (ri.isTraitementAvantCoup())?"0":"1";
            List<String> regleSousFormeMots = new ArrayList<>();
            regleSousFormeMots.add(s);

            for(ElementRegle elRe : regleSousFormeElemRegle){
                if(elRe.getJetonAssocie() != Jeton_Interface.FIN){
                    regleSousFormeMots.add(elRe.getNomRegle());
                }
            }

            //Renseignement de la regle au système de génération de règle
            getGenerateurDeRegle().addRegleSousFormeDeChaine(regleSousFormeMots);
        }
        try{
            //Génération de la règle
            getGenerateurDeRegle().genererRegles();
        }catch (MauvaiseDefinitionRegleException e){
            throw new VarianteException("Erreur création variante (REGLE) : " + e.getMessage());
        }
    }

    /*Getter et Setter*/
    public ArrayList<RegleInterface> getRegles() {
        return regles;
    }

    public void setRegles(ArrayList<RegleInterface> generateurDeRegle) {
        this.regles = generateurDeRegle;
    }
}
