package model.buildings;

import model.User;

public class CastleDepartment extends Building {

    private final int hitPoint;
    private final int peopleCapacity;
    public CastleDepartment(User owner, BuildingName name, int hp, int hitPoint, int peopleCapacity) {
        super(owner, name, hp);
        this.hitPoint = hitPoint;
        this.peopleCapacity = peopleCapacity;
    }

    public void reduceEnemySpeed() {};
    public void attackEnemy() {};

    public int getHitPoint() {
        return hitPoint;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }
}
