package controller;

import enums.Output;
import enums.environmentEnums.Material;
import model.Game;
import model.buildings.Storage;

import java.util.ArrayList;

public class StoreController {

    private Game game;

    public StoreController(Game game) {
        this.game = game;
    }

    public String showPriceList() {
        ArrayList<Material> minerals = Material.getMaterialsByType("mineral");
        ArrayList<Material>foods = Material.getMaterialsByType("food");
        ArrayList<Material>weapons = Material.getMaterialsByType("weapon");
        ArrayList<Material>tools = Material.getMaterialsByType("tool");
        StringBuilder stringBuilder = null;
        stringBuilder.append("<<<Price List>>>");
        stringBuilder.append("~Minerals~");
        for(int i = 0 ; i < minerals.size() ; i++)
            stringBuilder.append(i).append(minerals.get(i).getName()).append("  :  ").append(minerals.get(i).getInitialCost());
        stringBuilder.append("~Foods~");
        for(int i = 0 ; i < foods.size() ; i++)
            stringBuilder.append(i).append(foods.get(i).getName()).append("  :  ").append(foods.get(i).getInitialCost());
        stringBuilder.append("~weapons~");
        for(int i = 0 ; i < weapons.size() ; i++)
            stringBuilder.append(i).append(weapons.get(i).getName()).append("  :  ").append(weapons.get(i).getInitialCost());
        stringBuilder.append("~tools~");
        for(int i = 0 ; i < tools.size() ; i++)
            stringBuilder.append(i).append(tools.get(i).getName()).append("  :  ").append(tools.get(i).getInitialCost());
        return String.valueOf(stringBuilder);
    }

    public Output buy(String itemName, int amount) {
        Material material = Material.getMaterialByName(itemName);
        Storage storage = game.getCurrentPlayer().getStorage();
        if(material == null)
            return Output.ITEM_NOR_FOUND;
        if(game.getCurrentPlayer().getGold() != amount * material.getInitialCost())
            return Output.NOT_ENOUGH_MONEY;
        game.getCurrentPlayer().setGold(game.getCurrentPlayer().getGold() - amount * material.getInitialCost());
        storage.changeAmountOfItemInStockpile(material,amount);
        return Output.SUCCESSFUL_PURCHASE;
    }

    public Output sell(String itemName, int amount) {
        Material material = Material.getMaterialByName(itemName);
        Storage storage = game.getCurrentPlayer().getStorage();
        if(material == null)
            return Output.ITEM_NOR_FOUND;
        if(storage.getAmountOfItemInStockpile(material) != amount)
            return Output.NOT_ENOUGH_QUANTITY;
        game.getCurrentPlayer().setGold(game.getCurrentPlayer().getGold() + amount * material.getSecondaryCost());
        storage.changeAmountOfItemInStockpile(material,-amount);
        return Output.SUCCESSFUL_SALE;
    }
}
