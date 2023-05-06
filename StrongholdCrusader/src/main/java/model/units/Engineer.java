package model.units;

import enums.unitEnums.UnitsEnum;
import model.*;

public class Engineer extends Unit{
    public Engineer(User owner, String name) {
        super(owner, name);
        hasOil = false;
    }
    private boolean hasOil;

    public void chargeTar(){

    }
}
