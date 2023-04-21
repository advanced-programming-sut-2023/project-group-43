package controller;

import enums.Output;
import model.DataBase;

import model.User;

public class ProfileController {

    User currentUser;

    public ProfileController(User currentUser) {
        this.currentUser = currentUser;
    }
    public Output changePassword(String oldPassword, String newPassword) {
        return null;
    }

    public Output changeUsername(String Username) {return null; }
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

}
