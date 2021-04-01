package org.example;

import javafx.fxml.FXML;
import org.example.model.Joueur;
import org.example.model.Piece;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VarianteMenu1Controller extends Controller {

    @FXML
    private void boardButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("board");
    }

    @FXML
    private void playerButton() throws IOException {
        getApp().soundManager.playSound("button-click");
        getApp().setRoot("player");
    }

    @FXML
    private void continueButton() throws IOException {
        getApp().soundManager.playSound("button-confirm");

        if (getApp().varianteManager.getCurrent().getAllPawn().size() == 0) {
            //mettre les pieces par defaut
            File pawnFile = new File("src/main/resources/org/example/images/pawn.png");
            File kingFile = new File("src/main/resources/org/example/images/king.png");
            ArrayList<Joueur> j = getApp().varianteManager.getCurrent().getJoueurs();
            Joueur j2 = j.get(0);
            if (j.size() > 1) {
                j2 = j.get(1);
            }
            Piece p1 = new Piece("Paw", "file:" + pawnFile.getAbsolutePath(), j.get(0));
            p1.getJoueur().getTypePawnList().add(p1);

            Piece p2 = new Piece("King", "file:" + kingFile.getAbsolutePath(), j2);
            p2.getJoueur().getTypePawnList().add(p2);
        }

        getApp().setRoot("varianteMenu2");
    }
}
