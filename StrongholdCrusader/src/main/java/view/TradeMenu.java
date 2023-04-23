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
            if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.SEND_TRADE)) != null) {
                output = requestTrade(matcher);
            } else if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.ACCEPT_TRADE)) != null) {
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

    private Output requestTrade(Matcher matcher) {
        String resourceType = matcher.group("resourceType");
        int amount = Integer.parseInt(matcher.group("amount"));
        int price = Integer.parseInt(matcher.group("price"));
        String message = matcher.group("message");
        return tradeController.requestTrade(resourceType, amount, price, message);
    }

    private Output acceptTrade(Matcher matcher) {
        int id = Integer.parseInt(matcher.group("id"));
        String message = matcher.group("message");
        return tradeController.acceptTrade(id, message);
    }

}
