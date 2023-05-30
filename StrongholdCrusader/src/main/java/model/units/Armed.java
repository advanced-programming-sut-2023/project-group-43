package model.units;

import enums.environmentEnums.Material;
import enums.unitEnums.ArmedWeapon;
import enums.unitEnums.TroopType;
import model.User;

public class Armed extends Troop {
    public Armed(User owner, String name, TroopType troopType) {
        super(owner, name, troopType);
    }

    Material weapon = ArmedWeapon.getWeaponByUnitName(this.getName());

    public Material getWeapon() {
        return weapon;
    }

}
