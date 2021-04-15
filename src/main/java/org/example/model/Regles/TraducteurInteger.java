package org.example.model.Regles;

import java.util.ArrayList;
import java.util.List;

public class TraducteurInteger implements Traducteur_Objet_Regle {

    private String str_int;
    private IntegerRegle int_regle;

    public TraducteurInteger(String str_int) {
        this.str_int = str_int;
    }

    @Override
    public List<IntegerRegle> recupererTout() {
        try {
            int val = Integer.parseInt(this.str_int);
            this.int_regle = new IntegerRegle(val);
            List<IntegerRegle> lir = new ArrayList<>();
            lir.add(this.int_regle);
            return lir;
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
