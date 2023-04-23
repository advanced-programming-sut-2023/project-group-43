package enums.unitEnums;

import model.units.Troop;

public enum TroopType {
    ;

    private boolean canHide;
    private int damage;
    private String weapon;
     TroopType(boolean canHide, int damage, String weapon) {
        this.canHide = canHide;
        this.damage = damage;
        this.weapon = weapon;
    }

    public String getWeapon() {
        return weapon;
    }

    public boolean canHide() {
        return canHide;
    }

    public int getDamage() {
        return damage;
    }
}
