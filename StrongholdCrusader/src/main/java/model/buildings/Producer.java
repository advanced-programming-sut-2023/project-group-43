package model.buildings;

import enums.environmentEnums.Material;
import model.User;

import java.util.ArrayList;

public class Producer extends Building {

    private ArrayList<Material> producedMaterials = new ArrayList<>();
    private final int productionRate;
    private final int capacity;

    public Producer(String name, User owner, int productionRate, int capacity) {
        super(name, owner);
        this.productionRate = productionRate;
        this.capacity = capacity;
    }

    public void addProducedMaterial(Material material) {
        producedMaterials.add(material);
    }

    public ArrayList<Material> getProducedMaterials() {
        return producedMaterials;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void produceMaterials() {
        for (Material material : producedMaterials) {
            getOwner().getGovernance().getGovernanceResource().changeAmountOfItemInStockpile(material, productionRate);
        }
    }
}

