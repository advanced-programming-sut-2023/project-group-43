package model.buildings;

import enums.*;
import model.Cell;
import model.User;
import model.units.Unit;

import java.util.ArrayList;

public class Building {
    private final ArrayList<Cell> cells = new ArrayList<>();
    private final User owner;
    private final BuildingName name;
    private final int hp;
    private ArrayList<Unit> units = new ArrayList<>();
    private ResourceType resourceCost;

    public Building(User owner, BuildingName name, int hp) {
        this.owner = owner;
        this.name = name;
        this.hp = hp;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public User getOwner() {
        return owner;
    }

    public BuildingName getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void hireEngineerAndWorker() {};

}







