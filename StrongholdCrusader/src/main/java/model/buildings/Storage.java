package model.buildings;

import enums.BuildingName;
import enums.FoodType;
import enums.Material;
import enums.ResourceType;
import model.User;

import java.util.HashMap;

public class Storage extends Building {

    public Storage(User owner, BuildingName name, int hp, int rate) {
        super(owner, name, hp);
    }
    private HashMap<ResourceType, Integer> resources = new HashMap<>();
    private HashMap<FoodType, Integer> food = new HashMap<>();

    private HashMap<Material, Integer> materials = new HashMap<>();

    public HashMap<FoodType, Integer> getFood() {
        return food;
    }

    public void setFood(HashMap<FoodType, Integer> food) {
        this.food = food;
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
    public void addResource(ResourceType resourceType, int amount) {
        resources.put(resourceType, amount);
    }

    public int getAmountOfResource(ResourceType resourceType) {
        return resources.get(resourceType);
    }

    public void changeResourceAmount(ResourceType resourceType, int amount) {
        resources.replace(resourceType, resources.get(resourceType) + amount);
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
