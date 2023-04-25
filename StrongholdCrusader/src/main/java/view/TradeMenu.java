package view;

import controller.TradeController;
import enums.Output;
import enums.Validations;
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

    private Output requestTrade(Matcher matcher) {
        String resourceType = Validations.getInfo("t", matcher.group());
        String amount = Validations.getInfo("a", matcher.group());
        String price = Validations.getInfo("p", matcher.group());
        String message = Validations.getInfo("m", matcher.group());
        if (resourceType == null || amount == null || price == null || message == null) return null;
        if (!amount.matches("\\d+")) return null;
        if (!price.matches("\\d+")) return null;
        return tradeController.requestTrade(resourceType, Integer.parseInt(amount), Integer.parseInt(price), message);
    }

    private Output acceptTrade(Matcher matcher) {
        String id = Validations.getInfo("i", matcher.group());
        String message = Validations.getInfo("m", matcher.group());
        if (id == null || message == null) return null;
        if (!id.matches("\\d+")) return null;
        return tradeController.acceptTrade(Integer.parseInt(id), message);
    }

}