package enums.environmentEnums;

import enums.ImageEnum;
import javafx.scene.image.Image;

import java.util.ArrayList;

public enum Material {
    //minerals
    STONE("mineral", "stone", 4, 2, 0, ImageEnum.STONE.getAddress()),
    WOOD("mineral", "wood", 3, 1, 0, ImageEnum.WOOD.getAddress()),
    IRON("mineral", "iron", 45, 25, 0, ImageEnum.IRON.getAddress()),
    TAR("mineral", "tar", 16, 8, 0, ImageEnum.TAR.getAddress()),
    //foods
    WHEAT("food", "wheat", 23, 8, 0, ImageEnum.WHEAT.getAddress()),
    APPLE("food", "apple", 8, 4, 0, ImageEnum.APPLE.getAddress()),
    CHEESE("food", "cheese", 8, 4, 0, ImageEnum.CHEESE.getAddress()),
    MEAT("food", "meat", 8, 4, 0, ImageEnum.MEAT.getAddress()),
    FROZEN_MEAT("food", "frozenMeat", 4, 2, 0, ImageEnum.FROZEN_MEAT.getAddress()),
    BREAD("food", "bread", 8, 4, 0, ImageEnum.BREAD.getAddress()),
    HOP("food", "hop", 15, 8, 0, ImageEnum.HOP.getAddress()),
    FLOUR("food", "flour", 32, 10, 0, ImageEnum.FLOUR.getAddress()),
    BEER("food", "beer", 24, 12, 0, ImageEnum.BEER.getAddress()),
    //weapons
    BOW("weapon", "bow", 31, 15, 3, ImageEnum.BOW.getAddress()),
    SWORD("weapon", "sword", 58, 30, 1, ImageEnum.SWORD.getAddress()),
    SPEAR("weapon", "spear", 20, 10, 2, ImageEnum.SPEAR.getAddress()),
    CUDGEL("weapon", "cudgel", 58, 30, 1, ImageEnum.CUDGEL.getAddress()),
    GRENADE("weapon", "grenade", 36, 18, 2, ImageEnum.GRENADE.getAddress()),
    HOOK("weapon", "hook", 36, 18, 0, ImageEnum.HOOK.getAddress()),
    TORCH("weapon", "torch", 36, 18, 1, ImageEnum.TORCH.getAddress()),
    GRAVEL("weapon", "gravel", 36, 18, 1, ImageEnum.GRAVEL.getAddress()),
    //tools
    LADDER("tool", "ladder", 25, 12, 0, ImageEnum.LADDER.getAddress()),
    ARMOUR("tool", "armour", 58, 30, 0, ImageEnum.ARMOUR.getAddress()),
    //else
    HORSE("animal", "horse", 0, 0, 0, ImageEnum.HORSE.getAddress()
    ),
    PEASANT("human", "peasant", 0, 0, 0, ImageEnum.PEASANT.getAddress()),
    UNIT("human", "unit", 0, 0, 0, ImageEnum.UNIT.getAddress()),

    ;
    private String type;
    private String name;
    private int buyingPrice;
    private int sellingPrice;
    private int range;
    private String imageAddress;

    Material(String type, String name, int buyingPrice, int sellingPrice, int range, String imageAddress) {
        this.type = type;
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.range = range;
        this.imageAddress = imageAddress;
    }

    public static Material getMaterialByName(String name) {
        for (Material material : Material.values()) {
            if (material.name.equals(name))
                return material;
        }
        return null;
    }

    public static ArrayList<Material> getMaterialsByType(String type) {
        ArrayList<Material> materialsWithType = new ArrayList<>();
        for (Material material : Material.values()) {
            if (material.type.equals(type))
                materialsWithType.add(material);
        }
        return materialsWithType;
    }



    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public int getRange() {
        return range;
    }

    public String getImageAddress() {
        return imageAddress;
    }
}
