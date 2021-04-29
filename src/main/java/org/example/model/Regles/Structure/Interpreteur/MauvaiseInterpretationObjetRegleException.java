package org.example.model.Regles.Structure.Interpreteur;

import org.example.model.Regles.MauvaiseDefinitionRegleException;

public class MauvaiseInterpretationObjetRegleException extends MauvaiseDefinitionRegleException {

    public MauvaiseInterpretationObjetRegleException(){ super(); }

    public MauvaiseInterpretationObjetRegleException(String s){
        super("Interpreteur objet de regle: " + s);
    }
}
