package model.buildings;

import enums.BuildingName;
import model.User;

public class Converter extends Building{
    public Converter(User owner, BuildingName name, int hp) {
        super(owner, name, hp);
    }
}
