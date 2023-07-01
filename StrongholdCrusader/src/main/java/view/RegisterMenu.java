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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
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
    public TextField passwordRecoveryAnswer;
    public ChoiceBox<String> passwordRecoveryQuestion;
    public TextField passwordAnswerConfirmation;
    public Label questionError;
    public CheckBox randomSlogan;
    public Rectangle captchaRec;
    public TextField captcha;
    public CheckBox randomPassword;
    public Label passwordText;
    private String captchaNumber;

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
        setBackground(registerPane);
        stage.show();
    }

    private void setBackground(Pane pane) {
        pane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/01.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
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
                System.out.println("todo");
                //passwordError.setText("ok");
            //else passwordError.setText(output.getString());خ
            System.out.println("nothing");
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
        nickname.textProperty().addListener((observable, oldText, newText) -> {
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
        passwordAnswerConfirmation.textProperty().addListener((observable, oldText, newText) -> checkQuestion());
        passwordRecoveryAnswer.textProperty().addListener((observable, oldText, newText) -> checkQuestion());
        generateNewCaptcha();
    }

    public void generateNewCaptcha() {
        captchaNumber = RegisterAndLoginController.chooseCaptcha();
        captchaRec.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/captcha/" + captchaNumber + ".png").toExternalForm())));
    }

    private void checkQuestion() {
        if (!passwordRecoveryAnswer.getText().equals(passwordAnswerConfirmation.getText()))
            questionError.setText("not equal");
        else if (passwordAnswerConfirmation.getText().isEmpty()) questionError.setText("empty field");
        else questionError.setText("ok");
    }

    public static Stage getStage() {
        return stage;
    }

    public void createUser() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (checkEverything() && captcha.getText().equals(captchaNumber)) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText(RegisterAndLoginController.completeRegister(username.getText(),
                    password.getText(), nickname.getText(), email.getText(), slogan.getText(),
                    passwordRecoveryQuestion.getValue(), passwordRecoveryAnswer.getText()).getString());
        } else {
            alert.setContentText("you should complete everything correctly");
            alert.setAlertType(Alert.AlertType.ERROR);
        }
        alert.show();
        generateNewCaptcha();
    }

    private boolean checkEverything() {
        return usernameError.getText().equals("ok") && sloganError.getText().equals("ok") && emailError.getText().equals("ok")
                && nicknameError.getText().equals("ok") && questionError.getText().equals("ok") &&
                passwordConfirmationError.getText().equals("ok");
    }

    public void enterLoginMenu(MouseEvent mouseEvent) throws Exception {
        (new LoginMenu()).start(stage);
    }

    public void activateSlogan() {
        if (sloganCheckBox.isSelected()) {
            slogan.setDisable(false);
            sloganError.setText("empty field!");
        } else {
            slogan.setDisable(true);
            slogan.setText("");
            sloganError.setText("ok");
        }
    }

    public void chooseRandomSlogan() {
        if (randomSlogan.isSelected()) {
            sloganCheckBox.setSelected(true);
            slogan.setDisable(false);
            slogan.setText(RegisterAndLoginController.makeRandomSlogan());
        }
    }

    public void chooseRandomPassword() {
        if (randomPassword.isSelected()) {
            String passwordString = RegisterAndLoginController.makeRandomPassword();
            passwordText.setText(passwordString);
            password.setText(passwordString);
        }
    }
}
