package view;

import controller.RegisterAndLoginController;

import model.DataBase;

import enums.Output;
import enums.menuEnums.RegisterAndLoginCommands;


import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu extends Menu {


    private RegisterAndLoginController registerAndLoginController;

    private String randomPassword;

    public void run() {
        Scanner scanner = Menu.getScanner();
        String input;
        Output output;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            output = null;
            if ((matcher = RegisterAndLoginCommands.getMatcher(input, RegisterAndLoginCommands.CREATE_USER)) != null) {
                output = createUser(matcher, null, null, null);
            } else if (RegisterAndLoginCommands.getMatcher(input, RegisterAndLoginCommands.BACK) != null) {
                return;
            } else if (RegisterAndLoginCommands.getMatcher(input, RegisterAndLoginCommands.ENTER_LOGIN_MENU) != null) {
                enterLoginMenu();
            }
            if (output != null) {
                output = checkOutput(matcher, output);
                System.out.println(output.getString());
            } else System.out.println("invalid command");
        }
    }

    private Output createUser(Matcher matcher, String randomPassword, String randomConfirmation, String randomSlogan) {
        return parseMatcher(matcher, null, randomPassword, randomConfirmation, randomSlogan);
    }

    private Output createRandomPassword(Matcher matcher, Output output, String randomSlogan) {
        randomPassword = RegisterAndLoginController.makeRandomPassword();
        System.out.println("your random password is: " + randomPassword);
        System.out.println(output.getString());
        String randomPasswordConfirmation = scanner.nextLine();
        return createUser(matcher, randomPassword, randomPasswordConfirmation, randomSlogan);
    }

    private Output checkOutput(Matcher matcher, Output output) {
        String slogan = null;
        if (output.equals(Output.RANDOM_SLOGAN)) {
            System.out.print(output.getString());
            slogan = RegisterAndLoginController.makeRandomSlogan();
            System.out.println(slogan);
            output = createUser(matcher, null, null, slogan);
        }
        if (output.equals(Output.CONFIRM_PASSWORD)) {
            output = createRandomPassword(matcher, output, slogan);
        }
        if (output.equals(Output.CHOOSE_PASSWORD_RECOVERY_QUESTION)) {
            output = choosePasswordRecoveryQuestion(matcher, output, slogan);
        }
        return output;
    }

    private Output choosePasswordRecoveryQuestion(Matcher matcher, Output output, String randomSlogan) {
        Scanner scanner = Menu.getScanner();
        String input;
        Matcher recoveryMatcher;
        while (output == null || !output.equals(Output.SUCCESSFUL_PASSWORD_RECOVERY_QUESTION)) {
            if (output != null)
                System.out.println(output.getString());
            else
                System.out.println("invalid command");
            input = scanner.nextLine();
            output = null;
            if ((recoveryMatcher = RegisterAndLoginCommands.getMatcher
                    (input, RegisterAndLoginCommands.CHOOSE_PASSWORD_RECOVERY_QUESTION)) != null) {
                output = parseMatcher(matcher, recoveryMatcher, randomPassword, null, randomSlogan);
            }
        }
        return output;
    }

    public void enterLoginMenu() {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.run();
    }

    private Output parseMatcher(Matcher matcher, Matcher recoveryMatcher, String randomPassword, String randomConfirmation, String randomSlogan) {
        String username, password, passwordConfirmation, nickname, slogan;
        if ((username = matcher.group("username")) == null)
            username = matcher.group("username2");
        if ((password = matcher.group("password")) == null)
            password = matcher.group("password2");
        if ((nickname = matcher.group("nickname")) == null)
            nickname = matcher.group("nickname2");
        if ((slogan = matcher.group("slogan")) == null)
            slogan = matcher.group("slogan2");
        String email = matcher.group("email");
        if ((passwordConfirmation = matcher.group("passwordConfirmation")) == null)
            passwordConfirmation = matcher.group("passwordConfirmation2");
        if (recoveryMatcher == null) {
            boolean hasSlogan = matcher.group("sloganFlag") != null;
            if (matcher.group("random") != null) {
                password = "random";
                passwordConfirmation = null;
            }
            if (randomPassword != null) {
                password = randomPassword;
                passwordConfirmation = randomConfirmation;
            }
            if (randomSlogan != null)
                slogan = randomSlogan;
            return RegisterAndLoginController.createUser(username,
                    password, passwordConfirmation, nickname, email, slogan, hasSlogan);
        } else {
            String answer, answerConfirmation;
            if ((answer = recoveryMatcher.group("answer")) == null)
                answer = recoveryMatcher.group("answer2");
            if ((answerConfirmation = recoveryMatcher.group("answerConfirm")) == null)
                answerConfirmation = recoveryMatcher.group("answerConfirm2");
            int questionNumber = Integer.parseInt(recoveryMatcher.group("number"));
            if (slogan == null) slogan = randomSlogan;
            if (password == null) password = randomPassword;
            return RegisterAndLoginController.choosePasswordRecoveryQuestion(username,
                    password, nickname, email, slogan, questionNumber, answer, answerConfirmation);
        }
    }
}
