package model.units;

import enums.unitEnums.TroopType;
import enums.unitEnums.UnitsEnum;
import model.User;

public class Assassin extends Troop {
    public Assassin(User owner, UnitsEnum name, TroopType troopType) {
        super(owner, name, troopType);
    }
}
