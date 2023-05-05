package model;

import enums.environmentEnums.Material;
import java.util.HashMap;

public class GovernanceResource {
        //look after using Integer instead of int during the game
        HashMap<Material, Integer> storage = new HashMap<Material, Integer>();

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

        public int getAmountOfItemInStockpile(Material material){
            return storage.get(material);
        }

        public void changeAmountOfItemInStockpile(Material material,int amount){
            storage.put(material,getAmountOfItemInStockpile(material) + amount);
        }



    }
