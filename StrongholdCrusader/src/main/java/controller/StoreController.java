package controller;

import enums.environmentEnums.Material;
import model.Game;

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

    public String buy(String itemName, int amount) {return null;}

    public String sell(String itemName, int amount) {return null;}
}
