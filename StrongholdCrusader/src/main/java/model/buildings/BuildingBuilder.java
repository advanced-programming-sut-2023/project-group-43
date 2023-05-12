package model.buildings;

import enums.environmentEnums.Material;
import model.User;

public class BuildingBuilder {

    public static Converter converter;
    public static Producer producer;

    public static Building BuildingBuilder(String name, User owner) {
        //TODO : we should declare people capacity with details
        switch (name) {
            case "small stone gatehouse":
                return new CastleDepartment(name, owner, 8, 0, 0);
            case "big stone gatehouse":
                return new CastleDepartment(name, owner, 10, 0, 0);
            case "draw bridge":
                return new CastleDepartment(name, owner, 5, 0, 0);
            case "look out tower":
                return new CastleDepartment(name, owner, 15, 10, 2);
            case "perimeter tower":
            case "defensive turret":
                return new CastleDepartment(name, owner, 15, 20, 1);
            case "square tower":
            case "circle tower":
                return new CastleDepartment(name, owner, 15, 10, 1);
            case "caged war dogs":
                return new CagedWarDogs(name , owner);
            case "wheat farm":
                producer = new Producer(name, owner, 10, 10);
                producer.addProducedMaterial(Material.WHEAT);
                return producer;
            case "hop farm":
                producer = new Producer(name, owner, 11, 10);
                producer.addProducedMaterial(Material.HOP);
                return producer;
            case "hunting post":
                producer = new Producer(name, owner, 12, 10);
                producer.addProducedMaterial(Material.MEAT);
                return producer;
            case "apple garden":
                producer = new Producer(name, owner, 13, 10);
                producer.addProducedMaterial(Material.APPLE);
                return producer;
            case "stable":
                producer = new Producer(name, owner, 14, 10);
                producer.addProducedMaterial(Material.HORSE);
                return producer;
            case "wood cutter":
                producer = new Producer(name, owner, 16, 10);
                producer.addProducedMaterial(Material.WOOD);
                return producer;
            case "pitch rig":
                producer = new Producer(name, owner, 17, 10);
                producer.addProducedMaterial(Material.TAR);
                return producer;
            case "quarry":
                producer = new Producer(name, owner, 18, 10);
                producer.addProducedMaterial(Material.STONE);
                return producer;
            case "iron mine":
                producer = new Producer(name, owner, 19, 10);
                producer.addProducedMaterial(Material.IRON);
                return producer;
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
                return new Storage(name, owner, 100);
            case "stockpile":
            case "armoury":
                return new Storage(name, owner, 1000);
            case "church":
                return new PopularityBooster(name, owner, 10);
            case "cathedral":
                return new PopularityBooster(name, owner, 11);
            case "inn":
                return new PopularityBooster(name, owner, 12);
            case "pitch ditch":
            case "oil smelter":
            case "siege tent":
            case "market":
            case "dairy products":
            case "engineer guild":
            case "mercenary post":
            case "ox tether":
            case "hovel":
                return new Building(name, owner);
        }
        return null;
    }
}
