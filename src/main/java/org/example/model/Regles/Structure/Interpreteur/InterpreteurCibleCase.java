package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.*;
import org.example.model.Regles.Jeton;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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

    public InterpreteurCibleCase(String str_c1, String str_c2) {
        this.str_source = str_c1 + "#" + str_c2;
    }

    /**
     * */
    @Override
    public List<GroupCases> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        List<GroupCases> lret;
        String erreurPiece = "'" + this.str_source + "': ";
        if (this.str_source.contains("#")){
            try {
                erreurPiece = "Cases absolu + Cases relatives: " + erreurPiece;
                lret = new ArrayList<>();

                String[] str_proprio_type = this.str_source.split("#");
                if (str_proprio_type.length < 2) { throw new MauvaiseInterpretationObjetRegleException("Format: '#' vide"); }

                List<GroupCases> gcases_absolu = interpreterGroupCases(str_proprio_type[0], ord);
                List<GroupCases> gcases_relatif = interpreterGroupCases(str_proprio_type[1], ord);

                LinkedHashSet<Case> ensemble_casesabsolufinales = new LinkedHashSet<>();
                for(Case c: gcases_absolu.get(0).getCasesAbsolue()){
                    ensemble_casesabsolufinales.addAll(gcases_relatif.get(0).getCasesRelatives(c.getPosition()));
                }

                GroupCases newg = new GroupCases("[" + gcases_absolu.get(0).getName() + "]" + " absolu avec ["
                        + gcases_relatif.get(0).getName() + "] relatif",
                        ord.getVariante().getPlateau());

                ArrayList<Case> tmp = new ArrayList<>();
                tmp.addAll(ensemble_casesabsolufinales);
                newg.setCasesAbsolue(tmp);

                lret.add(newg);
                return lret;
            } catch (MauvaiseInterpretationObjetRegleException m) {
                throw new MauvaiseInterpretationObjetRegleException(erreurPiece + m.getMessage());
            }

        } else {
            lret = interpreterGroupCases(this.str_source, ord);
            return lret;
        }
    }


    public List<GroupCases> interpreterGroupCases(String str, OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        ArrayList<GroupCases> lret = new ArrayList<>();
        String erreurCase = "GroupCases: '" + this.str_source + "': ";
        if (str.charAt(0) == 'C' && str.length() >= 2) {
            if (str.equals("CALL")) {
                lret.addAll(ord.getVariante().getListGroupCases());
                return lret;
            } else {
                int indgc;
                try {
                    indgc = Integer.parseInt(str.substring(1));
                    if (indgc >= 0 && indgc < Jeton.CASE.getBorne()){
                        lret.add(ord.getVariante().getListGroupCases().get(indgc));
                        return lret;
                    } else {
                        throw new MauvaiseInterpretationObjetRegleException(erreurCase + "numéro case inconnu");
                    }
                } catch (NumberFormatException ne) {
                    throw new MauvaiseInterpretationObjetRegleException(erreurCase + "Entier imparsable (NumberFormatException)");
                } catch (IndexOutOfBoundsException ie) {
                    throw new MauvaiseInterpretationObjetRegleException(erreurCase + "numéro case inconnu (IndexOutOfBoundsException)");
                }
            }
        }
        throw new MauvaiseInterpretationObjetRegleException(erreurCase + "Format incorrect (syntaxe de la forme CALL ou CN où N est un entier positif)");
        //return null;
    }


}
