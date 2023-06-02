package view;

import controller.RegisterAndLoginController;
import enums.Output;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

public class LoginMenu extends Application {

    public PasswordField password;
    public TextField username;
    public Rectangle captchaRec;
    public TextField captcha;
    public Group group;
    public Label question;
    public TextField answer;
    public PasswordField newPassword;
    private String captchaNumber;
    private int incorrectPasswords = 0;
    private RegisterAndLoginController loginController;

    public void setLoginController(RegisterAndLoginController loginController) {
        this.loginController = loginController;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane registerPane = FXMLLoader.load(
                new URL(Objects.requireNonNull(LoginMenu.class.getResource("/fxml/loginMenu.fxml")).toExternalForm()));

        Scene scene = new Scene(registerPane);
        setBackground(registerPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        generateNewCaptcha();
    }

    private void setBackground(Pane pane) {
        pane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/01.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }

    private void generateNewCaptcha() {
        captchaNumber = RegisterAndLoginController.chooseCaptcha();
        captchaRec.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/captcha/" + captchaNumber + ".png").toExternalForm())));
    }

    private void forgetPassword(Matcher matcher) {
        String username;
        if ((username = matcher.group("username")) == null)
            username = matcher.group("username2");
        String question;
        System.out.println((question = RegisterAndLoginController.forgetPassword(username)));
        if (!question.equals("user does not exist")) {
            Scanner scanner = Menu.getScanner();
            String answer = scanner.nextLine();
            Output output = RegisterAndLoginController.checkPasswordRecoveryAnswer(username, answer);
            System.out.println(output.getString());
            if (output.equals(Output.CORRECT_PASSWORD_RECOVERY_ANSWER)) {
                String password = scanner.nextLine();
                System.out.println(RegisterAndLoginController.changePassword(username, password).getString());
            }
        }
    }

    private void checkForPause(Output output) {
        if (output != null && output.equals(Output.INCORRECT_PASSWORD)) incorrectPasswords++;
        else incorrectPasswords = 0;
        if (incorrectPasswords > 0 && (incorrectPasswords % 5) == 0) {
            try {
                System.out.println("you have to wait for " + incorrectPasswords + " seconds");
                TimeUnit.SECONDS.sleep(incorrectPasswords);
            } catch (Exception e) {
            }
        }
    }


    public void loginUser() throws Exception {
        if (captcha.getText().equals(captchaNumber)) {
            Output output = RegisterAndLoginController.loginUser(username.getText(), password.getText(), false);
            if (output.equals(Output.SUCCESSFUL_LOGIN)) {
                RegisterAndLoginController.enterMainMenu(username.getText());
            } else {
                showError(output.getString());
            }
        } else showError("wrong captcha");
    }

    private void showError(String text) {
        generateNewCaptcha();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.show();
        generateNewCaptcha();
    }

    public void back() throws Exception {
        (new RegisterMenu()).start(RegisterMenu.getStage());
    }

    public void forgetPassword() {
        if (RegisterAndLoginController.isUserExisted(username.getText())) {
            group.setVisible(true);
            question.setText(RegisterAndLoginController.getQuestion(username.getText()));
        }
        else
            showError("username does not exist!");
    }

    public void setNewPassword() {
        group.setVisible(false);
        if (RegisterAndLoginController.isAnswerCorrect(answer.getText(), username.getText())) {
            if (RegisterAndLoginController.checkPassword(newPassword.getText()) == null) {
                RegisterAndLoginController.changePassword(username.getText(), newPassword.getText());
            }
            else showError("wrong password format");
        }
        else showError("wrong answer");
    }
}
