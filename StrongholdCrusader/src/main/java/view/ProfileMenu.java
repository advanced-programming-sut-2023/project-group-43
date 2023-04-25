package view;

import controller.ProfileController;
import enums.Output;
import enums.Validations;
import enums.menuEnums.ProfileMenuCommands;
import enums.menuEnums.RegisterAndLoginCommands;

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
        System.out.println("profile menu:");
        while (true) {
            input = scanner.nextLine();
            output = null;
            if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_INFO)) != null) {
                output = changeInfo(matcher);
            }
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_PASSWORD)) != null) {
                output = changePassword(matcher);
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.REMOVE_SLOGAN) != null) {
                output = removeSlogan();
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_HIGHSCORE) != null) {
                System.out.println(displayHighScore());
                continue;
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_RANK) != null) {
                System.out.println(displayRank());
                continue;
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_SLOGAN) != null) {
                System.out.println(displaySlogan());
                continue;
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY) != null) {
                System.out.println(displayProfile());
            }
            else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.BACK) != null) {
                System.out.println("main menu:");
                return;
            }
            if (output != null) System.out.println(output.getString());
            else System.out.println("Invalid Command!");
        }
    }

    private Output changePassword(Matcher matcher){
        String oldPassword = Validations.getInfo("o", matcher.group());
        String newPassword = Validations.getInfo("n", matcher.group());
        if (oldPassword == null || newPassword == null) return null;
        return profileController.changePassword(oldPassword, newPassword);
    }

    private Output changeInfo(Matcher matcher) {
        String info = null, flag = null;
        if ((info = matcher.group("info")) == null)
            info = matcher.group("info");
        return profileController.changeInfo(flag, info);
    }
    private Output removeSlogan(){

        return profileController.removeSlogan();
    }
    private int displayHighScore() {
        return profileController.displayHighscore();
    }
    private int displayRank() {
        return profileController.displayRank();
    }

    private String displayProfile(){
        return profileController.displayAllProfile();
    }

    private String displaySlogan() {
        return profileController.displaySlogan();
    }
}
