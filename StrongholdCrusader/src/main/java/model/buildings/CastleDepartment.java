package model.buildings;
import enums.BuildingEnums.BuildingEnum;
import model.Cell;
import model.Game;
import model.User;
import model.units.Unit;

import java.util.ArrayList;

public class CastleDepartment extends Building {

    private final double hitPoint;
    private final int peopleCapacity;
    private boolean rightEntrance;
    private boolean gateIsOpen = true;

    private boolean isHidden = false;

    private double defendRange;
    private double fireRange;
    private String gateName;
    private CastleDepartment drawBridge;

    public CastleDepartment(String name, User owner, int peopleCapacity, int defendRange, int fireRange) {
        super(name , owner);
        this.hitPoint = BuildingEnum.getBuildingStructureByName(name).getHp();
        this.peopleCapacity = peopleCapacity;
        this.defendRange = defendRange;
        this.fireRange = fireRange;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void reduceEnemySpeed(ArrayList<Unit> units) {
        for (Unit unit: units) {
            if (!this.getOwner().equals(unit.getOwner()))
                unit.setSpeed(unit.getSpeed() - 1);//TODO
        }
    }
    public void attackEnemy(Game game, int x, int y) {
        int[][] array = {{1, 0, -1, 0, -1, -1, 1, 1}, {0, 1, 0, -1, -1, 1, -1, 1}};
        Cell[][] cells = game.getCells();
        for (int i = 0; i < 8; i++) {
            if (x + array[0][i] > 0 && x + array[0][i] < game.getRow() && y + array[1][i] > 0 && y + array[1][i] < game.getColumn()) {
                for (Unit unit: cells[x + array[0][i]][y + array[1][i]].getUnits()) {
                    if (!unit.getOwner().equals(game.getCurrentPlayer())) {
                        unit.setHitPoint(unit.getHitPoint() - this.defendRange);
                    }
                }
            }
        }
    }

    public double getHitPoint() {
        return hitPoint;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public boolean isRightEntrance() {
        return rightEntrance;
    }

    public void setRightEntrance(boolean rightEntrance) {
        this.rightEntrance = rightEntrance;
    }

    public boolean isGateIsOpen() {
        return gateIsOpen;
    }

    public void gateIsOpen(boolean gateIsOpen) {
        this.gateIsOpen = gateIsOpen;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public CastleDepartment getDrawBridge() {
        return drawBridge;
    }

    public void setDrawBridge(CastleDepartment drawBridge) {
        this.drawBridge = drawBridge;
    }

    public void openOrCloseGatehouse(boolean openIt) {
        this.gateIsOpen(openIt);
        this.drawBridge.gateIsOpen(openIt);
    }
    public CastleDepartment dropDrawBridge (Game game, int x, int y) {
        Cell[][] cell = game.getCells();
        int[][] array = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        for (int i = 0; i < 4; i++) {
            if (x + array[0][i] > 0 && x + array[0][i] < game.getRow() && y + array[1][i] > 0 && y + array[1][i] < game.getColumn()) {
                Building building = cell[x + array[0][i]][y + array[1][i]].getBuilding();
                if (building instanceof CastleDepartment &&
                        (building.getName().equals(BuildingEnum.SMALL_STONE_GATEHOUSE.getName()) || building.getName().equals(BuildingEnum.BIG_STONE_GATEHOUSE.getName())) &&
                        !((CastleDepartment) building).isRightEntrance()) {
                    return (CastleDepartment) building;
                }
            }
        }
        return null;
    }
}
