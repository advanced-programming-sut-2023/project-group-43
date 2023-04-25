package model.units;

import enums.unitEnums.TroopType;
import enums.unitEnums.UnitsEnum;
import model.User;

public class Unarmed extends Troop{
    public Unarmed(User owner, UnitsEnum name, TroopType troopType) {
        super(owner, name, troopType);
    }
}
