package org.example.model.Regles;

import java.io.Serializable;

public abstract class Consequence implements BlocDeRegle, Serializable {

    public abstract void comportement();
}
