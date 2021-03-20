module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires junit;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.model;
    exports org.example.model.EquationDeDeplacement;
    exports org.example.model.Regles;
}