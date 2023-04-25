package view;

import controller.ChangeEnvironmentController;
import enums.Output;
import enums.Validations;
import enums.menuEnums.EnvironmentChangeCommands;
import enums.menuEnums.RegisterAndLoginCommands;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ChangeEnvironmentMenu extends Menu {

    private ChangeEnvironmentController changeEnvironmentController;

    private String x, y, type;

    public ChangeEnvironmentMenu(ChangeEnvironmentController changeEnvironmentController) {
        this.changeEnvironmentController = changeEnvironmentController;
    }

    public void run() {
        Scanner scanner = Menu.getScanner();
        System.out.println("Enter the rows and columns of your desire battle ground : ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        System.out.println("Enter players username : ");
        ArrayList<String> playersArraylist = new ArrayList<>();
        while (scanner.hasNext()) {
            playersArraylist.add(scanner.next());
        }
        System.out.println(changeEnvironmentController.generateMap(playersArraylist, row, column).getString());
        String input;
        Output output;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            output = null;
            if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SET_TEXTURE)) != null) {
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
            } else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.DROP_UNIT)) != null) {
                output = dropUnit(matcher);
            } else if (input.matches("back")) {
                System.out.println("main menu:");
                return;
            } else if (input.matches("start game")) {
                enterGameMenu();
                System.out.println("main menu:");
                return;
            }
            if (output != null)
                System.out.println(output.getString());
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private Output setTexture(Matcher matcher) {
        if (parseMatcher(matcher))
            return changeEnvironmentController.setTexture(Integer.parseInt(x), Integer.parseInt(y), type);
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
        return changeEnvironmentController.setTextureRectangle(Integer.parseInt(x1), Integer.parseInt(y1),
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

    private Output dropUnit(Matcher matcher) {
        String count = Validations.getInfo("c", matcher.group());
        if (parseMatcher(matcher) && count != null && count.matches("\\d+"))
            return changeEnvironmentController.dropUnit(Integer.parseInt(x),
                    Integer.parseInt(y), type, Integer.parseInt(count));
        return null;
    }

    private void enterGameMenu() {
        changeEnvironmentController.enterGameMenu();
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
}
