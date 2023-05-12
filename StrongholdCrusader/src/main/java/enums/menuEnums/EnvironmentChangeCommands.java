package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EnvironmentChangeCommands {
    SHOW_MAP("show map" +
            "( \\-(?<flag>[xy])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    SHOW_DETAILS("show details" +
            "( \\-(?<flag>[xy])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    MAP_MOVMENTS("map (?<firstDirection>.*) (?<firstDisplacement>([0-9])+) (?<secondDirection>.*) (?<secondDisplacement>([0-9])+)"),
    SET_TEXTURE("settexture " +
            "( \\-(?<flag>[xyt])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){3}"),
    SET_TEXTURE_RECTANGLE("settexture" +
            "( \\-(?<flag>[(x1)|(y2)|(x2)|(y2)|(t)])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){5}"),
    CLEAR("clear" +
            "( \\-(?<flag>[xy])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){2}"),
    DROP_ROCK("droprock" +
            "( \\-(?<flag>[xyd])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){3}"),
    DROP_TREE("droptree" +
            "( \\-(?<flag>[xyt])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){3}"),
    DROP_BUILDING("dropbuilding" +
            "( \\-(?<flag>[xyt])( ((?<group>\\S+)|(\"(?<group2>[^\"]+)\")))){3}");
    private final String regex;

    private EnvironmentChangeCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, EnvironmentChangeCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
