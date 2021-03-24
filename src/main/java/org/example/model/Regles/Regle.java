package org.example.model.Regles;

import java.util.List;

public class Regle {
    private List<Bloc<Condition>> conditions;
    private List<Bloc<Consequence>> conséquences;
    private boolean bool;

    public Regle(){
        super();
        this.bool = true;
    }

    public void setBool(boolean b){
        this.bool = b;
    }

    public boolean getBool(){
        return this.bool;
    }
}
