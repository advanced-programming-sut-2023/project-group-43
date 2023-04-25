package model.buildings;
import enums.environmentEnums.Materials;
import model.User;

import java.util.ArrayList;

public class Converter extends Producer {

    private ArrayList<Materials> consumeMaterials = new ArrayList<>();

    public Converter(String name, User owner , int productionRate, int capacity) {
        super(name, owner, productionRate, capacity);
    }

    public void produceMaterial() {};
    public void consumeResource() {};

    public ArrayList<Materials> getConsumeMaterials() {
        return consumeMaterials;
    }

    public void addConsumeMaterial(Materials material){
        consumeMaterials.add(material);
    }
}
