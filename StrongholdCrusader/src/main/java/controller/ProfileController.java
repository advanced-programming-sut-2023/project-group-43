package controller;

import enums.Output;
import model.DataBase;

public class ProfileController {

    private DataBase dataBase;

    public ProfileController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public static Output changeProfile(String flag, String newInfo) {
    }

    public static Output changePassword(String oldPassword, String newPassword) {
    }

    private static Output changeUsername(String username) {
    }

    private static Output changeNickname(String nickname) {
    }

    private static Output changeEmail(String email) {
    }

    private static Output changeSlogan(String slogan) {
    }

    public static Output removeSlogan() {
    }

    public static Output displayProfile(String info) {
    }

    public static Output displayAllProfile() {}

    private static Output displayHighScore() {}

    private static Output displayRank() {}

    private static Output displaySlogan() {}
}
