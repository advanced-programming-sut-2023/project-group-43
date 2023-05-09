package model.units;

import controller.GameController;
import model.Cell;
import model.Game;
import model.User;
import model.buildings.Building;

import java.util.ArrayList;

public class Ladderman extends Unit {
    public Ladderman(User owner, String name) {
        super(owner, name);
    }
    public void addLadder(ArrayList<Building> buildings) {
        for (Building building: buildings) {
            if (building != null) {
                if (!building.getOwner().getUsername().equals(getOwner().getUsername())) {
                    building.setLadder(true);
                }
            }
        }
    }
}
