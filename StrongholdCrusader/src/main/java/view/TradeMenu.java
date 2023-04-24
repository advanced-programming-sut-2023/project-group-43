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

    private Output requestTrade(Matcher matcher) {
        String resourceType = null, amount = null, price = null, message = null;
        Matcher allMatcher = TradeMenuCommands.getMatcher(matcher.group(), TradeMenuCommands.GROUP);
        while (allMatcher.find()) {
            switch (matcher.group("flag")) {
                case "t":
                    if (resourceType != null) return null;
                    if ((resourceType = allMatcher.group("group")) == null)
                        resourceType = allMatcher.group("group2");
                    break;
                case "a":
                    if (amount != null) return null;
                    if ((amount = allMatcher.group("group")) == null) amount = allMatcher.group("group2");
                    break;
                case "p":
                    if (price != null) return null;
                    if ((price = allMatcher.group("group")) == null) price = allMatcher.group("group2");
                    break;
                case "m":
                    if (message != null) return null;
                    if ((message = allMatcher.group("group")) == null) message = allMatcher.group("group2");
            }
        }
        if (!amount.matches("\\d+")) return null;
        if (!price.matches("\\d+")) return null;
        return tradeController.requestTrade(resourceType, Integer.parseInt(amount), Integer.parseInt(price), message);
    }

    private Output acceptTrade(Matcher matcher) {
        String id = null, message = null;
        Matcher allMatcher = TradeMenuCommands.getMatcher(matcher.group(), TradeMenuCommands.GROUP);
        while (allMatcher.find()) {
            switch (matcher.group("flag")) {
                case "i":
                    if (id != null) return null;
                    if ((id = allMatcher.group("group")) == null)
                        id = allMatcher.group("group2");
                    break;
                case "m":
                    if (message != null) return null;
                    if ((message = allMatcher.group("group")) == null) message = allMatcher.group("group2");
            }
        }
        if (!id.matches("\\d+")) return null;
        return tradeController.acceptTrade(Integer.parseInt(id), message);
    }

}
