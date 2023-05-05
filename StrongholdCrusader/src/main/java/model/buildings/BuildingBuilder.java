package model.buildings;

import enums.environmentEnums.Material;
import model.User;

public class BuildingBuilder {

    public static Converter converter;
    public static Manufacturer manufacturer;

    public static Building BuildingBuilder(String name, User owner) {
        //TODO : we should declare people capacity with details
        switch (name) {
            case "small stone gatehouse":
                return new CastleDepartment(name, owner, 8);
            case "big stone gatehouse":
                return new CastleDepartment(name, owner, 10);
            case "draw bridge":
            case "look out tower":
            case "perimeter tower":
            case "defensive turret":
            case "square tower":
            case "circle tower":
                return new CastleDepartment(name, owner, 15);
            case "siege tent":
            case "caged war dogs":
            case "pitch ditch":
            case "oil smelter":
                return new Stronghold(name, owner);
            case "wheat farm":
                manufacturer = new Manufacturer(name, owner, 10, 10);
                manufacturer.addProducedMaterial(Material.WHEAT);
                return manufacturer;
            case "hop farm":
                manufacturer = new Manufacturer(name, owner, 11, 10);
                manufacturer.addProducedMaterial(Material.HOP);
                return manufacturer;
            case "hunting post":
                manufacturer = new Manufacturer(name, owner, 12, 10);
                manufacturer.addProducedMaterial(Material.MEAT);
                return manufacturer;
            case "apple garden":
                manufacturer = new Manufacturer(name, owner, 13, 10);
                manufacturer.addProducedMaterial(Material.APPLE);
                return manufacturer;
            case "stable":
                manufacturer = new Manufacturer(name, owner, 14, 10);
                manufacturer.addProducedMaterial(Material.HORSE);
                return manufacturer;
            case "howel":
                manufacturer = new Manufacturer(name, owner, 15, 10);
                manufacturer.addProducedMaterial(Material.PEASANT);
                return manufacturer;
            case "wood cutter":
                manufacturer = new Manufacturer(name, owner, 16, 10);
                manufacturer.addProducedMaterial(Material.WOOD);
                return manufacturer;
            case "pitch rig":
                manufacturer = new Manufacturer(name, owner, 17, 10);
                manufacturer.addProducedMaterial(Material.TAR);
                return manufacturer;
            case "quarry":
                manufacturer = new Manufacturer(name, owner, 18, 10);
                manufacturer.addProducedMaterial(Material.STONE);
                return manufacturer;
            case "iron mine":
                manufacturer = new Manufacturer(name, owner, 19, 10);
                manufacturer.addProducedMaterial(Material.IRON);
                return manufacturer;
            case "barrack":
                converter = new Converter(name, owner, 10, 10);
                converter.addConsumeMaterial(Material.PEASANT);
                converter.addProducedMaterial(Material.UNIT);
                return converter;
            case "bakery":
                converter = new Converter(name, owner, 11, 10);
                converter.addConsumeMaterial(Material.FLOUR);
                converter.addProducedMaterial(Material.BREAD);
                return converter;
            case "beer brewing":
                converter = new Converter(name, owner, 12, 10);
                converter.addConsumeMaterial(Material.HOP);
                converter.addProducedMaterial(Material.BEER);
                return converter;
            case "mill":
                converter = new Converter(name, owner, 13, 10);
                converter.addConsumeMaterial(Material.WHEAT);
                converter.addProducedMaterial(Material.FLOUR);
                return converter;
            case "poleturner":
                converter = new Converter(name, owner, 14, 10);
                converter.addConsumeMaterial(Material.WOOD);
                converter.addProducedMaterial(Material.SPEAR);
                return converter;
            case "fletcher":
                converter = new Converter(name, owner, 15, 10);
                converter.addConsumeMaterial(Material.WOOD);
                converter.addProducedMaterial(Material.BOW);
                return converter;
            case "blacksmith":
                converter = new Converter(name, owner, 16, 10);
                converter.addConsumeMaterial(Material.IRON);
                converter.addProducedMaterial(Material.SWORD);
                converter.addProducedMaterial(Material.CUDGEL);
                return converter;
            case "armourer":
                converter = new Converter(name, owner, 17, 10);
                converter.addProducedMaterial(Material.IRON);
                converter.addProducedMaterial(Material.ARMOUR);
                return converter;
            case "food stockpile":
            case "stockpile":
            case "armoury":
                return new Storage(name, owner);
            case "church":
                return new PopularityBooster(name, owner, 10);
            case "cathedral":
                return new PopularityBooster(name, owner, 11);
            case "inn":
                return new PopularityBooster(name, owner, 12);
            case "market":
            case "dairy products":
            case "engineer guild":
            case "mercenary post":
            case "ox tether":
                return new Building(name, owner);
        }
        return null;
    }
}
