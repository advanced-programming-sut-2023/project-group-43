package enums.BuildingEnums;

import enums.ImageEnum;
import javafx.scene.image.Image;

public enum BuildingEnum {
    //<<<----------castle department---------->>>
    //------> headquarter
    HEADQUARTER("headquarter", 0, 0, 0, 600, 0, ImageEnum.HEADQUARTER.getImage()),
    //------> 1) gatehouses
    SMALL_STONE_GATEHOUSE("small stone gatehouse", 0, 0, 100, 0, 0, ImageEnum.SMALL_STONE_GATEHOUSE.getImage()),
    BIG_STONE_GATEHOUSE("big stone gatehouse", 0, 20, 0, 200, 0, ImageEnum.BIG_STONE_GATEHOUSE.getImage()),
    //------> 2) bridge
    DRAW_BRIDGE("draw bridge", 10, 0, 0, 100, 0, ImageEnum.DRAW_BRIDGE.getImage()),
    //------> 3) towers
    LOOKOUT_TOWER("lookout tower", 0, 10, 0, 300, 0, ImageEnum.LOOKOUT_TOWER.getImage()),
    PERIMETER_TOWER("perimeter tower", 0, 10, 0, 300, 0, ImageEnum.PERIMETER_TOWER.getImage()),
    DEFENSEIVE_TURRET("defensive tower", 0, 15, 0, 400, 0, ImageEnum.DEFENSIVE_TOWER.getImage()),
    SQUARE_TOWER("square tower", 0, 35, 0, 300, 0, ImageEnum.SQUARE_TOWER.getImage()),
    CIRCLE_TOWER("circle tower", 0, 40, 0, 300, 0, ImageEnum.CIRCLE_TOWER.getImage()),
    //<<<----------keeps and strongholds---------->>>
    SIEGE_TENT("siege tent", 0, 0, 0, 500, 0, ImageEnum.SIEGE_TENT.getImage()),
    CAGED_WAR_DOGS("caged war dogs", 10, 0, 100, 400, 0, ImageEnum.CAGED_WAR_DOGS.getImage()),
    PITCH_DITCH("pitch ditch", 0, 0, 0, 500, 0, ImageEnum.PITCH_DITCH.getImage()),
    OIL_SMELTER("oil smelter", 0, 10, 100, 400, 1, ImageEnum.OIL_SMELTER.getImage()),
    //<<<----------converter---------->>>
    //part 1
    //------> 1) foods
    WHEAT_FARM("wheat farm", 15, 0, 0, 100, 1, ImageEnum.WHEAT_FARM.getImage()),
    HOP_FARM("hop farm", 15, 0, 0, 100, 1, ImageEnum.HOP_FARM.getImage()),
    HUNTING_POST("hunting post", 5, 0, 0, 200, 1, ImageEnum.HUNTING_POST.getImage()),
    APPLE_GARDEN("apple garden", 5, 0, 0, 100, 1, ImageEnum.APPLE_GARDEN.getImage()),
    //------> 2) animals
    STABLE("stable", 10, 0, 400, 200, 0, ImageEnum.STABLE.getImage()),
    //------> 3) humans
    HOVEL("hovel", 6, 0, 0, 100, 0, ImageEnum.HOVEL.getImage()),
    //-------> 4) items
    WOOD_CUTTER("wood cutter", 3, 0, 0, 200, 1, ImageEnum.WOOD_CUTTER.getImage()),
    PITCH_RIG("pitch rig", 20, 0, 0, 400, 1, ImageEnum.PITCH_RIG.getImage()),
    QUARRY("quarry", 20, 0, 0, 400, 3, ImageEnum.QUARRY.getImage()),
    IRON_MINE("iron mine", 20, 0, 0, 400, 2, ImageEnum.IRON_MINE.getImage()),
    //part 2
    //------> 1) humans
    BARRACK("barrack", 0, 15, 0, 300, 0, ImageEnum.BARRACK.getImage()),
    //------> 2) foods
    BAKERY("bakery", 10, 0, 0, 100, 1, ImageEnum.BAKERY.getImage()),

    DAIRY_PRODUCTS("dairy products", 10, 0, 0, 100, 1, ImageEnum.DAIRY_PRODUCT.getImage()),
    BEER_BREWING("beer brewing", 10, 0, 0, 100, 1, ImageEnum.BEER_BREWING.getImage()),
    MILL("mill", 20, 0, 0, 200, 3, ImageEnum.MILL.getImage()),
    //------> 3) weapons
    POLETURNER("poleturner", 10, 0, 100, 100, 1, ImageEnum.POLETURNETR.getImage()),
    FLETCHER("fletcher", 20, 0, 20, 150, 1, ImageEnum.FLETCHER.getImage()),
    BLACKSMITH("blacksmith", 20, 0, 100, 200, 1, ImageEnum.BLACK_SMITH.getImage()),
    ARMOURER("armourer", 20, 0, 100, 200, 1, ImageEnum.ARMOURER.getImage()),
    //<<<----------storage---------->>>
    FOOD_STOCKPILE("food stockpile", 5, 0, 0, 100, 0, ImageEnum.FOOD_STOCKPILE.getImage()),
    STOCKPILE("stockpile", 0, 0, 0, 100, 0, ImageEnum.STOCKPILE.getImage()),
    ARMOURY("armoury", 5, 0, 0, 100, 0, ImageEnum.ARMOURY.getImage()),
    //<<<----------popularity booster---------->>>
    CHURCH("church", 0, 0, 250, 200, 0, ImageEnum.CHURCH.getImage()),
    CATHEDRAL("cathedral", 0, 0, 1000, 200, 0, ImageEnum.CATHEDRAL.getImage()),
    INN("inn", 20, 0, 100, 200, 1, ImageEnum.INN.getImage()),
    //<<<----------store---------->>>
    MARKET("market", 5, 0, 0, 100, 1, ImageEnum.MARKET.getImage()),
    ENGINEER_GUILD("engineer guild", 10, 0, 100, 200, 0, ImageEnum.ENGINEER_GUILD.getImage()),
    MERCENARY_POST("mercenary post", 10, 0, 0, 200, 0, ImageEnum.MERCENARY_POST.getImage()),
    //none
    OX_TETHER("ox tether", 5, 0, 0, 50, 1, ImageEnum.OX_TETHER.getImage()),
    ;
    private String name;
    private int wood;
    private int stone;
    private int cost;
    private int hp;
    private int workerNumber;
    private Image image;

    BuildingEnum(String name, int wood, int stone, int cost, int hp, int workNumber, Image image) {
        this.name = name;
        this.wood = wood;
        this.stone = stone;
        this.cost = cost;
        this.hp = hp;
        this.workerNumber = workNumber;
        this.image = image;
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
