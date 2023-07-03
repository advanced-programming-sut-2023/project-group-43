package controller.GameControllers;

import enums.Output;
import model.Cell;
import model.Game;
import model.units.Assassin;
import model.units.Unit;

public class MapController {

    private final Game game ;

    public MapController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
