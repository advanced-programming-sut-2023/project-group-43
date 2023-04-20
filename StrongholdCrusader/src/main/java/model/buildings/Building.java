package model.buildings;

import enums.*;
import model.Cell;
import model.User;
import model.units.Unit;

import java.util.ArrayList;

public class Building {
    private String name;
    private User owner;
    private BuildingType buildingType;
    private ArrayList<Unit> units = new ArrayList<>();
    private static ArrayList<Building>buildings = new ArrayList<>();

    public void hireEngineerAndWorker() {};
    public void addUnit(Unit unit){
        units.add(unit);
    };
    public void addBuilding(Building building){
        buildings.add(building);
    };
    public User getOwner() {
        return owner;
    }
    public BuildingName getName()
    public ArrayList<Unit> getUnits() {
        return units;
    }

}







