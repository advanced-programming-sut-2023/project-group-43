package enums;

public enum Output {
    EMPTY_FIELD("warning : empty field!"),
    INVALID_USERNAME("warning : invalid username!"),
    DUPLICATE_USERNAME("warning : duplicate username!"),
    SUCCESSFUL_USERNAME_CHANGE("username changed successfully")
    ;
    private String string;
    private Output(String string) {
        this.string = string;
    }
    public String getString() {
        return this.string;
    }
}
