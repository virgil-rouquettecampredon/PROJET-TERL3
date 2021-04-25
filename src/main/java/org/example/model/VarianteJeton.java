package org.example.model;

import org.example.model.Regles.*;
import org.example.model.Regles.Structure.Automate.Automate_Regles_Semantique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VarianteJeton extends Variante<Jeton> {
    /*Classe modélisant une Variante telle qu'implémentéée dans l'interface*/

    private ArrayList<RegleInterface> regles;           // La liste de Regles sous forme de RegleInterface

    public VarianteJeton(String name, Plateau plateau, ArrayList<Joueur> joueurs, ArrayList<Joueur> ordreJoueurs, ArrayList<RegleInterface> regles, ArrayList<GroupCases> listGroupCases) {
        super(name, plateau, joueurs, ordreJoueurs, listGroupCases);
        this.regles = regles;
        setGenerateurDeRegle(new GenerateurDeRegle_Jeton(new Automate_Regles_Semantique()));
    }

    public VarianteJeton(Variante<Jeton> variante) {
        super(variante);
        this.regles = new ArrayList<>();
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
                    if(elRe.getJetonAssocie() != Jeton_Interface.ALIAS){
                        regleSousFormeMots.add(elRe.getNomRegle());
                    }else{
                        regleSousFormeMots.add(elRe.getNomInterface());
                        regleSousFormeMots.add(elRe.getNomRegle());
                    }
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
