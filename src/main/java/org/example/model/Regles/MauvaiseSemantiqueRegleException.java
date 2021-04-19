package org.example.model.Regles;

public class MauvaiseSemantiqueRegleException extends MauvaiseDefinitionRegleException{
    //Exception liée à l'analyse sémantique d'une règle
    public MauvaiseSemantiqueRegleException() { super(); }

    public MauvaiseSemantiqueRegleException(String s) {
        super(s);
    }
}
