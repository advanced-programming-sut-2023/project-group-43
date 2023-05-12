package controller;

import enums.Output;
import model.DataBase;
import model.User;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RegisterAndLoginControllerTest {
    //createUser
    @Test
    public void checkingFirstReturnStatement() {
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.createUser(null, null, null, null, null, null, false));
    }
    @Test
    public void checkingSecondReturnStatement() {
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.createUser(".", ".", ".", ".", ".", null, true));
    }
    @Test
    public void checkingThirdReturnStatement() {
        assertEquals(Output.INVALID_USERNAME, RegisterAndLoginController.createUser(".", ".", ".", ".", ".", null, false));
    }
    @Test
    public void checkingForthReturnStatement() {
        assertEquals(Output.DUPLICATE_USERNAME, RegisterAndLoginController.createUser("aida", ".", ".", ".", ".", null, false));
    }
    @Test
    public void checkingFifthReturnStatement() {
        assertEquals(Output.INCORRECT_PASSWORD_CONFIRMATION, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!Aa1!", ".", ".", null, false));
    }
    @Test
    public void checkingSixthReturnStatement() {
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.DUPLICATE_EMAIL, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", user.getEmail(), null, false));
    }
    @Test
    public void invalidEmailFormat() {
        assertEquals(Output.INVALID_EMAIL_FORMAT, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", "ioiouoi", null, false));
    }
    @Test
    public void randomSlogan(){
        assertEquals(Output.RANDOM_SLOGAN, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", "something@something.something", "random", true));
    }
    @Test
    public void randomPassword() {
        assertEquals(Output.CONFIRM_PASSWORD, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "random", null, ".", "something@something.something", null, false));
    }
    @Test
    public void lastReturnStatement() {
        assertEquals(Output.CHOOSE_PASSWORD_RECOVERY_QUESTION, RegisterAndLoginController.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", "something@something.something", null, false));
    }
    //checkPassword
    @Test
    public void checkShortPassword() {
        assertEquals(Output.SHORT_PASSWORD, RegisterAndLoginController.checkPassword("ababa"));
    }
    @Test
    public void checkPasswordWithoutCapitalCaseLetter() {
        assertEquals(Output.WITHOUT_CAPITAL_CASE_LETTER, RegisterAndLoginController.checkPassword("abababab"));
    }
    @Test
    public void checkPasswordWithoutLowerCaseLetter () {
        assertEquals(Output.WITHOUT_LOWER_CASE_LETTER, RegisterAndLoginController.checkPassword("ABABABABAB"));
    }
    @Test
    public void passwordWithoutNumber() {
        assertEquals(Output.WITHOUT_NUMBER, RegisterAndLoginController.checkPassword("ABABabab"));
    }
    @Test
    public void passwordWithoutSpecialCharacter() {
        assertEquals(Output.WITHOUT_SPECIAL_CHARACTER, RegisterAndLoginController.checkPassword("Aa1Aa1Aa1"));
    }
    @Test
    public void passwordCorrectAndShouldReturnNull() {
        assertNull(RegisterAndLoginController.checkPassword("Aa1!Aa1!Aa1!"));
    }
    //loginUser
    @Test
    public void emptyUsernameField() {
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.loginUser(null, "lskfh", false));
    }
    @Test
    public void emptyPasswordField() {
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.loginUser("aida", null, false));
    }
    @Test
    public void notExistentUsername() {
        assertEquals(Output.NONEXISTENT_USERNAME, RegisterAndLoginController.loginUser("asqarAgaBefarmaAkbarAgaNafarmaSoxraXanomTajeSari", "password", false));
    }
    //forgetPassword
    @Test
    public void userDoesNotExist() {
        assertEquals("user does not exist", RegisterAndLoginController.forgetPassword("asqarAgaBefarmaAkbarAgaNafarmaSoxraXanomTajeSari"));
    }
    //changePassword
    @Test
    public void changePasswordSuccessfully() {
        assertEquals(Output.SUCCESSFUL_PASSWORD_CHANGE, RegisterAndLoginController.changePassword("aida", "Aa1!Bb2!"));
    }
    @Test
    public void invalidPasswordForChangingPassword() {
        assertNotEquals(Output.SUCCESSFUL_PASSWORD_CHANGE, RegisterAndLoginController.changePassword("aida", "a"));
    }
    //Check captcha
    @Test
    public void captchaEnteredCorrectly() {
        assertEquals(Output.CAPTCHA_MATCHED, RegisterAndLoginController.checkCaptcha("123456", "123456"));
    }
    @Test
    public void wrongCaptchaEntered() {
        assertEquals(Output.CAPTCHA_NOT_MATCHED, RegisterAndLoginController.checkCaptcha("6543", "123456"));
    }
    //choosePasswordRecoveryQuestion
    @Test
    public void passwordRecoveryQuestionIsNull() {
        assertEquals(Output.INVALID_PASSWORD_RECOVERY_QUESTION, RegisterAndLoginController.choosePasswordRecoveryQuestion(4, null, null));
    }
    @Test
    public void incorrectAnswerConfirmation() {
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.INCORRECT_ANSWER_CONFIRMATION, RegisterAndLoginController.choosePasswordRecoveryQuestion(2, user.getPasswordRecoveryAnswer(), null));
    }
    @Test
    public void correctPasswordRecoveryQuestion() {
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.SUCCESSFUL_PASSWORD_RECOVERY_QUESTION, RegisterAndLoginController.choosePasswordRecoveryQuestion(2, user.getPasswordRecoveryAnswer(), user.getPasswordRecoveryAnswer()));
    }
    //completeRegister
    @Test
    public void completeRegister() {
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.SUCCESSFUL_REGISTER, RegisterAndLoginController.completeRegister(user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getSlogan(), 2, user.getPasswordRecoveryAnswer()));
    }
    //makePasswordRecoveryQuestion
    @Test
    public void invalidQuestionNumber() {
        assertNull(RegisterAndLoginController.makePasswordRecoveryQuestion(5));
    }
    @Test
    public void firstValidQuestionNumber() {
        assertEquals("What is my father’s name?", RegisterAndLoginController.makePasswordRecoveryQuestion(1));
    }
    @Test
    public void secondValidQuestionNumber() {
        assertEquals("What was my first pet’s name?", RegisterAndLoginController.makePasswordRecoveryQuestion(2));
    }
    @Test
    public void thirdValidQuestionNumber() {
        assertEquals("What is my mother’s last name?", RegisterAndLoginController.makePasswordRecoveryQuestion(3));
    }
}