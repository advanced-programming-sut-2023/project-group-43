package model;

import enums.environmentEnums.Texture;
import enums.environmentEnums.TreeType;
import model.buildings.Building;
import model.buildings.CastleDepartment;
import model.units.Unit;

import java.util.ArrayList;

public class Cell {
    private Texture texture;

    private Building building;

    private TreeType treeType;

    private boolean hasRock;
    private String rockDirection;
    private int cellSize;
    private boolean tunelHere = false;

    private int x;
    private int y;

    public boolean isTunelHere() {
        return tunelHere;
    }

    public void setTunelHere(boolean tunelHere) {
        this.tunelHere = tunelHere;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

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

    public String getRockDirection() {
        return rockDirection;
    }

    public void setRockDirection(String rockDirection) {
        this.rockDirection = rockDirection;
    }

    public void setTreeType(TreeType treeType) {
        this.treeType = treeType;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    public boolean isBlocked() {
        if (this.building != null && !building.HasLadder()) {
            if ((!(building instanceof CastleDepartment)) || (!((CastleDepartment) building).isHidden()))
            return true;
        }
        if (this.hasRock) return true;
        if (this.texture.isPassable()) return false;
        return true;
    }
}