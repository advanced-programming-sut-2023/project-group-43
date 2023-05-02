package controller;

import java.lang.String;
import java.util.ArrayList;

import enums.Output;
import model.*;
import model.buildings.Building;
import model.units.Unit;

public class GameController {

    private Game game;
    private boolean selected;
    private int selectedX;
    private int selectedY;
    private Building currentSelectedBuilding;

    public GameController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public Output selectBuilding(int x, int y) {
        return Output.SELECT_BUILDING;
    }

    public Output createUnit(String type, int count) {

    }

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

    public Output buildWeapon (String name) {return null;}

    public Output disbandUnit() {return null;}

    //Update handler
    public void applyChanges() {
        for(int i=0 ; i < game.getCells().length ; i++){
            for (int j = 0 ; j < game.getCells().length ; j++){
                applyUnitChanges(game.getCells()[i][j].getUnits());
                if(game.getCells()[i][j].getBuilding() != null)
                    applyBuildingChanges(game.getCells()[i][j].getBuilding());
            }
        }
    }

    public void applyUnitChanges(ArrayList<Unit>units){

    }
    public void applyBuildingChanges(Building building){

    }
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
