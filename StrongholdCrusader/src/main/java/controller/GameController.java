package controller;

import java.lang.String;
import java.util.ArrayList;

import enums.Output;
import model.*;
import model.buildings.Building;
import model.units.Unit;

public class GameController {

    private Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
    public Output selectBuilding(int row, int column) {
        if (!(row >= 1 && row <= game.getCells().length && column >= 1 && column <= game.getCells()[0].length))
            return Output.WRONG_COORDINATES;
        Building building = game.getCells()[row - 1][column - 1].getBuilding();
        if (building != null) {
            game.setSelectedBuilding(building);
            return Output.SELECT_BUILDING;
        }
        return Output.NO_BUILDING;
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

    private void applyDeathChange() {
        Cell[][] cell = game.getCells();
        for (int i = 0 ; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if (cell[i][j].getBuilding() != null) {
                    if (cell[i][j].getBuilding().getHp() <= 0) {
                        cell[i][j].getBuilding().getOwner().getGovernance().removeBuilding(cell[i][j].getBuilding());
                        cell[i][j].setBuilding(null);
                    }
                }
                if (cell[i][j].getUnits() != null) {
                    for (int k = 0; k < cell[i][j].getUnits().size(); k++) {
                        if (cell[i][j].getUnits().get(k).getHitPoint() <= 0) {
                            cell[i][j].getUnits().get(k).getOwner().getGovernance().removeUnit(cell[i][j].getUnits().get(k));
                            cell[i][j].getUnits().remove(k);
                        }
                    }
                }
            }
        }
    }

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
