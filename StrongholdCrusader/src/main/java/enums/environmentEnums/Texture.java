package enums.environmentEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Texture {
    GROUND("ground", true, "\u001b[48;5;137m"),
    GRAVEL_GROUND("Gravel ground", true, "\u001b[48;5;173m"),
    BOULDER("boulder", true, "\u001b[48;5;94m"),
    ROCK("rock", false, "\u001b[48;2;148;63;6m"),
    IRON("iron", true, "\u001b[48;5;88m"),
    GRASS("grass", true, "\u001b[48;5;40m"),
    MEADOW("meadow", true, "\u001b[48;5;22m"),
    DENSE_GRASSLAND("dense grassland", true, "\u001b[48;5;28m"),
    OIL("oil", true, "\u001b[48;5;17m"),
    PLAIN("plain", true, ""),
    SHALLOW_WATER("shallow water", true, "\u001b[48;5;123m"),
    RIVER("river", false, "\u001b[48;5;45m"),
    SMALL_POND("small pond", false, "\u001b[48;5;189m"),
    BIG_POND("big pond", false, "\u001b[48;5;183m"),
    BEACH("beach", true, "\u001b[48;5;195m"),
    SEA("sea", false, "\u001b[48;5;27m");
    private String texture;
    private boolean passable;
    private String color;

    private Texture(String regex, boolean passable, String color) {
        this.texture = regex;
        this.passable = passable;
        this.color = color;
    }

    public String getTexture() {
        return texture;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static Matcher getMatcher(String input, Texture command) {
        Pattern pattern = Pattern.compile(command.texture);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
    public void setTexture(String texture) {
        this.texture = texture;
    }
}
