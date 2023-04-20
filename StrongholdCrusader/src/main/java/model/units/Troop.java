package model.units;

import enums.TroopType;
import enums.UnitName;
import enums.UnitWeapon;
import model.*;

public class Troop extends Unit{
    public Troop(User owner, UnitName name, TroopType troopType) {
        super(owner, name);
        this.canHide = troopType.canHide();
        this.damage = troopType.getDamage();
        this.weapon = troopType.getWeapon();
    }

    private TroopType troopType;
    private boolean canHide;
    private UnitWeapon weapon;
    private int damage;

    public void attack() {

    }

    public void hide() {}

    public void shoot() {}
}
