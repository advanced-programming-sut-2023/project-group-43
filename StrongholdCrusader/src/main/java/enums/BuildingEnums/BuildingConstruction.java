package enums.BuildingEnums;

public enum BuildingConstruction {
    //<<<----------castle buildings---------->>>
    //------> 1) gatehouses
    SMALL_STONE_GATEHOUSE(0 , 0 ,0 , 0,0),
    BIG_STONE_GATEHOUSE(0 ,20,0 , 0,0),
    //------> 2) bridge
    DRAW_BRIDGE(10 ,0,0,0,0),
    //------> 3) towers
    LOOKOUT_TOWER(0,10,0,0,0),
    PERIMETER_TOWER(0,10,0,0,0),
    DEFENSEIVE_TURRET(0,15,0,0,0),
    SQUARE_TOWER(0,35,0,0,0),
    CIRCLE_TOWER(0,40,0,0,0),
    //<<<----------keeps and strongholds---------->>>
    SIEGE_TENT(0,0,0,0,0),
    CAGED_WAR_DOGS(10,0,100,0,0),
    PITCH_DITCH(0,0,0,0,0),
    OIL_SMELTER(0,10,100,0,1),
    //<<<----------manufacture---------->>>
    //------> 1) foods
    WHEAT_FARM(15,0,0,0,1),
    HOP_FARM(15,0,0,0,1),
    HUNTING_POST(5 , 0,0,0,1),
    APPLE_GARDEN(5,0,0,0,1),
    //------> 2) animals
    STABLE(10,0,400,0,0),
    //------> 3) humans
    HOWEL(6,0,0,0,0),
    //-------> 4) items
    WOOD_CUTTER(3,0,0,0,1),
    PITCH_RIG(20,0,0,0,1),
    QUARYY(20,0,0,0,3),
    IRON_MINE(20,0,0,0,2),
    //<<<----------converter---------->>>
    //------> 1) humans
    BARRACK(0 , 15 , 0,0,0),
    //------> 2) foods
    BAKERY(10,0,0,0,1),
    DAIRY_PRODUCTS(10,0,0,0,1),
    BEER_BREWING(10,0,0,0 , 1),
    MILL(20,0,0,0,3),
    //------> 3) weapons
    POLETURNER(10,0,100,0,1),
    FLETCHER(20,0,20,0,1),
    BLACKSMITH(20,0,100,0,1),
    ARMOURER(20,0,100,0,1),
    //<<<----------storage---------->>>
    FOOD_STOCKPILE(5,0,0,0,0),
    STOCKPILE(0,0,0,0,0),
    ARMPURY(5,0,0,0,0),
    //<<<----------popularity booster---------->>>
    CHURCH(0,0,250,0,0),
    CATHEDRAL(0,0,1000,0,0),
    INN(20,0,100,0,1),
    //<<<----------store---------->>>
    MARKET(5,0,0,0,1),
    ENGINEER_GUILD(10,0,100,0,0),
    ARMOURY(5 , 0, 0 , 0,0),
    MERCENARY_POST(10,0,0,0,0),
    //none
    OX_TETHER(5,0,0,0,1),
    ;
    private int wood;
    private int stone;
    private int gold;
    private int hp;
    private int laddernans;

    BuildingConstruction(int wood, int stone, int gold, int hp , int laddernans) {
        this.wood = wood;
        this.stone = stone;
        this.gold = gold;
        this.hp = hp;
        this.laddernans = laddernans;
    }
}