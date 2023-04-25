package model;

import enums.environmentEnums.Materials;

import java.util.HashMap;

public class Storage {
    private int gold;
    private int wood;
    private int stone;
    private HashMap<Materials, Integer> food = new HashMap<>();

    private HashMap<Materials, Integer> materials = new HashMap<>();

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

    public HashMap<Materials, Integer> getFood() {
        return food;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public void setFood(HashMap<Materials, Integer> food) {
        this.food = food;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void addFood(Materials type, int amount) {
        food.put(type, amount);
    }

    public int getAmountOfFood(Materials type) {
        return food.get(type);
    }

    public void changeFoodAmount(Materials type, int amount) {
        food.replace(type, food.get(type) + amount);
    }
    public void addMaterial(Materials materials, int amount) {
        this.materials.put(materials, amount);
    }

    public int getAmountOfMaterial(Materials materials) {
        return this.materials.get(materials);
    }

    public void changeMaterialAmount(Materials materials, int amount) {
        this.materials.replace(materials, this.materials.get(materials) + amount);
    }

}
