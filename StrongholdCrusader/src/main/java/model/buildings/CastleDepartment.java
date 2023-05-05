package model.buildings;
import enums.BuildingEnums.BuildingEnum;
import model.Cell;
import model.Game;
import model.User;
import model.units.Unit;

import java.util.ArrayList;

public class CastleDepartment extends Building {

    private final int hitPoint;
    private final int peopleCapacity;
    private boolean rightEntrance;
    private boolean gateIsOpen = true;
    private String gateName;
    private CastleDepartment drawBridge;

    public CastleDepartment(String name, User owner, int hitPoint, int peopleCapacity) {
        super(name , owner);
        this.hitPoint = hitPoint;
        this.peopleCapacity = peopleCapacity;
    }

    public void reduceEnemySpeed(ArrayList<Unit> units) {
        for (Unit unit: units) {
            unit.setSpeed(unit.getSpeed() - 1);
        }
    }
    public void incearseFireRange(ArrayList<Unit> units) {
        for (Unit unit: units) {

        }
    }

    public int getHitPoint() {
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
        Building building = cell[x + 1][y].getBuilding();
        if (building instanceof CastleDepartment &&
                (((CastleDepartment) building).getName().equals(BuildingEnum.SMALL_STONE_GATEHOUSE.getName()) || ((CastleDepartment) building).getName().equals(BuildingEnum.BIG_STONE_GATEHOUSE.getName())) &&
                !((CastleDepartment)building).isRightEntrance()) {
            return (CastleDepartment) building;
        }
        building = cell[x - 1][y].getBuilding();
        if (building instanceof CastleDepartment &&
                (((CastleDepartment) building).getName().equals(BuildingEnum.SMALL_STONE_GATEHOUSE.getName()) || ((CastleDepartment) building).getName().equals(BuildingEnum.BIG_STONE_GATEHOUSE.getName())) &&
                !((CastleDepartment)building).isRightEntrance()) {
            return (CastleDepartment) building;
        }
        building = cell[x][y + 1].getBuilding();
        if (building instanceof CastleDepartment &&
                (((CastleDepartment) building).getName().equals(BuildingEnum.SMALL_STONE_GATEHOUSE.getName()) || ((CastleDepartment) building).getName().equals(BuildingEnum.BIG_STONE_GATEHOUSE.getName())) &&
                ((CastleDepartment)building).isRightEntrance()) {
            return (CastleDepartment) building;
        }
        building = cell[x][y - 1].getBuilding();
        if (building instanceof CastleDepartment &&
                (((CastleDepartment) building).getName().equals(BuildingEnum.SMALL_STONE_GATEHOUSE.getName()) || ((CastleDepartment) building).getName().equals(BuildingEnum.BIG_STONE_GATEHOUSE.getName())) &&
                ((CastleDepartment)building).isRightEntrance()) {
            return (CastleDepartment) building;
        }
        return null;
    }
}
