package model.units;

import enums.unitEnums.UnitState;
import enums.unitEnums.UnitsEnum;
import model.Cell;
import model.User;

public class Unit {
    private User owner;
    private UnitsEnum unit;
    private Cell cell;
    private String name;
    private int hitPoint;
    private int damage;
    private int defense;
    private int speed;
    private int cost;
    private boolean isHidden = false;

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

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
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

    public void defense() {}
    public void move() {}
    public void digDitch() {}
    public void climbWall() {}

}


