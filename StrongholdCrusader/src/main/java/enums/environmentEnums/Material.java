package enums.environmentEnums;

import java.util.ArrayList;

public enum Material {
    //minerals
    STONE("mineral" , "stone" , 0 , 0, 0),
    WOOD("mineral" , "wood" , 0 , 0, 0),
    IRON("mineral" , "iron" , 0 , 0, 0),
    TAR("mineral" , "tar" , 0  , 0, 0),
    //foods
    WHEAT("food" , "wheat" , 0 ,0, 0),
    APPLE("food" , "apple" , 0 ,0, 0),
    CHEESE("food" , "cheese" , 0 ,0, 0),
    MEAT("food" , "meat" , 0, 0, 0),
    FROZEN_MEAT("food", "frozenMeat" , 0 ,0, 0),
    BREAD("food" , "bread" , 0 , 0, 0),
    HOP("food" , "hop"  ,0 ,0, 0),
    FLOUR("food" , "flour" , 0 ,0, 0),
    BEER("food","beer",0,0, 0),
    //weapons
    BOW("weapon" , "bow" , 0 ,0, 3),
    SWORD("weapon" , "sword" , 0 , 0, 1),
    SPEAR("weapon" , "spear" , 0 ,0, 2),
    CUDGEL("weapon" ,"cudgel",0, 0, 1),
    GRENADE("weapon","grenade",0,0, 2),
    HOOK("weapon","hook",0,0, 0),
    TORCH("weapon","torch",0,0, 1),
    GRAVEL("weapon","gravel",0,0, 1),
    //tools
    LADDER("tool" , "ladder" ,0,0, 0),
    ARMOUR("tool" , "armour" , 0 ,0, 0),
    //else
    HORSE("animal","horse",0,0, 0),
    PEASANT("human","peasant",0,0, 0),
    UNIT("human","unit",0,0, 0),

    ;
    private String type;
    private String name;
    private int initialCost;
    private int secondaryCost;

    private int range;

    Material(String type, String name, int initialCost , int secondaryCost, int range) {
        this.type = type;
        this.name = name;
        this.initialCost = initialCost;
        this.secondaryCost = secondaryCost;
        this.range = range;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getInitialCost() {
        return initialCost;
    }

    public int getSecondaryCost() {
        return secondaryCost;
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
