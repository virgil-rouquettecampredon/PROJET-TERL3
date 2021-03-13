package org.example.model.EquationDeDeplacement;

public class VecteurDeDeplacement extends EquationDeDeplacement {

    public VecteurDeDeplacement(boolean evaluable, int x, int y) {
        super(evaluable, x, y);
    }

    public VecteurDeDeplacement(int x, int y) {
        super(true,x, y);
    }
}
