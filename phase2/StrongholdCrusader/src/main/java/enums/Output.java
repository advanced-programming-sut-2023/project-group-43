package enums;

public enum Output {

    //manu names
    CHANGE_ENVIRONMENT_MENU("change environment menu"),
    GOVERNANCE_MENU("governance menu"),
    LOGIN_MENU("login menu"),
    MAIN_MENU("main menu"),
    MAP_MENU("map menu"),
    PROFILE_MENU("profile menu"),
    REGISTER_MENU("register menu"),
    STORE_MENU("store menu"),
    TRADE_MENU("trade menu"),


    //before game
    CAPTCHA_MATCHED("captcha matched"),
    CAPTCHA_NOT_MATCHED("captcha not matched!"),
    NO_BUILDING("no building in this coordination!"),
    EMPTY_FIELD("empty field!"),
    INVALID_USERNAME("invalid username!"),
    DUPLICATE_USERNAME("duplicated username!"),
    SUCCESSFUL_USERNAME_CHANGE("username changed successfully"),
    SUCCESSFUL_PASSWORD_CHANGE("password changed successfully"),
    SHORT_PASSWORD("password must have at least 6 characters!"),
    WITHOUT_CAPITAL_CASE_LETTER("password must have at least one capital letter"),
    WITHOUT_LOWER_CASE_LETTER("password must have at least one lower letter"),
    WITHOUT_NUMBER("password must have at least one number"),
    WITHOUT_SPECIAL_CHARACTER("password must have at least one special character"),
    INCORRECT_PASSWORD_CONFIRMATION("password and password confirmation aren't matched"),
    DUPLICATE_EMAIL("duplicated email"),
    INVALID_EMAIL_FORMAT("email format is invalid"),
    CONFIRM_PASSWORD("please re-enter your password here:"),
    RANDOM_SLOGAN("your slogan is: "),
    CHOOSE_PASSWORD_RECOVERY_QUESTION("Pick your security question: 1. What is my father’s name? 2. What " +
            "was my first pet’s name? 3. What is my mother’s last name?"),
    INVALID_PASSWORD_RECOVERY_QUESTION("warning: you have to choose a number between 1 to 3"),
    SUCCESSFUL_PASSWORD_RECOVERY_QUESTION("password recovery question has chosen successfully!"),
    SUCCESSFUL_REGISTER("user sign up successfully!"),
    INCORRECT_ANSWER_CONFIRMATION("warning: answer and answer confirmation aren't matched"),
    DUPLICATE_NICKNAME("warning : duplicate nickname!"),
    SUCCESSFUL_EMAIL_CHANGE("email changed successfully"),
    SUCCESSFUL_NICKNAME_CHANGE("nickname changed successfully"),
    SET_TEXTURE("texture is set successfully for this coordinates"),
    SET_TEXTURE_RECTANGLE("texture is set successfully for these coordinates"),
    WRONG_COORDINATES("warning : incorrect coordinates!"),
    BUILDING_IN_THIS_AREA("warning : you can not change the texture of this area because of existed buildings!"),
    BLOCK_CLEARED("block cleared successfully"),
    DROP_ROCK("rock dropped successfully"),
    WRONG_TREE_TYPE("warning : wrong tree type!"),
    DROP_TREE("tree dropped successfully"),
    INAPPROPRIATE_GROUND_FOR_TREE("the soil is not suitable for dropping the tree"),
    NONEXISTENT_USERNAME("this username does not exist"),
    INCORRECT_PASSWORD("Username and password didn't match!"),
    SUCCESSFUL_LOGIN("enter captcha to login"),
    CORRECT_PASSWORD_RECOVERY_ANSWER("your answer is correct, choose a new password:"),
    WRONG_PASSWORD_RECOVERY_ANSWER("your answer is incorrect"),


    //Game outputs
    WRONG_UNIT_NAME("wrong unit name"),
    WRONG_SELECT_FOR_BUILDING("The wrong building is selected"),
    SUCCESSFUL_UNIT_CREATION("Unit created successfully!"),
    NOT_ENOUGH_POPULATION("Not enough population!"),
    NOT_ENOUGH_WEAPON("Not enough weapon!"),
    SELECT_BUILDING
            ("The building selected successfully "),
    SELECT_UNIT("The unit successfully selected"),
    SLOGAN_REMOVED_SUCCESSFULLY("slogan removed successfully!"),
    DUPLICATED_NEW_PASSWORD("you entered your previous password,enter a new password!"),
    SUCCESSFUL_SLOGAN_CHANGE("slogan changed successfully!"),
    DUPLICATE_SLOGAN("warning : duplicate slogan!"),
    SUCCESSFUL_MAP_GENERATION("you generate the map successfully!"),
    DIG_TUNNEL_FAILED("warning : digging tunnel failed!"),
    DIG_TUNNEL_SUCCESSFUL("digging tunnel done successfully"),
    UNIT_DISBANDED_SUCCESSFULLY("unit/units disbanded successfully"),
    NO_UNIT_FOR_DISBANDING("no unit available for disbanding!"),
    NO_ENGINEER_HERE("no engineer selected for pouring oil!"),
    UNIT_STATE_SET_SUCCESSFULLY("unit state is set successfully"),
    THIS_IS_NOT_YOUR_BUILDING("warning : the selected building is not yours!"),
    NO_THIS_TYPE_UNIT("warning : no unit with given type in this cell"),
    NO_UNIT("no unit in this cell"),
    NOT_ENOUGH_STONE("warning : not enough stone for repairment!"),
    SUCCESSFUL_REPAIRMENT("repairment done successfully"),
    NOT_ENOUGH_UNITS("error : not enough units for dragging this building!"),


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
    EQUIPMENT_CREATED_SUCCESSFULLY("equipment created successfully!"),


    //some Output for all the project
    INVALID_NUMBER("Invalid number!"),


    //trade controller
    TRADE_ADDED("your trade request has added"),
    INCORRECT_ID("the trade doesn't exist"),
    TRADE_ACCEPTED("trade accepted successfully"),
    NOT_ENOUGH_RESOURCE("you don't have enough resource to send others"),
    NOT_ENOUGH_GOLD("the sender doesnt have enough gold"),
    //end of trade controller

    //change environment
    INVALID_BUILDING("you only can drop one headquarter now"),
    INVALID_STATE("please enter a valid state"),
    SUCCESSFUL_ACTION("you did it successfully!"),


    //moving and adding units
    INVALID_MOVE("your chosen destination is blocked!"),
    NOT_ENOUGH_UNIT("you don't have enough units to drop here"),
    INVALID_CELL("you cannot drop unit in this cell"),
    INVALID_DISTANCE("this cell is not in a valid distance from your units!"),


    ;
    private final String string;

    Output(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
