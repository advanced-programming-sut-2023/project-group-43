package model;

import enums.*;

import java.util.ArrayList;

public class Building {
    private User owner;

    private BuildingName name;
    private BuildingType type;

    private ArrayList<Cell> cells = new ArrayList<>();
    private int cost;

    private ResourceType resourceCost;

    private int hitPoint;
    private int damage;
    private int fireRange;
    private int peopleCapacity;
    private UnitName unitName;
    private int popularityRate;

    private int rate;
    private int recourseCapacity;
    private ResourceType savedResource;
    private ResourceType consumedRecourse;
    private Material producedMaterial;
    private int productionRate;

    private ArrayList<Unit> units = new ArrayList<>();

    public Building(User owner, BuildingName name) {
        this.owner = owner;
        this.name = name;

    }

    public User getOwner() {
        return owner;
    }

    public BuildingType getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getDamage() {
        return damage;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public UnitName getUnitType() {
        return unitName;
    }

    public int getPopularityRate() {
        return popularityRate;
    }

    public int getRate() {
        return rate;
    }

    public int getRecourseCapacity() {
        return recourseCapacity;
    }

    public ResourceType getSavedResource() {
        return savedResource;
    }

    public ResourceType getConsumedRecourse() {
        return consumedRecourse;
    }


    public Material getProducedMaterial() {
        return producedMaterial;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }


    public ResourceType getResourceCost() {
        return resourceCost;
    }

    public void reduceEnemySpeed() {}

    public void hireEngineerAndWorker() {}

    public void produceMaterial() {}

    public void consumeResource() {}

    public void makeUnit() {}

    public void increasePopularity() {}

    public void increasePopulation() {}

    public void attackEnemy() {}

    public void plantTree() {}

}






