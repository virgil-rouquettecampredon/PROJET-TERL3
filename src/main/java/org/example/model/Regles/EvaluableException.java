package org.example.model.Regles;

public abstract class EvaluableException extends Exception{
    //Exception liée aux élements évaluables (EstEvaluable)
    private String message;

    public EvaluableException(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }
}
