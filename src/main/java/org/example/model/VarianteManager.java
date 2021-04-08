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

    public List<Variante> getVariantes() {
        return variantes;
    }

    public Variante applyCurrent() {
        Variante vr = current.createVariante();
        variantes.add(vr);
        return vr;
    }

    public void saveCurrent(String path) throws IOException{
        Variante vrToSave = applyCurrent();
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(vrToSave);
        oos.close();
    }

    public Variante importFile(String path) throws IOException{
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Variante vr = (Variante) ois.readObject();
            ois.close();

            variantes.add(vr);
            return vr;
        }
        catch (IOException | ClassNotFoundException e) {
            throw new IOException();
        }
    }
}
