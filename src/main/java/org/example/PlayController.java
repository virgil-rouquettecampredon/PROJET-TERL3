package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.example.model.FactoryVariante960;
import org.example.model.Variante;

import java.io.IOException;

public class PlayController extends Controller {
    @FXML
    public ComboBox<VarianteBox> varianteBox;
    @FXML
    public Button button;

    @Override
    public void initialise() {
        for (Variante v:
                getApp().varianteManager.getVariantes()) {
            varianteBox.getItems().add(new VarianteBox(v));
        }
        varianteBox.getItems().add(new VarianteBox(FactoryVariante960.createVariante()));
    }

    @FXML
    private void beginButton() throws IOException {
        getApp().soundManager.playSound("button-click");

        if (varianteBox.getValue() != null) {
            System.out.println(varianteBox.getValue().variante);
            getApp().setRoot("game", varianteBox.getValue().variante);
        }
        else {
            showAlert(Alert.AlertType.ERROR, "Erreur : Aucune variante selectionnée");
        }
    }

    @FXML
    public void boxModified() {
        getApp().soundManager.playSound("button-hover");
        button.setDisable(false);
    }

    public void backButton() throws IOException{
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("home");
    }

    public static class VarianteBox {
        private String name;
        private Variante variante;

        public VarianteBox(Variante variante) {
            this.name = variante.getName();
            this.variante = variante;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Variante getVariante() {
            return variante;
        }

        public void setVariante(Variante variante) {
            this.variante = variante;
        }

        @Override
        public String toString() {
            return getName();
        }
    }


}
