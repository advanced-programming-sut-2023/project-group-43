package model.units;

import controller.GameController;
import enums.unitEnums.UnitState;
import enums.unitEnums.UnitsEnum;
import model.Cell;
import model.User;

import java.util.ArrayList;

public class Unit {
    private User owner;
    private UnitsEnum unit;
    private Cell cell;

    private Cell previousCell;
    private String name;
    private double hitPoint;
    private double damage;
    private double defense;
    private double speed;
    private double cost;

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

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public UnitsEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitsEnum unit) {
        this.unit = unit;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(double hitPoint) {
        this.hitPoint = hitPoint;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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

    public UnitState getState() {
        return state;
    }

    public void setState(UnitState state) {
        this.state = state;
    }

    public void move(GameController gameController) {
        if (currentTargetX >= 0 && currentTargetY >= 0) {
            ArrayList<Cell> path = gameController.findPath(currentTargetX, currentTargetY, this);
            System.out.println(path.size());
            for (Cell cell: path) {
                System.out.println("x: " + cell.getX());
                System.out.println("y: " + cell.getY());
            }
            if (path != null)  {
                if (speed <= path.size() - 1) {
                    cell.removeUnit(this);
                    path.get((int) speed).addUnit(this);
                    cell = path.get((int) speed);
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


}}


