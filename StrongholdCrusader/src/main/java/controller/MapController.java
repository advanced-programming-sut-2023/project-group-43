package controller;

import enums.Output;
import model.DataBase;

public class MapController {

    private DataBase dataBase;

    public MapController(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    public static Output showMap(int x, int y) {}

    public static Output moveMap(String verticalDirection, String horizontalDirection) {}

    public static Output showDetails(int x, int y) {}

}
