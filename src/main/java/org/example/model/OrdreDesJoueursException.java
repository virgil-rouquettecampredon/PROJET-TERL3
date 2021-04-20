package org.example.model;

public class OrdreDesJoueursException extends Exception {
    //Exception liée à un ordonnanceur de joueurs (pour connaitre le prochain joueur)

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