package enums.unitEnums;

import enums.Degrees.DamageDegree;
import enums.environmentEnums.Materials;

public enum ArmedWeapons {
    //TODO
    //tools ?
    //knight --> sword?
    ARCHER("archer",Materials.BOW.getName()),
    CROSSBOWMAN("crossbowman",Materials.BOW.getName()),
    SPEARMAN("spearman",Materials.SPEAR.getName()),
    SWORDSMAN("swordsman",Materials.SWORD.getName()),
    KNIGHT("knight",Materials.SPEAR.getName()),
    BLACK_MONK("black monk",Materials.CUDGEL.getName()),
    ARCHER_BOW("archer bow",Materials.BOW.getName()),
    SLAVE("slave",Materials.TORCH.getName()),
    SLINGER("slinger",Materials.GRAVEL.getName()),
    HORSE_ARCHER("horse archer",Materials.BOW.getName()),
    ARABIAN_SWORDSMAN("arabian swordsman" ,Materials.SWORD.getName()),
    FIRE_THROWER("fire thrower",Materials.TORCH.getName())
    ;
    private String name;
    private String weapon;

    ArmedWeapons(String name, String weapon) {
        this.name = name;
        this.weapon = weapon;
    }
}
