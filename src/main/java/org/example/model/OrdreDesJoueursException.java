package org.example.model;

public class OrdreDesJoueursException extends Exception {
    private String message;

    public OrdreDesJoueursException() { super(); }

    public OrdreDesJoueursException(String s) {
        super();
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}