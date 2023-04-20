package model.buildings;

import enums.Material;
import model.User;

public class Manufacturer extends Building {

    private final Material producedMaterial;
    private final int productionRate;
    public Manufacturer(User owner, BuildingName name, int hp, Material producedMaterial, int productionRate) {
        super(owner, name, hp);
        this.producedMaterial = producedMaterial;
        this.productionRate = productionRate;
    }

    public Material getProducedMaterial() {
        return producedMaterial;
    }

    public int getProductionRate() {
        return productionRate;
    }
}
