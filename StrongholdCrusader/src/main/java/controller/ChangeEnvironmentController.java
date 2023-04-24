package controller;

import enums.BuildingEnums.BuildingEnum;
import enums.Output;
import enums.environmentEnums.Texture;
import enums.environmentEnums.TreeType;
import enums.unitEnums.UnitsEnum;
import model.*;
import model.buildings.Building;
import model.units.Unit;
import view.GameMenu;

import java.util.ArrayList;


public class ChangeEnvironmentController {

    private Game game = new Game();

    private User currentUser;

    public ChangeEnvironmentController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void generateMap(ArrayList<User> playersArraylist, int row, int column) {
        Cell[][] cells = new Cell[row][column];
        game.setCells(cells);
        game.setPlayers(playersArraylist);
    }
    public Output chooseMap(int numberOfPlayers, int size) {return null;}

    public Output setTexture(int x, int y, String texture) {
        if (x <= 0 || y <= 0 || x > game.getCells().length || y > game.getCells()[0].length)
            return Output.WRONG_COORDINATES;
        if (game.getCells()[x - 1][y - 1].getBuilding() != null)
            return Output.BUILDING_IN_THIS_AREA;
        Texture texture1 = Texture.GROUND;
        for (Texture texture2: Texture.values()) {
            if (texture2.equals(texture)) {
                texture1 = texture2;
                break;
            }
        }
        game.getCells()[x-1][y-1].setTexture(texture1);
        return Output.SET_TEXTURE;
    }

    public Output setTextureRectangle(int x1, int y1, int x2, int y2, String texture) {
        if (x1 <= 0 || y1 <= 0 || x2 <= 0 || y2 <= 0 || x1 > x2 || y1 < y2)
            return Output.WRONG_COORDINATES;
        Texture texture1 = Texture.GROUND;
        for (Texture texture2: Texture.values()) {
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
        game.getCells()[x- 1][y - 1].setHasRock(true);
        game.getCells()[x- 1][y - 1].setRockDirection(direction);
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
        String buildingName = null;
        for (BuildingEnum buildingEnum : BuildingEnum.values()) {
            if (buildingEnum.getName().equals(type)) {
                buildingName = buildingEnum.getName();
                break;
            }
        }
        if (buildingName != null) {
            Building building = new Building(buildingName, currentUser);
            game.getCells()[x - 1][y - 1].setBuilding(building);
            return Output.BUILDING_DROPPED_SUCCESSFULLY;
        }
        return null;
    }

    public Output dropUnit(int x, int y, String type, int count) {
        if (x <= 0 || y <= 0 || x > game.getCells().length || y > game.getCells()[0].length)
            return Output.WRONG_COORDINATES;
        if (count <= 0) return Output.WRONG_COUNT;
        boolean found = false;
        UnitsEnum unitEnum1 = null;
        for (UnitsEnum unitsEnum : UnitsEnum.values()) {
            if (unitsEnum.getName().equals(type)) {
                found = true;
                unitEnum1 = unitsEnum;
                break;
            }
        }
        if (found) {
            ArrayList<Unit> droppedUnits = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Unit unit = new Unit(currentUser, unitEnum1);
                droppedUnits.add(unit);
            }
            game.getCells()[x - 1][y - 1].setUnits(droppedUnits);
            return Output.UNIT_DROPPED_SUCCESSFULLY;
        }
        return null;
    }

    public void enterGameMenu() {
        GameController gameController = new GameController(game);
        GameMenu gameMenu = new GameMenu(gameController);
        gameMenu.run();
    }

}
