package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public enum RegisterAndLoginCommands {
    CREATE_USER("user create(?<all>(( -(?<flag>(u|(email)|n|s))( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))?)" +
            "|( -p " +
            "((((?<password>\\S+)|(\"(?<password2>.+)\")) " +
            "((?<passwordConfirmation>\\S+)|(\"(?<passwordConfirmation2>[^\"]+)\")))" +
            "|(?<random>random))?" +
            "))){0,5}"),

    LOGIN_USER("user login((( -(?<flag>(u|p))( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}" +
            "((?<stayLoggedIn> --stay-logged-in)?)"),
    FORGET_PASSWORD("forgot my password - u (((?<username>\\S+)|(\"(?<username2>[^\"]+)\")))"),
    BACK("back"),
    ENTER_LOGIN_MENU("enter login menu"),
    CHOOSE_PASSWORD_RECOVERY_QUESTION("question pick" +
            "((( -(?<flag>(q|a|c))( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){3}"),
    GROUP("\\-(?<flag>(\\S+))( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\"))?)")
    ;
    private final String regex;

    RegisterAndLoginCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, RegisterAndLoginCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
    public static Matcher getWholeMatcher(String input, RegisterAndLoginCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }

}
