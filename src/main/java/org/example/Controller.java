package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class Controller {
    private App app;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void buttonHover() throws IOException {
        //getApp().soundManager.playSound("button-hover");
    }

    public void initialise() {

    }
}
