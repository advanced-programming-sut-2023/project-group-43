package model.units;

import enums.unitEnums.TroopType;
import model.Game;
import model.User;
import model.buildings.Building;

public class Tunneler extends Unit {
    public Tunneler(User owner, String name, TroopType troopType) {
        super(owner, name);
    }

    public void destroyBuilding(Game game) {
        Building building = getCell().getBuilding();
        if (building == null) return;
        if (!building.getOwner().getUsername().equals(getOwner().getUsername()))
            building.setHp(building.getHp() - 100);
    }
}
