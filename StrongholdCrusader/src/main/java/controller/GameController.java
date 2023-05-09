package controller;
import java.lang.String;
import java.util.ArrayList;
import enums.BuildingEnums.BuildingEnum;
import enums.Output;
import enums.RateNumber;
import enums.environmentEnums.Material;
import enums.unitEnums.ArmedWeapon;
import enums.unitEnums.UnitState;
import enums.unitEnums.UnitsEnum;
import model.*;
import model.buildings.Building;
import model.buildings.Converter;
import model.units.Unit;
import model.units.UnitsBuilder;

import model.units.Engineer;

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

    public Output createUnit(String name, int number) {
        Governance governance = game.getCurrentPlayer().getGovernance();
        if(number <= 0)return Output.INVALID_NUMBER;
        if(!game.getSelectedBuilding().getName().equals("barrack"))
            return Output.WRONG_SELECT_FOR_BUILDING;
        Unit unit = UnitsBuilder.unitsBuilder(name , game.getCurrentUser());
        String unitType = UnitsEnum.getTypeByUnitName(unit.getName());
        if(unitType == null)
            return Output.WRONG_UNIT_NAME;
        //Optional: Even if we could do it, we wouldn't make less than the number
        if(unit.getCost() * number> governance.getGold())
            return Output.NOT_ENOUGH_MONEY;
        if(number > governance.getUnemployedPopulation())
            return Output.NOT_ENOUGH_POPULATION;
        if(unitType.equals("armed")) {
            Material weapon = ArmedWeapon.getWeaponByUnitName(unit.getName());
            if(governance.getGovernanceResource().getAmountOfItemInStockpile(weapon) < number)
                return Output.NOT_ENOUGH_WEAPON;
            governance.getGovernanceResource().changeAmountOfItemInStockpile(weapon,number);
        }
        for(int i = 0 ; i < number ; i++){
            game.getCurrentUser().getGovernance().addUnit(unit);
        }
        governance.setGold(governance.getGold() - unit.getCost() * number);
        governance.setUnemployedPopulation(governance.getUnemployedPopulation() - number);
        return Output.SUCCESSFUL_UNIT_CREATION;
    }

    public Output repairCastle() {
        if (!game.getSelectedBuilding().getOwner().equals(game.getCurrentPlayer()))
            return Output.THIS_IS_NOT_YOUR_BUILDING;
        else if (game.getCurrentPlayer().getGovernance().getGovernanceResource().getAmountOfItemInStockpile(Material.STONE) < game.getSelectedBuilding().getStone())
            return Output.NOT_ENOUGH_STONE;
        else {
            game.getSelectedBuilding().setHp(BuildingEnum.getBuildingStructureByName(game.getSelectedBuilding().getName()).getHp());
            game.getCurrentPlayer().getGovernance().getGovernanceResource().changeAmountOfItemInStockpile(Material.STONE, (-1 * game.getSelectedBuilding().getStone()));
            return Output.SUCCESSFUL_REPAIRMENT;
        }
    }

    public Output selectUnit(int x, int y, String type) {
        ArrayList<Unit> cellUnits = game.getCells()[x - 1][y - 1].getUnits();
        ArrayList<Unit> resultcellUnits = new ArrayList<>();
        if (cellUnits.size() == 0) return Output.NO_UNIT;
        else {
            for (int i = 0; i < cellUnits.size(); i++) {
                if ((false == cellUnits.get(i).isHidden()) && cellUnits.get(i).getName().equals(type) && cellUnits.get(i).getOwner().equals(game.getCurrentPlayer())) {
                    resultcellUnits.add(cellUnits.get(i));
                }
            }
            if (resultcellUnits.size() == 0)
                return Output.NO_THIS_TYPE_UNIT;
            else {
                game.setSelectedUnit(resultcellUnits);
                return Output.SELECT_UNIT;
            }
        }
    }

    public Output moveUnit(int x, int y) {
        if (isCoordinateInvalid(x, y)) return Output.INVALID_NUMBER;
        for(Unit unit: game.getSelectedUnit()) {
            unit.setCurrentTargetX(x - 1);
            unit.setCurrentTargetY(y - 1);
        }
        return Output.SUCCESSFUL_ACTION;
    }

    public Output patrolUnit(int x1, int y1, int x2, int y2) {
        if (isCoordinateInvalid(x1, y1) || isCoordinateInvalid(x2, y2))
            return Output.INVALID_NUMBER;
        for(Unit unit: game.getSelectedUnit()) {
            unit.setCurrentTargetX(x1 - 1);
            unit.setCurrentTargetY(y1 - 1);
            unit.setNextTargetX(x2 - 1);
            unit.setNextTargetY(y2 - 1);
        }
        return Output.SUCCESSFUL_ACTION;
    }

    private boolean isCoordinateInvalid(int x, int y) {
        return (x <= 0 || y <= 0 || x >= game.getRow() || y >= game.getColumn());
    }

    public Output setUnitState(int x, int y, String state) {
        ArrayList<Unit> units = game.getCells()[x - 1][y - 1].getUnits();
        UnitState unitState = UnitState.getUnitStateByName(state);
        if (unitState == null) return Output.INVALID_STATE;
        for (Unit unit: units) {
            unit.setState(unitState);
        }
        return Output.UNIT_STATE_SETTED_SUCCESSFULLY;
    }

    public Output attack(int x, int y ,String item) {
        if(item.equals("e"))
            return attackToEnemy(x,y);
        if(item.equals("x"))
            return aearialAttack(x,y);
        return null;
    }

    private Output attackToEnemy(int x, int y) {return null;}

    private Output aearialAttack(int x, int y) {return null;}

    public Output pourOil(String direction) {
        Engineer engineer = null;
        for (int i = 0; i < game.getSelectedUnit().size(); i++) {
            if (game.getSelectedUnit().get(i) instanceof Engineer) {
                engineer = (Engineer) game.getSelectedUnit().get(i);
                break;
            }
        }
        if (engineer == null) return Output.NO_ENGINEER_HERE;
        else {
            //TODO pouring oil and go to oil
        }
        return null;
    }

    public Output digTunnel(int x, int y) {
        Cell cell = game.getCells()[x - 1][y - 1];
        if (cell.getBuilding() != null && (cell.getBuilding().getName().equals("lookout tower") ||
                cell.getBuilding().getName().equals("perimeter tower") ||
                cell.getBuilding().getName().equals("defensive tower") ||
                cell.getBuilding().getName().equals("square tower") ||
                cell.getBuilding().getName().equals("circle tower") ||
                cell.getBuilding().getName().equals("pitch ditch")
                )) {
            return Output.DIG_TUNNEL_FAILED;
        }
        else {
            cell.setTunelHere(true);
            return Output.DIG_TUNNEL_SUCCESSFUL;
        }
    }

    public Output buildEquipment(String equipmentName) {
        Material equipment = Material.getMaterialByName(equipmentName);
        game.getCurrentPlayer().getGovernance().getGovernanceResource().addToStorage(equipment);
        return Output.EQUIPMENT_CREATED_SUCCESSFULLY;
    }

    public Output disbandUnit() {
        if (game.getSelectedUnit().size() == 0) return Output.NO_UNIT_FOR_DISBANDING;
        else {
            for (int i = 0; i < game.getSelectedUnit().size(); i++) {
                game.getSelectedUnit().get(i).setHidden(true);
            }
            return Output.UNIT_DISBANDED_SUCCESSFULLY;
        }
    }

    public void applyChanges() {
        applyHitPointChange();
        applyDeathChange();
        updateMovements();
        updateResources();
        updateUnemployedPopulation();
        updateTaxIncome();
        //updatePopularity();
        updateFoodRate();
        updateTaxRate();
        updateEfficiency();
    }

    private void applyHitPointChange() {}

    private void applyDeathChange() {
        Cell[][] cells = game.getCells();
        int newUnemployedUnit = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].getBuilding() != null) {
                    if (cells[i][j].getBuilding().getHp() <= 0) {
                        cells[i][j].getBuilding().getOwner().getGovernance().deleteBuilding(cells[i][j].getBuilding());
                        cells[i][j].setBuilding(null);
                    }
                }
                if (cells[i][j].getUnits().size() != 0) {
                    for (int k = 0; k < cells[i][j].getUnits().size(); k++) {
                        if (cells[i][j].getUnits().get(k).getHitPoint() <= 0) {
                            cells[i][j].getUnits().get(k).getOwner().getGovernance().removeUnit(cells[i][j].getUnits().get(k));
                            cells[i][j].removeUnit(cells[i][j].getUnits().get(k));
                            newUnemployedUnit++ ;
                        }
                    }
                }
            }
        }
        game.getCurrentPlayer().getGovernance().setUnemployedPopulation(game.getCurrentPlayer().getGovernance().getUnemployedPopulation() + newUnemployedUnit);
    }

    public void updateMovements(){
        for(int i = 0 ; i < game.getPlayers().size();i++){
            for(int j = 0 ; j < game.getPlayers().get(i).getGovernance().getUnits().size() ; j++){
                //TODO -> how to call move function for every unit asal?
            }
        }
    }

    private void updateResources() {
        //some building should update automatically
        Cell[][] cells = game.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++){
                Converter converter = (Converter)cells[i][j].getBuilding();
                switch (converter.getName()){
                    case "wheat farm":
                        converter.produceMaterials();
                    case "hop farm":
                        converter.produceMaterials();
                    case "hunting post":
                        converter.produceMaterials();
                    case "apple garden":
                        converter.produceMaterials();
                    case "wood cutter":
                        converter.produceMaterials();
                    case "pitch rig":
                        converter.produceMaterials();
                    case "quarry":
                        converter.produceMaterials();
                    case "iron mine":
                        converter.produceMaterials();
                    case "bakery":
                        converter.consumeResource();
                        converter.produceMaterials();
                    case "dairy products":
                        converter.consumeResource();
                        converter.produceMaterials();
                    case "beer brewing":
                        converter.consumeResource();
                        converter.produceMaterials();
                    case "mill":
                        converter.consumeResource();
                        converter.produceMaterials();
                    case "poleturner":
                        converter.consumeResource();
                        converter.produceMaterials();
                    case "fletcher":
                        converter.consumeResource();
                        converter.produceMaterials();
                    case "blacksmith":
                        converter.consumeResource();
                        converter.produceMaterials();
                    case "armourer":
                        converter.consumeResource();
                        converter.produceMaterials();
                }
            }
        }
    }


    private void updateUnemployedPopulation() {}

    private void updatePopulation() {}


    private void updateTaxIncome() {
        Governance governance;
        for(int i = 0 ; i< game.getPlayers().size() ; i++){
            governance = game.getPlayers().get(i).getGovernance();
            if(governance.getTaxRate().getRateNumber() > 0)
                governance.setGold(governance.getGold() + (governance.getPopulation() * governance.getTaxRate().getRateNumber()));
            if(governance.getTaxRate().getRateNumber() == 0)
                governance.setPopularity(governance.getPopularity() + governance.getTaxRate().getPopularityIncrement());
            if(governance.getTaxRate().getRateNumber() < 0) {
                if(governance.getGold() < -(governance.getPopulation() * governance.getTaxRate().getRateNumber()))
                    governance.setTaxRate(RateNumber.TAX_RATE_0);
                else
                    governance.setGold(governance.getGold() - (governance.getPopulation() * governance.getTaxRate().getPayment()));
            }
            governance.setPopularity(governance.getPopularity() + governance.getTaxRate().getPopularityIncrement());
        }
    }

    private void updateFoodRate() {
        Governance governance;
        GovernanceResource governanceResource;
        int amountOfFood;
        int appleDelta; //we use delta to calculate the difference between amount of foods and amount of cheese,bread,meat and apple
        int breadDelta;
        int cheeseDelta;
        int meatDelta;
        for(int i = 0 ; i < game.getPlayers().size() ;i++){
            governance = game.getPlayers().get(i).getGovernance();
            governanceResource = governance.getGovernanceResource();
            amountOfFood = governance.getFoodRate().getRateNumber() * governance.getPopulation();
            if(governanceResource.amountOfFoodInStorage() < amountOfFood)
                governance.setFoodRate(RateNumber.FOOD_RATE_2);
            breadDelta = governanceResource.getAmountOfItemInStockpile(Material.BREAD);
            if(breadDelta > 0) {
                governanceResource.changeAmountOfItemInStockpile(Material.BREAD, breadDelta);
                amountOfFood = 0;
            }
            if(breadDelta < 0){
                governanceResource.changeAmountOfItemInStockpile(Material.BREAD,0);
                amountOfFood = -(breadDelta);
            }
            appleDelta = governanceResource.getAmountOfItemInStockpile(Material.APPLE) - amountOfFood;
            if(appleDelta > 0) {
                governanceResource.changeAmountOfItemInStockpile(Material.APPLE, appleDelta);
                amountOfFood = 0;
            }
            if(appleDelta < 0){
                governanceResource.changeAmountOfItemInStockpile(Material.APPLE,0);
                amountOfFood = -(appleDelta);
            }
            cheeseDelta = governanceResource.getAmountOfItemInStockpile(Material.CHEESE) - amountOfFood;
            if(cheeseDelta > 0) {
                governanceResource.changeAmountOfItemInStockpile(Material.CHEESE, cheeseDelta);
                amountOfFood = 0;
            }
            if(cheeseDelta < 0){
                governanceResource.changeAmountOfItemInStockpile(Material.CHEESE,0);
                amountOfFood = -(cheeseDelta);
            }
            meatDelta = governanceResource.getAmountOfItemInStockpile(Material.MEAT) - amountOfFood;
            if(meatDelta > 0)
                governanceResource.changeAmountOfItemInStockpile(Material.MEAT, meatDelta);
            if(meatDelta < 0)
                governanceResource.changeAmountOfItemInStockpile(Material.MEAT,0);
            governance.setPopularity(governance.getPopularity() + governance.getFoodRate().getPopularityIncrement());
        }
    }

    private void updateTaxRate() {
        Governance governance;
        for(int i = 0; i< game.getPlayers().size();i++){
            governance = game.getPlayers().get(i).getGovernance();
            if(governance.getGold() < )
        }
    }

    private void updateEfficiency() {}

    public boolean isGameEnded() {return false;}

    public String showGameResult() {return null;}

    private void updateScores() {}

    public void clearGame() {}

    public void goToNextPerson() {
        User user = null;
        boolean isNextPlayerFound = false;
        for (User player: game.getPlayers()) {
            if (isNextPlayerFound) {
                game.setCurrentPlayer(player);
                game.setSelectedUnit(new ArrayList<>());
        }
            if (player.getUsername().equals(game.getCurrentPlayer().getUsername())) {
                isNextPlayerFound = true;
            }
        }
        if (user == null) {
            game.setCurrentPlayer(game.getPlayers().get(0));
            game.setSelectedUnit(new ArrayList<>());
        }
    }


    public ArrayList<Cell> findPath(int sx, int sy, int tx, int ty) {
        Cell[][] cells = game.getCells();
        ArrayList<Cell> path = new ArrayList<>();
        if (cells[tx][ty].isBlocked()) return null;
        int currentX = sx;
        int currentY = sy;
        path.add(cells[tx][ty]);
        if (backTrack(cells, path, tx, ty)) return path;
        return null;
    }

    public boolean backTrack(Cell[][] cells, ArrayList<Cell> path, int tx, int ty) {
        Cell currentCell = path.get(path.size() - 1);
        int currentX = currentCell.getX();
        int currentY = currentCell.getY();
        if (currentX == tx && currentY == ty) return true;
        int[][] array = prioritizePathFinding(currentX, currentY, tx, ty);
        for (int i = 0; i < 4; i++) {
            if (currentX + array[0][i] >= 0 && currentX + array[0][i] < game.getRow() &&
                    currentY + array[1][i] >= 0 && currentY + array[1][i] < game.getColumn())  {
                if (!cells[currentX + array[0][i]][currentY + array[1][i]].isBlocked()) {
                    path.add(cells[currentX + array[0][i]][currentY + array[1][i]]);
                    if (backTrack(cells, path, tx, ty)) return true;
                    path.remove(cells[currentX + array[0][i]][currentY + array[1][i]]);
                }
            }
        }
        return false;
    }

    private int[][] prioritizePathFinding(int currentX, int currentY, int tx, int ty) {
        if (currentX > tx && currentY > ty) {
            int[][] array = {{-1, 0, 1, 0}, {0, -1, 0, 1}};
            return array;
        } else if (currentX > tx && currentY < ty) {
            int[][] array = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
            return array;
        } else if (currentX < tx && currentY > ty) {
            int[][] array = {{1, 0, -1, 0}, {0, -1, 0, 1}};
            return array;
        } else {
            int[][] array = {{1, 0, -1, 0}, {0, 1, 0, -1}};
            return array;
        }
    }

}
