package enums;

import javafx.scene.image.Image;

import java.util.Objects;

public enum ImageEnum {
    //<<BACKGROUND>>
    MAIN_MENU(new Image(Objects.requireNonNull(ImageEnum.class.getResource("")).toExternalForm()), "mainMenu", ""),
    SHOP_MENU(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/store.jpg")).toExternalForm()), "shopMenu","/images/background/store.jpg" ),
    TRADE_MENU(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/coin.jpg")).toExternalForm()), "tradeMenu","/images/background/coin.jpg" ),
    WOOD_MENU(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/wood.png")).toExternalForm()), "woodMenu", "/images/background/wood.png"),
    OLD_PAPER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/oldPaper.png")).toExternalForm()), "oldPaper", "/images/background/oldPaper.png"),
    REQUEST(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/request.jpg")).toExternalForm()),"request","/images/background/request.jpg"),


    //<<MATERIAL>>
    STONE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/Stone.png")).toExternalForm()), "stone","/images/material/Stone.png" ),
    WOOD(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/wood.jpg")).toExternalForm()), "wood","/images/material/wood.jpg"),
    IRON(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/iron.png")).toExternalForm()), "iron", "/images/material/iron.png"),
    TAR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/tar.png")).toExternalForm()), "tar", "/images/material/tar.png"),

    WHEAT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/wheat.png")).toExternalForm()), "wheat", "/images/material/wheat.png"),
    APPLE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/apple.png")).toExternalForm()), "apple", "/images/material/apple.png"),
    CHEESE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/cheese.png")).toExternalForm()), "cheese", "/images/material/cheese.png"),
    MEAT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/meat.png")).toExternalForm()), "meat","/images/material/meat.png"),
    FROZEN_MEAT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/frozenMeat.png")).toExternalForm()), "frozenMeat", "/images/material/frozenMeat.png"),
    BREAD(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/bread.png")).toExternalForm()), "bread", "/images/material/bread.png"),
    HOP(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/hop.png")).toExternalForm()), "hop", "/images/material/hop.png"),
    FLOUR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/flour.png")).toExternalForm()), "flour", "/images/material/flour.png"),
    BEER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/beer.png")).toExternalForm()), "beer", "/images/material/beer.png"),

    BOW(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/bow.png")).toExternalForm()), "bow","/images/material/bow.png" ),
    SWORD(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/sword.png")).toExternalForm()), "sword","/images/material/sword.png" ),
    SPEAR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/spear.png")).toExternalForm()), "spear", "/images/material/spear.png"),
    CUDGEL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/cudgel.png")).toExternalForm()), "cudgel", "/images/material/cudgel.png"),
    GRENADE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/grenade.png")).toExternalForm()), "grenade", "/images/material/grenade.png"),
    HOOK(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/hook.png")).toExternalForm()), "hook", "/images/material/hook.png"),
    TORCH(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/torch.png")).toExternalForm()), "torch", "/images/material/torch.png"),
    GRAVEL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/gravel.png")).toExternalForm()), "gravel", "/images/material/gravel.png"),

    LADDER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/ladder.png")).toExternalForm()), "ladder","/images/material/ladder.png" ),
    ARMOUR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/armour.png")).toExternalForm()), "armour", "/images/material/armour.png"),

    HORSE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/horse.png")).toExternalForm()), "horse","/images/material/horse.png" ),
    PEASANT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/peasant.png")).toExternalForm()), "peasant","/images/material/peasant.png" ),
    UNIT(new Image(ImageEnum.class.getResource("").toExternalForm()), "unit", ""),

    //stickers
    COIN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/coin.png")).toExternalForm()),"coin","/images/material/coin.png"),
    TREE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/tree.png")).toExternalForm()),"tree","/images/building/tree.png"),

    //building
    HEADQUARTER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/headquarter.png")).toExternalForm()), "headquarter", "/images/building/headquarter.png"),
    SMALL_STONE_GATEHOUSE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/smallGateHouse.png")).toExternalForm()), "small stone gatehouse", "/images/building/littleGateHouse.png"),
    BIG_STONE_GATEHOUSE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/bigGatehouse.png")).toExternalForm()), "big stone gatehouse", "/images/building/bigGatehouse.png"),
    DRAW_BRIDGE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/drawBridge.png")).toExternalForm()), "draw bridge", "/images/building/drawBridge.png"),
    LOOKOUT_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/lookoutTower.png")).toExternalForm()), "look out tower", "/images/building/lookoutTower.png"),
    PERIMETER_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/perimtrTower.png")).toExternalForm()), "perimeter tower", "/images/building/perimterTower.png"),
    DEFENSIVE_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/defensiveTower.png")).toExternalForm()), "defensive turret", "/images/building/defensiveTower.png"),
    SQUARE_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/squareTower.png")).toExternalForm()), "square tower", "/images/building/squareTower.png"),
    CIRCLE_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/circleTower.png")).toExternalForm()), "circle tower", "/images/building/circleTower.png"),
    SIEGE_TENT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/siegeTent.png")).toExternalForm()), "siege tent", "/images/building/siegeTent.png"),
    CAGED_WAR_DOGS(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/dogCage.png")).toExternalForm()), "caged war dogs", "/images/building/dogCage.png"),
    PITCH_DITCH(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/weaponStockpile.png")).toExternalForm()), "pitch ditch", "/images/building/weaponStockpile.png"),
    OIL_SMELTER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/oilSmelter.gif")).toExternalForm()), "oil smelter", "/images/building/oilSmelter.gif"),
    WHEAT_FARM(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/wheatFarm.gif")).toExternalForm()), "wheat farm", "/images/building/wheatFarm.gif"),
    HOP_FARM(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/hopFarm.gif")).toExternalForm()), "hop farm", "/images/building/hopFarm.gif"),
    HUNTING_POST(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/huntingPost.gif")).toExternalForm()), "hunting post", "/images/building/huntingPost.gif"),
    APPLE_GARDEN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/appleFarm.gif")).toExternalForm()), "apple garden", "/images/building/appleFarm.gif"),
    STABLE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/stable.png")).toExternalForm()), "stable", "/images/building/stable.png"),
    HOVEL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/hovel.png")).toExternalForm()), "hovel", "/images/building/hovel.png"),
    WOOD_CUTTER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/woodCutter.png")).toExternalForm()), "wood cutter", "/images/building/woodCutter.png"),
    PITCH_RIG(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/pitchrig.gif")).toExternalForm()), "pitch rig", "/images/building/pitchrig.gif"),
    QUARRY(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/quarry2.png")).toExternalForm()), "quarry", "/images/building/quarry2.png"),
    IRON_MINE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/quarry.png")).toExternalForm()), "iron mine", "/images/building/quarry.png"),
    BARRACK(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/barrack.png")).toExternalForm()), "barrack", "/images/building/barrack.png"),
    BAKERY(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/bakery.png")).toExternalForm()), "bakery", "/images/building/bakery.png"),
    DAIRY_PRODUCT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/dairyFarm.gif")).toExternalForm()), "dairy products","/images/building/dairyFarm.gif"),
    BEER_BREWING(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/beerProducer.png")).toExternalForm()), "beer brewing", "/images/building/beerProducer.png"),
    MILL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/mill.gif")).toExternalForm()), "mill", "/images/building/mill.gif"),
    POLETURNETR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/poleturner.png")).toExternalForm()), "poleturner", "/images/building/poleturner.png"),
    FLETCHER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/fletcher.png")).toExternalForm()), "fletcher","/images/building/fletcher.png"),
    BLACK_SMITH(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/blacksmith.png")).toExternalForm()), "blackSmith", "/images/building/blacksmith.png"),
    ARMOURER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/weaponStockpile.png")).toExternalForm()), "armourer", "/images/building/weaponStockpile.png"),
    FOOD_STOCKPILE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/stockpile.png")).toExternalForm()), "food stockpile", "/images/building/stockpile.png"),
    STOCKPILE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/stockpile.png")).toExternalForm()), "stockpile", "/images/building/stockpile.png"),
    ARMOURY(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/armoury.png")).toExternalForm()), "armoury", "/images/building/armoury.png"),
    CHURCH(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/church.png")).toExternalForm()), "church", "/images/building/church.png"),
    CATHEDRAL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/church.png")).toExternalForm()), "cathedral", "/images/building/church.png"),
    INN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/inn.png")).toExternalForm()), "inn", "/images/building/inn.png"),
    MARKET(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/market.png")).toExternalForm()), "market", "/images/building/market.png"),
    ENGINEER_GUILD(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/enginnerGuild.gif")).toExternalForm()), "engineer guild", "/images/building/enginnerGuild.gif"),
    MERCENARY_POST(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/mercenaryPost.png")).toExternalForm()), "mercenary post","/images/building/mercenaryPost.png"),
    OX_TETHER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/oxtether.gif")).toExternalForm()), "ox tether", "/images/building/oxtether.gif"),


    //texture
    GROUND(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/default.jpg")).toExternalForm()), "ground","/images/texture/default.jpg"),
    GRAVEL_GROUND(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/pebble.jpg")).toExternalForm()),"Gravel ground" ,"/images/texture/pebble.jpg"),
    BOULDER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/boulder.png")).toExternalForm()),"boulder","/images/texture/boulder.png"),
    ROCK(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/rock.png")).toExternalForm()),"rock","/images/texture/rock.png"),
    //IRON(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/")).toExternalForm())),
    GRASS(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/denseGrass.jpg")).toExternalForm()),"grass","/images/texture/denseGrass.jpg"),
    MEADOW(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/grass.png")).toExternalForm()),"meadow","/images/texture/grass.png"),
    DENSE_GRASSLAND(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/meadow.jpg")).toExternalForm()) ,"dense grassland", "/images/texture/meadow.jpg"),
    OIL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/oil.jpg")).toExternalForm()),"oil","/images/texture/oil.jpg"),
    PLAIN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/plain.png")).toExternalForm()) ,"plain" , "/images/texture/plain.png"),
    SHALLOW_WATER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/shallowWater.png")).toExternalForm()) ,"shallow water","/images/texture/shallowWater.png"),
    RIVER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/river.png")).toExternalForm()),"river","/images/texture/river.png"),
    SMALL_POND(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/river.png")).toExternalForm()),"small pond","/images/texture/smallPond.png"),
    BIG_POND(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/bigPond.jpg")).toExternalForm()),"big pond","/images/texture/bigPond.jpg"),
    BEACH(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/beach.jpg")).toExternalForm()),"beach","/images/texture/beach.jpg"),
    SEA(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/sea.jpg")).toExternalForm()),"sea","/images/texture/sea.jpg"),
    ILLNESS(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/poison.png")).toExternalForm()),"poison","/images/texture/poison.png"),
    FIRE_TEXTURE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/texture/fire.jpg")).toExternalForm()), "fire_texture", "/images/texture/fire.png"),
    //sampleMiniMap
    SAMPLE_MINI_MAP(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/sampleMiniMap.jpg")).toExternalForm()),"sampleMiniMap","/images/background/sampleMiniMap.jpg"),

    //units
    LORD(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/Lord.png")).toExternalForm()) , "lord" ,"/images/units/Lord.png"),
    WORKER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/worker.png")).toExternalForm()) , "worker","/images/units/worker.png"),
    ARCHER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/archer.png")).toExternalForm()) ,"archer","/images/units/archer.png"),
    CROSSBOWMAN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/bowman.png")).toExternalForm()) , "crossbowman","/images/units/bowman.png"),
    SWORDSMAN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/swordsman.png")).toExternalForm()) ,"swordsman","/images/units/swordsman.png"),
    ARCHER_BOW(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/archer.png")).toExternalForm()),"archerBow","/images/units/archer.png"),
    BLACKMONK(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/blackMonk.png")).toExternalForm()),"blackmonk","/images/units/blackMonk.png"),
    SLAVE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/slave.png")).toExternalForm()),"slave","/images/units/slave.png"),
    SLINGER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/slinger.png")).toExternalForm()),"slinger","/images/units/slinger.png"),
    HORSE_ARCHER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/horseArcher.png")).toExternalForm()),"horse archer","/images/units/horseArcher.png"),
    ARABIAN_SWORDSMAN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/swordsman.png")).toExternalForm()),"arabian swordsman","/images/units/swordsman.png"),
    FIRE_THROWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/fireThrower.png")).toExternalForm()),"fire thrower","/images/units/fireThrower.png"),
   PIKEMAN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/pikeman.png")).toExternalForm()),"pikeman","/images/units/pikeman.png"),
    MACEMAN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/maceman.png")).toExternalForm()),"maceman","/images/units/maceman.png"),
    KNIGHT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/knight.png")).toExternalForm()),"knight","/images/units/knight.png"),
    TUNNLER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/tunneler.png")).toExternalForm()),"tunneler","/images/units/tunneler.png"),
    SPEARMAN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/spearman.png")).toExternalForm()),"spearman","/images/units/spearman.png"),
    LADDERMAN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/ladderman.png")).toExternalForm()),"ladderman","/images/units/ladderman.png"),
   ENGINNER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/engineer.png")).toExternalForm()),"engineer","/images/units/engineer.png"),
    ASSASSIN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/assassin.png")).toExternalForm()),"assassin","/images/units/assassin.png"),
    DOG(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/units/dog.png")).toExternalForm()),"dog","/images/units/dog.png")

    ;



    private Image image;
    private String name;
    private String address;
    ImageEnum(Image image, String name, String address) {
        this.image = image;
        this.name = name;
        this.address = address;
    }

    public Image getImage() {
        return image;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public static String getNameByImage(Image image) {
        for(ImageEnum imageEnum : ImageEnum.values()){
            if(imageEnum.getImage().equals(image)) {
                return imageEnum.getName();
            }
        }
        return null;
    }
    public static Image getImageByName(String name){
        for(ImageEnum imageEnum : ImageEnum.values()){
            if(imageEnum.getName().equals(name)) {
                return imageEnum.getImage();
            }
        }
        return null;
    }

    public static String getImageAddressByName(String name){
        for(ImageEnum imageEnum : ImageEnum.values()){
            if(imageEnum.getName().equals(name))
                return imageEnum.getAddress();
        }
        return null;
    }


}
