package model.buildings;

import model.User;

public class PopularityBooster extends Building {
    private final int rate;
    public PopularityBooster(User owner, BuildingName name, int hp, int rate) {
        super(owner, name, hp);
        this.rate = rate;
    }

    public void increasePopularity() {};

    public int getRate() {
        return rate;
    }
}

