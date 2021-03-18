package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class Variante implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variante variante = (Variante) o;
        return Objects.equals(name, variante.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
