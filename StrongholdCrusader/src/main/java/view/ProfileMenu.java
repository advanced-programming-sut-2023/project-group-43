package view;

import controller.ProfileController;
import enums.Output;
import enums.menuEnums.ProfileMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends Menu{

    private ProfileController profileController;

    public ProfileMenu(ProfileController profileController) {
        this.profileController = profileController;
    }
    public void run(){
        Scanner scanner = Menu.getScanner();
        String input;
        Output output;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            output = null;
            if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_USERNAME)) != null) {
                output = changeUsername(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_NICKNAME)) != null) {
                output = changeNickname(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_PASSWORD)) != null) {
                output = changePassword(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_EMAIL)) != null) {
                output = changeEmail(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_SLOGAN)) != null) {
                output = changeSlogan(matcher);
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.REMOVE_SLOGAN) != null) {
                output = removeSlogan();
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_HIGHSCORE) != null) {
                output = displayHighScore();
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_RANK) != null) {
                output = displayRank();
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_SLOGAN) != null) {
                output = displaySlogan();
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY) != null) {
                output = displayProfile();
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.BACK) != null) {
                return;
            }
            if (output != null) System.out.println(output.getString());
            else System.out.println("Invalid Command!");
        }
    }

    private Output changePassword(Matcher matcher){
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        return profileController.changePassword(oldPassword, newPassword);
    }

    private Output changeUsername(Matcher matcher) {
        return profileController.changeUsername(matcher.group("username"));
    }

    private Output changeNickname(Matcher matcher) {
        return profileController.changeNickname(matcher.group("nickname"));
    }

    private Output changeEmail(Matcher matcher) {
        return profileController.changeEmail(matcher.group("email"));
    }

    private Output changeSlogan(Matcher matcher) {
        return profileController.changeSlogan(matcher.group("slogan"));
    }

    private Output removeSlogan(){

        return profileController.removeSlogan();
    }
    private Output displayHighScore() {
        return profileController.displayHighScore();
    }
    private Output displayRank() {
        return profileController.displayRank();
    }

    private Output displayProfile(){
        return profileController.displayAllProfile();
    }

    private Output displaySlogan() {
        return profileController.displaySlogan();
    }
}