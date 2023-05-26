package enums.BuildingEnums;

public enum BuildingEnum {
    //<<<----------castle department---------->>>
    //------> headquarter
    HEADQUARTER("headquarter", 0, 0, 0, 600, 0),
    //------> 1) gatehouses
    SMALL_STONE_GATEHOUSE("small stone gatehouse", 0, 0, 100, 0, 0),
    BIG_STONE_GATEHOUSE("big stone gatehouse", 0, 20, 0, 200, 0),
    //------> 2) bridge
    DRAW_BRIDGE("draw bridge", 10, 0, 0, 100, 0),
    //------> 3) towers
    LOOKOUT_TOWER("lookout tower", 0, 10, 0, 300, 0),
    PERIMETER_TOWER("perimeter tower", 0, 10, 0, 300, 0),
    DEFENSEIVE_TURRET("defensive tower", 0, 15, 0, 400, 0),
    SQUARE_TOWER("square tower", 0, 35, 0, 300, 0),
    CIRCLE_TOWER("circle tower", 0, 40, 0, 300, 0),
    //<<<----------keeps and strongholds---------->>>
    SIEGE_TENT("siege tent", 0, 0, 0, 500, 0),
    CAGED_WAR_DOGS("caged war dogs", 10, 0, 100, 400, 0),
    PITCH_DITCH("pitch ditch", 0, 0, 0, 500, 0),
    OIL_SMELTER("oil smelter", 0, 10, 100, 400, 1),
    //<<<----------converter---------->>>
    //part 1
    //------> 1) foods
    WHEAT_FARM("wheat farm", 15, 0, 0, 100, 1),
    HOP_FARM("hop farm", 15, 0, 0, 100, 1),
    HUNTING_POST("hunting post", 5, 0, 0, 200, 1),
    APPLE_GARDEN("apple garden", 5, 0, 0, 100, 1),
    //------> 2) animals
    STABLE("stable", 10, 0, 400, 200, 0),
    //------> 3) humans
    HOVEL("hovel", 6, 0, 0, 100, 0),
    //-------> 4) items
    WOOD_CUTTER("wood cutter", 3, 0, 0, 200, 1),
    PITCH_RIG("pitch rig", 20, 0, 0, 400, 1),
    QUARRY("quarry", 20, 0, 0, 400, 3),
    IRON_MINE("iron mine", 20, 0, 0, 400, 2),
    //part 2
    //------> 1) humans
    BARRACK("barrack", 0, 15, 0, 300, 0),
    //------> 2) foods
    BAKERY("bakery", 10, 0, 0, 100, 1),
    DAIRY_PRODUCTS("dairy products", 10, 0, 0, 100, 1),
    BEER_BREWING("beer brewing", 10, 0, 0, 100, 1),
    MILL("mill", 20, 0, 0, 200, 3),
    //------> 3) weapons
    POLETURNER("poleturner", 10, 0, 100, 100, 1),
    FLETCHER("fletcher", 20, 0, 20, 150, 1),
    BLACKSMITH("blacksmith", 20, 0, 100, 200, 1),
    ARMOURER("armourer", 20, 0, 100, 200, 1),
    //<<<----------storage---------->>>
    FOOD_STOCKPILE("food stockpile", 5, 0, 0, 100, 0),
    STOCKPILE("stockpile", 0, 0, 0, 100, 0),
    ARMOURY("armoury", 5, 0, 0, 100, 0),
    //<<<----------popularity booster---------->>>
    CHURCH("church", 0, 0, 250, 200, 0),
    CATHEDRAL("cathedral", 0, 0, 1000, 200, 0),
    INN("inn", 20, 0, 100, 200, 1),
    //<<<----------store---------->>>
    MARKET("market", 5, 0, 0, 100, 1),
    ENGINEER_GUILD("engineer guild", 10, 0, 100, 200, 0),
    MERCENARY_POST("mercenary post", 10, 0, 0, 200, 0),
    //none
    OX_TETHER("ox tether", 5, 0, 0, 50, 1),
    ;
    private String name;
    private int wood;
    private int stone;
    private int cost;
    private int hp;
    private int workerNumber;

    BuildingEnum(String name, int wood, int stone, int cost, int hp, int workNumber) {
        this.name = name;
        this.wood = wood;
        this.stone = stone;
        this.cost = cost;
        this.hp = hp;
        this.workerNumber = workNumber;
    }

    public String getName() {
        return name;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getCost() {
        return cost;
    }

    public int getHp() {
        return hp;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public static BuildingEnum getBuildingStructureByName(String name) {
        for (BuildingEnum value : BuildingEnum.values())
            if (value.name.equals(name))
                return value;
        return null;
    }
}
