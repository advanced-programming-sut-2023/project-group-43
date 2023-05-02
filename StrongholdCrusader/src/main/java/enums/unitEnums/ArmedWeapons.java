package enums.unitEnums;

import enums.environmentEnums.Material;

public enum ArmedWeapons {
    //TODO
    //tools ?
    //knight --> sword?
    ARCHER("archer", Material.BOW.getName()),
    CROSSBOWMAN("crossbowman", Material.BOW.getName()),
    SPEARMAN("spearman", Material.SPEAR.getName()),
    SWORDSMAN("swordsman", Material.SWORD.getName()),
    KNIGHT("knight", Material.SPEAR.getName()),
    BLACK_MONK("black monk", Material.CUDGEL.getName()),
    ARCHER_BOW("archer bow", Material.BOW.getName()),
    SLAVE("slave", Material.TORCH.getName()),
    SLINGER("slinger", Material.GRAVEL.getName()),
    HORSE_ARCHER("horse archer", Material.BOW.getName()),
    ARABIAN_SWORDSMAN("arabian swordsman" , Material.SWORD.getName()),
    FIRE_THROWER("fire thrower", Material.TORCH.getName())
    ;
    private String name;
    private String weapon;

    ArmedWeapons(String name, String weapon) {
        this.name = name;
        this.weapon = weapon;
    }
}
