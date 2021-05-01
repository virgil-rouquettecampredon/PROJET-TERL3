package org.example.model.Regles;

/**
 * IntegerRegle représente un entier en tand que SujetDeRegle. Cela permet de traiter des entiers dans
 * la création de règle (comparaison de timer, nombre de déplacement). L'objet est utilisé dans InterpreteurInteger*/
public class IntegerRegle implements SujetDeRegle, CibleDeRegle{

    private int val;

    public IntegerRegle(int val){
        this.val = val;
    }

    public int getVal() { return val; }
}
