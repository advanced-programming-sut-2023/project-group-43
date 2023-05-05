package model.buildings;
import enums.environmentEnums.Material;
import model.User;

import java.util.ArrayList;

public class Converter extends Producer {

    private ArrayList<Material> consumeMaterials = new ArrayList<>();

    public Converter(String name, User owner , int productionRate, int capacity) {
        super(name, owner, productionRate, capacity);
    }
    public void consumeResource() {
        for(Material material: consumeMaterials) {
            getOwner().getGovernance().getGovernanceResource().changeAmountOfItemInStockpile(material, getProductionRate());
        }
    }

    public ArrayList<Material> getConsumeMaterials() {
        return consumeMaterials;
    }

    public void addConsumeMaterial(Material material){
        consumeMaterials.add(material);
    }
}
