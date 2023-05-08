package model;

import enums.environmentEnums.Material;
import model.buildings.Building;
import model.buildings.Storage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GovernanceResource {
        //look after using Integer instead of int during the game
        HashMap<Material, Integer> storage = new HashMap<Material, Integer>();
        private User owner;
        //TODO
        //before starting game you should set items for stockpile based on material enum
        public static String chooseStorage(Material material){
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

    public void addToStorage(Material material){
            if(storage.get(material).equals(null))
                storage.put(material,1);
            else
                storage.put(material , storage.get(material) + 1);
        }

        public int getAmountOfItemInStockpile(Material material){
            return storage.get(material);
        }

        public void changeAmountOfItemInStockpile(Material material,int amount){
                ArrayList<Building> storages = owner.getGovernance().getAllBuildingsByName(chooseStorage(material));
                if (storages == null) return;
                Storage building = (Storage) storages.get(0);
                if (storage.get(material) + amount > building.getCapacity() * storages.size()) return;
            storage.put(material,getAmountOfItemInStockpile(material) + amount);
        }

        public int amountOfFoodInStorage(){
            return getAmountOfItemInStockpile(Material.BREAD) +
                    getAmountOfItemInStockpile(Material.CHEESE) +
                    getAmountOfItemInStockpile(Material.APPLE) +
                    getAmountOfItemInStockpile(Material.MEAT);
    }
}
