package model.buildings;
import enums.environmentEnums.Materials;
import model.User;

import java.util.ArrayList;

public class Converter extends Producer {

    private final Materials consumeMaterials;

    public Converter(String name, User owner, Materials producedMaterials,Materials consumeMaterials, int productionRate, int capacity) {
        super(name, owner, producedMaterials, productionRate, capacity);
        this.consumeMaterials = consumeMaterials;
    }

    public void produceMaterial() {};
    public void consumeResource() {};

    public Materials getConsumeMaterials() {
        return consumeMaterials;
    }
}
