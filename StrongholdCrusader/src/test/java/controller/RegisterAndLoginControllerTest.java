package controller;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterAndLoginControllerTest {
    @Test
    void checkShortPassword() {
        RegisterAndLoginController controller = new RegisterAndLoginController();
        assertEquals("warning : password must have at least 6 characters!", controller.checkPassword("ab").getString());
    }
}