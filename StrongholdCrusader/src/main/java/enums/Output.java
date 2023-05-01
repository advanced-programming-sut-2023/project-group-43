package enums;

public enum Output {
    CAPTCHA_MATCHED("captcha matched"),
    CAPTCHA_NOT_MATCHED("warning : captcha not matched!"),
    NO_BUILDING("warning : no building in this coordination!"),
    EMPTY_FIELD("warning : empty field!"),
    INVALID_USERNAME("warning : invalid username!"),
    DUPLICATE_USERNAME("warning : duplicated username!"),
    SUCCESSFUL_USERNAME_CHANGE("username changed successfully"),
    SUCCESSFUL_PASSWORD_CHANGE("password changed successfully"),
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
    SUCCESSFUL_PASSWORD_RECOVERY_QUESTION("user sign up successfully!"),
    INCORRECT_ANSWER_CONFIRMATION("warning: answer and answer confirmation aren't matched")
    ,
    DUPLICATE_NICKNAME("warning : duplicate nickname!"),
    SUCCESSFUL_EMAIL_CHANGE("email changed successfully"),
    SUCCESSFUL_NICKNAME_CHANGE("nickname changed successfully"),
    SET_TEXTURE("texture setted successfully for this coordinates"),
    SET_TEXTURE_RECTANGLE("texture setted successfully for these coordinates"),
    WRONG_COORDINATES("warning : incorrect coordinates!"),
    BUILDING_IN_THIS_AREA("warning : you can not change the texture of this area because of existed buildings!"),
    BLOCK_CLEARED("block cleared successfully"),
    DROP_ROCK("rock dropped successfully"),
    WRONG_TREE_TYPE("warning : wrong tree type!"),
    DROP_TREE("tree dropped successfully"),
    INAPPROPRIATE_GROUND_FOR_TREE("warning : the soil is not suitable for dropping the tree"),
    NONEXISTENT_USERNAME("warning: this username does not exist"),
    INCORRECT_PASSWORD("Username and password didn't match!"),
    SUCCESSFUL_LOGIN("user logged in successfully!"),
    CORRECT_PASSWORD_RECOVERY_ANSWER("your answer is correct, choose a new password:"),
    WRONG_PASSWORD_RECOVERY_ANSWER("your answer is incorrect"),
    //
    SELECT_BUILDING("The building selected successfully "),
    CREATE_UNIT("The unit successfully created"),
    REPAIR("repaired successfully!"),
    SELECT_UNIT("The unit successfully selected"),
    MOVE_UNIT("unit successfully moved to"),
    SET_UNIT_STATE("unit state was successfully set to"),
    DIRECT_ATTACK("direct attack command was successfully!"),
    AERIAL_ATTACK("aerial attack command was successfully!"),
    POUR_OIL("The oil successfully poured"),
    DIG_TUNNEL("The tunnel successfully digged"),
    BUILD_EQUIPMENT("The equipment successfully built"),
    DISBAND_UNIT("The unit disbanded"),
    SLOGAN_REMOVED_SUCCESSFULLY("slogan removed successfully!"),
    SUCCESSFUL_PASSWORD_CHANGEING("password changed successfully!"),
    DUPLICATED_NEWPASSWORD("warning : you entered your previous password,enter a new password!"),
    SUCCESSFUL_SLOGAN_CHANGE("slogan changed successfully!"),
    DUPLICATE_SLOGAN("warning : duplicate slogan!"),
    BUILDING_DROPPED_SUCCESSFULLY("building dropped successfully!"),
    WRONG_COUNT("warning : invalid number!"),
    UNIT_DROPPED_SUCCESSFULLY("unit droped successfully!"),
    SUCCESSFUL_MAP_GENERATION("you generate the map successfully!"),
    //Governance outputs
    SUCCESSFUL_FOOD_RATE_CHANGE("food rate changed successfully!"),
    SUCCESSFUL_TAX_RATE_CHANGE("tax rate changed successfully!"),
    SUCCESSFUL_FEAR_RATE_CHANGE("fear rate changed successfully!"),
    //Store outputs
    ITEM_NOR_FOUND("Item not found!"),
    NOT_ENOUGH_QUANTITY("Insufficient quantity!"),
    NOT_ENOUGH_MONEY("Not enough money!"),
    SUCCESSFUL_PURCHASE("The purchase was successful!"),
    SUCCESSFUL_SALE("The sale was successful!"),
    //Game outputs
    WRONG_UNIT_NAME("wrong unit name"),
    SUCCESSFUL_UNIT_CREATION("Unit created successfully!"),
    NOT_ENOUGH_POPULATION("Not enough population!"),



    //trade controller
    TRADE_ADDED("your trade request has added"),
    INCORRECT_ID("the trade doesn't exist"),
    TRADE_ACCEPTED("trade accepted successfully"),
    NOT_ENOUGH_RESOURCE("you don't have enough resource to send others"),
    NOT_ENOUGH_GOLD("the sender doesnt have enough gold")
    //end of trade controller
    ;
    private String string;

    private Output(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
