package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.model.Joueur;

class PlayerTableRow {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty team;
    private final SimpleIntegerProperty timerMinute;
    private final SimpleIntegerProperty timerSeconde;
    private final Joueur joueur;
    private final int id;

    PlayerTableRow(String name, int team, int id) {
        this.name = new SimpleStringProperty(name);
        this.team = new SimpleIntegerProperty(team);
        joueur = new Joueur(name, team);
        this.timerMinute = new SimpleIntegerProperty(joueur.getMinute());
        this.timerSeconde = new SimpleIntegerProperty(joueur.getSeconde());
        this.id = id;
    }

    PlayerTableRow(Joueur p, int id) {
        name = new SimpleStringProperty(p.getName());
        team = new SimpleIntegerProperty(p.getEquipe());
        this.timerMinute = new SimpleIntegerProperty(p.getMinute());
        this.timerSeconde = new SimpleIntegerProperty(p.getSeconde());
        this.joueur = p;
        this.id = id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleIntegerProperty timerMinuteProperty() {
        return timerMinute;
    }

    public int getTimerMinute() {return timerMinute.get();}

    public SimpleIntegerProperty timerSecondeProperty() {
        return timerSeconde;
    }

    public int getTimerSeconde() {return timerSeconde.get();}

    public int getTeam() {
        return team.get();
    }

    public SimpleIntegerProperty teamProperty() {
        return team;
    }

    public void setTeam(int team) {
        this.team.set(team);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Joueur getJoueur() { return joueur;}

    @Override
    public String toString() {
        return "PlayerTableRow{" +
                "name=" + name +
                ", team=" + team +
                '}';
    }

    public int getId() {
        return id;
    }
}
