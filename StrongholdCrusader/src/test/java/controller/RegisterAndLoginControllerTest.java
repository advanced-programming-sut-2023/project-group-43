package controller;

import enums.Output;
import junit.framework.TestCase;
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
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.DUPLICATE_USERNAME, controller.createUser("aida", ".", ".", ".", ".", null, false));
    }
    @Test
    public void checkingFifthReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.INCORRECT_PASSWORD_CONFIRMATION, controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!Aa1!", ".", ".", null, false));
    }
    @Test
    public void checkingSixthReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.DUPLICATE_EMAIL, controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", user.getEmail(), null, false));
    }
    @Test
    public void invalidEmailFormat() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.INVALID_EMAIL_FORMAT, controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!1111", ".", "ioiouoi", null, false));
    }
    @Test
    public void randomSlogan(){
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.RANDOM_SLOGAN, controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", "something@something.something", "random", true));
    }
    @Test
    public void randomPassword() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.CONFIRM_PASSWORD, controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "random", null, ".", "something@something.something", null, false));
    }
    @Test
    public void lastReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.CHOOSE_PASSWORD_RECOVERY_QUESTION, controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", "something@something.something", null, false));
    }
    //checkPassword
    @Test
    public void checkShortPassword() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.SHORT_PASSWORD, controller.checkPassword("ababa"));
    }
    @Test
    public void checkPasswordWithoutCapitalCaseLetter() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.WITHOUT_CAPITAL_CASE_LETTER, controller.checkPassword("abababab"));
    }
    @Test
    public void checkPasswordWithoutLowerCaseLetter () {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.WITHOUT_LOWER_CASE_LETTER, controller.checkPassword("ABABABABAB"));
    }
    @Test
    public void passwordWithoutNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.WITHOUT_NUMBER, controller.checkPassword("ABABabab"));
    }
    @Test
    public void passwordWithoutSpecialCharacter() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.WITHOUT_SPECIAL_CHARACTER, controller.checkPassword("Aa1Aa1Aa1"));
    }
    @Test
    public void passwordCorrectAndShouldReturnNull() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertNull(controller.checkPassword("Aa1!Aa1!Aa1!"));
    }
    //loginUser
    @Test
    public void emptyUsernameField() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.EMPTY_FIELD, controller.loginUser(null, "lskfh", false));
    }
    @Test
    public void emptyPasswordField() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.EMPTY_FIELD, controller.loginUser("aida", null, false));
    }
    public void wrongPassword() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.INCORRECT_PASSWORD, controller.loginUser("aida", "wrongPassword", false));
    }
    @Test
    public void successfulLogin() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.SUCCESSFUL_LOGIN, controller.loginUser("aida", user.getPassword(), false));
    }
    @Test
    public void notExistentUsername() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.NONEXISTENT_USERNAME, controller.loginUser("asqarAgaBefarmaAkbarAgaNafarmaSoxraXanomTajeSari", "password", false));
    }
    //forgetPassword
    @Test
    public void userDoesNotExist() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("user does not exist", controller.forgetPassword("asqarAgaBefarmaAkbarAgaNafarmaSoxraXanomTajeSari"));
    }
    //changePassword
    @Test
    public void changePasswordSuccessfully() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.SUCCESSFUL_PASSWORD_CHANGE, controller.changePassword("aida", "Aa1!Bb2!"));
    }
    @Test
    public void invalidPasswordForChangingPassword() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertNotEquals(Output.SUCCESSFUL_PASSWORD_CHANGE, controller.changePassword("aida", "a"));
    }
    //Check captcha
    @Test
    public void captchaEnteredCorrectly() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.CAPTCHA_MATCHED, controller.checkCaptcha("123456", "123456"));
    }
    @Test
    public void wrongCaptchaEntered() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.CAPTCHA_NOT_MATCHED, controller.checkCaptcha("6543", "123456"));
    }
    //choosePasswordRecoveryQuestion
    @Test
    public void passwordRecoveryQuestionIsNull() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.INVALID_PASSWORD_RECOVERY_QUESTION, controller.choosePasswordRecoveryQuestion(4, null, null));
    }
    @Test
    public void incorrectAnswerConfirmation() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.INCORRECT_ANSWER_CONFIRMATION, controller.choosePasswordRecoveryQuestion(2, user.getPasswordRecoveryAnswer(), null));
    }
    @Test
    public void correctPasswordRecoveryQuestion() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.SUCCESSFUL_PASSWORD_RECOVERY_QUESTION, controller.choosePasswordRecoveryQuestion(2, user.getPasswordRecoveryAnswer(), user.getPasswordRecoveryAnswer()));
    }
    //completeRegister
    @Test
    public void completeRegister() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals(Output.SUCCESSFUL_REGISTER, controller.completeRegister(user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getSlogan(), 2, user.getPasswordRecoveryAnswer()));
    }
    //makePasswordRecoveryQuestion
    @Test
    public void invalidQuestionNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertNull(controller.makePasswordRecoveryQuestion(5));
    }
    @Test
    public void firstValidQuestionNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("What is my father’s name?", controller.makePasswordRecoveryQuestion(1));
    }
    @Test
    public void secondValidQuestionNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("What was my first pet’s name?", controller.makePasswordRecoveryQuestion(2));
    }
    @Test
    public void thirdValidQuestionNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("What is my mother’s last name?", controller.makePasswordRecoveryQuestion(3));
    }
}