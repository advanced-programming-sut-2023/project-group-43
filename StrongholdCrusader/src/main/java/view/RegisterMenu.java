package view;


import controller.RegisterAndLoginController;
import enums.Output;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
    public Label usernameError;
    public Label passwordError;
    public Label passwordConfirmationError;
    public Label sloganError;
    public Label emailError;
    public Label nicknameError;

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

    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkUsername(username.getText())) == null)
                usernameError.setText("ok");
            else usernameError.setText(output.getString());
        });
        password.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkPassword(password.getText())) == null)
                passwordError.setText("ok");
            else passwordError.setText(output.getString());
        });
        passwordConfirmation.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkUsername(passwordConfirmation.getText())) == null)
                passwordConfirmationError.setText("ok");
            else passwordConfirmationError.setText(output.getString());
        });
        email.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkEmail(email.getText())) == null)
                emailError.setText("ok");
            else emailError.setText(output.getString());
        });
        nicknameError.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkNickname(nickname.getText())) == null)
                nicknameError.setText("ok");
            else nicknameError.setText(output.getString());
        });
        slogan.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkSlogan(slogan.getText(), sloganCheckBox.isSelected())) == null)
                sloganError.setText("ok");
            else sloganError.setText(output.getString());
        });
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
