package org.example.model.Regles.Structure.Interpreteur;

public class MauvaiseInterpretationObjetRegleException extends Exception {

    public MauvaiseInterpretationObjetRegleException(){ super(); }

    public MauvaiseInterpretationObjetRegleException(String s){
        super("Interpreteur objet de regle: " + s);
    }
}
