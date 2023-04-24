package view;

import controller.ChangeEnvironmentController;
import controller.GameController;
import enums.Output;
import enums.menuEnums.EnvironmentChangeCommands;
import enums.menuEnums.ProfileMenuCommands;
import model.DataBase;
import model.User;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;
import java.util.regex.Matcher;

public class ChangeEnvironmentMenu extends Menu{

    private ChangeEnvironmentController changeEnvironmentController;
    public ChangeEnvironmentMenu(ChangeEnvironmentController changeEnvironmentController) {
        this.changeEnvironmentController = changeEnvironmentController;
    }

    public void run() {
        Scanner scanner = Menu.getScanner();
        System.out.println("Enter the rows and columns of your desire battle ground : ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        System.out.println("Enter players username : ");
        ArrayList<User> playersArraylist = new ArrayList<>();
        while (scanner.hasNext()) {
            playersArraylist.add(DataBase.getInstance().getUserByUsername(scanner.next()));
        }
        changeEnvironmentController.generateMap(playersArraylist, row, column);
        String input;
        Output output;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            output = null;
            if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SET_TEXTURE)) != null) {
                //output = setTexture(matcher);
                setTexture(matcher);
            }
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.SET_TEXTURE_RECTANGLE)) != null) {
                setTextureRectangle(matcher);
            }
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.CLEAR)) != null) {
                output = clear(matcher);
            }
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.DROP_ROCK)) != null) {
                output = dropRock(matcher);
            }
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.DROP_TREE)) != null) {
                output = dropTree(matcher);
            }
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.DROP_BUILDING)) != null) {
                output = dropBuilding(matcher);
            }
            else if ((matcher = EnvironmentChangeCommands.getMatcher(input, EnvironmentChangeCommands.DROP_UNIT)) != null) {
                output = dropUnit(matcher);
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
    public void setTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        changeEnvironmentController.setTexture(x, y, type);
    }
    public void setTextureRectangle(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        String texture = matcher.group("type");
        changeEnvironmentController.setTextureRectangle(x1, y1, x2, y2, texture);
    }
    public Output clear(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return changeEnvironmentController.clear(x, y);
    }
    public Output dropRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String direction = matcher.group("direction");
        return changeEnvironmentController.dropRock(x, y, direction);
    }

    public Output dropTree(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        return changeEnvironmentController.dropTree(x, y, type);
    }

    public Output dropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        return changeEnvironmentController.dropBuilding(x, y, type);
    }

    public Output dropUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        int count = Integer.parseInt(matcher.group("count"));
        return changeEnvironmentController.dropUnit(x, y, type, count);
    }

    private void enterGameMenu() {
        changeEnvironmentController.enterGameMenu();
    }
}
