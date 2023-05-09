package model;

import enums.RateNumber;
import model.buildings.Building;
import model.units.Unit;

import java.util.ArrayList;

public class Governance {
    private int popularity;
    private int population;

    private int unemployedPopulation;
    private RateNumber foodRate;
    private RateNumber taxRate;
    private int fearRate;
    private double gold;
    private GovernanceResource governanceResource;

    private ArrayList<Unit> units = new ArrayList<>();

    private ArrayList<Building> buildings = new ArrayList<>();

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public RateNumber getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(RateNumber foodRate) {
        this.foodRate = foodRate;
    }

    public RateNumber getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(RateNumber taxRate) {
        this.taxRate = taxRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public int getPopulation() {
        return population;
    }

    public double getGold() {
        return gold;
    }

    public GovernanceResource getGovernanceResource() {
        return governanceResource;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {units.remove(unit);}
    public void addBuilding(Building building) {
        buildings.add(building);
    }
    public void deleteBuilding(Building building) {
        buildings.remove(building);
    }

    public int getUnemployedPopulation() {
        return unemployedPopulation;
    }

    public void setUnemployedPopulation(int unemployedPopulation) {
        this.unemployedPopulation = unemployedPopulation;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setGovernanceResource(GovernanceResource governanceResource) {
        this.governanceResource = governanceResource;
    }

    public Building getBuildingByName(String name) {
        for (Building building: buildings) {
            if (building.getName().equals(name))
               return building;
        }
        return null;
    }

    public ArrayList<Building> getAllBuildingsByName(String name) {
        ArrayList<Building> allBuildings = new ArrayList<>();
        for (Building building: buildings) {
            if (building.getName().equals(name))
                allBuildings.add(building);
        }
        return allBuildings;
    }

    public void changeGoldAmount(int amount) {
        gold += amount;
    }

    public void changePopulation(int amount){population += amount;}

}
