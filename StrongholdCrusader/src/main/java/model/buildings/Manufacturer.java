package model.buildings;
import enums.environmentEnums.Materials;
import model.User;

public class Manufacturer extends Producer {

    private final Materials producedMaterials;
    private final int productionRate;

    public Manufacturer(String name, User owner, Materials producedMaterials, int productionRate) {
        super(name, owner, producedMaterials, productionRate, capacity);
        this.producedMaterials = producedMaterials;
        this.productionRate = productionRate;
    }


    public Materials getProducedMaterial() {
        return producedMaterials;
    }

    public int getProductionRate() {
        return productionRate;
    }
}
