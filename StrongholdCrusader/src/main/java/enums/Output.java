package enums;

public enum Output {
    EMPTY_FIELD("warning : empty field!"),
    INVALID_USERNAME("warning : invalid username!"),
    DUPLICATE_USERNAME("warning : duplicate username!"),
    DUPLICATE_SLOGAN("warning : duplicate slogan!"),
    DUPLICATE_NICKNAME("warning : duplicate nickname!"),
    DUPLICATE_EMAIL("warning : duplicate email!"),
    SUCCESSFUL_EMAIL_CHANGE("email changed successfully"),
    SUCCESSFUL_SLOGAN_CHANGE("slogan changed successfully"),
    SUCCESSFUL_USERNAME_CHANGE("username changed successfully"),
    SUCCESSFUL_NICKNAME_CHANGE("nickname changed successfully"),
    SLOGAN_REMOVED_SUCCESSFULLY("slogan removed successfully"),
    SUCCESSFUL_PASSWORD_CHANGEING("password changed successfully"),
    DUPLICATED_NEWPASSWORD("warning : you entered your previous password,enter a new password!"),
    SET_TEXTURE("texture setted successfully for this coordinates"),
    SET_TEXTURE_RECTANGLE("texture setted successfully for these coordinates"),
    WRONG_COORDINATES("warning : incorrect coordinates!"),
    BUILDING_IN_THIS_AREA("warning : you can not change the texture of this area because of existed buildings!"),
    BLOCK_CLEARED("block cleared successfully"),
    DROP_ROCK("rock dropped successfully"),
    WRONG_TREE_TYPE("warning : wrong tree type!"),
    DROP_TREE("tree dropped successfully"),
    INAPPROPRIATE_GROUND_FOR_TREE("warning : the soil is not suitable for dropping the tree"),
    SHORT_PASSWORD("warning : password must have at least 6 characters!"),
    WITHOUT_CAPITAL_CASE_LETTER("warning: password must have at least one capital letter"),
    WITHOUT_LOWER_CASE_LETTER("warning: password must have at least one lower letter"),
    WITHOUT_NUMBER("warning: password must have at least one number"),
    WITHOUT_SPECIAL_CHARACTER("warning: password must have at least one special character"),
    INCORRECT_PASSWORD_CONFIRMATION("warning: password and password confirmation aren't matched"),
    INVALID_EMAIL_FORMAT("warning: email format is invalid"),
    CONFIRM_PASSWORD("please re-enter your password here:"),
    RANDOM_SLOGAN("your slogan is: "),
    CHOOSE_PASSWORD_RECOVERY_QUESTION("Pick your security question: 1. What is my father’s name? 2. What" +
            "was my first pet’s name? 3. What is my mother’s last name?"),
    INVALID_PASSWORD_RECOVERY_QUESTION("warning: you have to choose a number between 1 to 3"),
    SUCCESSFUL_PASSWORD_RECOVERY_QUESTION("your password recovery question have chosen successfully"),
    INCORRECT_ANSWER_CONFIRMATION("warning: answer and answer confirmation aren't matched");
    private String string;
    private Output(String string) {
        this.string = string;
    }
    public String getString() {
        return this.string;
    }
}
