package model.units;

import controller.GameController;
import enums.unitEnums.UnitState;
import enums.unitEnums.UnitsEnum;
import model.Cell;
import model.Game;
import model.User;

import java.util.ArrayList;

public class Unit {
    private User owner;
    private UnitsEnum unit;
    private Cell cell;

    private Cell previousCell;
    private String name;
    private int hitPoint;
    private int damage;
    private int defense;
    private int speed;
    private int cost;

    private int currentTargetX = -1;
    private int currentTargetY = -1;
    private int nextTargetX = -1;
    private int nextTargetY = -1;

    private UnitState state;

    public Unit(User owner,String name) {
        unit = UnitsEnum.getUnitByName(name);
        this.owner = owner;
        this.name = name;
        this.hitPoint = unit.getHitPoint();
        this.defense = unit.getDefense();
        this.speed = unit.getSpeed();
        this.cost = unit.getCost();
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCost() {
        return cost;
    }

    public UnitState getState() {
        return state;
    }

    public void setState(UnitState state) {
        this.state = state;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Cell getPreviousCell() {
        return previousCell;
    }

    public void setPreviousCell(Cell previousCell) {
        this.previousCell = previousCell;
    }

    public int getCurrentTargetX() {
        return currentTargetX;
    }

    public void setCurrentTargetX(int currentTargetX) {
        this.currentTargetX = currentTargetX;
    }

    public int getCurrentTargetY() {
        return currentTargetY;
    }

    public void setCurrentTargetY(int currentTargetY) {
        this.currentTargetY = currentTargetY;
    }

    public int getNextTargetX() {
        return nextTargetX;
    }

    public void setNextTargetX(int nextTargetX) {
        this.nextTargetX = nextTargetX;
    }

    public int getNextTargetY() {
        return nextTargetY;
    }

    public void setNextTargetY(int nextTargetY) {
        this.nextTargetY = nextTargetY;
    }

    public void move(GameController gameController) {
        if (currentTargetX >= 0 && currentTargetY >= 0) {
            ArrayList<Cell> path = gameController.findPath(cell.getX(), cell.getY(), currentTargetX, currentTargetY, this);
            if (path != null)  {
                if (speed <= path.size() - 1) {
                    cell.removeUnit(this);
                    path.get(speed).addUnit(this);
                    cell = path.get(speed);
                } else {
                    cell.removeUnit(this);
                    path.get(path.size() - 1).addUnit(this);
                    cell = path.get(path.size() - 1);
                }
            }
            if (currentTargetY == cell.getX() && currentTargetY == cell.getY()) {
                if (nextTargetX == -1 && nextTargetX == -1) {
                    currentTargetY = -1;
                    currentTargetX = -1;
                } else {
                    int temp = currentTargetX;
                    currentTargetX = nextTargetX;
                    nextTargetX = currentTargetX;
                    temp = currentTargetY;
                    currentTargetY = nextTargetY;
                    nextTargetY = currentTargetY;
                }
            }
        }
    }

}


