package view;


import controller.RegisterAndLoginController;
import enums.Output;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public CheckBox sloganCheckBox;
    public Label usernameError;
    public Label passwordError;
    public Label passwordConfirmationError;
    public Label sloganError;
    public Label emailError;
    public Label nicknameError;
    public TextField passwordRecoveryAnswer;
    public ChoiceBox passwordRecoveryQuestion;
    public TextField passwordAnswerConfirmation;
    public Label questionError;
    private Stage stage;

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DataBase.getInstance().saveData();
            System.exit(0);
        }));
        launch(RegisterMenu.class, args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        BorderPane registerPane = FXMLLoader.load(
                new URL(RegisterMenu.class.getResource("/fxml/registerMenu.fxml").toExternalForm()));

        Scene scene = new Scene(registerPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("What is my father’s name?",
                "What was my first pet’s name?",
                "What is my mother’s last name?");
        passwordRecoveryQuestion.setItems(list);
        passwordRecoveryQuestion.setValue("What is my father’s name?");
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
            if ((output = RegisterAndLoginController.checkPasswordConfirmation(passwordConfirmation.getText(),
                    password.getText())) == null)
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
        passwordAnswerConfirmation.textProperty().addListener((observable, oldText, newText) -> {
            checkQuestion();
        });
        passwordRecoveryAnswer.textProperty().addListener((observable, oldText, newText) -> {
            checkQuestion();
        });
    }

    private void checkQuestion() {
        if (!passwordRecoveryAnswer.getText().equals(passwordAnswerConfirmation.getText()))
            questionError.setText("not equal");
        else if (passwordAnswerConfirmation.getText().isEmpty()) questionError.setText("empty field");
        else questionError.setText("ok");
    }

    public void createUser(MouseEvent mouseEvent) {
    }

    public void enterLoginMenu() throws Exception {
        RegisterAndLoginController registerAndLoginController = new RegisterAndLoginController();
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.setLoginController(registerAndLoginController);
        loginMenu.start(stage);
    }

    public void activateSlogan() {
        if (sloganCheckBox.isSelected()) slogan.setDisable(false);
        else {
            slogan.setDisable(true);
            slogan.setText("");
            sloganError.setText("ok");
        }
    }
}
