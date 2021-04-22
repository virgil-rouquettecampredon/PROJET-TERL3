package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {
    private int x;                  // La position x dans le plateau
    private int y;                  // La position y dans le plateau

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*DEBUT GETTER SETTER*/

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /*FIN GETTER SETTER*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "("+x +", "+y+")";
    }
}