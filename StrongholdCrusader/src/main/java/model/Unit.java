package model;

import enums.UnitState;
import enums.UnitName;
import enums.UnitType;
import enums.UnitWeapon;

public class Unit {
    private User owner;
    private UnitName name;

    private UnitType type;

    private Cell cell;
    private int hitPoint;
    private int damage;
    private int defense;
    private int speed;
    private int cost;

    private boolean hasHorse;
    private boolean hasLadder;
    private boolean canHide;
    private UnitWeapon weapon;

    private UnitState state;

    public Unit(User owner, UnitName name) {
        this.owner = owner;
        this.name = name;
        this.hitPoint = name.getHitPoint();
        this.damage = name.getDamage();
        this.defense = name.getDefense();
        this.speed = name.getSpeed();
        this.cost = name.getCost();
        this.hasHorse = name.HasHorse();
        this.hasLadder = name.HasLadder();
        this.canHide = name.CanHide();
        this.weapon = name.getWeapon();
    }

    public User getOwner() {
        return owner;
    }

    public UnitName getName() {
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


    public boolean HasHorse() {
        return hasHorse;
    }

    public boolean HasLadder() {
        return hasLadder;
    }

    public boolean CanHide() {
        return canHide;
    }


    public UnitWeapon getWeapon() {
        return weapon;
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

    public void setType(UnitType type) {
        this.type = type;
    }

    public UnitType getType() {
        return type;
    }

    public void attack() {}
    public void defense() {}
    public void shoot() {}
    public void move() {}
    public void digDitch() {}
    public void climbWall() {}
    public void hide() {}
}


