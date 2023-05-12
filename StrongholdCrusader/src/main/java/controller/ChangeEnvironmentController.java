package controller;

import enums.Output;
import enums.environmentEnums.Texture;
import enums.environmentEnums.TreeType;
import enums.unitEnums.TroopType;
import enums.unitEnums.UnitState;
import model.Cell;
import model.DataBase;
import model.Game;
import model.User;
import model.buildings.Building;
import model.buildings.CastleDepartment;
import model.units.Unarmed;
import model.units.Unit;
import view.GameMenu;

import java.util.ArrayList;


public class ChangeEnvironmentController {

    private Game game = new Game();

    private User currentUser;

    public ChangeEnvironmentController(User currentUser) {
        this.currentUser = currentUser;
    }

    public Output generateMap(ArrayList<String> usernames, int row, int column, int turns, int mapOption) {
        game.addPlayer(currentUser);
        game.setCurrentUser(currentUser);
        game.setRow(row);
        game.setColumn(column);
        for (String username : usernames) {
            User user = DataBase.getInstance().getUserByUsername(username);
            if (user == null)
                return Output.INVALID_USERNAME;
            game.addPlayer(user);
        }
        game.setCells(new Cell[row][column]);
        GameController.setDefualtMaps(row, column);
        Cell[][] cells = GameController.getDefualtMaps(mapOption);
        game.setCells(cells);
        game.setTurns(turns);
        return Output.SUCCESSFUL_MAP_GENERATION;
    }

    public Output chooseMap(int numberOfPlayers, int size) {
        return null;
    }

    public Output setTexture(int x, int y, String texture) {
        if (x <= 0 || y <= 0 || x > game.getCells().length || y > game.getCells()[0].length)
            return Output.WRONG_COORDINATES;
        if (game.getCells()[x - 1][y - 1].getBuilding() != null)
            return Output.BUILDING_IN_THIS_AREA;
        Texture texture1 = Texture.GROUND;
        for (Texture texture2 : Texture.values()) {
            if (texture2.equals(texture)) {
                texture1 = texture2;
                break;
            }
        }
        game.getCells()[x - 1][y - 1].setTexture(texture1);
        return Output.SET_TEXTURE;
    }

    public Output setTextureRectangle(int x1, int y1, int x2, int y2, String texture) {
        if (x1 <= 0 || y1 <= 0 || x2 <= 0 || y2 <= 0 || x1 > x2 || y1 < y2)
            return Output.WRONG_COORDINATES;
        Texture texture1 = Texture.GROUND;
        for (Texture texture2 : Texture.values()) {
            if (texture2.equals(texture)) {
                texture1 = texture2;
                break;
            }
        }
        for (int i = x1 - 1; i <= x2 - 1; i++) {
            for (int j = y1 - 1; j <= y2 - 1; j++) {
                if (game.getCells()[i][j].getBuilding() != null)
                    return Output.BUILDING_IN_THIS_AREA;
                game.getCells()[i][j].setTexture(texture1);
            }
        }
        for (int i = x1 - 1; i <= x2 - 1; i++) {
            for (int j = y1 - 1; j <= y2 - 1; j++) {
                game.getCells()[i][j].setTexture(texture1);
            }
        }
        return Output.SET_TEXTURE_RECTANGLE;
    }

    public Output clear(int x, int y) {
        game.getCells()[x - 1][y - 1].setBuilding(null);
        game.getCells()[x - 1][y - 1].setUnits(null);
        game.getCells()[x - 1][y - 1].setTexture(Texture.GROUND);
        game.getCells()[x - 1][y - 1].setTreeType(null);
        game.getCells()[x - 1][y - 1].setHasRock(false);
        return Output.BLOCK_CLEARED;
    }

    public Output dropRock(int x, int y, String direction) {
        if (x <= 0 || y <= 0 || x > game.getCells().length || y > game.getCells()[0].length)
            return Output.WRONG_COORDINATES;
        game.getCells()[x - 1][y - 1].setHasRock(true);
        game.getCells()[x - 1][y - 1].setRockDirection(direction);
        return Output.DROP_ROCK;
    }

    public Output dropTree(int x, int y, String type) {
        if (x <= 0 || y <= 0 || x > game.getCells().length || y > game.getCells()[0].length)
            return Output.WRONG_COORDINATES;
        TreeType treeType = null;
        for (TreeType treeType1 : TreeType.values()) {
            if (treeType1.getTreeType().equals(type)) {
                treeType = treeType1;
                break;
            }
        }
        if (treeType == null) return Output.WRONG_TREE_TYPE;
        if (!(game.getCells()[x - 1][y - 1].getTexture().equals(Texture.GRASS) || game.getCells()[x - 1][y - 1].getTexture().equals(Texture.DENSE_GRASSLAND)))
            return Output.INAPPROPRIATE_GROUND_FOR_TREE;
        game.getCells()[x - 1][y - 1].setTreeType(treeType);
        return Output.DROP_TREE;
    }

    public Output dropBuilding(int x, int y, String type) {
        if (x <= 0 || y <= 0 || x > game.getCells().length || y > game.getCells()[0].length)
            return Output.WRONG_COORDINATES;
        if (!type.matches("headquarter")) return Output.INVALID_BUILDING;
        Building building = new CastleDepartment("headquarter", game.getCurrentPlayer(), 1, 20, 0);
        building.setCell(game.getCells()[x - 1][y - 1]);
        game.getCells()[x - 1][y - 1].setBuilding(building);
        game.getCurrentPlayer().getGovernance().addBuilding(building);
        Unit unit = new Unarmed(game.getCurrentPlayer(), "lord", TroopType.LORD);
        unit.setState(UnitState.STANDING);
        unit.setCell(building.getCell());
        game.getCells()[x - 1][y - 1].addUnit(unit);
        return Output.SUCCESSFUL_ACTION;
    }

    public void enterGameMenu() {
        GameController gameController = new GameController(game);
        GameMenu gameMenu = new GameMenu(gameController);
        gameMenu.setTurns(game.getTurns());
        gameMenu.setNumberOfPlayers(game.getPlayers().size());
        gameMenu.run();
    }

    public String goToNextPerson() {
        User user = null;
        boolean isNextPlayerFound = false;
        if (game.getCurrentPlayer().getGovernance().getBuildingByName("headquarter") == null) {
            return "you haven't selected your headquarter yet";
        }
        for (User player : game.getPlayers()) {
            if (isNextPlayerFound) {
                game.setCurrentPlayer(player);
                break;
            }
            if (player.getUsername().equals(game.getCurrentPlayer().getUsername())) {
                isNextPlayerFound = true;
            }
        }
        if (user == null) {
            game.setCurrentPlayer(game.getPlayers().get(0));
            return "everyone has changed map please start game";
        }
        return game.getCurrentPlayer() + " can change map";
    }

}
