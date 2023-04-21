package model.buildings;

import enums.Buildings;
import model.User;

public class PopularityBooster extends Building {

    private int rate;
    private Buildings buildings;
    public PopularityBooster(String name, User owner, Buildings buildings, int rate) {
        super(name, owner, buildings);
        this.rate = rate;
    }

    public void increasePopularity() {};

    public int getRate() {
        return rate;
    }
}

