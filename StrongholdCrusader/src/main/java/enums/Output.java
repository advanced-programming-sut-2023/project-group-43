package enums;

public enum Output {
    EMPTY_FIELD("warning : empty field!"),
    INVALID_USERNAME("warning : invalid username!"),
    DUPLICATE_USERNAME("warning : duplicate username!"),
    SUCCESSFUL_USERNAME_CHANGE("username changed successfully")
    ;
    private String output;
    private Output(String output) {
        this.output = output;
    }
    public void setOutput(String output) {
        this.output = output;
    }
}
