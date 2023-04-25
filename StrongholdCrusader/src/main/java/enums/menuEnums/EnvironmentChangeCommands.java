package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EnvironmentChangeCommands {
    SHOW_MAP("show map" +
            "((( -(?<flag>(x|y))( ((?<group>\\S+)|(\"(?<group2[^\"].+)\")))){2}"),
    SHOW_DETAILS("show details" +
            "((( -(?<flag>(x|y))( ((?<group>\\S+)|(\"(?<group2[^\"].+)\")))){2}"),
    MAP_MOVMENTS("map (?<firstDirection>.*) (?P<fitsDisplacement>([0-9])+) (?<secondDirection>.*) (?P<secondDisplacement>([0-9])+)"),
    SET_TEXTURE("settexture " +
            "((( -(?<flag>(x|y|t))( ((?<group>\\S+)|(\"(?<group2[^\"].+)\")))){3}"),
    SET_TEXTURE_RECTANGLE("settexture" +
            "((( -(?<flag>(x1|y1|x2|y2|t))( ((?<group>\\S+)|(\"(?<group2[^\"].+)\")))){5}"),
    CLEAR("clear" +
            "((( -(?<flag>(x|y))( ((?<group>\\S+)|(\"(?<group2[^\"].+)\")))){2}"),
    DROP_ROCK("droprock" +
            "((( -(?<flag>(x|y|d))( ((?<group>\\S+)|(\"(?<group2[^\"].+)\")))){3}"),
    DROP_TREE("droptree" +
            "((( -(?<flag>(x|y|t))( ((?<group>\\S+)|(\"(?<group2[^\"].+)\")))){3}"),
    DROP_BUILDING("dropbuilding" +
            "((( -(?<flag>(x|y|t))( ((?<group>\\S+)|(\"(?<group2[^\"].+)\")))){3}"),
    DROP_UNIT("dropunit" +
            "((( -(?<flag>(x|y|t|c))( ((?<group>\\S+)|(\"(?<group2[^\"].+)\")))){4}");
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
