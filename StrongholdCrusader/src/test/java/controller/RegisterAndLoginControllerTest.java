package controller;

import enums.Output;
import model.DataBase;
import model.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterAndLoginControllerTest {
    //createUser
    @Test
    public void checkingFirstReturnStatement() {
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.createUser(null, null, null, null, null, null, false));
    }
    @Test
    public void checkingSecondReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.EMPTY_FIELD, RegisterAndLoginController.createUser(".", ".", ".", ".", ".", null, true));
    }
    @Test
    public void checkingThirdReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.INVALID_USERNAME, RegisterAndLoginController.createUser(" ", ".", ".", ".", ".", null, true));
    }
    @Test
    void checkingForthReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals(Output.DUPLICATE_USERNAME, controller.createUser("aida", ".", ".", ".", ".", null, true));
    }
    @Test
    void checkingFifthReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning : password must have at least 6 characters!", controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", ".", ".", ".", ".", null, true));
    }
    @Test
    void checkingSixthReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals("warning: duplicated email", controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!", ".", user.getEmail(), null, true));
    }
    @Test
    void checkingSeventhReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals("warning: password and password confirmation aren't matched", controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!1111", ".", user.getEmail(), null, true));
    }
    @Test
    void invalidEmailFormat() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning: email format is invalid", controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!1111", ".", "ioiouoi", null, true));
    }
    @Test
    void randomSlogan(){
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("your slogan is: ", controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "Aa1!Aa1!", "Aa1!Aa1!1111", ".", "ioiouoi", "random", true));
    }
    @Test
    void randomPassword() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("please re-enter your password here:", controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "random", null, ".", "ioiouoi", null, true));
    }
    @Test
    void lastReturnStatement() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("Pick your security question: 1. What is my father’s name? 2. What " +
                "was my first pet’s name? 3. What is my mother’s last name?", controller.createUser("aidaaidaaidaaidaaidaaidaaidaaidaaidaaidaaida", "random", "random", ".", "ioiouoi", null, true));
    }
    //checkPassword
    @Test
    void checkShortPassword() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning : password must have at least 6 characters!", controller.checkPassword("ababa").getString());
        }
    @Test
    void checkPasswordWithoutCapitalCaseLetter() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning: password must have at least one capital letter", controller.checkPassword("abababab").getString());
    }
    @Test
    void checkPasswordWithoutLowerCaseLetter () {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning: password must have at least one lower letter", controller.checkPassword("ABABABABAB").getString());
    }
    @Test
    void passwordWithoutNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning: password must have at least one number", controller.checkPassword("ABABabab").getString());
    }
    @Test
    void passwordWithoutSpecialCharacter() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning: password must have at least one special character", controller.checkPassword("Aa1Aa1Aa1").getString());
    }
    @Test
    void passwordCorrectAndShouldReturnNull() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertNull(controller.checkPassword("Aa1!Aa1!Aa1!"));
    }
    //loginUser
    @Test
    void emptyUsernameField() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning : empty field!", controller.loginUser(null, "lskfh", false));
    }
    @Test
    void emptyPasswordField() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning : empty field!", controller.loginUser("aida", null, false));
    }
    @Test
    void successfulLogin() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals("enter captcha to login", controller.loginUser("aida", user.getPassword(), false));
    }
    @Test
    void notExistentUsername() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("asqarAgaBefarmaAkbarAgaNafarmaSoxraXanomTajeSari");
        assertNull(user);
    }
    //forgetPassword
    @Test
    void userDoesNotExist() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("asqarAgaBefarmaAkbarAgaNafarmaSoxraXanomTajeSari");
        assertNull(user);
    }
    //changePassword
    @Test
    void changePasswordSuccessfully() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals("password changed successfully", controller.changePassword("aida", user.getPassword()));
    }
    @Test
    void invalidPasswordForChangingPassword() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertNotEquals("password changed successfully", controller.changePassword("aida", "a"));
    }
    //Check captcha
    @Test
    void captchaEnteredCorrectly() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("captcha matched", controller.checkCaptcha("123456", "123456").getString());
    }
    @Test
    void wrongCaptchaEntered() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning : captcha not matched!", controller.checkCaptcha("6543", "123456").getString());
    }
    //choosePasswordRecoveryQuestion
    @Test
    void passwordRecoveryQuestionIsNull() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning: you have to choose a number between 1 to 3", controller.choosePasswordRecoveryQuestion(4, null, null).getString());
    }
    @Test
    void incorrectAnswerConfirmation() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals("warning: answer and answer confirmation aren't matched", controller.choosePasswordRecoveryQuestion(2, user.getPasswordRecoveryAnswer(), null).getString());
    }
    @Test
    void correctPasswordRecoveryQuestion() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals("password recovery question has chosen successfully!", controller.choosePasswordRecoveryQuestion(2, user.getPasswordRecoveryAnswer(), user.getPasswordRecoveryAnswer()).getString());
    }
    //completeRegister
    @Test
    void completeRegister() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        User user = DataBase.getInstance().getUserByUsername("aida");
        assertEquals("user sign up successfully!", controller.completeRegister(user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getSlogan(), 2, user.getPasswordRecoveryAnswer()));
    }
    //makePasswordRecoveryQuestion
    @Test
    void invalidQuestionNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertNull(controller.makePasswordRecoveryQuestion(5));
    }
    @Test
    void firstValidQuestionNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("What is my father’s name?", controller.makePasswordRecoveryQuestion(1));
    }
    @Test
    void secondValidQuestionNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("What was my first pet’s name?", controller.makePasswordRecoveryQuestion(2));
    }
    @Test
    void thirdValidQuestionNumber() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("What is my mother’s last name?", controller.makePasswordRecoveryQuestion(3));
    }
}