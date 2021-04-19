package org.example.model.Regles;

public class MauvaiseSyntaxeRegleException extends MauvaiseDefinitionRegleException{
    //Exception liée à l'analyse syntaxique d'une règle
    public MauvaiseSyntaxeRegleException() { super(); }

    public MauvaiseSyntaxeRegleException(String s) {
        super(s);
    }
}
