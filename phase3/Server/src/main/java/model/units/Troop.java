package model.units;

import enums.unitEnums.TroopType;
import model.User;

public class Troop extends Unit {
    public Troop(User owner, String name, TroopType troopType) {
        super(owner, name);
    }

    private TroopType troopType;
    private boolean canHide = false;
    private double damage;

    public TroopType getTroopType() {
        return troopType;
    }

    public void setTroopType(TroopType troopType) {
        this.troopType = troopType;
    }

    public boolean isCanHide() {
        return canHide;
    }

    public void setCanHide(boolean canHide) {
        this.canHide = canHide;
    }

    public void attack() {

    }

    public void hide() {
        canHide = true;
    }

    public void shoot() {
    }

}
