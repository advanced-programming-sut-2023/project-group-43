package controller;

import enums.Output;
import model.*;

public class ProfileController {

    User currentUser;

    public ProfileController(User currentUser) {
        this.currentUser = currentUser;
    }
    public Output changeProfile(String flag, String newInfo) {
    }

    public Output changePassword(String oldPassword, String newPassword) {
    }

    private Output changeUsername(String username) {
    }

    private Output changeNickname(String nickname) {
    }

    private Output changeEmail(String email) {
    }

    private Output changeSlogan(String slogan) {
    }

    public Output removeSlogan() {
    }

    public Output displayProfile(String info) {
    }

    public Output displayAllProfile() {}

    private Output displayHighScore() {}

    private Output displayRank() {}

    private Output displaySlogan() {}
}
