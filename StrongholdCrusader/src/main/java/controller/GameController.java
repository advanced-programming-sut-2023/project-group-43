package controller;

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

    public Output dropBuilding(int x, int y, String type) {return null;}

    public Output selectBuilding(int x, int y) {return null;}

    public Output createUnit(String type, int count) {return null;}

    public Output repairCastle() {return null;}

    public Output selectUnit(int x, int y) {return null;}

    public Output moveUnit(int x, int y) {return null;}

    public Output patrolUnit(int x1, int y1, int x2, int y2) {return null;}

    public Output setUnitState(int x, int y, String state) {return null;}

    public Output attack(int x, int y) {return null;}

    private Output attackToEnemy(int x, int y) {return null;}

    private Output airAttack(int x, int y) {return null;}

    public Output pourOil(String direction) {return null;}

    public Output digTunnel(int x, int y) {return null;}

    public Output buildEquipment (String equipmentName) {return null;}

    public Output disbandUnit() {return null;}

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

    public Output showGameResult() {return null;}

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
