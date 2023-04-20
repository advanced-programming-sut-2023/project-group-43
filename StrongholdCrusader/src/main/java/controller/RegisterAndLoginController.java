package controller;

import enums.Output;
import model.*;
import view.MainMenu;

public class RegisterAndLoginController {

    public static Output createUser(String username,
                                    String password,
                                    String nickname,
                                    String email,
                                    String slogan,
                                    String passwordRecoveryQuestion,
                                    String passwordRecoveryAnswer) {
        return null;
    }

    public static Output checkPassword(String password) {
        return null;
    }

    public static Output loginUser(String username, String password) {
        return null;
    }

    public static Output forgetPassword() {
        return null;
    }

    private static String makeRandomPassword() {
        return null;
    }

    private static String makeRandomSlogan() {
        return null;
    }

    private static String makeCaptcha() {return null;}

    public static void enterMainMenu(String username) {
        User currentUser = DataBase.getInstance().getUserByUsername(username);
        MainController mainController = new MainController(currentUser);
        MainMenu mainMenu = new MainMenu(mainController);
        mainMenu.run();
    }

}

