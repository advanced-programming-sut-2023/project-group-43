package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StoreMenuCommands {
    //TODO -> fix item name and amount regex
    SHOW_PRICE_LIST("show price list"),
    BUY("buy"),
    SELL("sell")
    ;
    final String regex;

    StoreMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, StoreMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
