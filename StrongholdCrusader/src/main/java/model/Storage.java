package model;

import enums.FoodType;
import enums.Material;

import java.util.HashMap;

public class Storage {
    private int gold;
    private int wood;
    private int stone;
    private HashMap<FoodType, Integer> food = new HashMap<>();

    private HashMap<Material, Integer> materials = new HashMap<>();

    public Storage(int gold, int wood, int stone) {
        this.gold = gold;
        this.stone = stone;
        this.wood = wood;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public int getStone() {
        return stone;
    }

    public int getWood() {
        return wood;
    }

    public HashMap<FoodType, Integer> getFood() {
        return food;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public void setFood(HashMap<FoodType, Integer> food) {
        this.food = food;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void addFood(FoodType type, int amount) {
        food.put(type, amount);
    }

    public int getAmountOfFood(FoodType type) {
        return food.get(type);
    }

    public void changeFoodAmount(FoodType type, int amount) {
        food.replace(type, food.get(type) + amount);
    }
    public void addMaterial(Material material, int amount) {
        materials.put(material, amount);
    }

    public int getAmountOfMaterial(Material material) {
        return materials.get(material);
    }

    public void changeMaterialAmount(Material material, int amount) {
        materials.replace(material, materials.get(material) + amount);
    }

}
