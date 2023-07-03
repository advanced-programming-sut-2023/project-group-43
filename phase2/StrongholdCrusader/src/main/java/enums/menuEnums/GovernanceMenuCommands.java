package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GovernanceMenuCommands {
    FEAR_RATE_SHOW("show fear rate"),
    SHOW_POPULARITY_FACTORS("show popularity factors"),
    SHOW_POPULARITY("show popularity"),
    SHOW_FOOD_LIST("show food list"),
    FOOD_RATE("food rate -r (?<rate>(\\d+)|(\"\\s*\\d+\\s*\"))"),
    FOOD_RATE_SHOW("show food rate"),
    TAX_RATE("tax rate -r (?<rate>(\\d+)|(\"\\s*\\d+\\s*\"))"),
    TAX_RATE_SHOW("tax rate show"),
    FEAR_RATE("fear rate -r (?<rate>(\\d+)|(\"\\s*\\d+\\s*\"))"),
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
