package controller;

import enums.Output;
import enums.Validations;
import model.*;

public class ProfileController {

    User currentUser;

    public ProfileController(User currentUser) {
        this.currentUser = currentUser;
    }
    public static Output checkPassword(String password) {
        if (password.matches(".{1,5}")) {
            return Output.SHORT_PASSWORD;
        } else if (password.matches("[^A-Z]+")) {
            return Output.WITHOUT_CAPITAL_CASE_LETTER;
        } else if (password.matches("[^a-z]+")) {
            return Output.WITHOUT_LOWER_CASE_LETTER;
        } else if (password.matches("[^\\d]+")) {
            return Output.WITHOUT_NUMBER;
        } else if (password.matches("\\w+")) {
            return Output.WITHOUT_SPECIAL_CHARACTER;
        }
        return null;
    }
    public Output changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) return Output.DUPLICATED_NEWPASSWORD;
        Output output = checkPassword(newPassword);
        if (output.equals(null)) {
            currentUser.setPassword(newPassword);
            return Output.SUCCESSFUL_PASSWORD_CHANGEING;
        }
        else return output;
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
        if (nickname.equals(null)) return Output.EMPTY_FIELD;
        if (nickname.equals(currentUser.getNickname())) return Output.DUPLICATE_NICKNAME;
        currentUser.setNickname(nickname);
        return Output.SUCCESSFUL_NICKNAME_CHANGE;
    }

    public Output changeEmail(String email) {
        if (email.equals(null)) return Output.EMPTY_FIELD;
        if (email.toLowerCase().equals(currentUser.getNickname().toLowerCase())) return Output.DUPLICATE_EMAIL;
        if (!email.matches("[\\w\\._]+\\@[\\w\\._]+\\.[\\w\\._]+")) return Output.INVALID_EMAIL_FORMAT;
        currentUser.setEmail(email);
        return Output.SUCCESSFUL_EMAIL_CHANGE;
    }

    public Output changeSlogan(String slogan) {
        if (slogan == null) return Output.EMPTY_FIELD;
        if (slogan.toLowerCase().equals(currentUser.getSlogan().toLowerCase())) return Output.DUPLICATE_SLOGAN;
        currentUser.setSlogan(slogan);
        return Output.SUCCESSFUL_SLOGAN_CHANGE;
    }
    public int displayHighscore() {
        return currentUser.getScore();
    }
    public int displayRank() {
        return DataBase.getRank(currentUser);
    }
    public String displaySlogan() {
        return currentUser.getSlogan();
    }

    public Output removeSlogan() {
        currentUser.setSlogan(null);
        return Output.SLOGAN_REMOVED_SUCCESSFULLY;
    }
    public String displayAllProfile() {
        StringBuilder userProfile = new StringBuilder();
        userProfile.append("Username : ").append(currentUser.getUsername()).append("\n");
        userProfile.append("Nickname : ").append(currentUser.getNickname()).append("\n");
        userProfile.append("Email : ").append(currentUser.getEmail()).append("\n");
        userProfile.append("Slogan : ").append(currentUser.getSlogan()).append("\n");
        userProfile.append("Score : ").append(currentUser.getScore()).append("\n");
        return userProfile.toString();
    }
}
