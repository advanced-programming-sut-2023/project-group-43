package controller;

import enums.Output;
import enums.Validations;
import model.DataBase;
import model.User;

public class ProfileController {

    User currentUser;

    public ProfileController(User currentUser) {
        this.currentUser = currentUser;
    }

    public Output changeInfo(String flag, String info) {
        return switch (flag) {
            case "u" -> changeUsername(info);
            case "n" -> changeNickname(info);
            case "e" -> changeEmail(info);
            case "s" -> changeSlogan(info);
            default -> null;
        };
    }


    public Output changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) return Output.DUPLICATED_NEW_PASSWORD;
        return RegisterAndLoginController.checkPassword(newPassword);
    }

    public Output changeUsername(String username) {
        if (!Validations.check(username, Validations.VALID_USERNAME)) return Output.INVALID_USERNAME;
        if (DataBase.getInstance().getUserByUsername(username) != null) return Output.DUPLICATE_USERNAME;
        currentUser.setUsername(username);
        return Output.SUCCESSFUL_USERNAME_CHANGE;
    }


    public Output changeNickname(String nickname) {
        if (nickname.equals(currentUser.getNickname())) return Output.DUPLICATE_NICKNAME;
        currentUser.setNickname(nickname);
        return Output.SUCCESSFUL_NICKNAME_CHANGE;
    }

    public Output changeEmail(String email) {
        if (email.equalsIgnoreCase(currentUser.getNickname())) return Output.DUPLICATE_EMAIL;
        if (!email.matches("[\\w\\.\\_]+@[\\w\\.\\_]+\\.[\\w\\.\\_]+")) {
            return Output.INVALID_EMAIL_FORMAT;
        }
        currentUser.setEmail(email);
        return Output.SUCCESSFUL_EMAIL_CHANGE;
    }

    public Output changeSlogan(String slogan) {
        if (slogan == null) return Output.EMPTY_FIELD;
        if (slogan.equalsIgnoreCase(currentUser.getSlogan())) return Output.DUPLICATE_SLOGAN;
        currentUser.setSlogan(slogan);
        return Output.SUCCESSFUL_SLOGAN_CHANGE;
    }

    public int displayHighScore() {
        return currentUser.getScore();
    }

    public int displayRank() {
        return DataBase.getInstance().getRank(currentUser);
    }

    public String displaySlogan() {
        return currentUser.getSlogan();
    }

    public Output removeSlogan() {
        currentUser.setSlogan(null);
        return Output.SLOGAN_REMOVED_SUCCESSFULLY;
    }

    public String displayAllProfile() {
        return "Username : " + currentUser.getUsername() + "\n" +
                "Nickname : " + currentUser.getNickname() + "\n" +
                "Email : " + currentUser.getEmail() + "\n" +
                "Slogan : " + currentUser.getSlogan() + "\n" +
                "Score : " + currentUser.getScore() + "\n";
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
