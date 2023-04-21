package model.buildings;

import enums.BuildingEnums.BuildingStructure;
import model.User;

public class Storage extends Building {
    public Storage(String name, User owner, BuildingStructure buildingStructure) {
        super(name, owner, buildingStructure);
    }
}
