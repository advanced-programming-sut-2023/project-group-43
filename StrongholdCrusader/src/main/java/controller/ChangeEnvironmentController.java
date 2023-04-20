package controller;

import enums.Output;
import enums.Texture;
import model.*;
import view.GameMenu;

import java.util.ArrayList;

public class ChangeEnvironmentController {

    private Game game = new Game();

    private User currentUser;

    public ChangeEnvironmentController(User currentUser) {
        this.currentUser = currentUser;
    }


    public void showMap(int row, int column) {
        //TODO
    }
    public void showMapDetails(int row, int column) {

    }
    public void moveMap(int horizontalDisplacement, int verticalDisplacement) {}
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
        game.getCells()[x - 1][y - 1].setWaterArea(null);
        game.getCells()[x - 1][y - 1].setHasRock(false);
        return Output.BLOCK_CLEARED;
    }

    public Output dropRock(int x, int y, String direction) {return null;}

    public Output dropTree(int x, int y, String type) {return null;}

    public Output dropBuilding(int x, int y, String type) {return null;}

    public Output dropUnit(int x, int y, String type, int count) {return null;}

    public void enterGameMenu() {
        GameController gameController = new GameController(game);
        GameMenu gameMenu = new GameMenu(gameController);
        gameMenu.run();
    }

}
