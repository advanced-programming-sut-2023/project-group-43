package model;

import enums.environmentEnums.Material;

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

    public HashMap<Material, Integer> getStorage() {
        return storage;
    }

    public ArrayList getOnlineMaterials() {
        ArrayList<Material> materials = new ArrayList<>();
        materials.addAll(storage.keySet());
        return materials;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void addToStorage(Material material) {
        storage.merge(material, 1, Integer::sum);
    }

    public int getAmountOfItemInStockpile(Material material) {
        if (storage.get(material) == null) return 0;
        return storage.get(material);
    }

    public void changeAmountOfItemInStockpile(Material material, int amount) {
        if (storage.get(material) == null) {
            storage.put(material, amount);
        } else
            storage.put(material, getAmountOfItemInStockpile(material) + amount);
    }

    public int amountOfFoodInStorage() {
        return getAmountOfItemInStockpile(Material.BREAD) +
                getAmountOfItemInStockpile(Material.CHEESE) +
                getAmountOfItemInStockpile(Material.APPLE) +
                getAmountOfItemInStockpile(Material.MEAT);
    }
}
