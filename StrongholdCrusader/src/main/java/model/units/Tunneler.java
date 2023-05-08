package model.units;

import enums.unitEnums.TroopType;
import model.User;

public class Tunneler extends Troop{

    private boolean isHidden = false;
    public Tunneler(User owner, String name, TroopType troopType) {
        super(owner, name, troopType);
    }

    public boolean isHidden() {
        return isHidden;
    }
}
