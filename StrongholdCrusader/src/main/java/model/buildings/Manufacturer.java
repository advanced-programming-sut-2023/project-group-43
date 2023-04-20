package model.buildings;

import enums.BuildingName;
import model.User;

public class Manufacturer extends Building {
    public Manufacturer(User owner, BuildingName name, int hp) {
        super(owner, name, hp);
    }
}
