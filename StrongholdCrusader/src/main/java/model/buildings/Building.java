package model.buildings;
import enums.BuildingEnum;
import model.User;
import model.units.Unit;

import java.util.ArrayList;

public class Building {
    private final User owner;
    private final String name;
    private final int wood;
    private final int stone;
    private final int gold;
    private final int hp;
    private final int ladderlans;

    //building
    private ArrayList<Unit> units = new ArrayList<>();
    private static ArrayList<Building>buildings = new ArrayList<>();

    public Building(String name, User owner ) {
        this.name = name;
        this.owner = owner;
        this.wood = BuildingEnum.getBuildingStructureByName(name).getWood();
        this.stone = BuildingEnum.getBuildingStructureByName(name).getStone();
        this.gold = BuildingEnum.getBuildingStructureByName(name).getGold();
        this.hp = BuildingEnum.getBuildingStructureByName(name).getHp();
        this.ladderlans = BuildingEnum.getBuildingStructureByName(name).getLaddernans();
    }

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

    public static ArrayList<Building> getBuildings() {
        return buildings;
    }
}







