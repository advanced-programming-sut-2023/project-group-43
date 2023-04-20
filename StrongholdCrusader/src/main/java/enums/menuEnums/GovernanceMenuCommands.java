package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GovernanceMenuCommands {
    ;
    private final String regex;

    private GovernanceMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GovernanceMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
