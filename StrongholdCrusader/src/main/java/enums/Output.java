package enums;

public enum Output {
    EMPTY_FIELD("warning : empty field!"),
    INVALID_USERNAME("warning : invalid username!"),
    DUPLICATE_USERNAME("warning : duplicate username!"),
    SUCCESSFUL_USERNAME_CHANGE("username changed successfully"),
    SET_TEXTURE("texture setted successfully for this coordinates"),
    SET_TEXTURE_RECTANGLE("texture setted successfully for these coordinates"),
    WRONG_COORDINATES("warning : incorrect coordinates!"),
    BUILDING_IN_THIS_AREA("warning : you can not change the texture of this area because of existed buildings!"),
    BLOCK_CLEARED("block cleared successfully"),
    DROP_ROCK("rock dropped successfully"),
    WRONG_TREE_TYPE("warning : wrong tree type!"),
    DROP_TREE("tree dropped successfully"),
    INAPPROPRIATE_GROUND_FOR_TREE("warning : the soil is not suitable for dropping the tree");
    private String string;
    private Output(String string) {
        this.string = string;
    }
    public String getString() {
        return this.string;
    }
}
