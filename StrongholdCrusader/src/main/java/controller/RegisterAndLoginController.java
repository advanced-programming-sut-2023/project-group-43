package controller;

import enums.Output;
import model.DataBase;

public class RegisterAndLoginController {

    private DataBase dataBase;

    public RegisterAndLoginController(DataBase dataBase) {
        this.dataBase = dataBase;
    }
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

}

