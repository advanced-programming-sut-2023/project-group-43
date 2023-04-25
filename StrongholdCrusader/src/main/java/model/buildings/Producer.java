package model.buildings;

import enums.environmentEnums.Materials;
import model.User;

import java.util.ArrayList;

public class Producer extends Building{

    private ArrayList<Materials> producedMaterials = new ArrayList<>();
    private final int productionRate;
    private final int capacity;

    public Producer(String name, User owner, int productionRate, int capacity) {
        super(name, owner);
        this.productionRate = productionRate;
        this.capacity = capacity;
    }

    public void addProducedMaterial(Materials material){
        producedMaterials.add(material);
    }

    public ArrayList<Materials> getProducedMaterials() {
        return producedMaterials;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public int getCapacity() {
        return capacity;
    }
}

