package model.buildings;
import enums.environmentEnums.Materials;
import model.User;

public class Converter extends Building{

    private final int recourseCapacity;
    private final Materials producedMaterials;
    private final int productionRate;
    public Converter(String name, User owner, int recourseCapacity, Materials consumedRecourse, Materials producedMaterials, int productionRate) {
        super(name , owner);
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
