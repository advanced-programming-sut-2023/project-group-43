package model.buildings;

import enums.BuildingName;
import model.User;

public class CastleDepartment extends Building {
    public CastleDepartment(User owner, BuildingName name, int hp) {
        super(owner, name, hp);
    }
}
