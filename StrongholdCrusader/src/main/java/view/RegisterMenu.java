package view;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DataBase;

import java.net.URL;

public class RegisterMenu extends Application {

    public PasswordField password;
    public PasswordField passwordConfirmation;
    public TextField slogan;
    public TextField email;
    public TextField nickname;
    public TextField username;

    private static Stage stage;
    public CheckBox sloganCheckBox;

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DataBase.getInstance().saveData();
            System.exit(0);
        }));
        launch(RegisterMenu.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenu.stage = stage;
        BorderPane registerPane = FXMLLoader.load(
                new URL(RegisterMenu.class.getResource("/fxml/registerMenu.fxml").toExternalForm()));

        Scene scene = new Scene(registerPane);
        stage.setScene(scene);
        stage.show();
    }


    public static Stage getStage() {
        return stage;
    }

    public void createUser(MouseEvent mouseEvent) {
    }

    public void enterLoginMenu(MouseEvent mouseEvent) throws Exception {
        (new LoginMenu()).start(stage);
    }

    public void activateSlogan(MouseEvent mouseEvent) {
        if (sloganCheckBox.isSelected()) slogan.setDisable(false);
        else slogan.setDisable(true);
    }
}
