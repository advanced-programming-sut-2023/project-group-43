package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    CHANGE_USERNAME("profile change -u (?<username>.+)"),
    CHANGE_NICKNAME("profile change -n (?<nickname>.+)"),
    CHANGE_PASSWORD("profile change password -o (?<oldPassword>.+) -n (?<newPassword>.+)"),
    CHANGE_EMAIL("profile change -e (?<email>.+)"),
    CHANGE_SLOGAN("profile change slogan -s (?<slogan>.+)"),
    REMOVE_SLOGAN("Profile remove slogan"),
    DISPLAY_HIGHSCORE("profile display highscore"),
    DISPLAY_RANK("profile display rank"),
    DISPLAY_SLOGAN("profile display slogan"),
    PROFILE_DISPLAY("profile display"),
    BACK("back")
    ;
    private final String regex;

    private ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
