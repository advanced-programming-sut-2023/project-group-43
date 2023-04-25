package view;

import controller.RegisterAndLoginController;
import enums.Output;
import enums.Validations;
import enums.menuEnums.RegisterAndLoginCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu extends Menu {

    private String randomPassword;
    private String username, password, passwordConfirmation, email, slogan, nickname;
    boolean hasSlogan;

    public void run() {
        Scanner scanner = Menu.getScanner();
        String input;
        Output output;
        Matcher matcher;
        System.out.println("register menu:");
        while (true) {
            input = scanner.nextLine();
            output = null;
            randomPassword = null;
            if ((matcher = RegisterAndLoginCommands.getMatcher(input, RegisterAndLoginCommands.CREATE_USER)) != null) {
                output = createUser(matcher, null, null, null);
            } else if (RegisterAndLoginCommands.getMatcher(input, RegisterAndLoginCommands.BACK) != null) {
                return;
            } else if (RegisterAndLoginCommands.getMatcher(input, RegisterAndLoginCommands.ENTER_LOGIN_MENU) != null) {
                enterLoginMenu();
                continue;
            }
            if (output != null) {
                output = checkOutput(matcher, output);
                System.out.println(output.getString());
            } else System.out.println("invalid command");
        }
    }

    private Output createUser(Matcher matcher, String randomPassword, String randomConfirmation, String randomSlogan) {
        if (!parseMatcher(matcher, randomSlogan)) {
            return null;
        }
        if (matcher.group("random") != null) {
            password = "random";
        }
        if (randomPassword != null) {
            password = randomPassword;
            passwordConfirmation = randomConfirmation;
        }
        return RegisterAndLoginController.createUser(username,
                password, passwordConfirmation, nickname, email, slogan, hasSlogan);
    }

    private Output createRandomPassword(Matcher matcher, Output output, String randomSlogan) {
        randomPassword = RegisterAndLoginController.makeRandomPassword();
        System.out.println("your random password is: " + randomPassword);
        System.out.println(output.getString());
        String randomPasswordConfirmation = scanner.nextLine();
        return createUser(matcher, randomPassword, randomPasswordConfirmation, randomSlogan);
    }

    private Output checkOutput(Matcher matcher, Output output) {
        String randomSlogan = null;
        if (output.equals(Output.RANDOM_SLOGAN)) {
            System.out.print(output.getString());
            randomSlogan = RegisterAndLoginController.makeRandomSlogan();
            System.out.println(randomSlogan);
            output = createUser(matcher, null, null, randomSlogan);
        }
        if (output.equals(Output.CONFIRM_PASSWORD)) {
            output = createRandomPassword(matcher, output, randomSlogan);
        }
        if (output.equals(Output.CHOOSE_PASSWORD_RECOVERY_QUESTION)) {
            output = choosePasswordRecoveryQuestion(matcher, output, randomSlogan);
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
                parseMatcher(matcher, randomSlogan);
                String answer = Validations.getInfo("a", matcher.group());
                String answerConfirmation = Validations.getInfo("c", matcher.group());
                String number = Validations.getInfo("q", matcher.group());
                if (number == null || answerConfirmation == null || answer == null) return null;
                if(!number.matches("\\d+"))
                    return Output.INVALID_PASSWORD_RECOVERY_QUESTION;
                int questionNumber = Integer.parseInt(recoveryMatcher.group("number"));
                if (randomPassword != null) password = randomPassword;
                if (randomSlogan != null) slogan = randomSlogan;
                output = RegisterAndLoginController.choosePasswordRecoveryQuestion(username,
                        password, nickname, email, slogan, questionNumber, answer, answerConfirmation);
            }
        }
        return output;
    }

    public void enterLoginMenu() {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.run();
    }

    private boolean parseMatcher(Matcher matcher, String randomSlogan) {
        username = null;
        password = null;
        passwordConfirmation = null;
        nickname = null;
        slogan = null;
        email = null;
        hasSlogan = false;
        if ((password = matcher.group("password")) == null) password = matcher.group("password2");
        if ((passwordConfirmation = matcher.group("passwordConfirmation")) == null)
            passwordConfirmation = matcher.group("passwordConfirmation2");
        if (matcher.group("random") != null) password = "random";
        Matcher allMatcher = RegisterAndLoginCommands.getWholeMatcher(matcher.group(), RegisterAndLoginCommands.GROUP);
        while (allMatcher.find()) {
            switch (allMatcher.group("flag")) {
                case "u":
                    if (username != null) return false;
                    if ((username = allMatcher.group("group")) == null) username = allMatcher.group("group2");
                    break;
                case "email":
                    if (email != null) return false;
                    if ((email = allMatcher.group("group")) == null) email = allMatcher.group("group2");
                    break;
                case "n":
                    if (nickname != null) return false;
                    if ((nickname = allMatcher.group("group")) == null) nickname = allMatcher.group("group2");
                    break;
                case "s":
                    if (slogan != null) return false;
                    if ((slogan = allMatcher.group("group")) == null) slogan = allMatcher.group("group2");
                    hasSlogan = true;
            }
        }
        if (randomSlogan != null) slogan = randomSlogan;
        return true;
    }
}
