package enums.unitEnums;

import enums.BuildingEnums.BuildingEnum;
import enums.Degrees.DefenseDegree;
import enums.Degrees.SpeedDegree;

public enum UnitsEnum {
    //TODO : I defined 0 as average and -2 -1 --- > 1 2 and -10 as null
    ARCHER("archer","armed" ,-2, DefenseDegree.LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 0),
    CROSSBOWMAN("crossbowman","armed",-1,DefenseDegree.AVERAGE.getDegree() , SpeedDegree.LOW.getDegree(),  0),
    SPEARMAN("spearman","spearman",0,DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.AVERAGE.getDegree(), 0),
    PIKEMAN("pikeman" ,"unarmed",0,DefenseDegree.HIGH.getDegree(), SpeedDegree.HIGH.getDegree(), 0),
    MACEMAN("maceman","unarmed",2,DefenseDegree.AVERAGE.getDegree(), SpeedDegree.AVERAGE.getDegree(), 0),
    SWORDSMAN("swordsman","armed",2,DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.VERY_LOW.getDegree(), 0),
    KNIGHT("knight" , "unarmed",2,DefenseDegree.HIGH.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 0),
    TUNNELER("tunneler","tunneler",0,DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 0),
    LADDERMAN("ladderman" ,"ladderman",-10,DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 0),
    ENGINEER("engineer","engineer",-10,DefenseDegree.VERY_LOW.getDegree(), SpeedDegree.AVERAGE.getDegree(), 0),
    BLACKMONK("black monk","armed" ,0 , DefenseDegree.AVERAGE.getDegree(),SpeedDegree.LOW.getDegree(), -1 ),
    ARCHER_BOW("archer bow" ,"armed", -1,DefenseDegree.LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 0),
    SLAVE("slave" , "armed",-2,DefenseDegree.VERY_VERY_LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 0),
    SLINGER("slinger" ,"armed",-1,DefenseDegree.LOW.getDegree(), SpeedDegree.HIGH.getDegree(), 0),
    ASSASSIN("assassin","assassin",0,DefenseDegree.AVERAGE.getDegree(), SpeedDegree.AVERAGE.getDegree(), 0),
    HORSE_ARCHER("horse archer", "armed",-1,DefenseDegree.AVERAGE.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 0),
    ARABIAN_SWORDSMAN("arabian swordsman" ,"armed",1,DefenseDegree.HIGH.getDegree(), SpeedDegree.VERY_HIGH.getDegree(), 0),
    FIRE_THROWER("fire thrower","armed",1,DefenseDegree.LOW.getDegree(), SpeedDegree.VERY_HIGH.getDegree(),0 )

    ;
    private String name;
    private String type;
    private int hitPoint;
    private int defense;
    private int speed;
    private int cost;

    UnitsEnum(String name,String type, int hitPoint, int defense, int speed, int cost) {
        this.type = type;
        this.name = name;
        this.hitPoint = hitPoint;
        this.defense = defense;
        this.speed = speed;
        this.cost = cost;
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

}
