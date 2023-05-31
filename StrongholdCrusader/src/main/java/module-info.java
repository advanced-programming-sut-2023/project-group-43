module com.example.strongholdcrusader {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.graphics;
    requires java.datatransfer;

    opens model to com.google.gson, javafx.graphics, javafx.scene;
    exports model;
    opens view to javafx.fxml;
    exports view;
    opens controller to com.google.gson, javafx.fxml;
    exports controller;
    exports controller.GameControllers;
    opens controller.GameControllers to com.google.gson, javafx.fxml;
    exports controller.UserControllers;
    opens controller.UserControllers to com.google.gson, javafx.fxml;
}