package org.example.model;

public class VarianteException extends Exception{
    //Exception liée à un variante de jeu

    private String message;

    public VarianteException() { super(); }

    public VarianteException(String s) {
        super();
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
