package enums.unitEnums;

public enum UnitState {
    STANDING,
    DEFENSIVE,
    MOVING,
    OFFENSIVE;
    public static UnitState getUnitStateByName(String name){
        switch (name){
            case "standing":
                return STANDING;
            case "defensive":
                return DEFENSIVE;
            case "offensive":
                return OFFENSIVE;
            default:
                return null;
        }
    }
}

