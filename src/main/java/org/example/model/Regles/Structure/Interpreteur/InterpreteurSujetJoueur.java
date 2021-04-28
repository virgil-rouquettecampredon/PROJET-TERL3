package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.GroupCases;
import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Jeton;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurSujetJoueur extends InterpreteurSujet<Joueur> {


    private String str_source;

    public InterpreteurSujetJoueur(String str_source){
        this.str_source = str_source;
    }

    @Override
    public List<Joueur> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        //System.out.println("source : " + str_source);

        List<Joueur> lret = new ArrayList<>();
        String erreurJoueur = "'" + this.str_source + "': ";
        if (this.str_source.charAt(0) == 'J' && this.str_source.length() >= 2) {
            erreurJoueur = "Joueur: " + erreurJoueur;
            if (this.str_source.equals("JALL")) {
                lret.addAll(ord.getVariante().getJoueurs());
                return lret;
            } else {
                try {
                    //System.out.println("Borne Joueur: " + Jeton.JOUEUR.getBorne(0));
                    int numjoueur = Integer.parseInt(this.str_source.substring(1));
                    if (numjoueur < Jeton.JOUEUR.getBorne(0) && numjoueur >= 0) {
                        lret.add(ord.getVariante().getJoueurs().get(numjoueur));
                        return lret;
                    } else {
                        throw new MauvaiseInterpretationObjetRegleException(erreurJoueur + "numéro joueur inconnu");
                    }
                } catch (NumberFormatException ne) {
                    throw new MauvaiseInterpretationObjetRegleException(erreurJoueur + "Entier imparsable (NumberFormatException)");
                } catch (IndexOutOfBoundsException ie) {
                    throw new MauvaiseInterpretationObjetRegleException(erreurJoueur + "numéro joueur inconnu (IndexOutOfBoundsException)");
                }
            }
        } else if (this.str_source.charAt(0) == 'E' && this.str_source.length() >= 2) {
            try {
                erreurJoueur = "Equipe: " + erreurJoueur;
                int numequipe = Integer.parseInt(this.str_source.substring(1));
                if (numequipe < Jeton.JOUEUR.getBorne(1) && numequipe >= 0) {
                    for (Joueur j : ord.getVariante().getJoueurs()) {
                        if (j.getEquipe() == numequipe) lret.add(j);
                    }
                    return lret;
                } else {
                    throw new MauvaiseInterpretationObjetRegleException(erreurJoueur + "numéro équipe inconnu");
                }
            } catch (NumberFormatException ne) {
                throw new MauvaiseInterpretationObjetRegleException(erreurJoueur + "Entier imparsable (NumberFormatException)");
            }
        }
        throw new MauvaiseInterpretationObjetRegleException(erreurJoueur + "Format incorrect (syntaxe de la forme JALL, JN ou EN où N est un entier positif)");

        //catch (NumberFormatException | IndexOutOfBoundsException e) {return null;}
        //return null;
    }

}
