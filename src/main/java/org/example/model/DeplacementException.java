package org.example.model;

public class DeplacementException extends RuntimeException {
    private String message;

    public DeplacementException() { super(); }
    public DeplacementException(String s) {
        super();
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
