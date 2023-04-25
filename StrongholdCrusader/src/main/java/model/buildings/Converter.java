package model.buildings;
import enums.environmentEnums.Materials;
import model.User;

public class Converter extends Producer {

    private final int recourseCapacity;

    public Converter(String name, User owner, Materials producedMaterials, int productionRate, int recourseCapacity) {
        super(name, owner, producedMaterials, productionRate);
        this.recourseCapacity = recourseCapacity;
    }

    public void produceMaterial() {};
    public void consumeResource() {};

    public int getRecourseCapacity() {
        return recourseCapacity;
    }
}
