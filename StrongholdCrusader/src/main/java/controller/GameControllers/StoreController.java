package controller.GameControllers;

import controller.GameControllers.GameController;
import enums.Output;
import enums.environmentEnums.Material;
import model.Game;
import model.Governance;

import java.util.ArrayList;

public class StoreController {

    private final Game game;
    private final GameController gameController;
    private String storeName;

    public StoreController(Game game, GameController gameController) {
        this.game = game;
        this.gameController = gameController;
    }

    public Output buy(String itemName, int amount) {
        Governance governance = game.getCurrentPlayer().getGovernance();
        switch (storeName) {
            case "market" -> {
                Material material = Material.getMaterialByName(itemName);
                if (material == null)
                    return Output.ITEM_NOR_FOUND;
                if (governance.getGold() < amount * material.getBuyingPrice())
                    return Output.NOT_ENOUGH_MONEY;
                governance.changeGoldAmount(-amount * material.getBuyingPrice());
                governance.getGovernanceResource().changeAmountOfItemInStockpile(material, amount);
                return Output.SUCCESSFUL_PURCHASE;
            }
            case "engineer guild", "barrack", "mercenary post" -> {
                if (isPossible(storeName, itemName)) {
                    return gameController.createUnit(itemName, amount);
                }
                return Output.WRONG_SELECT_FOR_BUILDING;
            }
        }
        return null;
    }

    private boolean isPossible(String storeName, String itemName) {
        switch (itemName) {
            case "engineer":
            case "ladderman":
                if (storeName.matches("engineer guild")) return true;
                break;
            case "worker":
            case "archer":
            case "crossbowman":
            case "swordsman":
            case "black monk":
            case "spearman":
            case "pikeman":
            case "maceman":
            case "knight":
            case "tunneler":
            case "assassin":
                if (storeName.matches("barrack")) return true;
                break;
            case "archer bow":
            case "slave":
            case "slinger":
            case "horse archer":
            case "arabian swordsman":
            case "fire thrower":
                if (storeName.matches("mercenary post")) return true;
        }
        return false;
    }

    public Output sell(String itemName, int amount) {
        if (!storeName.matches("market")) return Output.WRONG_SELECT_FOR_BUILDING;
        Material material = Material.getMaterialByName(itemName);
        Governance governance = game.getCurrentPlayer().getGovernance();
        if (material == null)
            return Output.ITEM_NOR_FOUND;
        if (governance.getGovernanceResource().getAmountOfItemInStockpile(material) < amount)
            return Output.NOT_ENOUGH_QUANTITY;
        game.getCurrentPlayer().getGovernance().changeGoldAmount(amount * material.getSellingPrice());
        governance.getGovernanceResource().changeAmountOfItemInStockpile(material, -amount);
        return Output.SUCCESSFUL_SALE;
    }

    public Game getGame() {
        return game;
    }
}
