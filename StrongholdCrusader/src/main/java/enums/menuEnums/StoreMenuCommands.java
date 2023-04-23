package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StoreMenuCommands {

    ;
    final String regex;

    private StoreMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, StoreMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
