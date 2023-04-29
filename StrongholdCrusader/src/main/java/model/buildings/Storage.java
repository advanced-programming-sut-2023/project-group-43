package model.buildings;

import enums.environmentEnums.Materials;
import enums.BuildingEnums.BuildingEnum;
import enums.environmentEnums.Materials;
import model.User;

import java.util.HashMap;

public class Storage extends Building {

    //look after using Integer instead of int during the game
    HashMap<Materials, Integer> storage = new HashMap<>();

    public Storage(String name, User owner) {

        super(name, owner);
    }

    //TODO
    //before starting game you should set items for stockpile based on material enum
    public static String ChooseStorage(Materials material){
        return switch (material.getType()) {
            case "mineral" -> "stockpile";
            case "food" -> "foodStockpile";
            case "weapon", "tool" ->
                // tools are ladder and armourer
                    "armoury";
            default -> null;
        };
    }

    public void addToStorage(Materials material){
        storage.put(material , 0);
    }

    public int getAmountOfItemInStockpile(String item) {
        return storage.get(item);
        //TODO : what about nulls?
    }

    public void changeAmount(int amount, String name) {
        Materials materials = null;
        materials =  materials.getMaterialByName(name);
        storage.replace(materials, storage.get(name) + amount);
    }
}



