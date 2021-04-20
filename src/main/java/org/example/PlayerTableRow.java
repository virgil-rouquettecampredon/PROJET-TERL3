package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.model.Joueur;

class PlayerTableRow {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty team;
    private final Joueur joueur;

    PlayerTableRow(String name, int team) {
        this.name = new SimpleStringProperty(name);
        this.team = new SimpleIntegerProperty(team);
        joueur = new Joueur(name, team);
    }

    PlayerTableRow(Joueur p) {
        name = new SimpleStringProperty(p.getName());
        team = new SimpleIntegerProperty(p.getEquipe());
        this.joueur = p;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

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
}
