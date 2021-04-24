package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Case;
import org.example.model.GroupCases;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Plateau;

import java.util.ArrayList;
import java.util.List;


/**
 * Le rôle de la Classe InterpreteurCibleCase est de retourner grâce à la fonction recupererTout selon la chaine de
 * caractère donné à l'initialisation la liste des Cases qui correspondent selon le contexte de jeu
 * Exemple
 * */

public class InterpreteurCibleCase extends InterpreteurCible<GroupCases> {

    private String str_source;

    public InterpreteurCibleCase(String str_source) {
        this.str_source = str_source;
    }

    @Override
    public List<GroupCases> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        List<GroupCases> lret = new ArrayList<>();
        Plateau p = ord.getPlateau();
        /*String erreurCase = "Case: '" + this.str_source + "': ";
        if (this.str_source.charAt(0) == 'C' && this.str_source.length() >= 2) {
            if (this.str_source.equals("CALL")) {
                try {
                    lret.add(ord.getVariante().getListGroupCases().get(0));
                    return lret;
                } catch (IndexOutOfBoundsException ie) {


                }
            } else {
                int indc;
                try {
                    indc = Integer.parseInt(this.str_source.substring(1));
                    if (indc >= 0 && indc < p.getHeightY()*p.getWitdhX()){
                        int yc = indc%(p.getHeightY());
                        int xc = indc/(p.getWitdhX());
                        lret.add(p.getEchiquier().get(xc).get(yc));
                        return lret;
                    } else {
                        throw new MauvaiseInterpretationObjetRegleException(erreurCase + "numéro case inconnu");
                    }
                } catch (NumberFormatException ne) {
                    throw new MauvaiseInterpretationObjetRegleException(erreurCase + "Entier imparsable (NumberFormatException)");
                }
            }
        }
        throw new MauvaiseInterpretationObjetRegleException(erreurCase + "Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)");*/
        return null;
    }

}
