package model.buildings;
import enums.BuildingEnums.BuildingEnum;
import model.Cell;
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

    private Cell cell;

    //building
    private ArrayList<Unit> units = new ArrayList<>();

    public Building(String name, User owner) {
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
    public String getName(){return name;}
    public User getOwner() {
        return owner;
    }
    public ArrayList<Unit> getUnits() {
        return units;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getGold() {
        return gold;
    }

    public int getHp() {
        return hp;
    }

    public int getLadderlans() {
        return ladderlans;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}







