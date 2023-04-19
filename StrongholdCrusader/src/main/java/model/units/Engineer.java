package model.units;

import enums.UnitName;
import model.*;

public class Engineer extends Unit{
    public Engineer(User owner, UnitName name) {
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
