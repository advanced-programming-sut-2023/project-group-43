package enums;

import model.units.Troop;

public enum TroopType {
    ;
     TroopType(boolean canHide, int damage, UnitWeapon weapon) {
        this.canHide = canHide;
        this.damage = damage;
        this.weapon = weapon;
    }
    private boolean canHide;
    private int damage;
    private UnitWeapon weapon;

    public UnitWeapon getWeapon() {
        return weapon;
    }

    public boolean canHide() {
        return canHide;
    }

    public int getDamage() {
        return damage;
    }
}
