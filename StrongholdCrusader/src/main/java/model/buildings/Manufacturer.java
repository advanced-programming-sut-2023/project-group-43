package model.buildings;
import enums.Buildings;
import enums.Materials;
import model.User;

public class Manufacturer extends Building {

    private final Materials producedMaterials;
    private final int productionRate;

    public Manufacturer(String name, User owner, Buildings buildings, Materials producedMaterials, int productionRate) {
        super(name, owner, buildings);
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
