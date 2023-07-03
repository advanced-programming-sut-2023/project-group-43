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
    opens enums to com.google.gson;
    exports enums;
    opens enums.environmentEnums to com.google.gson;
    exports enums.environmentEnums;
    opens enums.BuildingEnums to com.google.gson;
    exports enums.BuildingEnums;
    opens enums.Degrees to com.google.gson;
    exports enums.Degrees;
    opens enums.unitEnums to com.google.gson;
    exports enums.unitEnums;
    opens model.units to com.google.gson;
    exports model.units;
    opens model.buildings to com.google.gson;
    exports model.buildings;
    opens network to com.google.gson;
    exports network;
}