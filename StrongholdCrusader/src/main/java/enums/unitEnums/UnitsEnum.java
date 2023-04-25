package enums.unitEnums;

import enums.BuildingEnums.BuildingEnum;

public enum UnitsEnum {
    //TODO : I defined 0 as average and -2 -1 --- > 1 2 and -10 as null
    ARCHER("archer","armed" ,-1 , -1 , 1 , 0),
    CROSSBOWMAN("crossbowman","armed",-1,0 , -1 , 0),
    SPEARMAN("spearman","spearman",0,-2,0,0),
    PIKEMAN("pikeman" ,"unarmed",0,2,1,0),
    MACEMAN("maceman","unarmed",2,0,0,0),
    SWORDSMAN("swordsman","armed",2,-2,-2,0),
    KNIGHT("knight" , "unarmed",2,1,2,0),
    TUNNELER("tunneler","tunneler",0,-1,2,0),
    LADDERMAN("ladderman" ,"ladderman",-10,-2,1,0),
    ENGINEER("engineer","engineer",-10,-2,0,0),
    BLACKMONK("black monk","armed" ,0 , 0 , -1 , 0),
    ARCHER_BOW("archer bow" ,"armed", -1,-1,1,0),
    SLAVE("slave" , "armed",-2,-3,1,0),
    SLINGER("slinger" ,"armed",-1,-2,1,0),
    ASSASSIN("assassin","assassin",0,0,0,0),
    HORSE_ARCHER("horse archer", "armed",-1,0,2,0),
    ARABIAN_SWORDSMAN("arabian swordsman" ,"armed",1,1,2,0),
    FIRE_THROWER("fire thrower","armed",1,-1,2 ,0 )

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

    public UnitsEnum getUnitStructureByName(String name) {
        for(UnitsEnum value : UnitsEnum.values())
            if(value.equals(name))
                return value;
        return null;
    }
}
