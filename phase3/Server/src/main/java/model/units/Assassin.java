package model.units;

import enums.unitEnums.TroopType;
import model.User;
import model.buildings.Building;
import model.buildings.CastleDepartment;

import java.util.ArrayList;

public class Assassin extends Troop {

    private boolean isHidden = true;

    public Assassin(User owner, String name, TroopType troopType) {
        super(owner, name, troopType);
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void getCastleDepartment(ArrayList<Building> buildings) {
        for (Building building : buildings) {
            if (building instanceof CastleDepartment) {
                CastleDepartment castleDepartment = (CastleDepartment) building;
                if (!castleDepartment.getOwner().getUsername().equals(getOwner().getUsername()))
                    castleDepartment.setHidden(true);
            }
        }
    }

}
