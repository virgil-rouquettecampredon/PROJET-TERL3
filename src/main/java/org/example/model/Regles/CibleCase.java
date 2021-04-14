package org.example.model.Regles;

import org.example.model.Case;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Plateau;

import java.util.ArrayList;
import java.util.List;

public class CibleCase extends Cible<Case> {

    private String str_source;

    public CibleCase(String str_source) {
        this.str_source = str_source;
    }

    public List<Case> recupererTout(OrdonnanceurDeJeu ord) {
        List<Case> lret = new ArrayList<>();
        Plateau p = ord.getPlateau();
        if (this.str_source.charAt(0) == 'C' && this.str_source.length() >= 2) {
            if (this.str_source.equals("CALL")) {
                for (List<Case> listec: p.getEchiquier() ){
                    lret.addAll(listec);
                }
                return lret;
            } else {
                int indc = Integer.parseInt(this.str_source.substring(1));
                int yc = indc%(p.getHeightY());
                int xc = indc/(p.getWitdhX());
                lret.add(p.getEchiquier().get(xc).get(yc));
                return lret;
            }
        }
        return null;
    }

}
