package controller;

import enums.Output;
import model.DataBase;

public class GameController {

    public static Output dropBuilding(int x, int y, String type) {return null;}

    public static Output selectBuilding(int x, int y) {return null;}

    public static Output createUnit(String type, int count) {return null;}

    public static Output repairCastle() {return null;}

    public static Output selectUnit(int x, int y) {return null;}

    public static Output moveUnit(int x, int y) {return null;}

    public static Output patrolUnit(int x1, int y1, int x2, int y2) {return null;}

    public static Output setUnitState(int x, int y, String state) {return null;}

    public static Output attack(int x, int y) {return null;}

    private static Output attackToEnemy(int x, int y) {return null;}

    private static Output airAttack(int x, int y) {return null;}

    public static Output pourOil(String direction) {return null;}

    public static Output digTunnel(int x, int y) {return null;}

    public static Output buildEquipment (String equipmentName) {return null;}

    public static Output disbandUnit() {return null;}

    public static void applyChanges() {}

    private static void applyHitPointChange() {}

    private static void applyDeathChange() {}

    public static void updateForNextTurn() {}

    private static void updateResources() {}

    private static void updateUnemployedPopulation() {}

    private static void updateTaxIncome() {}

    private static void updatePopularity() {}

    private static void updateFoodRate() {}

    private static void updateTaxRate() {}

    private static void updateEfficiency() {}

    public static boolean isGameEnded() {return false;}

    public static Output showGameResult() {return null;}

    private static void updateScores() {}

    public static void clearGame() {}
}
