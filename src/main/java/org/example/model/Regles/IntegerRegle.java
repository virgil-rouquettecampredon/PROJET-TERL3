package org.example.model.Regles;

public class IntegerRegle implements SujetDeRegle, CibleDeRegle{

    private int val;

    public IntegerRegle(int val){
        this.val = val;
    }

    public int getVal() { return val; }
}
