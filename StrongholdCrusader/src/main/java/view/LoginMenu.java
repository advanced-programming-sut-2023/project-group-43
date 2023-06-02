package view;

import controller.RegisterAndLoginController;
import enums.Output;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

public class LoginMenu extends Application {

    public PasswordField password;
    public TextField username;
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
        primaryStage.setScene(scene);
        primaryStage.show();
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
        Output output = RegisterAndLoginController.loginUser(username.getText(), password.getText(), false);
        if (output.equals(Output.SUCCESSFUL_LOGIN)) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(RegisterMenu.getStage());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(output.getString());
            alert.show();
        }
    }

    public void back() throws Exception {
        (new RegisterMenu()).start(RegisterMenu.getStage());
    }

    public void forgetPassword(MouseEvent mouseEvent) {
    }
}
