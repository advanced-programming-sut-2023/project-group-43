package view;

import controller.GameController;
import controller.MapController;
import enums.Output;
import enums.menuEnums.EnvironmentChangeCommands;
import model.DataBase;
import model.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu extends Menu{

    private MapController mapController;

    public MapMenu(MapController mapController) {
        this.mapController = mapController;
    }

    public void run(){
        System.out.println("map menu:");
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
        }
    }

    public void showMap(Matcher matcher) {
        int row = Integer.parseInt(matcher.group("x"));
        int column = Integer.parseInt(matcher.group("y"));

        mapController.showMap(row, column);
    }
    public void showMapDetails(Matcher matcher) {
        int row = Integer.parseInt(matcher.group("x"));
        int column = Integer.parseInt(matcher.group("y"));
        mapController.showMapDetails(row, column);
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
        mapController.moveMap(horizontalDisplacement, verticalDisplacement);
    }
}
