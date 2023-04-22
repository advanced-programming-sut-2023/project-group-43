package model.units;

import enums.unitEnums.TroopType;
import enums.unitEnums.Units;
import model.*;

public class Troop extends Unit{
    public Troop(User owner, Units name, TroopType troopType) {
        super(owner, name);
        this.canHide = troopType.canHide();
        this.damage = troopType.getDamage();
        this.weapon = troopType.getWeapon();
    }

    private TroopType troopType;
    private boolean canHide;
    private String weapon;
    private int damage;

    public void attack() {

    }

    public void hide() {}

    public void shoot() {}
}
