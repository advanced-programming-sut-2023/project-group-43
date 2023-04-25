package model.units;

import enums.unitEnums.TroopType;
import enums.unitEnums.UnitsEnum;
import model.User;

public class Spearman extends Unarmed{

    public Spearman(User owner, UnitsEnum name, TroopType troopType) {
        super(owner, name, troopType);
    }
}
