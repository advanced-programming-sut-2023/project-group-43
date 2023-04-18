package view;

import controller.GameController;
import controller.StoreController;
import model.DataBase;

import java.util.regex.Matcher;

public class StoreMenu extends Menu{

    private StoreController storeController;

    public StoreMenu(DataBase dataBase) {
        super(dataBase);
        this.storeController = new StoreController(dataBase);
    }

    public void run() {
    }

    private String buy(Matcher matcher) {
        return null;
    }

    private String sell(Matcher matcher) {
        return null;
    }
}
