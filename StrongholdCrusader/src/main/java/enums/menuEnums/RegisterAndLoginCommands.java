package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

<<<<<<< Updated upstream:StrongholdCrusader/src/main/java/enums/Commands.java
public enum Commands {
    CREATE_USER("user create -u (?<username>.+) -p (?<password>.+)" +
            " (?<password confirmation>.+) -email (?<email>.+) -n (?<nickname>.+) (?<slogan>.+)"),
    LOGIN_USER("user login -u (?<username>.+) (?<password>.+)"),
    FORGET_PASSWORD("forgot my password - u (?<username>.+)")
=======
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
>>>>>>> Stashed changes:StrongholdCrusader/src/main/java/enums/menuEnums/RegisterAndLoginCommands.java
    ;
    private final String regex;

    private Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
