package org.example.model.EquationDeDeplacement;

import org.example.model.Position;

public class PositionDeDeplacement extends EquationDeDeplacement {
    public PositionDeDeplacement(boolean evaluable, int x, int y) {
        super(evaluable, x, y);
    }

    public PositionDeDeplacement(int x, int y) {
        super(x, y);
    }

    public Position evaluate(Position depart){
        Position p = super.evaluate(depart);
        setEvaluable(false);
        return p;
    }
}
