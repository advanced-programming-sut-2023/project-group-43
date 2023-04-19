package controller;

import enums.Output;
import model.DataBase;
import model.Game;

public class StoreController {

    private Game game;

    public StoreController(Game game) {
        this.game = game;
    }

    public Output showPriceList() {return null;}

    public Output buy(String itemName, int amount) {return null;}

    public Output sell(String itemName, int amount) {return null;}
}
