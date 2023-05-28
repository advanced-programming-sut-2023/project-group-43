package view;

import controller.RegisterAndLoginController;
import enums.Output;
import enums.Validations;
import enums.menuEnums.RegisterAndLoginCommands;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

public class LoginMenu extends Application {

    private int incorrectPasswords = 0;

    public void run() {
        Scanner scanner = Menu.getScanner();
        String input;
        Output output;
        Matcher matcher;
        System.out.println("login menu:");
        while (true) {
            input = scanner.nextLine();
            output = null;
            if (input.matches("show current menu"))
                output = Output.LOGIN_MENU;
            if ((matcher = RegisterAndLoginCommands.getMatcher(input, RegisterAndLoginCommands.LOGIN_USER)) != null) {
                output = loginUser(matcher);
            } else if ((matcher = RegisterAndLoginCommands.getMatcher(input, RegisterAndLoginCommands.FORGET_PASSWORD)) != null) {
                forgetPassword(matcher);
                continue;
            } else if (RegisterAndLoginCommands.getMatcher(input, RegisterAndLoginCommands.BACK) != null) {
                System.out.println("register menu:");
                return;
            }
            if (output != null) System.out.println(output.getString());
            else System.out.println("invalid command");
            if (output != null && output.equals(Output.SUCCESSFUL_LOGIN)) {
                String captcha = RegisterAndLoginController.generateCaptcha();
                System.out.println(RegisterAndLoginController.asciiArt(captcha));
                input = scanner.nextLine();
                output = RegisterAndLoginController.checkCaptcha(captcha, input);
                System.out.println(output.getString());
                if (output.equals(Output.CAPTCHA_MATCHED)) {
                    String username = Validations.getInfo("u", matcher.group());
                    enterMainMenu(username);
                }
            }
            checkForPause(output);
        }
    }

    private Output loginUser(Matcher matcher) {
        String username = Validations.getInfo("u", matcher.group());
        String password = Validations.getInfo("p", matcher.group());
        Boolean isStayLoggedIn = (matcher.group("stayLoggedIn") != null);
        return RegisterAndLoginController.loginUser(username, password, isStayLoggedIn);
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

    private void enterMainMenu(String username) {
        RegisterAndLoginController.enterMainMenu(username);
        System.out.println("login menu:");
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane registerPane = FXMLLoader.load(
                new URL(LoginMenu.class.getResource("/fxml/loginMenu.fxml").toExternalForm()));

        Scene scene = new Scene(registerPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void enterMainMenu(MouseEvent mouseEvent) {
    }

    public void back() throws Exception {
        (new RegisterMenu()).start(RegisterMenu.getStage());
    }

    public void forgetPassword(MouseEvent mouseEvent) {
    }
}
