package model.buildings;

import enums.environmentEnums.Materials;
import model.User;

public class Producer extends Building{

    private final Materials producedMaterials;
    private final int productionRate;

    public Producer(String name, User owner, Materials producedMaterials, int productionRate) {
        super(name, owner);
        this.producedMaterials = producedMaterials;
        this.productionRate = productionRate;
    }

    public Materials getProducedMaterials() {
        return producedMaterials;
    }

    public int getProductionRate() {
        return productionRate;
    }
}
