package controller;

import enums.Output;
import model.DataBase;
import model.User;
import view.MainMenu;
import view.RegisterMenu;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class RegisterAndLoginController {

    public static Output createUser(String username,
                                    String password,
                                    String passwordConfirmation,
                                    String nickname,
                                    String email,
                                    String slogan,
                                    boolean hasSlogan) {
        if (checkNickname(nickname) != null) {
            return Output.EMPTY_FIELD;
        } else if (checkSlogan(slogan, hasSlogan) != null) {
            return checkSlogan(slogan, hasSlogan);
        } else if (checkUsername(username) != null) return checkUsername(username);
        else if (checkPassword(password) != null) {
            return checkPassword(password);
        } else if (checkPasswordConfirmation(passwordConfirmation, password) != null)
            return checkPasswordConfirmation(passwordConfirmation, password);
        else if (checkEmail(email) != null) return checkEmail(email);
        if (slogan != null && slogan.equals("random")) {
            return Output.RANDOM_SLOGAN;
        }
        if (password.equals("random") && passwordConfirmation == null) {
            return Output.CONFIRM_PASSWORD;
        }
        return Output.CHOOSE_PASSWORD_RECOVERY_QUESTION;
    }

    public static Output checkSlogan(String slogan, boolean hasSlogan) {
        if (slogan.isEmpty() && hasSlogan) return Output.EMPTY_FIELD;
        return null;
    }

    public static Output checkPasswordConfirmation(String passwordConfirmation, String password) {
        if (!password.equals(passwordConfirmation)) {
            return Output.INCORRECT_PASSWORD_CONFIRMATION;
        }
        return null;
    }

    public static Output checkUsername(String username) {
        if (username.isEmpty()) return Output.EMPTY_FIELD;
        else if (!username.matches("[\\w_]+"))
            return Output.INVALID_USERNAME;
        else if (DataBase.getInstance().getUserByUsername(username) != null)
            return Output.DUPLICATE_USERNAME;
        return null;
    }

    public static Output checkNickname(String nickname) {
        if (nickname.isEmpty()) return Output.EMPTY_FIELD;
        return null;
    }

    public static Output checkEmail(String email) {
        if (email.isEmpty()) return Output.EMPTY_FIELD;
        if (DataBase.getInstance().getUserByEmail(email) != null) {
            return Output.DUPLICATE_EMAIL;
        } else if (!email.matches("[\\w\\._]+\\@[\\w\\._]+\\.[\\w\\._]+")) {
            return Output.INVALID_EMAIL_FORMAT;
        }
        return null;
    }

    public static String makeShaCode(String password) {
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        final byte[] hashBytes = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashBytes);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static Output checkPassword(String password) {
        if (password.matches(".{1,5}")) {
            return Output.SHORT_PASSWORD;
        } else if (password.matches("[^A-Z]+")) {
            return Output.WITHOUT_CAPITAL_CASE_LETTER;
        } else if (password.matches("[^a-z]+")) {
            return Output.WITHOUT_LOWER_CASE_LETTER;
        } else if (password.matches("[^\\d]+")) {
            return Output.WITHOUT_NUMBER;
        } else if (password.matches("\\w+")) {
            return Output.WITHOUT_SPECIAL_CHARACTER;
        }
        return null;
    }

    public static Output loginUser(String username, String password, boolean isStayLoggedIn) {
        if (username == null || password == null) return Output.EMPTY_FIELD;
        User user = DataBase.getInstance().getUserByUsername(username);
        if (user == null) return Output.NONEXISTENT_USERNAME;
        password = makeShaCode(password);
        if (!password.equals(user.getPassword()))
            return Output.INCORRECT_PASSWORD;
        if (isStayLoggedIn) user.setLoggedIn(true);
        return Output.SUCCESSFUL_LOGIN;
    }

    public static String forgetPassword(String username) {
        User user = DataBase.getInstance().getUserByUsername(username);
        if (user == null) return "user does not exist";
        return user.getPasswordRecoveryQuestion();
    }

    public static Output checkPasswordRecoveryAnswer(String username, String answer) {
        User user = DataBase.getInstance().getUserByUsername(username);
        if (user.getPasswordRecoveryAnswer().equals(answer)) return Output.CORRECT_PASSWORD_RECOVERY_ANSWER;
        return Output.WRONG_PASSWORD_RECOVERY_ANSWER;
    }

    public static Output changePassword(String username, String password) {
        User user = DataBase.getInstance().getUserByUsername(username);
        Output output = checkPassword(password);
        if (output != null) return output;
        password = makeShaCode(password);
        user.setPassword(password);
        return Output.SUCCESSFUL_PASSWORD_CHANGE;
    }

    public static String suggestUsername(String username) {
        Random random = new Random();
        while (DataBase.getInstance().getUserByUsername(username) != null)
            username += (char) (random.nextInt(9) + '0');
        return username;
    }

    public static String makeRandomPassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        String specialCharacters = ".+-)(*&^%$#@!~?";
        for (int i = 0; i < 3; i++) {
            password.append((char) (random.nextInt(25) + 'a'));
            password.append((char) (random.nextInt(25) + 'A'));
            password.append((char) (random.nextInt(9) + '0'));
            password.append(specialCharacters.charAt(random.nextInt(14)));
        }
        return password.toString();
    }

    public static String makeRandomSlogan() {
        String[] slogans = new String[7];
        slogans[0] = "My men approach, you will trouble me no more";
        slogans[1] = "Damn you Boy! I will have revenge!";
        slogans[2] = "I will tear down your castle, stone by stone if I have to! But i will have your head!";
        slogans[3] = "Soon you will see what it means to be Real Warfare!";
        slogans[4] = "Is there no one who will rid me of your irritating presence?!";
        slogans[5] = "Your time on this earth is limited. Time to say your prayers!";
        slogans[6] = "I will kill you soon! You and all your vermin!";
        Random random = new Random();
        return slogans[random.nextInt(7)];
    }
    public static String chooseCaptcha() {
        String[] captcha = {"1181", "1381", "1491", "1722", "1959", "2163", "2177", "2723"};
        Random random = new Random();
        return captcha[random.nextInt(7)];
    }
    public static void enterMainMenu(String username) throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setMainUserController(username);
        (new MainMenu()).start(RegisterMenu.getStage());
    }

    public static Output choosePasswordRecoveryQuestion(int passwordRecoveryQuestionNumber,
                                                        String passwordRecoveryAnswer,
                                                        String answerConfirmation) {
        String passwordRecoveryQuestion = makePasswordRecoveryQuestion(passwordRecoveryQuestionNumber);
        if (passwordRecoveryQuestion == null)
            return Output.INVALID_PASSWORD_RECOVERY_QUESTION;
        if (!passwordRecoveryAnswer.equals(answerConfirmation))
            return Output.INCORRECT_ANSWER_CONFIRMATION;
        return Output.SUCCESSFUL_PASSWORD_RECOVERY_QUESTION;
    }

    public static Output completeRegister(String username,
                                          String password,
                                          String nickname,
                                          String email,
                                          String slogan,
                                          String passwordRecoveryQuestion,
                                          String passwordRecoveryAnswer) {
        String SHA = makeShaCode(password);
        User user = new User(username, SHA, nickname, email, passwordRecoveryQuestion, passwordRecoveryAnswer, slogan);
        DataBase.getInstance().addUser(user);
        return Output.SUCCESSFUL_REGISTER;
    }

    public static String makePasswordRecoveryQuestion(int number) {
        String[] questions = new String[3];
        questions[0] = "What is my father’s name?";
        questions[1] = "What was my first pet’s name?";
        questions[2] = "What is my mother’s last name?";
        if (number > 0 && number < 4)
            return questions[number - 1];
        return null;
    }

    public static boolean isUserExisted(String username) {
        return (DataBase.getInstance().getUserByUsername(username) != null);
    }

    public static String getQuestion(String username) {
        return DataBase.getInstance().getUserByUsername(username).getPasswordRecoveryQuestion();
    }

    public static boolean isAnswerCorrect(String answer, String username) {
        return DataBase.getInstance().getUserByUsername(username).getPasswordRecoveryAnswer().equals(answer);
    }
}

