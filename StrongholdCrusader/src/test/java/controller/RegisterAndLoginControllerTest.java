package controller;

import enums.Output;
import model.DataBase;
import model.User;

import static org.junit.Assert.*;

public class RegisterAndLoginControllerTest {
    //createUser
    @org.junit.Test
    public void checkingFirstReturnStatement() {
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.createUser(null, null, null, null, null, null, false));
    }

    @org.junit.Test
    public void checkingSecondReturnStatement() {
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.createUser(".", ".", ".", ".", ".", null, true));
    }

    @org.junit.Test
    public void checkingThirdReturnStatement() {
        assertEquals(Output.INVALID_USERNAME, RegisterAndLoginController.createUser(".", ".", ".", ".", ".", null, false));
    }

    @org.junit.Test
    public void checkingForthReturnStatement() {
        assertEquals(Output.DUPLICATE_USERNAME, RegisterAndLoginController.createUser("aida", ".", ".", ".", ".", null, false));
    }

    @org.junit.Test
    public void checkingFifthReturnStatement() {
        assertEquals(Output.INCORRECT_PASSWORD_CONFIRMATION, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!Aa1!", ".", ".", null, false));
    }

    @org.junit.Test
    public void checkingSixthReturnStatement() {
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.DUPLICATE_EMAIL, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", user.getEmail(), null, false));
    }

    @org.junit.Test
    public void invalidEmailFormat() {
        assertEquals(Output.INVALID_EMAIL_FORMAT, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!1111", ".", "ioiouoi", null, false));
    }

    @org.junit.Test
    public void randomSlogan() {
        assertEquals(Output.RANDOM_SLOGAN, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", "something@something.something", "random", true));
    }

    @org.junit.Test
    public void randomPassword() {
        assertEquals(Output.CONFIRM_PASSWORD, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "random", null, ".", "something@something.something", null, false));
    }

    @org.junit.Test
    public void lastReturnStatement() {
        assertEquals(Output.CHOOSE_PASSWORD_RECOVERY_QUESTION, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", "something@something.something", null, false));
    }

    //checkPassword
    @org.junit.Test
    public void checkShortPassword() {
        assertEquals(Output.SHORT_PASSWORD, RegisterAndLoginController.checkPassword("ababa"));
    }

    @org.junit.Test
    public void checkPasswordWithoutCapitalCaseLetter() {
        assertEquals(Output.WITHOUT_CAPITAL_CASE_LETTER, RegisterAndLoginController.checkPassword("abababab"));
    }

    @org.junit.Test
    public void checkPasswordWithoutLowerCaseLetter() {
        assertEquals(Output.WITHOUT_LOWER_CASE_LETTER, RegisterAndLoginController.checkPassword("ABABABABAB"));
    }

    @org.junit.Test
    public void passwordWithoutNumber() {
        assertEquals(Output.WITHOUT_NUMBER, RegisterAndLoginController.checkPassword("ABABabab"));
    }

    @org.junit.Test
    public void passwordWithoutSpecialCharacter() {
        assertEquals(Output.WITHOUT_SPECIAL_CHARACTER, RegisterAndLoginController.checkPassword("Aa1Aa1Aa1"));
    }

    @org.junit.Test
    public void passwordCorrectAndShouldReturnNull() {
        assertNull(RegisterAndLoginController.checkPassword("Aa1!Aa1!Aa1!"));
    }

    //loginUser
    @org.junit.Test
    public void emptyUsernameField() {
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.loginUser(null, "lskfh", false));
    }

    @org.junit.Test
    public void emptyPasswordField() {
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.loginUser("aida", null, false));
    }

    @org.junit.Test
    public void successfulLogin() {
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.SUCCESSFUL_LOGIN, RegisterAndLoginController.loginUser("aida", user.getPassword(), false));
    }

    @org.junit.Test
    public void notExistentUsername() {
        assertEquals(Output.NONEXISTENT_USERNAME, RegisterAndLoginController.loginUser("asqarAgaBefarmaAkbarAgaNafarmaSoxraXanomTajeSari", "password", false));
    }

    //forgetPassword
    @org.junit.Test
    public void userDoesNotExist() {
        assertEquals("user does not exist", RegisterAndLoginController.forgetPassword("asqarAgaBefarmaAkbarAgaNafarmaSoxraXanomTajeSari"));
    }

    //changePassword
    @org.junit.Test
    public void changePasswordSuccessfully() {
        assertEquals(Output.SUCCESSFUL_PASSWORD_CHANGE, RegisterAndLoginController.changePassword("aida", "Aa1!Bb2!"));
    }

    @org.junit.Test
    public void invalidPasswordForChangingPassword() {
        assertNotEquals(Output.SUCCESSFUL_PASSWORD_CHANGE, RegisterAndLoginController.changePassword("aida", "a"));
    }

    //Check captcha
    @org.junit.Test
    public void captchaEnteredCorrectly() {
        assertEquals(Output.CAPTCHA_MATCHED, RegisterAndLoginController.checkCaptcha("123456", "123456"));
    }

    @org.junit.Test
    public void wrongCaptchaEntered() {
        assertEquals(Output.CAPTCHA_NOT_MATCHED, RegisterAndLoginController.checkCaptcha("6543", "123456"));
    }

    //choosePasswordRecoveryQuestion
    @org.junit.Test
    public void passwordRecoveryQuestionIsNull() {
        assertEquals(Output.INVALID_PASSWORD_RECOVERY_QUESTION, RegisterAndLoginController.choosePasswordRecoveryQuestion(4, null, null));
    }

    @org.junit.Test
    public void incorrectAnswerConfirmation() {
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.INCORRECT_ANSWER_CONFIRMATION, RegisterAndLoginController.choosePasswordRecoveryQuestion(2, user.getPasswordRecoveryAnswer(), null));
    }

    @org.junit.Test
    public void correctPasswordRecoveryQuestion() {
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.SUCCESSFUL_PASSWORD_RECOVERY_QUESTION, RegisterAndLoginController.choosePasswordRecoveryQuestion(2, user.getPasswordRecoveryAnswer(), user.getPasswordRecoveryAnswer()));
    }

    //completeRegister
    @org.junit.Test
    public void completeRegister() {
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.SUCCESSFUL_REGISTER, RegisterAndLoginController.completeRegister(user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getSlogan(), 2, user.getPasswordRecoveryAnswer()));
    }

    //makePasswordRecoveryQuestion
    @org.junit.Test
    public void invalidQuestionNumber() {
        assertNull(RegisterAndLoginController.makePasswordRecoveryQuestion(5));
    }

    @org.junit.Test
    public void firstValidQuestionNumber() {
        assertEquals("What is my father’s name?", RegisterAndLoginController.makePasswordRecoveryQuestion(0));
    }

    @org.junit.Test
    public void secondValidQuestionNumber() {
        assertEquals("What was my first pet’s name?", RegisterAndLoginController.makePasswordRecoveryQuestion(1));
    }

    @org.junit.Test
    public void thirdValidQuestionNumber() {
        assertEquals("What is my mother’s last name?", RegisterAndLoginController.makePasswordRecoveryQuestion(2));
    }
}
