package org.example.model.EquationDeDeplacement;

public class VecteurDeDeplacement extends EquationDeDeplacement {

    public VecteurDeDeplacement(boolean evaluable, int x, int y, TypeDeplacement typeDeplacement) {
        super(evaluable, x, y, typeDeplacement);
    }

    public VecteurDeDeplacement(int x, int y, TypeDeplacement typeDeplacement) {
        super(true, x, y, typeDeplacement);
    }

    public VecteurDeDeplacement(VecteurDeDeplacement vec) {
        this(vec.getX(), vec.getY(), vec.getTypeDeplacement());
    }
}
