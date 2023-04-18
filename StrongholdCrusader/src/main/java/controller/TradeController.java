package controller;

import enums.Output;
import model.DataBase;
import model.Trade;

public class TradeController {

    private DataBase dataBase;

    public TradeController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public static Output requestTrade(String resourceType, int resourceAmount, int price, String message) {return null;}

    public static Output showTradeList() {return null;}

    public static Output showNotification() {return null;}

    public static Output acceptTrade(int id, String message) {return null;}

    public static Output showTradeHistory() {return null;}
}
