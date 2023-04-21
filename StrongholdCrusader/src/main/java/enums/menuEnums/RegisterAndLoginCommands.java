package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterAndLoginCommands {
    CREATE_USER("user create -u " + "((?<username>\\S+)|(\"(?<username2>.+)\"))?" + " -p " +
            "((((?<password>\\S+)|(\"(?<password2>.+)\")) " +
            "((?<passwordConfirmation>\\S+)|(\"(?<passwordConfirmation2>.+)\")))" +
            "|(?<random>random))?" +
            " -email (?<email>\\S+)?" +
            " -n ((?<nickname>\\S+)|(\"(?<nickname2>.+)\"))?" +
            "(?<sloganFlag> -s (" +
            "(?<slogan>\\S+)|(\"(?<slogan2>.+)\")"+
            ")?)?"),
    LOGIN_USER("user login -u ((((?<username>\\S+)|(\"(?<username2>.+)\"))) ((((?<password>\\S+)|(\"(?<password2>.+)\")))"),
    FORGET_PASSWORD("forgot my password - u ((((?<username>\\S+)|(\"(?<username2>.+)\")))"),
    BACK("back"),
    ENTER_LOGIN_MENU("enter login menu"),
    CHOOSE_PASSWORD_RECOVERY_QUESTION("question pick -q (?<number>\\d+) -a (((?<answer>\\S+)|(\"(?<answer2>.+)\")))" +
            " -c ((?<answerConfirm>\\S+)|(\"(?<answerConfirm2>.+)\"))")
    ;
    private final String regex;

    private RegisterAndLoginCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, RegisterAndLoginCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
