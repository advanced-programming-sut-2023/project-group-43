package enums;

public enum Material {
    //minerals
    STONE("mineral" , "stone" , 0 , 0),
    WOOD("mineral" , "wood" , 0 , 0),
    IRON("mineral" , "iron" , 0 , 0),
    TAR("mineral" , "tar" , 0  , 0),
    //foods
    WHEAT("food" , "wheat" , 0 ,0 ),
    APPLE("food" , "apple" , 0 ,0),
    CHEESE("food" , "cheese" , 0 ,0),
    MEAT("food" , "meat" , 0, 0),
    FROZEN_MEAT("food", "frozenMeat" , 0 ,0),
    BREAD("food" , "bread" , 0 , 0),
    HOP("food" , "hop"  ,0 ,0),
    FLOUR("food" , "flour" , 0 ,0),
    //weapons
    BOW("weapon" , "bow" , 0 ,0),
    ARMOR("weapon" , "armor" , 0 ,0),
    SWORD("weapon" , "sword" , 0 ,0),
    SPEAR("weapon" , "spear" , 0 ,0),

    ;
    private String type;
    private String name;
    private int initialCost;
    private int secondaryCost;

    Material(String type, String name, int initialCost , int secondaryCost) {
        this.type = type;
        this.name = name;
        this.initialCost = initialCost;
        this.secondaryCost = secondaryCost;
    }

    public Material getMaterialByName(String name){
        for(Material material : Material.values()){
            if(material.name.equals(name))
                return material;
        }
        return null;
    }
}
