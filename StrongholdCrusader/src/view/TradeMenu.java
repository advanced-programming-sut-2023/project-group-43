package view;

import controller.TradeController;
import enums.Output;
import enums.menuEnums.TradeMenuCommands;

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
        Output output;
        Matcher matcher;
        System.out.println("trade menu:");
        tradeController.showNotification();
        while (true) {
            input = scanner.nextLine();
            output = null;
            if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.SEND_TRADE)).matches()) {
                output = requestTrade(matcher);
            } else if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.ACCEPT_TRADE)).matches()) {
                output = acceptTrade(matcher);
            } else if (input.matches("trade list")) {
                output = tradeController.showTradeList();
            } else if (input.matches("trade history")) {
                output = tradeController.showTradeHistory();
            } else if (input.matches("back")) {
                System.out.println("main menu:");
                return;
            } if (output == null) System.out.println("invalid command");
            else System.out.println(output.getString());
        }
    }

    private String requestTrade(Matcher matcher) {
        return null;
    }

    private String acceptTrade(Matcher matcher) {
        return null;
    }

}
