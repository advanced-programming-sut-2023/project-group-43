package view;

import controller.GameControllers.ChangeEnvironmentController;
import enums.Output;
import enums.Validations;
import enums.menuEnums.EnvironmentChangeCommands;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ChangeEnvironmentMenu extends Application {
    public static Stage stage;
    private String x, y, type;
    private static final ChangeEnvironmentController changeEnvironmentController = new ChangeEnvironmentController();
    @Override
    public void start(Stage stage) throws Exception {

        ChangeEnvironmentMenu.stage = stage;
        BorderPane changeEnvironmentMenuPane = FXMLLoader.load(new URL(RegisterMenu.class.getResource("/fxml/changeEnvironmentMenu.fxml").toExternalForm()));
        Scene scene = new Scene(changeEnvironmentMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    public void run() {
        //TODO --> fix controller problem
        changeEnvironmentController.initializeGame();
        getReady();
        Scanner scanner = Menu.getScanner();
        String input;
        Output output;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            output = null;
            if (input.matches("show current menu"))
                output = Output.CHANGE_ENVIRONMENT_MENU;
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SET_TEXTURE)) != null) {
                output = setTexture(matcher);
            } else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SET_TEXTURE_RECTANGLE)) != null) {
                setTextureRectangle(matcher);
            } else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.CLEAR)) != null) {
                output = clear(matcher);
            } else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.DROP_ROCK)) != null) {
                output = dropRock(matcher);
            } else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.DROP_TREE)) != null) {
                output = dropTree(matcher);
            } else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.DROP_BUILDING)) != null) {
                output = dropBuilding(matcher);
            } else if (input.matches("next")) {
                System.out.println(changeEnvironmentController.goToNextPerson());
                continue;
            } else if (input.matches("back")) {
                System.out.println("main menu:");
                return;
            } else if (input.matches("start game")) {
                if (enterGameMenu()) {
                    System.out.println("main menu:");
                    return;
                } else {
                    System.out.println("you cannot start the game until everyone choose their headquarters");
                    continue;
                }
            }
            if (output != null)
                System.out.println(output.getString());
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private Output setTexture(Matcher matcher) {
        if (parseMatcher(matcher)) {
            System.out.println(type);
            System.out.println(x);
            System.out.println(y);
            return changeEnvironmentController.setTexture(Integer.parseInt(x), Integer.parseInt(y), type);
        }

        return null;
    }

    private Output setTextureRectangle(Matcher matcher) {
        String x1 = Validations.getInfo("x1", matcher.group());
        String y1 = Validations.getInfo("y1", matcher.group());
        String x2 = Validations.getInfo("x2", matcher.group());
        String y2 = Validations.getInfo("y2", matcher.group());
        String type = Validations.getInfo("t", matcher.group());
        if (x1 == null || y1 == null || x2 == null || y2 == null || type == null) return null;
        if (!(x1.matches("\\d+") && y1.matches("\\d+") && x2.matches("\\d+") && y2.matches("\\d+")))
            return null;
        //return changeEnvironmentController.setTextureRectangle(Integer.parseInt(x1), Integer.parseInt(y1),
                Integer.parseInt(x2), Integer.parseInt(y2), type);
    }

    private Output clear(Matcher matcher) {
        String x = Validations.getInfo("x", matcher.group());
        String y = Validations.getInfo("y", matcher.group());
        if (x == null || y == null || !x.matches("\\d+") || !y.matches("\\d+"))
            return null;
        return changeEnvironmentController.clear(Integer.parseInt(x), Integer.parseInt(y));
    }

    private Output dropRock(Matcher matcher) {
        String x = Validations.getInfo("x", matcher.group());
        String y = Validations.getInfo("y", matcher.group());
        String direction = Validations.getInfo("d", matcher.group());
        if (x == null || y == null || direction == null || !x.matches("\\d+") || !y.matches("\\d+"))
            return null;
        return changeEnvironmentController.dropRock(Integer.parseInt(x), Integer.parseInt(y), direction);
    }

    private Output dropTree(Matcher matcher) {
        if (parseMatcher(matcher))
            return changeEnvironmentController.dropTree(Integer.parseInt(x), Integer.parseInt(y), type);
        return null;
    }

    private Output dropBuilding(Matcher matcher) {
        if (parseMatcher(matcher))
            return changeEnvironmentController.dropBuilding(Integer.parseInt(x), Integer.parseInt(y), type);
        return null;
    }

    private boolean enterGameMenu() {
        return changeEnvironmentController.enterGameMenu();
    }

    private boolean parseMatcher(Matcher matcher) {
        x = null;
        y = null;
        type = null;
        x = Validations.getInfo("x", matcher.group());
        y = Validations.getInfo("y", matcher.group());
        type = Validations.getInfo("t", matcher.group());
        return x != null && y != null && type != null && x.matches("\\d+") && y.matches("\\d+");
    }

    private void getReady() {
        String input;
        Scanner scanner = Menu.getScanner();
        System.out.println("change environment menu:");
        System.out.println("Enter the rows and columns of your desire battle ground : ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        System.out.println("Enter players username:");
        ArrayList<String> playersArraylist = new ArrayList<>();
        input = scanner.nextLine();
        while (true) {
            input = scanner.nextLine();
            if (input.matches("\\S*end\\S*")) break;
            playersArraylist.add(input);
        }
        System.out.println("number of turns:");
        input = scanner.nextLine();
        while (!input.matches("\\d+")) {
            System.out.println("wrong input");
            input = scanner.nextLine();
        }
        int turns = Integer.parseInt(input);
        System.out.println("choose your desire map:\nenter 1 or 2");
        int mapOption = Integer.parseInt(scanner.nextLine());
        while (mapOption != 2 && mapOption != 1) {
            System.out.println("wrong map number");
            mapOption = scanner.nextInt();
        }
        System.out.println(changeEnvironmentController.generateMap(playersArraylist, row, column, turns, mapOption).getString());
        changeEnvironmentController.getGame().setCurrentPlayer(changeEnvironmentController.getCurrentUser());
    }

}
