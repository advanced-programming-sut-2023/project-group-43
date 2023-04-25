package enums.unitEnums;

public enum TroopType {
    ARCHER("archer", -1 , , "bow" ),
    CROSSBOWMAN("crossbowman", -1, ,"bow" ),
    SPEARMAN("spearman",,),
    PIKEMAN("pikeman" ,"armed",0,),
    MACEMAN("maceman","",2,),
    SWORDSMAN("swordsman",2,),
    KNIGHT("knight" ,2,),
    TUNNELER("tunneler",0,),
    BLACK_MONK("black monk", 0, ),
    ARCHER_BOW("archer bow" , -1,),
    SLAVE("slave" , -2,),
    SLINGER("slinger" ,-1,),
    ASSASSIN("assassin",0,),
    HORSE_ARCHER("horse archer",-1,),
    ARABIAN_SWORDSMAN("arabian swordsman" , 1,),
    FIRE_THROWER("fire thrower",1,)
    ;

    private String name;
    private String type;
    private boolean canHide;
    private int damage;
    private String weapon;
     TroopType( String name,int damage,String weapon) {
         this.name = name;
         this.type = type;
         this.canHide = canHide;
         this.damage = damage;
         this.weapon = weapon;
    }

}
