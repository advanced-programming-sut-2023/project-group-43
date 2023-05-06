package enums.unitEnums;

import enums.Degrees.DamageDegree;

public enum TroopType {
    ARCHER("archer", DamageDegree.LOW.getDegree(), true, false, false),
    CROSSBOWMAN("crossbowman", DamageDegree.LOW.getDegree(), true, false, true),
    SPEARMAN("spearman", DamageDegree.AVERAGE.getDegree(), true, false, false),
    PIKEMAN("pikeman", DamageDegree.AVERAGE.getDegree(), false, false, true),
    MACEMAN("maceman", DamageDegree.HIGH.getDegree(), false, false, false),
    SWORDSMAN("swordsman", DamageDegree.VERY_HIGH.getDegree(), true, false, true),
    KNIGHT("knight", DamageDegree.VERY_HIGH.getDegree(), true, true, false),
    TUNNELER("tunneler", DamageDegree.AVERAGE.getDegree(), false, false, false),
    BLACK_MONK("black monk", DamageDegree.AVERAGE.getDegree(), true, false, true),
    ARCHER_BOW("archer bow", DamageDegree.LOW.getDegree(), true, false, false),
    SLAVE("slave", DamageDegree.VERY_LOW.getDegree(), true, false, false),
    SLINGER("slinger", DamageDegree.LOW.getDegree(), true, false, false),
    ASSASSIN("assassin", DamageDegree.AVERAGE.getDegree(), false, false, false),
    HORSE_ARCHER("horse archer", DamageDegree.LOW.getDegree(), true, true, false),
    ARABIAN_SWORDSMAN("arabian swordsman", DamageDegree.HIGH.getDegree(), true, true, false),
    FIRE_THROWER("fire thrower", DamageDegree.HIGH.getDegree(), true, false, false),
    DOG("dog", DamageDegree.HIGH.getDegree(), false, false, false);

    private String name;
    private int damage;
    private Boolean hasWeapon;
    private Boolean hasHorse;
    private Boolean hasArmour;

    TroopType(String name, int damage, Boolean hasWeapon, Boolean hasHorse, Boolean hasArmour) {
        this.name = name;
        this.damage = damage;
        this.hasWeapon = hasWeapon;
        this.hasHorse = hasHorse;
        this.hasArmour = hasArmour;
    }

    public int getDamage() {
        return damage;
    }

    public Boolean getHasWeapon() {
        return hasWeapon;
    }

    public Boolean getHasHorse() {
        return hasHorse;
    }

    public Boolean getHasArmour() {
        return hasArmour;
    }
}
