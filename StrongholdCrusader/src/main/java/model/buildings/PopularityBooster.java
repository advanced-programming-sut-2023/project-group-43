package model.buildings;

import model.User;

public class PopularityBooster extends Building {

    private int rate;
    public PopularityBooster(String name, User owner, int rate) {
        super(name, owner);
        this.rate = rate;
    }

    public void increasePopularity() {
        getOwner().getGovernance().setPopularity(getOwner().getGovernance().getPopularity() + rate);
    }

    public int getRate() {
        return rate;
    }
}

