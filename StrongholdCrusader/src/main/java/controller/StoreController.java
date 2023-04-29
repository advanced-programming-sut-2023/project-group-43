package controller;

import enums.Output;
import model.DataBase;
import model.Game;

public class StoreController {

    private Game game;

    public StoreController(Game game) {
        this.game = game;
    }

    public String showPriceList() {
        StringBuilder stringBuilder;
        stringBuilder.append("");
    }

    public String buy(String itemName, int amount) {return null;}

    public String sell(String itemName, int amount) {return null;}
}
