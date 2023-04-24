package enums;

import java.util.regex.Pattern;

public enum Validations {
    VALID_USERNAME("[a-zA-Z0-9_]+");
    private final String regex;
    private Validations(String regex) {
        this.regex = regex;
    }
    public static boolean check (String string, Validations term) {
        return Pattern.compile(term.regex).matcher(string).matches();
    }
}
