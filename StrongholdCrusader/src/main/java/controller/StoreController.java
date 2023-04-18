package controller;

import enums.Output;
import model.DataBase;

public class StoreController {

    private DataBase dataBase;

    public StoreController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public static Output showPriceList() {}

    public static Output buy(String itemName, int amount) {}

    public static Output sell(String itemName, int amount) {}
}
