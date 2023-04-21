package model.buildings;

import enums.BuildingEnums.BuildingStructure;
import enums.Material;
import model.User;

public class Converter extends Building{

    private final int recourseCapacity;
    private final Material producedMaterial;
    private final int productionRate;
    public Converter(String name, User owner, BuildingStructure buildingStructure, int recourseCapacity, ResourceType consumedRecourse, Material producedMaterial, int productionRate) {
        super(name , owner ,buildingStructure);
        this.recourseCapacity = recourseCapacity;
        this.producedMaterial = producedMaterial;
        this.productionRate = productionRate;
    }
    public void produceMaterial() {};
    public void consumeResource() {};

    public int getRecourseCapacity() {
        return recourseCapacity;
    }

    public Material getProducedMaterial() {
        return producedMaterial;
    }

    public int getProductionRate() {
        return productionRate;
    }
}
