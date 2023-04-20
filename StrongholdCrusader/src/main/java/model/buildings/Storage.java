package model.buildings;

import enums.BuildingName;
import model.User;

public class Storage extends Building {
    public Storage(User owner, BuildingName name, int hp) {
        super(owner, name, hp);
    }
}
