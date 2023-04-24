package view;

import controller.TradeController;
import enums.Output;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu extends Menu{

    private TradeController tradeController;

    public TradeMenu(TradeController tradeController) {
        this.tradeController = tradeController;
    }

    public void run() {
        Scanner scanner = Menu.getScanner();
        String input;
        Matcher matcher;
        System.out.println("trade menu:");
        while (true){
            input = scanner.nextLine();
            if(input.matches("back")){
                System.out.println("game menu:");
                break;
            }
        }
    }

    private String requestTrade(Matcher matcher) {
        return null;
    }

    private String acceptTrade(Matcher matcher) {
        return null;
    }

}
