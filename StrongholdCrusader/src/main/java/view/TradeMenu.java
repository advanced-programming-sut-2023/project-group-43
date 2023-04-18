package view;

import controller.TradeController;
import model.DataBase;

import java.util.regex.Matcher;

public class TradeMenu extends Menu{

    private TradeController tradeController;

    public TradeMenu(DataBase dataBase) {
        super(dataBase);
        this.tradeController = new TradeController(dataBase);
    }

    public void run() {
    }

    private String requestTrade(Matcher matcher) {
        return null;
    }

    private String acceptTrade(Matcher matcher) {
        return null;
    }

}
