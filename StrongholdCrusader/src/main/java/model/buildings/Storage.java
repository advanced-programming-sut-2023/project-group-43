package model.buildings;

import enums.environmentEnums.Material;
import model.User;
import java.util.HashMap;

public class Storage extends Building{

    //look after using Integer instead of int during the game
    HashMap<Material, Integer>storage = new HashMap<Material, Integer>();
    public Storage(String name, User owner) {
        super(name, owner);
    }

    //TODO
    //before starting game you should set items for stockpile based on material enum
    public static String ChooseStorage(Material material){
        return switch (material.getType()) {
            case "mineral" -> "stockpile";
            case "food" -> "foodStockpile";
            case "weapon", "tool" ->
                // tools are ladder and armourer
                    "armoury";
            default -> null;
        };
    }

    public void addToStorage(Material material){
        storage.put(material , 0);
    }

    public int getAmountOfItemInStockpile(String item){
        return storage.get(item);
        }
    }


