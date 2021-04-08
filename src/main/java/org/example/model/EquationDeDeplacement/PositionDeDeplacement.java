package org.example.model.EquationDeDeplacement;

import org.example.model.Position;

public class PositionDeDeplacement extends EquationDeDeplacement {
    public PositionDeDeplacement(boolean evaluable, int x, int y, TypeDeplacement typeDeplacement) {
        super(evaluable, x, y, typeDeplacement);
    }

    public PositionDeDeplacement(int x, int y, TypeDeplacement typeDeplacement) {
        super(true, x, y, typeDeplacement);
    }

    public Position evaluate(Position depart){
        Position p = super.evaluate(depart);
        setEvaluable(false);
        return p;
    }
}
