package enums.unitEnums;

public enum Units {
    ;
    private int hitPoint;
    private int defense;
    private int speed;
    private int cost;
    boolean hasLadder;

    Units(int hitPoint, int defense, int speed, int cost, boolean hasLadder) {
        this.hitPoint = hitPoint;
        this.defense = defense;
        this.speed = speed;
        this.cost = cost;
        this.hasLadder = hasLadder;
    }

    public int getHitPoint() {
        return hitPoint;
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

    public boolean HasLadder() {
        return hasLadder;
    }
}
