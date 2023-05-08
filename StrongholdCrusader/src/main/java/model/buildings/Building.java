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
    private final int cost;
    private int hp;
    private final int ladderlans;

    private Cell cell;

    //building
    private boolean hasLadder = false;

    public boolean hasLadder() {
        return hasLadder;
    }
    public void setLadder(boolean hasLadder) {
        this.hasLadder = hasLadder;
    }
    private ArrayList<Unit> units = new ArrayList<>();

    public Building(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.wood = BuildingEnum.getBuildingStructureByName(name).getWood();
        this.stone = BuildingEnum.getBuildingStructureByName(name).getStone();
        this.cost = BuildingEnum.getBuildingStructureByName(name).getCost();
        this.hp = BuildingEnum.getBuildingStructureByName(name).getHp();
        this.ladderlans = BuildingEnum.getBuildingStructureByName(name).getWorkerNumber();
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

    public int getCost() {
        return cost;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
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







