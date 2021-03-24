package org.example.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Classe de transition entre l'interface et les variantes en mémoire
public class VarianteManager {
    private VarianteBuilder current;
    private List<Variante> variantes;

    public VarianteManager() {
        variantes = new ArrayList<>();
        current = new VarianteBuilder();
    }

    public void setCurrent(VarianteBuilder current) {
        this.current = current;
    }

    public VarianteBuilder getCurrent() {
        return current;
    }

    public void saveCurrent(String path) {
        Variante vrToSave = current.createVariante();
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(current);
            oos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Variante importFile(String path) {
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Variante vr = (Variante) ois.readObject();
            ois.close();

            variantes.add(vr);
            return vr;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
