package model.buildings;
import enums.Buildings;
import enums.Materials;
import model.User;

public class Converter extends Building{

    private final int recourseCapacity;
    private final Materials producedMaterials;
    private final int productionRate;
    public Converter(String name, User owner, Buildings buildings, int recourseCapacity, Materials consumedRecourse, Materials producedMaterials, int productionRate) {
        super(name , owner , buildings);
        this.recourseCapacity = recourseCapacity;
        this.producedMaterials = producedMaterials;
        this.productionRate = productionRate;
    }
    public void produceMaterial() {};
    public void consumeResource() {};

    public int getRecourseCapacity() {
        return recourseCapacity;
    }

    public Materials getProducedMaterial() {
        return producedMaterials;
    }

    public int getProductionRate() {
        return productionRate;
    }
}
