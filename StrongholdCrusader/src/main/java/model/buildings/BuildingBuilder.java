package model.buildings;

import enums.environmentEnums.Materials;
import model.User;
public class BuildingBuilder {

    public static Building BuildingBuilder(String name , User owner){
        //TODO : we should declare people capacity with details
        switch (name){
            case "small stone gatehouse":
                return new CastleDepartment(name, owner,100,8 );
            case "big stone gatehouse":
                    return new CastleDepartment(name,owner,100,10);
            case "draw bridge":
                    return new CastleDepartment(name,owner,100,0);
            case "look out tower":
                    return new CastleDepartment(name,owner,150,0);
            case "perimeter tower":
                return new CastleDepartment(name,owner,153 , 0);
            case "defensive turret":
                return new CastleDepartment(name,owner,10,0);
            case "square tower":
                return new CastleDepartment(name,owner,15,0);
            case "circle tower":
                return new CastleDepartment(name,owner,20,0);
            case "siege tent":
            case "caged war dogs":
            case "pitch ditch":
                case "oil smelter":
                return new Stronghold(name,owner);
            case "wheat farm":
                return new Manufacturer(name,owner, Materials.WHEAT ,10);
            case "hop farm":
                return new Manufacturer(name,owner,Materials.HOP,10);
            case "hunting post":
                return new Manufacturer(name,owner,Materials.MEAT , 10);
            case "apple garden":
                return new Manufacturer(name,owner,Materials.APPLE,10);
            case "stable":
                return new Manufacturer(name,owner,Materials.HORSE,10);
            case "howel":
                return new Manufacturer(name,owner,Materials.PEASANT,10);
            case "wood cutter":
                return new Manufacturer(name,owner,Materials.WOOD,10);
            case "pitch rig":
                return new Manufacturer(name,owner,Materials.TAR,10);
            case "quarry":
                return new Manufacturer(name,owner,Materials.STONE,10);
            case "iron mine":
                return new Manufacturer(name,owner,Materials.IRON,10);
            case "barrack":
                return new Converter(name,owner,Materials.PEASANT,Materials.UNIT,10,10);
            case "bakery":
                return new Converter(name,owner,Materials.WHEAT,Materials.BREAD,10,10);
            case "dairy products":
                return new Building(name,owner);
            case "beer brewing":
                return new Converter(name,owner,Materials.HOP,Materials.BEER,10,10);
            case "mill":
                return new Converter(name,owner,Materials.WHEAT,Materials.FLOUR,10,10);
            case "poleturner":
                return new Converter(name,owner,Materials.IRON,Materials.SPEAR,10,10);
            case "fletcher":
                return new Converter(name,owner,Materials.WOOD,Materials.BOW,10,10);
            case "blacksmith":
                return new Converter(name,owner,Materials.IRON,Materials.SWORD,10,10);
            case "armourer":
                return new Converter(name,owner,Materials.IRON,Materials.ARMOUR,10,10);
            case "food stockpile":
            case "stockpile":
            case "armoury":
                return new Storage(name,owner);
            case "church":
                return new PopularityBooster(name,owner,10);
            case "cathedral":
                return new PopularityBooster(name,owner,11);
            case "inn":
                return new PopularityBooster(name,owner,12);
            case "market":
                return new Store(name,owner);
            case "engineer guild":
            case "mercenary post":
            case "ox tether":
                return new Building(name,owner);
        }
        return null;
    }
}
