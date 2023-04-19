package controller;

import enums.Output;
import model.DataBase;
import model.Game;

public class MapController {

    private Game game;

    public MapController(Game game) {
        this.game = game;
    }
    public Output showMap(int x, int y) {return null;}

    public Output moveMap(String verticalDirection, String horizontalDirection) {return null;}

    public Output showDetails(int x, int y) {return null;}

}
