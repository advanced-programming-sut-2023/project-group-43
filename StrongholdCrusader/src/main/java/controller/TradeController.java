package controller;

import enums.Output;
import model.DataBase;
import model.Game;
import model.Trade;

public class TradeController {

    private Game game;

    public TradeController(Game game) {
        this.game = game;
    }

    public Output requestTrade(String resourceType, int resourceAmount, int price, String message) {return null;}

    public Output showTradeList() {return null;}

    public Output showNotification() {return null;}

    public Output acceptTrade(int id, String message) {return null;}

    public Output showTradeHistory() {return null;}
}
