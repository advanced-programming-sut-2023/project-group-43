package model.buildings;

import enums.BuildingStructure;
import model.User;

public class PopularityBooster extends Building {

    private int rate;
    private BuildingStructure buildingStructure;
    public PopularityBooster(String name, User owner, BuildingStructure buildingStructure , int rate) {
        super(name, owner, buildingStructure);
        this.rate = rate;
    }

    public void increasePopularity() {};

    public int getRate() {
        return rate;
    }
}

