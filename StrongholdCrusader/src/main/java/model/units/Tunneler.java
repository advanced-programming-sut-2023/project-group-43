package model.units;

import enums.unitEnums.TroopType;
import enums.unitEnums.UnitsEnum;
import model.User;

public class Tunneler extends Troop{

    public Tunneler(User owner, UnitsEnum name, TroopType troopType) {
        super(owner, name, troopType);
    }
}
