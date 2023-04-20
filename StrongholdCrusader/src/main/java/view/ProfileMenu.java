package view;

import controller.GameController;
import controller.ProfileController;
import enums.Commands;
import enums.menuEnums.ProfileMenuCommands;
import model.DataBase;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends Menu{

    private ProfileController profileController;

    public ProfileMenu(ProfileController profileController) {
        this.profileController = profileController;
    }
    public void run(Scanner scanner){
        String input;
        while (true) {
            input = scanner.nextLine();
            if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_USERNAME) != null) {
                changingUsername(ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_USERNAME));
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_NICKNAME) != null) {
                System.out.println(changeNickname(ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_NICKNAME)));
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_PASSWORD) != null) {
                System.out.println(changePassword(ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_PASSWORD)));
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_EMAIL) != null) {
                System.out.println(changeEmail(ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_EMAIL)));
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_SLOGAN) != null) {
                System.out.println(changeSlogan(ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_SLOGAN)));
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.REMOVE_SLOGAN) != null) {
                System.out.println(removeSlogan());
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_HIGHSCORE) != null) {
                System.out.println(displayHighscore());
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_RANK) != null) {
                System.out.println(displayRank());
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_SLOGAN) != null) {
                System.out.println(displaySlogan());
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY) != null) {
                System.out.println(displayProfile());
            }
            else System.out.println("Invalid Command!");
        }
    }

    private String changeProfile(Matcher matcher){return null;}

    private String changePassword(Matcher matcher){
        return String.valueOf(profileController.changePassword(matcher.group("oldPassword"), matcher.group("newPassword")));
    }

    private String changingUsername(Matcher matcher) {
        return String.valueOf(profileController.changeUsername(matcher.group("username")));
    }

    private String changeNickname(Matcher matcher) {
        return String.valueOf(profileController.changeNickname(matcher.group("nickname")));
    }

    private String changeEmail(Matcher matcher) {
        return String.valueOf(profileController.changeEmail(matcher.group("email")));
    }

    private String changeSlogan(Matcher matcher) {
        return String.valueOf(profileController.changeSlogan(matcher.group("slogan")));
    }

    private String removeSlogan(){
        return String.valueOf(profileController.removeSlogan());
    }
    private String displayHighscore() {
        return String.valueOf(profileController.displayHighscore());
    }
    private String displayRank() {
        return String.valueOf(profileController.displayRank());
    }

    private String displayProfile(){
        return String.valueOf(profileController.displayAllProfile());
    }

    private String displaySlogan() {
        return String.valueOf(profileController.displaySlogan());
    }
}
