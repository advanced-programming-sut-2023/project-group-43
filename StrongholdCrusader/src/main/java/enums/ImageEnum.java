package enums;

import javafx.scene.image.Image;

import java.util.Objects;

public enum ImageEnum {
    //<<BACKGROUND>>
    MAIN_MENU(new Image(Objects.requireNonNull(ImageEnum.class.getResource("")).toExternalForm())),
    SHOP_MENU(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/store.jpg")).toExternalForm())),
    TRADE_MENU(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/coin.jpg")).toExternalForm())),
    WOOD_MENU(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/wood.png")).toExternalForm())),
    OLD_PAPER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/oldPaper.png")).toExternalForm())),
    REQUEST(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/background/request.jpg")).toExternalForm())),


    //<<MATERIAL>>
    STONE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/Stone.png")).toExternalForm())),
    WOOD(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/wood.jpg")).toExternalForm())),
    IRON(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/iron.png")).toExternalForm())),
    TAR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/tar.png")).toExternalForm())),

    WHEAT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/wheat.png")).toExternalForm())),
    APPLE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/apple.png")).toExternalForm())),
    CHEESE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/cheese.png")).toExternalForm())),
    MEAT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/meat.png")).toExternalForm())),
    FROZEN_MEAT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/frozenMeat.png")).toExternalForm())),
    BREAD(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/bread.png")).toExternalForm())),
    HOP(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/hop.png")).toExternalForm())),
    FLOUR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/flour.png")).toExternalForm())),
    BEER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/beer.png")).toExternalForm())),

    BOW(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/bow.png")).toExternalForm())),
    SWORD(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/sword.png")).toExternalForm())),
    SPEAR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/spear.png")).toExternalForm())),
    CUDGEL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/cudgel.png")).toExternalForm())),
    GRENADE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/grenade.png")).toExternalForm())),
    HOOK(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/hook.png")).toExternalForm())),
    TORCH(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/torch.png")).toExternalForm())),
    GRAVEL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/gravel.png")).toExternalForm())),

    LADDER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/ladder.png")).toExternalForm())),
    ARMOUR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/armour.png")).toExternalForm())),

    HORSE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/horse.png")).toExternalForm())),
    PEASANT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/peasant.png")).toExternalForm())),
    UNIT(new Image(ImageEnum.class.getResource("").toExternalForm())),

    //stickers
    COIN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/material/coin.png")).toExternalForm())),

    //building
    HEADQUARTER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/headquarter.png")).toExternalForm())),
    SMALL_STONE_GATEHOUSE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/littleGateHouse.png")).toExternalForm())),
    BIG_STONE_GATEHOUSE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/bigGatehouse.png")).toExternalForm())),
    DRAW_BRIDGE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/drawBridge.png")).toExternalForm())),
    LOOKOUT_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/lookoutTower.png")).toExternalForm())),
    PERIMETER_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/perimterTower.png")).toExternalForm())),
    DEFENSIVE_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/defensiveTower.png")).toExternalForm())),
    SQUARE_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/squareTower.png")).toExternalForm())),
    CIRCLE_TOWER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/circleTower.png")).toExternalForm())),
    SIEGE_TENT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/siegeTent.png")).toExternalForm())),
    CAGED_WAR_DOGS(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/dogCage.png")).toExternalForm())),
    PITCH_DITCH(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/weaponStockpile.png")).toExternalForm())),
    OIL_SMELTER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/oilSmelter.gif")).toExternalForm())),
    WHEAT_FARM(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/wheatFarm.gif")).toExternalForm())),
    HOP_FARM(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/hopFarm.gif")).toExternalForm())),
    HUNTING_POST(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/huntingPost.gif")).toExternalForm())),
    APPLE_GARDEN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/appleFarm.gif")).toExternalForm())),
    STABLE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/stable.png")).toExternalForm())),
    HOVEL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/hovel.png")).toExternalForm())),
    WOOD_CUTTER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/woodCutter.png")).toExternalForm())),
    PITCH_RIG(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/pitchrig.gif")).toExternalForm())),
    QUARRY(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/quarry2.png")).toExternalForm())),
    IRON_MINE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/quarry.png")).toExternalForm())),
    BARRACK(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/barrack.png")).toExternalForm())),
    BAKERY(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/bakery.png")).toExternalForm())),
    DAIRY_PRODUCT(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/dairyFarm.gif")).toExternalForm())),
    BEER_BREWING(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/beerProducer.png")).toExternalForm())),
    MILL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/mill.gif")).toExternalForm())),
    POLETURNETR(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/poleturner.png")).toExternalForm())),
    FLETCHER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/fletcher.png")).toExternalForm())),
    BLACK_SMITH(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/blacksmith.png")).toExternalForm())),
    ARMOURER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/weaponStockpile.png")).toExternalForm())),
    FOOD_STOCKPILE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/stockpile.png")).toExternalForm())),
    STOCKPILE(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/stockpile.png")).toExternalForm())),
    ARMOURY(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/armoury.png")).toExternalForm())),
    CHURCH(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/church.png")).toExternalForm())),
    CATHEDRAL(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/church.png")).toExternalForm())),
    INN(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/inn.png")).toExternalForm())),
    MARKET(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/market.png")).toExternalForm())),
    ENGINEER_GUILD(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/enginnerGuild.gif")).toExternalForm())),
    MERCENARY_POST(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/mercenaryPost.png")).toExternalForm())),
    OX_TETHER(new Image(Objects.requireNonNull(ImageEnum.class.getResource("/images/building/oxtether.gif")).toExternalForm()));




    public Image image;

    ImageEnum(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
    }
