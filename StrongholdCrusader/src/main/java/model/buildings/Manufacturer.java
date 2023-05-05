package model.buildings;
import model.User;

public class Manufacturer extends Producer {


    public Manufacturer(String name, User owner,int productionRate,int capacity) {
        super(name, owner, productionRate, capacity);
    }

}
