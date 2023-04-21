package model.buildings;
import enums.Buildings;
import model.User;
import model.units.Unit;

import java.util.ArrayList;

public class Building {
    private final String name;
    private final User owner;
    //building
    private final Buildings buildings;
    private ArrayList<Unit> units = new ArrayList<>();
    private static ArrayList<Building>buildings = new ArrayList<>();

    public Building(String name, User owner, Buildings buildings) {
        this.name = name;
        this.owner = owner;
        this.buildings = buildings;
    }

    public void hireEngineerAndWorker() {};
    public void addUnit(Unit unit){
        units.add(unit);
    };
    public void addBuilding(Building building){
        buildings.add(building);
    };
    public String getName(){return name;}
    public User getOwner() {
        return owner;
    }
    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Buildings getBuildingStructure() {
        return buildings;
    }

    public static ArrayList<Building> getBuildings() {
        return buildings;
    }
}







