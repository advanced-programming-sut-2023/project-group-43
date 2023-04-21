package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    //enter menu commands
    ENTER_CHANGE_ENVIRONMENT_MENU("enter change environment menu"),
    ENTER_STORE_MENU("enter store menu"),
    ENTER_TRADE_MENU("enter trade menu"),
    ENTER_GOVERNANCE_MENU("enter governance menu"),
    //choose and change items inside game
    SELECT_BUILDING("select building -x (?<x>\\d+) -y (?<y>\\d+)"),
    CREATE_UNIT("create unit -t (?<type>\\.+) -c (?<count>\\d+)"),
    REPAIR("repair"),
    //people and units
    SELECT_UNIT("select unit -x (?<x>\\d+) -y (?<y>\\d+)"),
    MOVE_UNIT("move unit to -x (?<x>\\d+) -y (?<y>\\d+)"),
    SET_UNITS_STATE("set -x (?<x>\\d+) -y (?<y>\\d+) -s (?<state>(standing)|(defensive)|(offensive))"),
    DIRECT_ATTACK("attack -e (?<x>\\d+) (?<y>\\d+)"),
    AERIAL_ATTACK("attack -x (?<x>\\d+) -y (?<y>\\d+)"),
    POUR_OIL("pour oil -d (?<direction>(up)|(down)|(left)|(right))"),
    DIG_TUNNEL("dig tunnel -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_EQUIPMENT("build -q (?<equipmentName>\\.+)"),
    DISBAND_UNIT("disband unit"),
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
