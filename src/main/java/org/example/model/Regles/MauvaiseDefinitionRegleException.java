package org.example.model.Regles;

public class MauvaiseDefinitionRegleException extends Exception{
    private String message;

    public MauvaiseDefinitionRegleException() { super(); }

    public MauvaiseDefinitionRegleException(String s) {
        super();
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
