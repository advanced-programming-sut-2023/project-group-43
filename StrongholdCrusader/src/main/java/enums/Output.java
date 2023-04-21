package enums;

public enum Output {
<<<<<<< Updated upstream
=======
    EMPTY_FIELD("warning : empty field!"),
    INVALID_USERNAME("warning : invalid username!"),
    DUPLICATE_USERNAME("warning : duplicated username!"),
    SUCCESSFUL_USERNAME_CHANGE("username changed successfully"),
    SHORT_PASSWORD("warning : password must have at least 6 characters!"),
    WITHOUT_CAPITAL_CASE_LETTER("warning: password must have at least one capital letter"),
    WITHOUT_LOWER_CASE_LETTER("warning: password must have at least one lower letter"),
    WITHOUT_NUMBER("warning: password must have at least one number"),
    WITHOUT_SPECIAL_CHARACTER("warning: password must have at least one special character"),
    INCORRECT_PASSWORD_CONFIRMATION("warning: password and password confirmation aren't matched"),
    DUPLICATE_EMAIL("warning: duplicated email"),
    INVALID_EMAIL_FORMAT("warning: email format is invalid"),
    CONFIRM_PASSWORD("please re-enter your password here:"),
    RANDOM_SLOGAN("your slogan is: "),
    CHOOSE_PASSWORD_RECOVERY_QUESTION("Pick your security question: 1. What is my father’s name? 2. What" +
            "was my first pet’s name? 3. What is my mother’s last name?"),
    INVALID_PASSWORD_RECOVERY_QUESTION("warning: you have to choose a number between 1 to 3"),
    SUCCESSFUL_PASSWORD_RECOVERY_QUESTION("your password recovery question have chosen successfully"),
    INCORRECT_ANSWER_CONFIRMATION("warning: answer and answer confirmation aren't matched")
>>>>>>> Stashed changes
    ;
    private String output;

    public void setOutput(String output) {
        this.output = output;
    }
}
