package enums;

import enums.menuEnums.RegisterAndLoginCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Validations {
    VALID_USERNAME("[a-zA-Z0-9_]+"),
    GROUP("\\-(?<flag>(\\S+))( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\"))?)");
    private final String regex;
    private Validations(String regex) {
        this.regex = regex;
    }
    public static boolean check (String string, Validations term) {
        return Pattern.compile(term.regex).matcher(string).matches();
    }
    public static String getInfo(String flag, String input) {
        Matcher matcher = RegisterAndLoginCommands.getWholeMatcher(input, RegisterAndLoginCommands.GROUP);
        String info = null;
        while (matcher.find()) {
            if (flag.equals(matcher.group(flag))) {
                if (info != null) return null;
                if ((info = matcher.group("group")) == null)
                    info = matcher.group("group2");
            }
        }
        return info;
    }
}
