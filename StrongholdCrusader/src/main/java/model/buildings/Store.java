package model.buildings;

import enums.Buildings;
import model.User;

public class Store extends Building {
    public Store(String name, User owner, Buildings buildings) {
        super(name, owner, buildings);
    }
}
