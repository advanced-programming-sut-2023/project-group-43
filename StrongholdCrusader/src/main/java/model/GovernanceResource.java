package model;

import enums.environmentEnums.Material;
import model.buildings.Building;
import model.buildings.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public class GovernanceResource {
    //look after using Integer instead of int during the game
    HashMap<Material, Integer> storage = new HashMap<>();
    private User owner;
    //before starting game you should set items for stockpile based on material enum
    public static String chooseStorage(Material material) {
        return switch (material.getType()) {
            case "mineral" -> "stockpile";
            case "food" -> "foodStockpile";
            case "weapon", "tool" ->
                // tools are ladder and armourer
                    "armoury";
            default -> null;
        };
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void addToStorage(Material material) {
        storage.merge(material, 1, Integer::sum);
    }

    public int getAmountOfItemInStockpile(Material material) {
        return storage.get(material);
    }

    public void changeAmountOfItemInStockpile(Material material, int amount) {
        ArrayList<Building> storages = owner.getGovernance().getAllBuildingsByName(chooseStorage(material));
        if (storages == null) return;
        if (storages.size() > 0) {
            Storage building = (Storage) storages.get(0);
            if (storage.get(material) + amount > building.getCapacity() * storages.size()) return;
            storage.put(material, getAmountOfItemInStockpile(material) + amount);
        }
    }

    public int amountOfFoodInStorage() {
        return getAmountOfItemInStockpile(Material.BREAD) +
                getAmountOfItemInStockpile(Material.CHEESE) +
                getAmountOfItemInStockpile(Material.APPLE) +
                getAmountOfItemInStockpile(Material.MEAT);
    }
}
