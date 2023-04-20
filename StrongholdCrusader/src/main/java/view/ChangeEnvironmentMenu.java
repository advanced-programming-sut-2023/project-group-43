package view;

import controller.ChangeEnvironmentController;
import controller.GameController;
import enums.Output;
import enums.menuEnums.EnvironmentChangeCommands;
import enums.menuEnums.ProfileMenuCommands;
import model.DataBase;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ChangeEnvironmentMenu extends Menu{

    private ChangeEnvironmentController changeEnvironmentController;

    public ChangeEnvironmentMenu(ChangeEnvironmentController changeEnvironmentController) {
        this.changeEnvironmentController = changeEnvironmentController;
    }

    public void run() {
        Scanner scanner = Menu.getScanner();
        String input;
        Output output;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            output = null;
            if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SHOW_MAP)) != null) {
                //output = showMap(matcher);
                showMap(matcher);
            }
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SHOW_DETAILS)) != null) {
                //output = showMapDetails(matcher);
                showMapDetails(matcher);
            }
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.MAP_MOVMENTS)) != null) {
                //output = moveMap(matcher);
                moveMap(matcher);
            }
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SET_TEXTURE)) != null) {
                //output = setTexture(matcher);
                setTexture(matcher);
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
    public void showMap(Matcher matcher) {
        int row = Integer.parseInt(matcher.group("x"));
        int column = Integer.parseInt(matcher.group("y"));
        changeEnvironmentController.showMap(row, column);
    }
    public void showMapDetails(Matcher matcher) {
        int row = Integer.parseInt(matcher.group("x"));
        int column = Integer.parseInt(matcher.group("y"));
        changeEnvironmentController.showMapDetails(row, column);
    }
    public void moveMap(Matcher matcher) {
        int horizontalDisplacement = 0;
        int verticalDisplacement = 0;
        if (matcher.group("firstDirection").equals("up")) {
            if (matcher.group("firtsDisplacement") != null) {
                verticalDisplacement = Integer.parseInt(matcher.group("firtsDisplacement"));
            }
            else verticalDisplacement = 1;
        }
        if (matcher.group("firstDirection").equals("down")) {
            if (matcher.group("firtsDisplacement") != null) {
                verticalDisplacement = Integer.parseInt(matcher.group("firtsDisplacement")) * -1;
            }
            else verticalDisplacement = -1;
        }
        if (matcher.group("secondDirection").equals("up")) {
            if (matcher.group("secondDisplacement") != null) {
                verticalDisplacement = Integer.parseInt(matcher.group("secondDisplacement"));
            }
            else verticalDisplacement = 1;
        }
        if (matcher.group("secondDirection").equals("down")) {
            if (matcher.group("secondDisplacement") != null) {
                verticalDisplacement = Integer.parseInt(matcher.group("secondDisplacement")) * -1;
            }
            else verticalDisplacement = -1;
        }

        if (matcher.group("firstDirection").equals("right")) {
            if (matcher.group("firtsDisplacement") != null) {
                horizontalDisplacement = Integer.parseInt(matcher.group("firtsDisplacement"));
            }
            else horizontalDisplacement = 1;
        }
        if (matcher.group("firstDirection").equals("left")) {
            if (matcher.group("firtsDisplacement") != null) {
                horizontalDisplacement = Integer.parseInt(matcher.group("firtsDisplacement")) * -1;
            }
            else horizontalDisplacement = -1;
        }
        if (matcher.group("secondDirection").equals("right")) {
            if (matcher.group("secondDisplacement") != null) {
                horizontalDisplacement = Integer.parseInt(matcher.group("secondDisplacement"));
            }
            else horizontalDisplacement = 1;
        }
        if (matcher.group("secondDirection").equals("left")) {
            if (matcher.group("secondDisplacement") != null) {
                horizontalDisplacement = Integer.parseInt(matcher.group("secondDisplacement")) * -1;
            }
            else horizontalDisplacement = -1;
        }

        changeEnvironmentController.moveMap(horizontalDisplacement, verticalDisplacement);
    }
    public void setTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        changeEnvironmentController.setTexture(x, y, type);
    }
    public String setTextureRectangle(Matcher matcher) {
        return null;
    }

    public String clearMap(Matcher matcher) {
        return null;
    }

    public String dropRock(Matcher matcher) {
        return null;
    }

    public String dropTree(Matcher matcher) {
        return null;
    }

    public String dropBuilding(Matcher matcher) {
        return null;
    }

    public String dropUnit(Matcher matcher) {
        return null;
    }

    private void enterGameMenu() {
        changeEnvironmentController.enterGameMenu();
    }
}
