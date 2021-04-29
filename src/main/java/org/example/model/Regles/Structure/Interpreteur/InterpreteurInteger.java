package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.GroupCases;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.IntegerRegle;

import java.util.ArrayList;
import java.util.List;

public class InterpreteurInteger implements Interpreteur_Objet_Regle<IntegerRegle> {

    private String str_int;
    private IntegerRegle int_regle;

    public InterpreteurInteger(String str_int) {
        this.str_int = str_int;
    }

    @Override
    public List<IntegerRegle> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        try {
            int val = Integer.parseInt(this.str_int);

            //TODO enlever
            System.out.println("INT NB : " + val);

            this.int_regle = new IntegerRegle(val);
            List<IntegerRegle> lir = new ArrayList<>();
            lir.add(this.int_regle);
            return lir;
        } catch (NumberFormatException nfe) {
            //return null;
            throw new MauvaiseInterpretationObjetRegleException("Entier: '" + this.str_int + "': Entier imparsable (NumberFormatException)");
        }
    }
}
