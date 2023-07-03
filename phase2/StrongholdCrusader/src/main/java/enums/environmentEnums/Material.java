package enums.environmentEnums;

import enums.ImageEnum;
import javafx.scene.image.Image;

import java.util.ArrayList;

public enum Material {
    //minerals
    STONE("mineral", "stone", 4, 2, 0, ImageEnum.STONE.getImage()),
    WOOD("mineral", "wood", 3, 1, 0, ImageEnum.WOOD.getImage()),
    IRON("mineral", "iron", 45, 25, 0, ImageEnum.IRON.getImage()),
    TAR("mineral", "tar", 16, 8, 0, ImageEnum.TAR.getImage()),
    //foods
    WHEAT("food", "wheat", 23, 8, 0, ImageEnum.WHEAT.getImage()),
    APPLE("food", "apple", 8, 4, 0, ImageEnum.APPLE.getImage()),
    CHEESE("food", "cheese", 8, 4, 0, ImageEnum.CHEESE.getImage()),
    MEAT("food", "meat", 8, 4, 0, ImageEnum.MEAT.getImage()),
    FROZEN_MEAT("food", "frozenMeat", 4, 2, 0, ImageEnum.FROZEN_MEAT.getImage()),
    BREAD("food", "bread", 8, 4, 0, ImageEnum.BREAD.getImage()),
    HOP("food", "hop", 15, 8, 0, ImageEnum.HOP.getImage()),
    FLOUR("food", "flour", 32, 10, 0, ImageEnum.FLOUR.getImage()),
    BEER("food", "beer", 24, 12, 0, ImageEnum.BEER.getImage()),
    //weapons
    BOW("weapon", "bow", 31, 15, 3, ImageEnum.BOW.getImage()),
    SWORD("weapon", "sword", 58, 30, 1, ImageEnum.SWORD.getImage()),
    SPEAR("weapon", "spear", 20, 10, 2, ImageEnum.SPEAR.getImage()),
    CUDGEL("weapon", "cudgel", 58, 30, 1, ImageEnum.CUDGEL.getImage()),
    GRENADE("weapon", "grenade", 36, 18, 2, ImageEnum.GRENADE.getImage()),
    HOOK("weapon", "hook", 36, 18, 0, ImageEnum.HOOK.getImage()),
    TORCH("weapon", "torch", 36, 18, 1, ImageEnum.TORCH.getImage()),
    GRAVEL("weapon", "gravel", 36, 18, 1, ImageEnum.GRAVEL.getImage()),
    //tools
    LADDER("tool", "ladder", 25, 12, 0, ImageEnum.LADDER.getImage()),
    ARMOUR("tool", "armour", 58, 30, 0, ImageEnum.ARMOUR.getImage()),
    //else
    HORSE("animal", "horse", 0, 0, 0, ImageEnum.HORSE.getImage()),
    PEASANT("human", "peasant", 0, 0, 0, ImageEnum.PEASANT.getImage()),
    UNIT("human", "unit", 0, 0, 0, ImageEnum.UNIT.getImage()),

    ;
    private String type;
    private String name;
    private int buyingPrice;
    private int sellingPrice;
    private int range;
    private Image image;

    Material(String type, String name, int buyingPrice, int sellingPrice, int range, Image imageAddress) {
        this.type = type;
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.range = range;

        this.image = imageAddress;
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

    public Image getImage() {
        return image;
    }
}
