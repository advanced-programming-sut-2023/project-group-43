package controller;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RegisterAndLoginControllerTest {
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
}