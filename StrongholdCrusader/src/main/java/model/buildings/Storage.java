package model.buildings;

import enums.environmentEnums.Material;
import model.User;

import java.util.HashMap;

public class Storage extends Building {

    private int capacity;
    public Storage(String name, User owner, int capacity) {

        super(name, owner);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}


