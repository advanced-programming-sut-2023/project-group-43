package enums.environmentEnums;

import java.util.ArrayList;

public enum Material {
    //minerals
    STONE("mineral" , "stone" , 4 , 2, 0),
    WOOD("mineral" , "wood" , 3 , 1, 0),
    IRON("mineral" , "iron" , 45 , 25, 0),
    TAR("mineral" , "tar" , 16  , 8, 0),
    //foods
    WHEAT("food" , "wheat" , 23 ,8, 0),
    APPLE("food" , "apple" , 8,4, 0),
    CHEESE("food" , "cheese" , 8 ,4, 0),
    MEAT("food" , "meat" , 8, 4, 0),
    FROZEN_MEAT("food", "frozenMeat" , 4 ,2, 0),
    BREAD("food" , "bread" , 8 , 4, 0),
    HOP("food" , "hop"  ,15 ,8, 0),
    FLOUR("food" , "flour" , 32 ,10, 0),
    BEER("food","beer",24,12, 0),
    //weapons
    BOW("weapon" , "bow" , 31 ,15, 3),
    SWORD("weapon" , "sword" , 58 , 30, 1),
    SPEAR("weapon" , "spear" , 20 ,10, 2),
    CUDGEL("weapon" ,"cudgel",58, 30, 1),
    GRENADE("weapon","grenade",36,18, 2),
    HOOK("weapon","hook",36,18, 0),
    TORCH("weapon","torch",36,18, 1),
    GRAVEL("weapon","gravel",36,18, 1),
    //tools
    LADDER("tool" , "ladder" ,25,12, 0),
    ARMOUR("tool" , "armour" , 58 ,30, 0),
    //else
    HORSE("animal","horse",0,0, 0),
    PEASANT("human","peasant",0,0, 0),
    UNIT("human","unit",0,0, 0),

    ;
    private String type;
    private String name;
    private int buyingPrice;
    private int sellingPrice;

    private int range;

    Material(String type, String name, int buyingPrice , int sellingPrice, int range) {
        this.type = type;
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.range = range;
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

    public static Material getMaterialByName(String name){
        for(Material material : Material.values()){
            if(material.name.equals(name))
                return material;
        }
        return null;
    }

    public static ArrayList getMaterialsByType(String type){
        ArrayList<Material> materialsWithType = new ArrayList<>();
        for(Material material : Material.values()){
            if(material.type.equals(type))
                materialsWithType.add(material);
        }
        return materialsWithType;
    }
}
