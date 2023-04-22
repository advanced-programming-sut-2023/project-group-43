package enums.environmentEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TreeType {
    DESERT_SHRUB("desert shrub"),
    CHERRY_PALM("Cherry palm"),
    OLIVE_TREE("olive tree"),
    COCONUT_PALM("coconut palm"),
    DATE("date");
    private String treeType;
    private TreeType(String regex) {
        this.treeType = regex;
    }

    public static Matcher getMatcher(String input, TreeType command) {
        Pattern pattern = Pattern.compile(command.treeType);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }
}
