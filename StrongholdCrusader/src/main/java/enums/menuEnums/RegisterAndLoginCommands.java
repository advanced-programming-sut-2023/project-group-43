package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterAndLoginCommands {
    CREATE_USER("user create -u (?<username>.+) -p (?<password>.+)" +
            " (?<password confirmation>.+) -email (?<email>.+) -n (?<nickname>.+) (?<slogan>.+)"),
    LOGIN_USER("user login -u (?<username>.+) (?<password>.+)"),
    FORGET_PASSWORD("forgot my password - u (?<username>.+)"),
    ;
    private final String regex;

    private RegisterAndLoginCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, RegisterAndLoginCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
