package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    //enter menu commands
    ENTER_MAP_MENU("enter map menu"),
    ENTER_TRADE_MENU("enter trade menu"),
    ENTER_GOVERNANCE_MENU("enter governance menu"),
    //choose and change items inside game
    SELECT_BUILDING("select building" +
            "( \\-(?<flag>[xy])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    CREATE_UNIT("create unit" +
            "( \\-(?<flag>[ct])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    REPAIR_CASTLE("repair"),
    //people and units
    SELECT_UNIT("select unit" +
            "( \\-(?<flag>[xyt])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){3}"),
    MOVE_UNIT("move unit to" +
            "( \\-(?<flag>[xy])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    SET_UNITS_STATE("set" +
            "( \\-(?<flag>[xys])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){3}"),
    ATTACK("attack" +
            "( \\-(?<flag>[xy])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    ATTACK_ENEMY("attack -e" +
            "( \\-(?<flag>[xy])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    POUR_OIL("pour oil -d (?<direction>(up)|(down)|(left)|(right))"),
    DIG_TUNNEL("dig tunnel" +
            "( \\-(?<flag>[xy])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    BUILD_EQUIPMENT("build -q (?<equipmentName>\\.+)"),
    DISBAND_UNIT("disband unit"),
    PATROL_UNIT("patrol unit" +
            "( \\-(?<flag>[(x1)|(y1)|(x2)|(y2)|(t)])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){3}"),
    DROP_UNIT("dropunit" +
            "( \\-(?<flag>[xytc])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){4}");
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
