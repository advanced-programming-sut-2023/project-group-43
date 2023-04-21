package model.buildings;
import enums.Buildings;
import model.User;

public class CastleDepartment extends Building {

    private final int hitPoint;
    private final int peopleCapacity;
    private Buildings buildings;
    public CastleDepartment(String name , User owner, Buildings buildings, int hitPoint, int peopleCapacity) {
        super(name , owner , buildings);
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
