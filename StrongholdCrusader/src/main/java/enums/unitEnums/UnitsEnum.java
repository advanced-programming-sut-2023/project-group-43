package enums.unitEnums;

import enums.Degrees.DefenseDegree;
import enums.Degrees.SpeedDegree;

public enum UnitsEnum {
    //lord
    LORD("lord", "lord", 2, DefenseDegree.HIGH.getDegree(), SpeedDegree.AVERAGE.getDegree(), 0),
    //worker
    WORKER("worker", "worker", 1, DefenseDegree.VERY_VERY_LOW.getDegree(), SpeedDegree.AVERAGE.getDegree(), 0),
    //armed
    ARCHER("archer", "armed", -2, DefenseDegree.LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 10),
    CROSSBOWMAN("crossbowman", "armed", -1, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.LOW.getDegree(), 20),
    SWORDSMAN("swordsman", "armed", 2, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.VERY_LOW.getDegree(), 10),
    ARCHER_BOW("archer bow", "armed", -1, DefenseDegree.LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 20),
    BLACKMONK("black monk", "armed", 0, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.LOW.getDegree(), -1),
    SLAVE("slave", "armed", -2, DefenseDegree.VERY_VERY_LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 20),
    SLINGER("slinger", "armed", -1, DefenseDegree.LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 30),
    HORSE_ARCHER("horse archer", "armed", -1, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 30),
    ARABIAN_SWORDSMAN("arabian swordsman", "armed", 1, DefenseDegree.HIGH.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 20),
    FIRE_THROWER("fire thrower", "armed", 1, DefenseDegree.LOW.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 20),
    //unarmed
    PIKEMAN("pikeman", "unarmed", 0, DefenseDegree.HIGH.getDegree(), SpeedDegree.HIGH.getDegree(), 20),
    MACEMAN("maceman", "unarmed", 2, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.AVERAGE.getDegree(), 20),
    KNIGHT("knight", "unarmed", 2, DefenseDegree.HIGH.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 40),
    //tunneler
    TUNNELER("tunneler", "tunneler", 0, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 20),
    //spearman
    SPEARMAN("spearman", "spearman", 0, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.AVERAGE.getDegree(), 10),
    //ladderman
    LADDERMAN("ladderman", "ladderman", -10, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 20),
    //engineer
    ENGINEER("engineer", "engineer", -10, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.AVERAGE.getDegree(), 30),
    //assassin
    ASSASSIN("assassin", "assassin", 1, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.AVERAGE.getDegree(), 30),
    //dog
    DOG("dog", "dog", 0, DefenseDegree.HIGH.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 5);
    private final String name;
    private final String type;
    private final double hitPoint;
    private final double defense;
    private final double speed;
    private double cost;

    UnitsEnum(String name, String type, double hitPoint, double defense, double speed, double cost) {
        this.type = type;
        this.name = name;
        this.hitPoint = hitPoint;
        this.defense = defense;
        this.speed = speed;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getHitPoint() {
        return hitPoint;
    }

    public double getCost() {
        return cost;
    }

    public double getDefense() {
        return defense;
    }

    public double getSpeed() {
        return speed;
    }


    public static UnitsEnum getUnitByName(String name) {
        for (UnitsEnum value : UnitsEnum.values())
            if (value.getName().equals(name))
                return value;
        return null;
    }

    //TODO :  we might have a better place for this function -> getTypeByUnitName
    public static String getTypeByUnitName(String unitName) {
        if (unitName.matches("lord"))
            return "lord";
        if (unitName.matches("worker"))
            return "worker";
        if (unitName.matches("(archer)|(crossbowman)|(swordsman)|(archer bow)|(black monk)|(slave)|(slinger)|(horse archer)|(arabian swordsman)|(fire thrower)"))
            return "armed";
        if (unitName.matches("(pikeman)|(maceman)|(knight)"))
            return "unarmed";
        if (unitName.matches("spearman"))
            return "spearman";
        if (unitName.matches("tunneler"))
            return "tunneler";
        if (unitName.matches("enginner"))
            return "engineer";
        if (unitName.matches("assassin"))
            return "assassin";
        if (unitName.matches("ladderman"))
            return "ladderman";
        return null;
    }

}
