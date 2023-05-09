package model.buildings;

import enums.unitEnums.TroopType;
import enums.unitEnums.UnitState;
import model.User;
import model.units.Unarmed;
import model.units.Unit;

public class CagedWarDogs extends Building {
    public CagedWarDogs(String name, User owner) {
        super(name, owner);
        for (int i = 0 ; i < 3; i++) {
            Unarmed dog = new Unarmed(owner, "dog", TroopType.DOG);
            this.getCell().addUnit(dog);
            dog.setState(UnitState.OFFENSIVE);
        }
    }

    public void freeDogs() {
        for (Unit unit: this.getCell().getUnits()) {
            if (unit.getName().equals("dog")) {
                unit.setState(UnitState.OFFENSIVE);
            }
        }
    }
}
