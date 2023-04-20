package controller;

import enums.Output;
import enums.Validations;
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

    public Output changeUsername(String username) {
        if (username.equals(null)) return Output.EMPTY_FIELD;
        if (!Validations.check(username, Validations.VALID_USERNAME)) return Output.INVALID_USERNAME;
        if (currentUser.getUsername().equals(username)) return Output.DUPLICATE_USERNAME;
        //TODO
        currentUser.setUsername(username);
        return Output.SUCCESSFUL_USERNAME_CHANGE;
    }

    public Output changeNickname(String nickname) {
    }

    public Output changeEmail(String email) {
    }

    public Output changeSlogan(String slogan) {
    }
    public Output displayHighscore() {
    }
    public Output displayRank() {}
    public Output displaySlogan() {}

    public Output removeSlogan() {
    }

    public Output displayProfile(String info) {
    }

    public Output displayAllProfile() {}

    private Output displayHighScore() {}

    private Output displayRank() {}

    private Output displaySlogan() {}
}
