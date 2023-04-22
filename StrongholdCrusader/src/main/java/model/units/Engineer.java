package model.units;

import enums.unitEnums.UnitsEnum;
import model.*;

public class Engineer extends Unit{
    public Engineer(User owner, UnitsEnum name) {
        super(owner, name);
        hasOil = false;
    }
    private boolean hasOil;

    public void setHasOil(boolean hasOil) {
        this.hasOil = hasOil;
    }

    public boolean hasOIl() {
        return hasOil;
    }
}
