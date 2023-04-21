package model.buildings;

import enums.BuildingEnums.BuildingStructure;
import model.User;

public class Stronghold extends Building{
    public Stronghold(String name, User owner, BuildingStructure buildingStructure) {
        super(name, owner, buildingStructure);
    }
}
