package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.model.VarianteManager;

import java.io.IOException;

public class App extends Application {

    public Scene scene;

    public SoundManager soundManager;
    public VarianteManager varianteManager;

    @Override
    public void start(Stage stage) throws IOException {
        //commentaire a supprimer pour push
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
        //commentaire
    }

    public void setRoot(String fxml) throws IOException {
        setRoot(fxml, null);
    }

    public void setRoot(String fxml, Object var) throws IOException {
        scene.setRoot(loadFXML(fxml, var));
    }

    private Parent loadFXML(String fxml, Object var) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        Pane layout = fxmlLoader.load();

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