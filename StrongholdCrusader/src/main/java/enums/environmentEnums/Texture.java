package enums.environmentEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Texture {
    GROUND("ground"),
    GRAVEL_GROUND("Gravel ground"),
    BOULDER("boulder"),
    ROCK("rock"),
    IRON("iron"),
    GRASS("grass"),
    MEADOW("meadow"),
    DENSE_GRASSLAND("dense grassland"),
    OIL("oil"),
    PLAIN("plain"),
    SHALLOW_WATER("shallow water"),
    RIVER("river"),
    SMALL_POND("small pond"),
    BIG_POND("big pond"),
    BEACH("beach"),
    SEA("sea");
    private String texture;

    private Texture(String regex) {
        this.texture = regex;
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
