package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    CREATE_USER("user create -u (?<username>.+) -p (?<password>.+)" +
            " (?<password confirmation>.+) -email (?<email>.+) -n (?<nickname>.+) (?<slogan>.+)"),
    LOGIN_USER("user login -u (?<username>.+) (?<password>.+)"),
    FORGET_PASSWORD("forgot my password - u (?<username>.+)")
    ;
    private final String regex;

    private Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
