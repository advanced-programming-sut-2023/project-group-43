package model.buildings;
import enums.environmentEnums.Materials;
import model.User;

public class Manufacturer extends Producer {


    public Manufacturer(String name, User owner,int productionRate,int capacity) {
        super(name, owner, productionRate, capacity);
    }

}
