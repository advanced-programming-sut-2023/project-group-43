package model.units;

import enums.unitEnums.TroopType;
import enums.unitEnums.UnitsEnum;
import model.*;

public class Troop extends Unit{
    public Troop(User owner, String name, TroopType troopType) {
        super(owner, name);
    }

    private TroopType troopType;
    private boolean canHide;
    private int damage;

    public void attack() {

    }

    public void hide() {}

    public void shoot() {}
}
