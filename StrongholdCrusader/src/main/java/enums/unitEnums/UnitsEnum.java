package enums.unitEnums;

import enums.Degrees.DefenseDegree;
import enums.Degrees.SpeedDegree;
import enums.ImageEnum;
import javafx.scene.image.Image;

public enum UnitsEnum {
    //lord
    LORD("lord", "lord", 2, DefenseDegree.HIGH.getDegree(), SpeedDegree.AVERAGE.getDegree(), 0, ImageEnum.LORD.getImage()),
    //worker
    WORKER("worker", "worker", 1, DefenseDegree.VERY_VERY_LOW.getDegree(), SpeedDegree.AVERAGE.getDegree(), 0, ImageEnum.WORKER.getImage()),
    //armed
    ARCHER("archer", "armed", 2, DefenseDegree.LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 10, ImageEnum.ARCHER.getImage()),
    CROSSBOWMAN("crossbowman", "armed", 1, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.LOW.getDegree(), 20, ImageEnum.CROSSBOWMAN.getImage()),
    SWORDSMAN("swordsman", "armed", 2, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.VERY_LOW.getDegree(), 10, ImageEnum.SWORDSMAN.getImage()),
    ARCHER_BOW("archer bow", "armed", 1, DefenseDegree.LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 20, ImageEnum.ARCHER_BOW.getImage()),
    BLACKMONK("black monk", "armed", 1, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.LOW.getDegree(), -1, ImageEnum.BLACKMONK.getImage()),
    SLAVE("slave", "armed", 2, DefenseDegree.VERY_VERY_LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 20, ImageEnum.SLAVE.getImage()),
    SLINGER("slinger", "armed", 1, DefenseDegree.LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 30, ImageEnum.SLINGER.getImage()),
    HORSE_ARCHER("horse archer", "armed", 1, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 30, ImageEnum.HORSE_ARCHER.getImage()),
    ARABIAN_SWORDSMAN("arabian swordsman", "armed", 1, DefenseDegree.HIGH.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 20, ImageEnum.ARABIAN_SWORDSMAN.getImage()),
    FIRE_THROWER("fire thrower", "armed", 1, DefenseDegree.LOW.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 20, ImageEnum.FIRE_THROWER.getImage()),
    //unarmed
    PIKEMAN("pikeman", "unarmed", 1, DefenseDegree.HIGH.getDegree(), SpeedDegree.HIGH.getDegree(), 20, ImageEnum.PIKEMAN.getImage()),
    MACEMAN("maceman", "unarmed", 2, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.AVERAGE.getDegree(), 20, ImageEnum.MACEMAN.getImage()),
    KNIGHT("knight", "unarmed", 2, DefenseDegree.HIGH.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 40, ImageEnum.KNIGHT.getImage()),
    //tunneler
    TUNNELER("tunneler", "tunneler", 1, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 20, ImageEnum.TUNNLER.getImage()),
    //spearman
    SPEARMAN("spearman", "spearman", 1, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.AVERAGE.getDegree(), 10, ImageEnum.SPEARMAN.getImage()),
    //ladderman
    LADDERMAN("ladderman", "ladderman", 1, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 20, ImageEnum.LADDERMAN.getImage()),
    //engineer
    ENGINEER("engineer", "engineer", 1, DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.AVERAGE.getDegree(), 30, ImageEnum.ENGINNER.getImage()),
    //assassin
    ASSASSIN("assassin", "assassin", 1, DefenseDegree.AVERAGE.getDegree(), SpeedDegree.AVERAGE.getDegree(), 30, ImageEnum.ASSASSIN.getImage()),
    //dog
    DOG("dog", "dog", 1, DefenseDegree.HIGH.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 5, ImageEnum.DOG.getImage());
    private final String name;
    private final String type;
    private final double hitPoint;
    private final double defense;
    private final double speed;
    private double cost;
    private Image image;

    UnitsEnum(String name, String type, double hitPoint, double defense, double speed, double cost, Image image) {
        this.type = type;
        this.name = name;
        this.hitPoint = hitPoint;
        this.defense = defense;
        this.speed = speed;
        this.cost = cost;
        this.image = image;
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
        if (unitName.matches("engineer"))
            return "engineer";
        if (unitName.matches("assassin"))
            return "assassin";
        if (unitName.matches("ladderman"))
            return "ladderman";
        return null;
    }

}
