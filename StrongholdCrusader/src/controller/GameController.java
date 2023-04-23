package controller;

import java.lang.String;

import enums.Output;
import model.*;
import view.GovernanceMenu;
import view.MapMenu;
import view.StoreMenu;
import view.TradeMenu;

public class GameController {

    private Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public String selectBuilding(int x, int y) {
        return Output.SELECT_BUILDING.getString();
    }

    public String createUnit(String type, int count) {return null;}

    public String repairCastle() {return null;}

    public String selectUnit(int x, int y) {return null;}

    public String moveUnit(int x, int y) {return null;}

    public String patrolUnit(int x1, int y1, int x2, int y2) {return null;}

    public String setUnitState(int x, int y, String state) {return null;}

    public String attack(int x, int y) {return null;}

    private String attackToEnemy(int x, int y) {return null;}

    private String aearialAttack(int x, int y) {return null;}

    public String pourOil(String direction) {return null;}

    public String digTunnel(int x, int y) {return null;}

    public String buildEquipment (String equipmentName) {return null;}

    public String disbandUnit() {return null;}

    public void applyChanges() {}

    private void applyHitPointChange() {}

    private void applyDeathChange() {}

    public void updateForNextTurn() {}

    private void updateResources() {}

    private void updateUnemployedPopulation() {}

    private void updateTaxIncome() {}

    private void updatePopularity() {}

    private void updateFoodRate() {}

    private void updateTaxRate() {}

    private void updateEfficiency() {}

    public boolean isGameEnded() {return false;}

    public String showGameResult() {return null;}

    private void updateScores() {}

    public void clearGame() {}

    public void enterStoreMenu() {
        StoreController storeController = new StoreController(game);
        StoreMenu storeMenu = new StoreMenu(storeController);
        storeMenu.run();
    }

    public void enterTradeMenu() {
        TradeController tradeController = new TradeController(game);
        TradeMenu tradeMenu = new TradeMenu(tradeController);
        tradeMenu.run();
    }

    public void enterGovernanceMenu() {
        GovernanceController governanceController = new GovernanceController(game);
        GovernanceMenu governanceMenu = new GovernanceMenu(governanceController);
        governanceMenu.run();
    }

    public void enterMapMenu() {
        MapController mapController = new MapController(game);
        MapMenu mapMenu = new MapMenu(mapController);
        mapMenu.run();
    }
}
