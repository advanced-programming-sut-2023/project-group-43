package model;

import enums.BuildingName;
import model.buildings.Building;
import model.units.Unit;

import java.util.ArrayList;

public class Governance {
    private int popularity;

    private int population;
    private int foodRate;
    private int taxRate;
    private int fearRate;

    private ArrayList<Unit> units = new ArrayList<>();

    private ArrayList<Building> buildings = new ArrayList<>();

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getPopulation() {
        return population;
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

    public void removeUnit(Unit unit) {

    }
    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public Building findBuilding(BuildingName name) {
        for(Building building: buildings) {
            if(building.getName().equals(name)) {
                return building;
            }
        }
        return null;
    }
}
