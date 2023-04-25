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

    public Game getGame() {
        return game;
    }

    public Output selectBuilding(int x, int y) {
        return Output.SELECT_BUILDING;
    }

    public Output createUnit(String type, int count) {return null;}

    public Output repairCastle() {return null;}

    public Output selectUnit(int x, int y) {return null;}

    public Output moveUnit(int x, int y) {return null;}

    public Output patrolUnit(int x1, int y1, int x2, int y2) {return null;}

    public Output setUnitState(int x, int y, String state) {return null;}

    public Output attack(int x, int y ,String item) {return null;}

    private Output attackToEnemy(int x, int y) {return null;}

    private Output aearialAttack(int x, int y) {return null;}

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

    public String showGameResult() {return null;}

    private void updateScores() {}

    public void clearGame() {}

    public void goToNextPerson() {
        User user = null;
        boolean isNextPlayerFound = false;
        for (User player: game.getPlayers()) {
            if (isNextPlayerFound)
                game.setCurrentPlayer(player);
            if (player.getUsername().equals(game.getCurrentPlayer().getUsername())) {
                isNextPlayerFound = true;
            }
        }
        if (user == null) game.setCurrentPlayer(game.getPlayers().get(0));
    }

}
