package controller;

import enums.Output;
import model.Game;
import model.GovernanceResource;
import model.Trade;
import model.User;

public class TradeController {

    private final Game game;

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
        for (Trade trade : game.getTrades()) {
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
        output += "\namount: " + trade.getAmount();
        output += "\nprice: " + trade.getPrice();
        output += "\nmessage: " + trade.getMessage();
        return output;
    }

    public String showNotification() {
        StringBuilder output = new StringBuilder("notification:");
        for (Trade trade : game.getCurrentPlayer().getTrades()) {
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
        return checkTrade(trade, message);
    }

    private Output checkTrade(Trade trade, String message) {
        GovernanceResource receiverStorage = game.getCurrentPlayer().getGovernance().getGovernanceResource();
        if (trade.getSender().getGovernance().getGold() < trade.getPrice())
            return Output.NOT_ENOUGH_GOLD;
        if (receiverStorage.getAmountOfItemInStockpile(trade.getResource()) < trade.getAmount()) {
            return Output.NOT_ENOUGH_RESOURCE;
        }
        trade.getSender().getGovernance().changeGoldAmount(-trade.getPrice());
        receiverStorage.changeAmountOfItemInStockpile(trade.getResource(), trade.getAmount());
        trade.setReceiver(game.getCurrentPlayer());
        trade.setAccepted(true);
        Trade newTrade = new Trade(trade.getSender(), trade.getResourceName(), trade.getAmount(), trade.getPrice(), message);
        newTrade.setId(trade.getId());
        newTrade.setAccepted(true);
        trade.getSender().addTrade(newTrade);
        return Output.TRADE_ACCEPTED;
    }

    public String showTradeHistory() {
        User user = game.getCurrentPlayer();
        StringBuilder output = new StringBuilder("trade history:");
        for (Trade trade : user.getTrades()) {
            if (trade.getSender().getUsername().equals(user.getUsername())) {
                output.append(showTrade(trade));
            } else if (trade.isAccepted() && trade.getReceiver().getUsername().equals(user.getUsername()))
                output.append(showTrade(trade));
        }
        return output.toString();
    }
}
