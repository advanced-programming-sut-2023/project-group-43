package view;

import controller.GameController;
import controller.StoreController;
import enums.Output;
import enums.menuEnums.StoreMenuCommands;
import model.DataBase;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StoreMenu extends Menu{

    private StoreController storeController;

    public StoreMenu(StoreController storeController) {
        this.storeController = storeController;
    }

    public void run() {
        Scanner scanner = Menu.getScanner();
        String input;
        Matcher matcher;
        System.out.println("store menu:");
        while (true){
            input = scanner.nextLine();
            if (input.matches("back")){
                System.out.println("game menu:");
                return;
            }
            else if(StoreMenuCommands.getMatcher(input,StoreMenuCommands.SHOW_PRICE_LIST)!= null){
                System.out.println(storeController.showPriceList());
            }
            else if((matcher = StoreMenuCommands.getMatcher(input,StoreMenuCommands.BUY)) != null){
                System.out.println(buy(matcher));
            }
            else if ((matcher = StoreMenuCommands.getMatcher(input,StoreMenuCommands.SELL))!= null) {
                System.out.println(sell(matcher));
            }
        }
    }

    private String buy(Matcher matcher) {
        String itemName = matcher.group("itemName");
        int amount = Integer.parseInt(matcher.group("amount"));
        return storeController.buy(itemName,amount);
    }

    private String sell(Matcher matcher) {
        String itemName = matcher.group("itemName");
        int amount = Integer.parseInt(matcher.group("amount"));
        return storeController.sell(itemName,amount);
    }
}