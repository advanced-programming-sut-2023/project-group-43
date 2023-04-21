package model.buildings;

import enums.BuildingStructure;
import model.User;

public class Store extends Building {
    public Store(String name, User owner, BuildingStructure buildingStructure) {
        super(name, owner, buildingStructure);
    }
}
