package controller;

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

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String showPriceList() {
        ArrayList<Material> minerals = Material.getMaterialsByType("mineral");
        ArrayList<Material> foods = Material.getMaterialsByType("food");
        ArrayList<Material> weapons = Material.getMaterialsByType("weapon");
        ArrayList<Material> tools = Material.getMaterialsByType("tool");
        StringBuilder stringBuilder = null;
        stringBuilder.append("<<<Price List>>>");
        stringBuilder.append("~Minerals~");
        for (int i = 0; i < minerals.size(); i++)
            stringBuilder.append(i).append(minerals.get(i).getName()).append("  :  ").append(minerals.get(i).getBuyingPrice());
        stringBuilder.append("~Foods~");
        for (int i = 0; i < foods.size(); i++)
            stringBuilder.append(i).append(foods.get(i).getName()).append("  :  ").append(foods.get(i).getBuyingPrice());
        stringBuilder.append("~weapons~");
        for (int i = 0; i < weapons.size(); i++)
            stringBuilder.append(i).append(weapons.get(i).getName()).append("  :  ").append(weapons.get(i).getBuyingPrice());
        stringBuilder.append("~tools~");
        for (int i = 0; i < tools.size(); i++)
            stringBuilder.append(i).append(tools.get(i).getName()).append("  :  ").append(tools.get(i).getBuyingPrice());
        return String.valueOf(stringBuilder);
    }

    public Output buy(String itemName, int amount) {
        Governance governance = game.getCurrentPlayer().getGovernance();
        switch (storeName) {
            case "market" -> {
                Material material = Material.getMaterialByName(itemName);
                if (material == null)
                    return Output.ITEM_NOR_FOUND;
                if (governance.getGold() != amount * material.getBuyingPrice())
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
            case "ladderman":
            case "assassin":
                if (storeName.matches("barrack")) return true;
                break;
            case "archer bow":
            case "slave":
            case "slinger":
            case "horse archer":
            case "arabian swordsman":
            case "fire thrower":
                if(storeName.matches("mercenary post")) return true;
        }
        return false;
    }

    public Output sell(String itemName, int amount) {
        if (!storeName.matches("market")) return Output.WRONG_SELECT_FOR_BUILDING;
        Material material = Material.getMaterialByName(itemName);
        Governance governance = game.getCurrentPlayer().getGovernance();
        if (material == null)
            return Output.ITEM_NOR_FOUND;
        if (governance.getGovernanceResource().getAmountOfItemInStockpile(material) != amount)
            return Output.NOT_ENOUGH_QUANTITY;
        game.getCurrentPlayer().getGovernance().changeGoldAmount(amount * material.getSellingPrice());
        governance.getGovernanceResource().changeAmountOfItemInStockpile(material, -amount);
        return Output.SUCCESSFUL_SALE;
    }
}
