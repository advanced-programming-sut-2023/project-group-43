package model.buildings;
import enums.BuildingStructure;
import enums.Material;
import model.User;

public class Manufacturer extends Building {

    private final Material producedMaterial;
    private final int productionRate;

    public Manufacturer(String name, User owner, BuildingStructure buildingStructure, Material producedMaterial, int productionRate) {
        super(name, owner, buildingStructure);
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
