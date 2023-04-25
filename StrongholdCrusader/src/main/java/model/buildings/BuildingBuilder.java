package model.buildings;

import enums.environmentEnums.Materials;
import model.User;
public class BuildingBuilder {

    public static Converter converter;
    public static Manufacturer manufacturer;
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
                manufacturer = new Manufacturer(name,owner,10,10);
                manufacturer.addProducedMaterial(Materials.WHEAT);
                return manufacturer;
            case "hop farm":
                manufacturer = new Manufacturer(name,owner,11,10);
                manufacturer.addProducedMaterial(Materials.HOP);
                return manufacturer;
            case "hunting post":
                manufacturer = new Manufacturer(name,owner,12 , 10);
                manufacturer.addProducedMaterial(Materials.MEAT);
                return manufacturer;
            case "apple garden":
                manufacturer = new Manufacturer(name,owner,13,10);
                manufacturer.addProducedMaterial(Materials.APPLE);
                return manufacturer;
            case "stable":
                manufacturer = new Manufacturer(name,owner,14,10);
                manufacturer.addProducedMaterial(Materials.HORSE);
                return manufacturer;
            case "howel":
                manufacturer = new Manufacturer(name,owner,15,10);
                manufacturer.addProducedMaterial(Materials.PEASANT);
                return manufacturer;
            case "wood cutter":
                manufacturer = new Manufacturer(name,owner,16,10);
                manufacturer.addProducedMaterial(Materials.WOOD);
                return manufacturer;
            case "pitch rig":
                manufacturer = new Manufacturer(name,owner,17,10);
                manufacturer.addProducedMaterial(Materials.TAR);
                return manufacturer;
            case "quarry":
                manufacturer = new Manufacturer(name,owner,18,10);
                manufacturer.addProducedMaterial(Materials.STONE);
                return manufacturer;
            case "iron mine":
                manufacturer = new Manufacturer(name,owner,19,10);
                manufacturer.addProducedMaterial(Materials.IRON);
                return manufacturer;
            case "barrack":
                converter = new Converter(name,owner,10,10);
                converter.addConsumeMaterial(Materials.PEASANT);
                converter.addProducedMaterial(Materials.UNIT);
                return converter;
            case "bakery":
                converter = new Converter(name,owner,11,10);
                converter.addConsumeMaterial(Materials.FLOUR);
                converter.addProducedMaterial(Materials.BREAD);
                return converter;
            case "beer brewing":
                converter =  new Converter(name,owner,12,10);
                converter.addConsumeMaterial(Materials.HOP);
                converter.addProducedMaterial(Materials.BEER);
                return converter;
            case "mill":
                converter = new Converter(name,owner,13,10);
                converter.addConsumeMaterial(Materials.WHEAT);
                converter.addProducedMaterial(Materials.FLOUR);
                return converter;
            case "poleturner":
                converter = new Converter(name,owner,14,10);
                converter.addConsumeMaterial(Materials.WOOD);
                converter.addProducedMaterial(Materials.SPEAR);
                return converter;
            case "fletcher":
                converter = new Converter(name,owner,15,10);
                converter.addConsumeMaterial(Materials.WOOD);
                converter.addProducedMaterial(Materials.BOW);
                return converter;
            case "blacksmith":
                converter =  new Converter(name,owner,16,10);
                converter.addConsumeMaterial(Materials.IRON);
                converter.addProducedMaterial(Materials.SWORD);
                converter.addProducedMaterial(Materials.CUDGEL);
                return converter;
            case "armourer":
                converter = new Converter(name,owner,17,10);
                converter.addProducedMaterial(Materials.IRON);
                converter.addProducedMaterial(Materials.ARMOUR);
                return converter;
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
            case "dairy products":
            case "engineer guild":
            case "mercenary post":
            case "ox tether":
                return new Building(name,owner);
        }
        return null;
    }
}
