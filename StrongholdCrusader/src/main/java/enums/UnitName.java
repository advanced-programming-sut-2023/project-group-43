package enums;

public enum UnitName {
    ;
    private int hitPoint;
    private int damage;
    private int defense;
    private int speed;
    private int cost;
    boolean hasHorse;
    boolean hasLadder;
    boolean canHide;
    UnitWeapon weapon;

    UnitName(int hitPoint, int damage, int defense, int speed,
             int cost, boolean hasHorse, boolean hasLadder, boolean canHide, UnitWeapon weapon) {
        this.hitPoint = hitPoint;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.cost = cost;
        this.hasHorse = hasHorse;
        this.hasLadder = hasLadder;
        this.canHide = canHide;
        this.weapon = weapon;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getDamage() {
        return damage;
    }

    public int getCost() {
        return cost;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public UnitWeapon getWeapon() {
        return weapon;
    }

    public boolean HasHorse() {
        return hasHorse;
    }

    public boolean CanHide() {
        return canHide;
    }

    public boolean HasLadder() {
        return hasLadder;
    }
}
