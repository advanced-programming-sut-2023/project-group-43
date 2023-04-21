package model.buildings;

import enums.BuildingEnums.BuildingStructure;
import model.User;

public class PopularityBooster extends Building {

    private int rate;
    public PopularityBooster(String name, User owner, BuildingStructure buildingStructure , int rate) {
        super(name, owner, buildingStructure);
        this.rate = rate;
    }

    public void increasePopularity() {};

    public int getRate() {
        return rate;
    }
}

