package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    //TODO -> how to handle dots inside ""
    //enter menu commands
    ENTER_CHANGE_ENVIRONMENT_MENU("enter change environment menu"),
    ENTER_STORE_MENU("enter store menu"),
    ENTER_TRADE_MENU("enter trade menu"),
    ENTER_GOVERNANCE_MENU("enter governance menu"),
    //choose and change items inside game
    SELECT_BUILDING("select building -x (?<x>(\\d+)|(\"\\s*\\d+\\s*\")) -y (?<y>(\\d+)|(\"\\s*\\d+\\s*\")"),
    CREATE_UNIT("create unit -t (?<type>(\\.+)) -c (?<count>(\\d+)|(\"\\s*\\d+\\s*\")"),
    REPAIR_Castle("repair"),
    //people and units
    SELECT_UNIT("select unit -x (?<x>(\\d+)|(\"\\s*\\d+\\s*\")) -y (?<y>(\\d+)|(\"\\s*\\d+\\s*\"))"),
    MOVE_UNIT("move unit to -x (?<x>(\\d+)|(\"\\s*\\d+\\s*\")) -y (?<y>(\\d+)|(\"\\s*\\d+\\s*\"))"),
    SET_UNITS_STATE("set -x (?<x>(\\d+)|(\"\\s*\\d+\\s*\")) -y (?<y>(\\d+)|(\"\\s*\\d+\\s*\")) -s (?<state>(standing)|(defensive)|(offensive))"),
    ATTACK("attack -(?<item>(x)|(e)) (?<x>(\\d+)|(\"\\s*\\d+\\s*\")) (?<y>(\\d+)|(\"\\s*\\d+\\s*\"))"),
    POUR_OIL("pour oil -d (?<direction>(up)|(down)|(left)|(right))"),
    DIG_TUNNEL("dig tunnel -x (?<x>(\\d+)|(\"\\s*\\d+\\s*\")) -y (?<y>(\\d+)|(\"\\s*\\d+\\s*\"))"),
    BUILD_EQUIPMENT("build -q (?<equipmentName>\\.+)"),
    DISBAND_UNIT("disband unit"),
    PATROL_UNIT("patrol unit -x1 (?<x1>(\\d+)|(\"\\s*\\d+\\s*\")) -y1 (?<y1>(\\d+)|(\"\\s*\\d+\\s*\")) -x2 (?<x2>(\\d+)|(\"\\s*\\d+\\s*\")) -y2 (?<y2>(\\d+)|(\"\\s*\\d+\\s*\"))"),
    ;
    private final String regex;
    private GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
