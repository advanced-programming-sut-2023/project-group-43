package model.units;

import enums.unitEnums.TroopType;
import enums.unitEnums.UnitsEnum;
import model.User;

public class Assassin extends Troop {

    private boolean onTopOfTheWall = false;
    public Assassin(User owner, String name, TroopType troopType) {
        super(owner, name, troopType);
        this.setHidden(true);
    }

    public void climbWall() {
        onTopOfTheWall = true;
    }

    //TODO---> When seen by the enemy --> ishidden = false;

}
