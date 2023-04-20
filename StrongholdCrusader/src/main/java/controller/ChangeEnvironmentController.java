package controller;

import enums.Output;
import model.*;
import view.GameMenu;

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
    public void generateMap(int numberOfPlayers, int size) {}

    public Output chooseMap(int numberOfPlayers, int size) {return null;}

    public Output setTexture(int x, int y, String texture) {return null;}

    public Output setTextureRectangle(int x1, int y1, int x2, int y2, String texture) {return null;}

    public Output clearMap(int x, int y) {return null;}

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
