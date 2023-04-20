package enums.menuEnums;

import enums.Commands;
import view.ChangeEnvironmentMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ChangeEnvironmentCommands {
    SHOW_MAP("show map -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)]"),
    SHOW_DETAILS("show details -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)]"),
    MAP_MOVMENTS("map (?<firstDirection>.*) (?P<fitsDisplacement>([0-9])+) (?<secondDirection>.*) (?P<secondDisplacement>([0-9])+)"),
    SET_TEXTURE("settexture -x [(?<x>-?[0-9]+)] -y [(?<y>-?[0-9]+)] -t [(?<type>.*]");
    private final String regex;

    private ChangeEnvironmentCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ChangeEnvironmentCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
