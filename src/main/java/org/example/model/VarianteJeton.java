package org.example.model;

import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Automate.Automate_Regles_Semantique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VarianteJeton extends Variante<Jeton> {
    /*Classe modélisant une Variante telle qu'implémentéée dans l'interface*/

    public VarianteJeton(String name, Plateau plateau, ArrayList<Joueur> joueurs, ArrayList<Joueur> ordreJoueurs, ArrayList<RegleInterface> regles, ArrayList<GroupCases> listGroupCases) {
        super(name, plateau, joueurs, ordreJoueurs, regles, listGroupCases);
        setGenerateurDeRegle(new GenerateurDeRegle_Jeton(new Automate_Regles_Semantique()));
    }

    public VarianteJeton(Variante variante) {
        super(variante);
        setGenerateurDeRegle(new GenerateurDeRegle_Jeton(new Automate_Regles_Semantique()));
    }

    public void initialiser() throws VarianteException{
        for(RegleInterface ri : getRegles()){
            //Récupération de la liste des éléments composants une Regle sur l'interface
            ArrayList<ElementRegle> regleSousFormeElemRegle = ri.getRegle();

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
}
