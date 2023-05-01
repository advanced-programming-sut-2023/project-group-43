package controller;

import java.lang.String;

import enums.Output;
import model.*;
import model.units.Armed;
import model.units.Troop;
import model.units.Unit;
import model.units.UnitsBuilder;

public class GameController {

    private Game game;
    private boolean selected;
    private int selectedX;
    private int selectedY;

    public GameController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public Output selectBuilding(int x, int y) {
        //TODO: if x and y ---> invalid ---> error
        selectedX = x;
        selectedY = y;
        return Output.SELECT_BUILDING;
    }

    public Output createUnit(String name, int count) {
        //TODO :  I'm not sure this type casting is valid
        Troop troop = (Troop) UnitsBuilder.UnitsBuilder(name,game.getCurrentPlayer());
        if(troop == null)
            return Output.WRONG_UNIT_NAME;
        if(troop.getCost() * count > game.getCurrentPlayer().getGold())
            return Output.NOT_ENOUGH_MONEY;
        if(game.getCurrentPlayer().getGovernance().getPopulation() < count)
            return Output.NOT_ENOUGH_POPULATION;
        return Output.SUCCESSFUL_UNIT_CREATION;
    }

    public Output repairCastle() {return null;}

    public Output selectUnit(int x, int y) {return null;}

    public Output moveUnit(int x, int y) {return null;}

    public Output patrolUnit(int x1, int y1, int x2, int y2) {return null;}

    public Output setUnitState(int x, int y, String state) {return null;}

    public Output attack(int x, int y ,String item) {
        if(item.equals())
            attackToEnemy(x,y);
        if(item.equals())
            aearialAttack(x,y);
    }

    private Output attackToEnemy(int x, int y) {return null;}

    private Output aearialAttack(int x, int y) {return null;}

    public Output pourOil(String direction) {return null;}

    public Output digTunnel(int x, int y) {return null;}

    public Output buildWeapon (String name) {return null;}

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

    public void resetSelectCell(){
        selectedX = 0;
        selectedY = 0;
    }

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
