package controller;

import enums.Output;
import model.Game;
import model.Trade;
import model.User;
import model.buildings.Building;
import model.buildings.Storage;

import javax.swing.plaf.basic.BasicButtonUI;

public class TradeController {

    private Game game;

    public TradeController(Game game) {
        this.game = game;
    }

    public Output requestTrade(String resource, int resourceAmount, int price, String message) {
        Trade trade = new Trade(game.getCurrentPlayer(), resource, resourceAmount, price, message);
        game.addTrade(trade);
        return Output.TRADE_ADDED;
    }

    public String showTradeList() {
        StringBuilder output = new StringBuilder("trade list:");
        for (Trade trade: game.getTrades()) {
            if (!trade.isAccepted() && !game.getCurrentPlayer().getUsername().equals(trade.getSender().getUsername())) {
                output.append(showTrade(trade));
            }
        }
        return output.toString();
    }

    private String showTrade(Trade trade) {
        String output = "";
        output += "\ntrade id: " + trade.getId();
        output += "\nsender: " + trade.getSender().getUsername();
        output += "\nresource: " + trade.getResourceName();
        output += "\namount: "  + trade.getAmount();
        output += "\nprice: " + trade.getPrice();
        output += "\nmessage: " + trade.getMessage();
        return output;
    }

    public String showNotification() {
        StringBuilder output = new StringBuilder("notification:");
        for (Trade trade: game.getCurrentPlayer().getTrades()) {
            if (!trade.isSeen(game.getCurrentPlayer())) {
                if (!trade.getSender().getUsername().equals(game.getCurrentPlayer().getUsername())) {
                    if (trade.isAccepted()) continue;
                }
                output.append(showTrade(trade));
            }
        }
        return output.toString();
    }

    public Output acceptTrade(int id, String message) {
        Trade trade = game.getTradeById(id);
        if (trade == null) return Output.INCORRECT_ID;
        Storage senderStorage = (Storage) trade.getSender().getGovernance().getBuildingByName("stockpile");
        Storage receiverStorage = (Storage) game.getCurrentPlayer().getGovernance().getBuildingByName("stockpile");
        if (senderStorage.getAmountOfItemInStockpile("gold") < trade.getPrice())
            return Output.NOT_ENOUGH_GOLD;
        if (receiverStorage.getAmountOfItemInStockpile(trade.getResourceName()) < trade.getAmount())
            return Output.NOT_ENOUGH_RESOURCE;
        senderStorage.changeAmount(-trade.getPrice(), "gold");
        receiverStorage.changeAmount(trade.getAmount(), trade.getResourceName());
        trade.setReceiver(game.getCurrentPlayer());
        trade.setAccepted(true);
        trade.setMessage(message);
        trade.getSender().addTrade(trade);
        return Output.TRADE_ACCEPTED;
    }

    public String showTradeHistory() {
        User user = game.getCurrentPlayer();
        StringBuilder output = new StringBuilder("trade history:");
        for (Trade trade: user.getTrades()) {
            if (trade.getSender().getUsername().equals(user.getUsername())) {
                output.append(showTrade(trade));
            } else if (trade.isAccepted() && trade.getReceiver().getUsername().equals(user.getUsername()))
                output.append(showTrade(trade));
        }
        return output.toString();
    }
}
