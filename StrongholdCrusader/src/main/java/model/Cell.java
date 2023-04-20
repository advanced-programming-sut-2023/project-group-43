package model;

import enums.Texture;
import enums.TreeType;
import enums.WaterArea;
import model.buildings.Building;
import model.units.Unit;

import java.util.ArrayList;

public class Cell {
    private Texture texture;

    private Building building;

    private TreeType treeType;

    private boolean hasRock;

    private WaterArea waterArea;

    private ArrayList<Unit> units = new ArrayList<>();

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public boolean HasRock() {
        return hasRock;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

    public void setTreeType(TreeType treeType) {
        this.treeType = treeType;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void setWaterArea(WaterArea waterArea) {
        this.waterArea = waterArea;
    }

    public WaterArea getWaterArea() {
        return waterArea;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {

    }
}
