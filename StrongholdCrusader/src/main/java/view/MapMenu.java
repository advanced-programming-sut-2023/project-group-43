package view;

import controller.GameControllers.MapController;
import enums.Output;
import enums.Validations;
import enums.menuEnums.EnvironmentChangeCommands;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu extends Application {

    private MapController mapController;

    public MapController getMapController() {
        return mapController;
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void run() {
        System.out.println("map menu:");
        Scanner scanner = Menu.getScanner();
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if (input.matches("show current menu"))
                System.out.println(Output.MAP_MENU.getString());
            if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SHOW_MAP)) != null) {
                showMap(matcher);
            } else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SHOW_DETAILS)) != null) {
                showMapDetails(matcher);
            } else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.MAP_MOVMENTS)) != null) {
                moveMap(matcher);
            } else if (input.matches("back")) {
                System.out.println("game menu:");
                return;
            } else System.out.println("invalid command!");
            ;
        }
    }

    public void showMap(Matcher matcher) {
        String row = Validations.getInfo("x", matcher.group());
        String column = Validations.getInfo("x", matcher.group());

        System.out.println(mapController.showMap(Integer.parseInt(row), Integer.parseInt(column)));
    }

    public void showMapDetails(Matcher matcher) {
        int row = Integer.parseInt(Validations.getInfo("x", matcher.group()));
        int column = Integer.parseInt(Validations.getInfo("y", matcher.group()));
        System.out.println(mapController.showMapDetails(row, column));
    }

    public void moveMap(Matcher matcher) {
        int horizontalDisplacement = 0;
        int verticalDisplacement = 0;
        if (matcher.group("firstDirection").equals("up")) {
            if (matcher.group("firstDisplacement") != null) {
                verticalDisplacement = Integer.parseInt(matcher.group("firstDisplacement"));
            } else verticalDisplacement = 1;
        }
        if (matcher.group("firstDirection").equals("down")) {
            if (matcher.group("firstDisplacement") != null) {
                verticalDisplacement = Integer.parseInt(matcher.group("firstDisplacement")) * -1;
            } else verticalDisplacement = -1;
        }
        if (matcher.group("secondDirection").equals("up")) {
            if (matcher.group("secondDisplacement") != null) {
                verticalDisplacement = Integer.parseInt(matcher.group("secondDisplacement"));
            } else verticalDisplacement = 1;
        }
        if (matcher.group("secondDirection").equals("down")) {
            if (matcher.group("secondDisplacement") != null) {
                verticalDisplacement = Integer.parseInt(matcher.group("secondDisplacement")) * -1;
            } else verticalDisplacement = -1;
        }

        if (matcher.group("firstDirection").equals("right")) {
            if (matcher.group("firstDisplacement") != null) {
                horizontalDisplacement = Integer.parseInt(matcher.group("firstDisplacement"));
            } else horizontalDisplacement = 1;
        }
        if (matcher.group("firstDirection").equals("left")) {
            if (matcher.group("firstDisplacement") != null) {
                horizontalDisplacement = Integer.parseInt(matcher.group("firstDisplacement")) * -1;
            } else horizontalDisplacement = -1;
        }
        if (matcher.group("secondDirection").equals("right")) {
            if (matcher.group("secondDisplacement") != null) {
                horizontalDisplacement = Integer.parseInt(matcher.group("secondDisplacement"));
            } else horizontalDisplacement = 1;
        }
        if (matcher.group("secondDirection").equals("left")) {
            if (matcher.group("secondDisplacement") != null) {
                horizontalDisplacement = Integer.parseInt(matcher.group("secondDisplacement")) * -1;
            } else horizontalDisplacement = -1;
        }
        System.out.println(mapController.moveMap(horizontalDisplacement, verticalDisplacement));
    }

}
