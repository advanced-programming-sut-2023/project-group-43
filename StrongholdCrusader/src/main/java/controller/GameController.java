package controller;

import java.lang.String;
import java.util.ArrayList;

import enums.Output;
import model.*;
import model.buildings.Building;
import model.units.Unit;

public class GameController {

    private Game game;
    private Building currentSelectedBuilding;
    private int unitDeathHitPoint;
    private int BuildingDeathHitPoint;

    public GameController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public int getUnitDeathHitPoint() {
        return unitDeathHitPoint;
    }

    public void setUnitDeathHitPoint(int unitDeathHitPoint) {
        this.unitDeathHitPoint = unitDeathHitPoint;
    }

    public int getBuildingDeathHitPoint() {
        return BuildingDeathHitPoint;
    }

    public void setBuildingDeathHitPoint(int buildingDeathHitPoint) {
        BuildingDeathHitPoint = buildingDeathHitPoint;
    }

    public Output selectBuilding(int row, int column) {
        if (!this.validCordinate(row, column)) return Output.WRONG_COORDINATES;
        Building building = game.getCells()[row - 1][column - 1].getBuilding();
        if (building != null) {
            this.currentSelectedBuilding = building;
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
                    if (cell[i][j].getBuilding().getHp() == getBuildingDeathHitPoint()) {
                        cell[i][j].getBuilding().getOwner().getGovernance().removeBuilding(cell[i][j].getBuilding());
                        cell[i][j].setBuilding(null);
                    }
                }
                if (cell[i][j].getUnits() != null) {
                    for (int k = 0; k < cell[i][j].getUnits().size(); k++) {
                        if (cell[i][j].getUnits().get(k).getHitPoint() == getUnitDeathHitPoint()) {
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

    private void updateFoodRate() {
        Cell[][] cell = game.getCells();
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[i].length; j++) {
                if (cell[i][j].getUnits().size() != 0)
                    for (int h = 0; h < game.getPlayers().size(); h++) {
                        for (int k = 0; k < cell[i][j].getUnits().size(); k++) {
                            if (cell[i][j].getUnits().get(k).getOwner().equals(game.getPlayers().get(h))) {
                                game.getPlayers().get(h);
                            }
                        }
                    }
            }
        }
    }

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
    public boolean validCordinate(int x, int y) {
        if (x >= 1 && x <= game.getCells().length && y >= 1 && y <= game.getCells()[0].length)
            return true;
        return false;
    }
}
