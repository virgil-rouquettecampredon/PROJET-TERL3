package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.example.model.EndGameData;
import org.example.model.Joueur;

import java.io.IOException;

public class GameOverController extends Controller {
    @FXML
    public VBox joueursVBox;

    private EndGameData data;
    @Override
    public void initialise() {
        data = (EndGameData) userVar;
        while (!data.getJoueursClasse().isEmpty()) {
            StringBuilder sb = new StringBuilder("Equipe ");
            int equipe = data.getJoueursClasse().removeLast();
            sb.append(equipe);
            sb.append(" ( ");
            for (Joueur j : data.getVariante().getJoueurs()) {
                if (j.getEquipe() == equipe) {
                    sb.append(j.getName());
                    sb.append(" ");
                }
            }
            sb.append(")");

            Label l = new Label(sb.toString());
            l.getStyleClass().add("label-input");
            l.setTextAlignment(TextAlignment.CENTER);
            l.setWrapText(true);
            joueursVBox.getChildren().add(l);
        }
    }

    @FXML
    private void backButton() throws IOException {
        getApp().soundManager.playSound("button-cancel");
        getApp().setRoot("home");
    }

    @FXML
    private void restartButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");
        getApp().setRoot("game", data.getVariante());
    }
}
