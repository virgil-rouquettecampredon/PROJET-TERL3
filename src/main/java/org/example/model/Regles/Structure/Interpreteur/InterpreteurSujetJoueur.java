package org.example.model.Regles.Structure.Interpreteur;

import java.util.List;
import java.util.ArrayList;
import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Jeton;

public class InterpreteurSujetJoueur extends InterpreteurSujet<Joueur> {

    private String str_source;

    public InterpreteurSujetJoueur(String str_source) {
        this.str_source = str_source;
    }

    public List<Joueur> recupererTout(OrdonnanceurDeJeu ord) {
        List<Joueur> lret = new ArrayList<>();
        try {
            if (this.str_source.charAt(0) == 'J' && this.str_source.length() >= 2) {
                if (this.str_source.equals("JALL")) {
                    lret.addAll(ord.getJoueurs());
                    return lret;
                } else {
                    int numjoueur = Integer.parseInt(this.str_source.substring(1));
                    if (numjoueur < Jeton.JOUEUR.getBorne(0) && numjoueur >= 0) {
                        lret.add(ord.getJoueur(numjoueur));
                        return lret;
                    }
                }
            } else if (this.str_source.charAt(0) == 'E' && this.str_source.length() >= 2) {
                int numequipe = Integer.parseInt(this.str_source.substring(1));
                if (numequipe < Jeton.JOUEUR.getBorne(1) && numequipe >= 0) {
                    for (Joueur j : ord.getJoueurs()) {
                        if (j.getEquipe() == numequipe) lret.add(j);
                    }
                    return lret;
                }
            }
            return null;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return null;
        }
    }

}
