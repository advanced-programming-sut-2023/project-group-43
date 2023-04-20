package model.buildings;

import enums.BuildingName;
import model.User;

public class PopularityBooster extends Building {
    public PopularityBooster(User owner, BuildingName name, int hp) {
        super(owner, name, hp);
    }
}
