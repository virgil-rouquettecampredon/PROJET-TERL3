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

import java.io.IOException;

public class App extends Application {

    public static SoundManager soundManager;
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //commentaire a supprimer pour push
        soundManager = new SoundManager();
        stage.setTitle("ChessBurger");
        stage.setResizable(false);
        scene = new Scene(loadFXML("home"), 1024, 640);
        stage.setScene(scene);
        stage.setOnCloseRequest(t -> {
            App.soundManager.playSound("lose");
            Platform.exit();
            System.exit(0);
        });
        stage.show();
        //commentaire
    }

    public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Pane layout = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.setApp(this);
        return layout;
    }

    public static void main(String[] args) {
        launch();
    }

}