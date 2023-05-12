package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    ENTER_CHANGE_ENVIRONMENT_MENU("enter change environment menu"),
    ENTER_PROFILE_MENU("enter profile menu");
    private final String regex;

    private MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
