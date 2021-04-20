package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    private App app;                                    // Chemin d'acces pour tout les controlleurs vers le main

    protected Object userVar;                           // Variable utile à l'initialisation de certains controlleurs

    /**
     * Permet d'acceder au main
     * @return le main courrant
     */
    public App getApp() {
        return app;
    }

    /**
     * Definit le chemin vers main
     * @param app l'application
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Action effectué lorsque la sourie survole un bouton (à la base utilisé pour emettre un son)
     */
    @FXML
    private void buttonHover(){
        //getApp().soundManager.playSound("button-hover");
    }

    /**
     * Fonction exécutée au chargement de tous les Controllers
     */
    public void initialise() {

    }

    /**
     * Definit la variable utilisateur
     * @param userVar la variable à stocker
     */
    public void setUserVar(Object userVar) {
        this.userVar = userVar;
    }

    /**
     * Fait apparraître une petite fenêtre. Utile pour afficher des erreurs ou des informations
     * @param type Le type de fenetre
     * @param text Le texte contenue dans la fenetre
     * @return
     */
    public Optional<ButtonType> showAlert(Alert.AlertType type, String text) {
        Alert alert = new Alert(type);
        alert.setContentText(text);
        alert.setResizable(true);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        alert.getDialogPane().setPrefWidth(1000);
        return alert.showAndWait();
    }
}
