package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands {
    SEND_TRADE("trade -t (?<resourceType>\\S+) -a (?<amount>\\d+) -p (?<price>\\d+) -m (?<message>.+)"),
    ACCEPT_TRADE("trade accept -i (?<id>\\d+) -m message")
    ;
    private final String regex;

    private TradeMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, TradeMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
