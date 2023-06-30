package model;

import enums.environmentEnums.Texture;
import enums.environmentEnums.TreeType;
import javafx.scene.image.Image;
import model.buildings.Building;
import model.buildings.CastleDepartment;
import model.units.Assassin;
import model.units.Spearman;
import model.units.Tunneler;
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
    private boolean illness = false;

    public boolean isIllness() {
        return illness;
    }

    public void setIllness(boolean illness) {
        this.illness = illness;
    }

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
        if (units == null) units = new ArrayList<>();
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        if (units.size() > 1)
            units.remove(unit);
        units = new ArrayList<>();
    }

    public boolean isBlocked(Unit unit) {
        if (this.building != null && !building.hasLadder() && !building.getOwner().getUsername().equals(unit.getOwner().getUsername())) {
            if (!(unit instanceof Assassin)) {
                if ((!(building instanceof CastleDepartment)) || (!((CastleDepartment) building).isHidden())) {
                    if (!(unit instanceof Tunneler)) {
                        if (!(unit.getName().equals("pikeman")) || !(building instanceof CastleDepartment))
                            return true;
                    }
                }
            }
        }
        if (this.building != null && building.hasLadder()) {
            if (!(unit instanceof Spearman || unit.getName().equals("maceman"))) {
                return true;
            }
        }
        if (this.hasRock) return true;
        if (this.texture.isPassable()) return false;
        return true;
    }
}