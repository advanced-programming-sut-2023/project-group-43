package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands {
    SEND_TRADE("trade" +
            "( \\-(?<flag>[tamp])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){4}"),
    ACCEPT_TRADE("trade accept" +
            "( \\-(?<flag>[im])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    GROUP("\\-(?<flag>(\\S+))( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\"))?)")
    ;
    private final String regex;

    private TradeMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, TradeMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}