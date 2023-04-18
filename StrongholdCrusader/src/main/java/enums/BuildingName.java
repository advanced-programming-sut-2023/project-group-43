package enums;

public class BuildingName {

    private int cost;
    private int hitPoint;
    private int peopleCapacity;
    private BuildingType buildingType;


    public BuildingName(int cost, int hitPoint, int peopleCapacity, BuildingType buildingType ) {
    }

    public int getCost() {
        return cost;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }
}
