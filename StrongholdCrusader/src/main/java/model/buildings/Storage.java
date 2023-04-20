package model.buildings;

import model.User;

public class Storage extends Building {

    public Storage(User owner, BuildingName name, int hp, int rate) {
        super(owner, name, hp);
    }
}
