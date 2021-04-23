package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Case;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Plateau;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurCibleCase extends InterpreteurCible<Case> {

    private String str_source;

    public InterpreteurCibleCase(String str_source) {
        this.str_source = str_source;
    }

    @Override
    public List<Case> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        List<Case> lret = new ArrayList<>();
        Plateau p = ord.getPlateau();
        String erreurCase = "Case: '" + this.str_source + "': ";
        if (this.str_source.charAt(0) == 'C' && this.str_source.length() >= 2) {
            if (this.str_source.equals("CALL")) {
                for (List<Case> listec: p.getEchiquier() ){
                    lret.addAll(listec);
                }
                return lret;
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
        throw new MauvaiseInterpretationObjetRegleException(erreurCase + "Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)");
        //return null;
    }

}
