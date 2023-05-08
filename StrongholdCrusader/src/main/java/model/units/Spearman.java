package model.units;

import enums.unitEnums.TroopType;
import enums.unitEnums.UnitsEnum;
import model.Cell;
import model.Game;
import model.User;
import model.buildings.Building;

import java.util.ArrayList;

public class Spearman extends Unarmed{

    public Spearman(User owner, String name, TroopType troopType) {
        super(owner, name, troopType);
    }

    public void dropLadder(ArrayList<Building> buildings) {
        for (Building building: buildings) {
            if (building != null) {
                if (building.getOwner().getUsername().equals(getOwner().getUsername())) {
                    building.setLadder(false);
                }
            }
        }
    }
}
