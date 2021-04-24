package org.example.model;

import org.example.GameController;
import org.example.model.Regles.Jeton;

import java.util.Deque;
import java.util.Queue;

public class EndGameData {
    private Variante<Jeton> variante;
    private Deque<Integer> equipesClasse;
    private String message;
    private GameController gameController;

    public EndGameData(Variante<Jeton> variante, Deque<Integer> equipesClasse, String message, GameController gameController) {
        this.variante = variante;
        this.equipesClasse = equipesClasse;
        this.message = message;
        this.gameController = gameController;
    }

    //geteurs setteurs

    public Variante<Jeton> getVariante() {
        return variante;
    }

    public void setVariante(Variante<Jeton> variante) {
        this.variante = variante;
    }

    public Deque<Integer> getJoueursClasse() {
        return equipesClasse;
    }

    public void setJoueursClasse(Deque<Integer> equipesClasse) {
        this.equipesClasse = equipesClasse;
    }

    public String getMessage() {return message;}

    public void setMessage(String s) {message = s;}

    public GameController getGameController() {return gameController;}

    public void setGameController(GameController c) {gameController = c;}
}
