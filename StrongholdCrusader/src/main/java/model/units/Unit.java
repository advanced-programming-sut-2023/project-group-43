package model.units;

import enums.unitEnums.UnitState;
import enums.unitEnums.Units;
import model.Cell;
import model.User;

public class Unit {
    private User owner;
    private Units unit;
    private String type;
    private Cell cell;
    private int hitPoint;
    private int damage;
    private int defense;
    private int speed;
    private int cost;
    private boolean hasLadder;

    private UnitState state;

    public Unit(User owner, Units unit) {
        this.owner = owner;
        this.unit = unit;
        this.hitPoint = name.getHitPoint();
        this.defense = name.getDefense();
        this.speed = name.getSpeed();
        this.cost = name.getCost();
        this.hasLadder = name.HasLadder();
    }

    public User getOwner() {
        return owner;
    }

    public Units getName() {
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


    public boolean HasLadder() {
        return hasLadder;
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


