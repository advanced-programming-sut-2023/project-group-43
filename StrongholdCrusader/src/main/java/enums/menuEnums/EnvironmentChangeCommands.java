package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EnvironmentChangeCommands {
    SHOW_MAP("show map -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)]"),
    SHOW_DETAILS("show details -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)]"),
    MAP_MOVMENTS("map (?<firstDirection>.*) (?P<fitsDisplacement>([0-9])+) (?<secondDirection>.*) (?P<secondDisplacement>([0-9])+)"),
    SET_TEXTURE("settexture -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)] -t [(?<type>.*]");
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
