package enums.unitEnums;

import enums.BuildingEnums.BuildingEnum;

public enum UnitsEnum {
    //TODO : I defined 0 as average and -2 -1 --- > 1 2 and -10 as null
    ARCHER("archer" , -1 , -1 , 1 , 0),
    CROSSBOWMEN("crossbowmen",-1,0 , -1 , 0),
    SPEARMEN("spearmen",0,-2,0,0),
    PIKEMEN("pikemen" ,0,2,1,0),
    MACEMEN("macemen",2,0,0,0),
    SWORDSMEN("swordsmen",2,-2,-2,0),
    KNIGHT("knight" ,2,1,2,0),
    TUNNELER("tunneler",0,-1,2,0),
    Laddermen("laddermen" ,-10,-2,1,0),
    ENGINEER("engineer", -10,-2,0,0),
    BLACKMONK("black monk", 0 , 0 , -1 , 0),
    ARCHER_BOW("archer bow" , -1,-1,1,0),
    SLAVE("slave" , -2,-3,1,0),
    SLINGER("slinger" ,-1,-2,1,0),
    ASSASSIN("assassin",0,0,0,0),
    HORSE_ARCHER("horse archer",-1,0,2,0),
    ARABIAN_SWORDSMEN("arabian swordsmen" , 1,1,2,0),
    FIRE_THROWER("fire thrower",1,-1,2 ,0 )

    ;
    private String name;
    private int hitPoint;
    private int defense;
    private int speed;
    private int cost;

    UnitsEnum(String name, int hitPoint, int defense, int speed, int cost) {
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

    public static UnitsEnum getBuildingStructureByName(String name) {
        for(UnitsEnum value : UnitsEnum.values())
            if(value.equals(name))
                return value;
        return null;
    }
}
