package model.buildings;

import enums.BuildingEnums.BuildingStructure;
import model.User;
import model.units.Unit;

import java.util.ArrayList;

public class Building {
    private String name;
    private User owner;
    //building
    private BuildingStructure buildingStructure;
    private ArrayList<Unit> units = new ArrayList<>();
    private static ArrayList<Building>buildings = new ArrayList<>();

    public Building(String name, User owner, BuildingStructure buildingStructure) {
        this.name = name;
        this.owner = owner;
        this.buildingStructure = buildingStructure;
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

}







