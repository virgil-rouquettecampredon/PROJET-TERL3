package org.example.model.Regles;

public class MauvaiseSyntaxeRegleException extends MauvaiseDefinitionRegleException{
    public MauvaiseSyntaxeRegleException() { super(); }

    public MauvaiseSyntaxeRegleException(String s) {
        super(s);
    }
}
