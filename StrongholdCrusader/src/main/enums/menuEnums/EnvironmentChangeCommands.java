package enums.menuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EnvironmentChangeCommands {
    SHOW_MAP("show map -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)]"),
    SHOW_DETAILS("show details -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)]"),
    MAP_MOVMENTS("map (?<firstDirection>.*) (?P<fitsDisplacement>([0-9])+) (?<secondDirection>.*) (?P<secondDisplacement>([0-9])+)"),
    SET_TEXTURE("settexture -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)] -t [(?<type>.*]"),
    SET_TEXTURE_RECTANGLE("settexture -x1 [(?<x1>-?[0-9]+)] -y1 [(?<y1>-?[0-9]+)] -x2 [(?<x2>-?[0-9]+)] -y2 [(?<y2>-?[0-9]+)] -t [(?<type>.*]"),
    CLEAR("clear -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)]"),
    DROP_ROCK("droprock -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)] -d [(?<direction>.*]"),
    DROP_TREE("droptree -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)] -t [(?<type>.*]"),
    DROP_BUILDING("dropbuilding -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)] -t [(?<type>.*]"),
    DROP_UNIT("dropunit -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)] -t [(?<type>.*] -c [(?<count>.*]");
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
