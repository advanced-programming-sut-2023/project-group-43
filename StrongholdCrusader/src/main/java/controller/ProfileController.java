package controller;

import enums.Output;
<<<<<<< Updated upstream
import model.DataBase;

public class ProfileController {

    public static Output changeProfile(String flag, String newInfo) {
    }

    public static Output changePassword(String oldPassword, String newPassword) {
=======
import enums.Validations;
import model.User;

public class ProfileController {

    User currentUser;

    public ProfileController(User currentUser) {
        this.currentUser = currentUser;
    }
    public Output changePassword(String oldPassword, String newPassword) {
        return null;
>>>>>>> Stashed changes
    }

    private static Output changeUsername(String username) {
    }

<<<<<<< Updated upstream
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
=======
    public Output changeNickname(String nickname) {
        return null;
    }

    public Output changeEmail(String email) {
        return null;
    }

    public Output changeSlogan(String slogan) {
        return null;
    }

    public Output displayHighScore() {
        return null;
    }

    public Output displayRank() {
        return null;
    }

    public Output displaySlogan() {
        return null;
    }

    public Output removeSlogan() {
        return null;
    }


    public Output displayAllProfile() {
        return null;
    }

>>>>>>> Stashed changes
}
