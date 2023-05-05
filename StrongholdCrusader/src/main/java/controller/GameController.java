package controller;

import java.lang.String;
import java.util.ArrayList;

import enums.Output;
import enums.environmentEnums.Material;
import enums.unitEnums.UnitsEnum;
import model.*;
import model.buildings.Building;
import model.units.Troop;
import model.units.Unit;
import model.units.UnitsBuilder;


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


    //create unit function should run during the turn and after updating add the users that has been built.
    public Output createUnit(String unitName, int count) {
        if(UnitsEnum.getUnitByName(unitName).equals(null))
            return Output.WRONG_UNIT_NAME;
        if(count <= 0)
            return Output.INVALID_NUMBER; //TODO
        Unit unit = UnitsBuilder.unitsBuilder(unitName , game.getCurrentPlayer());
        return checkUnitSupplies(unit);
    }

    public Output checkUnitSupplies(Unit unit){
        if(unit.getCost() > game.getCurrentPlayer().getGovernance().getGold())
            return Output.NOT_ENOUGH_MONEY;
        if(unit instanceof Troop){
            Material weapon = (Troop)(unit).get;
        }


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
