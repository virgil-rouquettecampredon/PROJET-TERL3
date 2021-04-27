package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.example.model.EndGameData;
import org.example.model.Regles.Jeton;
import org.example.model.Variante;
import org.example.model.VarianteManager;

import java.io.IOException;

public class App extends Application {

    public Scene scene;                         // Scene principale de l'application

    public SoundManager soundManager;           // Gestionnaire du son
    public VarianteManager varianteManager;     // Gestionnaire des variantes

    /**
     * Equivalent du main pour javaFX
     * @param stage donné par javaFX pour transtmettre la scene principale
     * @throws IOException quand on ne peux pas charger la permière scene
     */
    @Override
    public void start(Stage stage) throws IOException {
        soundManager = new SoundManager();
        varianteManager = new VarianteManager();

        stage.setTitle("ChessBurger");
        stage.setResizable(false);
        scene = new Scene(loadFXML("home", null), 1024, 640);
        stage.setScene(scene);
        stage.setOnCloseRequest(t -> {
            soundManager.playSound("lose");
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }

    /**
     * Charger une scene dans la fenetre principale
     * @param fxml le nom du fichier fxml à charger (ex: "home" pour home.fxml)
     * @throws IOException si impossible de charger
     */
    public void setRoot(String fxml) throws IOException {
        setRoot(fxml, null);
    }

    /**
     * Charger une scene dans la fenetre principale
     * @param fxml le nom du fichier fxml à charger (ex: "home" pour home.fxml)
     * @param var variable optionnelle qui sera chargé dans Controller et qui peux être récupéré
     * @throws IOException
     */
    public void setRoot(String fxml, Object var) throws IOException {
        scene.setRoot(loadFXML(fxml, var));
    }

    public Parent loadFXML(String fxml, Object var) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        Parent layout = fxmlLoader.load();

        Controller controller = fxmlLoader.getController();
        controller.setApp(this);
        controller.setUserVar(var);
        controller.initialise();

        return layout;
    }

    public static void main(String[] args) {
        launch();
    }

}