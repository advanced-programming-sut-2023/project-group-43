package model.units;

import enums.unitEnums.TroopType;
import model.User;

public class UnitsBuilder {
    public Unit UnitsBuilder(String name, User owner) {
        switch (name) {
            case "archer":
                return new Armed(owner, name, TroopType.ARCHER);
            case "crossbowman":
                return new Armed(owner, name, TroopType.CROSSBOWMAN);
            case "swordsman":
                return new Armed(owner, name, TroopType.SWORDSMAN);
            case "black monk":
                return new Armed(owner, name, TroopType.BLACK_MONK);
            case "archer bow":
                return new Armed(owner, name, TroopType.ARCHER_BOW);
            case "slave":
                return new Armed(owner, name, TroopType.SLAVE);
            case "slinger":
                return new Armed(owner, name, TroopType.SLINGER);
            case "horse archer":
                return new Armed(owner, name, TroopType.HORSE_ARCHER);
            case "arabian swordsman":
                return new Armed(owner, name, TroopType.ARABIAN_SWORDSMAN);
            case "fire thrower":
                    return new Armed(owner, name, TroopType.FIRE_THROWER);
            case "spearman":
                return new Spearman(owner, name, TroopType.SPEARMAN);
            case "pikeman":
                return new Unarmed(owner, name, TroopType.PIKEMAN);
            case "maceman":
                return new Unarmed(owner, name, TroopType.MACEMAN);
            case "knight":
                return new Unarmed(owner, name, TroopType.KNIGHT);
            case "tunneler":
                return new Tunneler(owner, name, TroopType.TUNNELER);
            case "ladderman":
                return new Ladderman(owner, name);
            case "engineer":
                return new Engineer(owner, name);
            case "assassin":
                return new Assassin(owner, name, TroopType.ASSASSIN);

        }
        return null;
    }
}
