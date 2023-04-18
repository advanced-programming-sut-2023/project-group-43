package controller;

import enums.Output;
import model.DataBase;

public class ChangeEnvironmentController {

    private DataBase dataBase;

    public ChangeEnvironmentController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public static void generateMap(int numberOfPlayers, int size) {}

    public static Output chooseMap(int numberOfPlayers, int size) {return null;}

    public static Output setTexture(int x, int y, String texture) {return null;}

    public static Output setTextureRectangle(int x1, int y1, int x2, int y2, String texture) {return null;}

    public static Output clearMap(int x, int y) {return null;}

    public static Output dropRock(int x, int y, String direction) {return null;}

    public static Output dropTree(int x, int y, String type) {return null;}

    public static Output dropBuilding(int x, int y, String type) {return null;}

    public static Output dropUnit(int x, int y, String type, int count) {return null;}

}
