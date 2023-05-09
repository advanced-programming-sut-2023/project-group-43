package controller;

import enums.BuildingEnums.BuildingEnum;
import enums.Output;
import enums.RateNumber;
import enums.environmentEnums.Material;
import enums.unitEnums.ArmedWeapon;
import enums.unitEnums.UnitState;
import enums.unitEnums.UnitsEnum;
import model.*;
import model.buildings.Building;
import model.buildings.CagedWarDogs;
import model.buildings.Converter;
import model.buildings.Producer;
import model.units.*;

import java.util.ArrayList;

public class GameController {

    private Game game;

    private Cell village = new Cell();


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
        if (number <= 0) return Output.INVALID_NUMBER;
        if (!game.getSelectedBuilding().getName().equals("barrack"))
            return Output.WRONG_SELECT_FOR_BUILDING;
        Unit unit = UnitsBuilder.unitsBuilder(name, game.getCurrentUser());
        assert unit != null;
        String unitType = UnitsEnum.getTypeByUnitName(unit.getName());
        if (unitType == null)
            return Output.WRONG_UNIT_NAME;
        //Optional: Even if we could do it, we wouldn't make less than the number
        if (unit.getCost() * number > governance.getGold())
            return Output.NOT_ENOUGH_MONEY;
        if (number > governance.getUnemployedPopulation())
            return Output.NOT_ENOUGH_POPULATION;
        if (unitType.equals("armed")) {
            Material weapon = ArmedWeapon.getWeaponByUnitName(unit.getName());
            if (governance.getGovernanceResource().getAmountOfItemInStockpile(weapon) < number)
                return Output.NOT_ENOUGH_WEAPON;
            governance.getGovernanceResource().changeAmountOfItemInStockpile(weapon, number);
        }
        for (int i = 0; i < number; i++) {
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
            for (Unit cellUnit : cellUnits) {
                if (cellUnit.getName().equals(type) && cellUnit.getOwner().equals(game.getCurrentPlayer())) {
                    resultcellUnits.add(cellUnit);
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
        for (Unit unit : game.getSelectedUnit()) {
            if (unit.getCell().equals(village)) unit.setCell(unit.getPreviousCell());
            unit.setCurrentTargetX(x - 1);
            unit.setCurrentTargetY(y - 1);
        }
        return Output.SUCCESSFUL_ACTION;
    }

    public Output patrolUnit(int x1, int y1, int x2, int y2) {
        if (isCoordinateInvalid(x1, y1) || isCoordinateInvalid(x2, y2))
            return Output.INVALID_NUMBER;
        for (Unit unit : game.getSelectedUnit()) {
            if (unit.getCell().equals(village)) unit.setCell(unit.getPreviousCell());
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
        for (Unit unit : units) {
            unit.setState(unitState);
        }
        return Output.UNIT_STATE_SETTED_SUCCESSFULLY;
    }

    public Output attack(int x, int y, String item) {
        if (item.equals("e"))
            return attackToEnemy(x, y);
        if (item.equals("x"))
            return aearialAttack(x, y);
        return null;
    }

    private Output attackToEnemy(int x, int y) {
        return null;
    }

    private Output aearialAttack(int x, int y) {
        return null;
    }

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
            engineer.pourOil(game, direction);
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
        } else {
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
            for (Unit unit : game.getSelectedUnit()) {
                unit.setPreviousCell(unit.getCell());
                unit.setCell(village);
            }
            return Output.UNIT_DISBANDED_SUCCESSFULLY;
        }
    }

    public void applyChanges() {
        updateUnitTargets();
        updateMovements();
        updateResources();
        applyHitPointChange();
        applyDeathChange();
        updateTaxIncome();
        updateReligiousPopularity();
        updateFoodRate();
        updateTaxRate();
        updateWorkersEfficiency();
        updateDamageEfficiency();
    }

    private void applyHitPointChange() {
        for (User user : game.getPlayers()) {
            for (Unit unit : user.getGovernance().getUnits()) {
                int currentX = unit.getCell().getX();
                int currentY = unit.getCell().getY();
                if (unit instanceof Armed) {
                    int range = ((Armed) unit).getWeapon().getRange();
                    setVariables(currentX, currentY, range, true, user, unit);
                } else if (unit instanceof Unarmed) {
                    setVariables(currentX, currentY, 1, true, user, unit);
                } else if (unit instanceof Engineer) {
                    ((Engineer) unit).chargeTar();
                    ;
                } else if (unit instanceof Tunneler) {
                    ((Tunneler) unit).destroyBuilding(game);
                } else if (unit instanceof Ladderman) {
                    ((Ladderman) unit).addLadder(findBuildingsAround(unit));
                }
                if (unit instanceof Spearman) {
                    ((Spearman) unit).dropLadder(findBuildingsAround(unit));
                }
                if (unit instanceof Assassin) {
                    ((Assassin) unit).getCastleDepartment(findBuildingsAround(unit));
                }
            }
        }
    }

    private Cell setVariables(int currentX, int currentY, int range, boolean shouldFight, User user, Unit unit) {
        int sx = currentX - range;
        int fx = currentX + range;
        int sy = currentY - range;
        int fy = currentY + range;
        if (sx < 0) sx = 0;
        if (sy < 0) sy = 0;
        if (fx >= game.getRow()) fx = game.getRow() - 1;
        if (fy >= game.getColumn()) fy = game.getColumn() - 1;
        if (shouldFight) fight(sx, sy, fx, fy, user, unit);
        else return findCell(sx, sy, fx, fy, user);
        return null;
    }

    private void fight(int sx, int sy, int fx, int fy, User user, Unit unit) {
        for (int xIterator = sx; xIterator <= fx; xIterator++) {
            for (int yIterator = sy; yIterator <= fy; yIterator++) {
                Cell currentCell = game.getCells()[xIterator][yIterator];
                for (Unit potentialEnemy : currentCell.getUnits()) {
                    if (!potentialEnemy.getOwner().getUsername().equals(user.getUsername()) && potentialEnemy.getCell().getBuilding() == null) {
                        potentialEnemy.setHitPoint(potentialEnemy.getHitPoint() - unit.getDamage());
                    }
                }
            }
        }
    }

    private Cell findCell(int sx, int sy, int fx, int fy, User user) {
        for (int xIterator = sx; xIterator <= fx; xIterator++) {
            for (int yIterator = sy; yIterator <= fy; yIterator++) {
                Cell currentCell = game.getCells()[xIterator][yIterator];
                for (Unit enemy : currentCell.getUnits()) {
                    if (!enemy.getOwner().getUsername().equals(user.getUsername())) {
                        if (enemy instanceof Assassin) ((Assassin) enemy).setHidden(false);
                        return currentCell;
                    }
                }
            }
        }
        return null;
    }

    private ArrayList<Building> findBuildingsAround(Unit unit) {
        ArrayList<Building> buildings = new ArrayList<>();
        int sx = unit.getCell().getX() - 1;
        int sy = unit.getCell().getY() - 1;
        if (sx < 0) sx = 0;
        if (sy < 0) sy = 0;
        int fx = unit.getCell().getX() + 1;
        int fy = unit.getCell().getY() + 1;
        if (fx >= game.getRow()) fx--;
        if (fy >= game.getColumn()) fy--;
        for (int x = sx; x <= fx; x++) {
            for (int y = sy; y <= fy; y++) {
                if (game.getCells()[x][y].getBuilding() != null)
                    buildings.add(game.getCells()[x][y].getBuilding());
            }
        }
        return buildings;
    }

    private void applyDeathChange() {
        for (User user : game.getPlayers()) {
            for (Building building : user.getGovernance().getBuildings()) {
                if (building.getHp() <= 0) {
                    if (building instanceof CagedWarDogs) ((CagedWarDogs) building).freeDogs();
                    user.getGovernance().setUnemployedPopulation(user.getGovernance().getUnemployedPopulation() + building.getLadderlans());
                    user.getGovernance().deleteBuilding(building);
                    building.getCell().setBuilding(null);
                }
            }
            for (Unit unit : user.getGovernance().getUnits()) {
                if (unit.getHitPoint() <= 0) {
                    unit.getCell().removeUnit(unit);
                    user.getGovernance().removeUnit(unit);
                    user.getGovernance().setPopulation(user.getGovernance().getPopulation() - 1);
                }
            }
        }
    }

    public void updateUnitTargets() {
        for (User user : game.getPlayers()) {
            for (Unit unit : user.getGovernance().getUnits()) {
                if (unit.getState().equals(UnitState.STANDING)) {
                    unit.setCurrentTargetX(-1);
                    unit.setCurrentTargetY(-1);
                    unit.setNextTargetY(-1);
                    unit.setNextTargetX(-1);
                } else if (unit.getState().equals(UnitState.OFFENSIVE)) {
                    Cell enemyCell = findEnemy(1000, unit.getCell().getX(), unit.getCell().getY(), user, unit);
                    unit.setCurrentTargetX(enemyCell.getX());
                    unit.setCurrentTargetY(enemyCell.getY());
                    unit.setNextTargetY(-1);
                    unit.setNextTargetX(-1);
                } else if (unit.getState().equals(UnitState.DEFENSIVE)) {
                    Cell enemyCell = findEnemy(3, unit.getCell().getX(), unit.getCell().getY(), user, unit);
                    assert enemyCell != null;
                    unit.setCurrentTargetX(enemyCell.getX());
                    unit.setCurrentTargetY(enemyCell.getY());
                    unit.setNextTargetY(-1);
                    unit.setNextTargetX(-1);
                }
            }
        }
    }

    private Cell findEnemy(int distance, int currentX, int currentY, User user, Unit unit) {
        for (int i = 0; i < distance; i++) {
            Cell cell;
            if ((cell = setVariables(currentX, currentY, i, false, user, unit)) != null)
                return cell;
        }
        return null;
    }

    public void updateMovements() {
        for (User user : game.getPlayers()) {
            for (Unit unit : user.getGovernance().getUnits()) {
                unit.move(this);
            }
        }
    }

    private void updateResources() {
        //some building should update automatically
        Cell[][] cells = game.getCells();
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cell[j].getBuilding() instanceof Producer) {
                    Producer producer = (Producer) cell[j].getBuilding();
                    switch (producer.getName()) {
                        case "wheat farm":
                            producer.produceMaterials();
                        case "hop farm":
                            producer.produceMaterials();
                        case "hunting post":
                            producer.produceMaterials();
                        case "apple garden":
                            producer.produceMaterials();
                        case "wood cutter":
                            producer.produceMaterials();
                        case "pitch rig":
                            producer.produceMaterials();
                        case "quarry":
                            producer.produceMaterials();
                        case "iron mine":
                            producer.produceMaterials();
                    }
                }
                else{
                        Converter converter = (Converter) cell[j].getBuilding();
                        switch (converter.getName()) {
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
        }

    private void updateReligiousPopularity() {
        Cell[][] cells = game.getCells();
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++){
                Building building = cell[0].getBuilding();
                if(building.getName().equals("church") || building.getName().equals("cathedral"))
                    building.getOwner().getGovernance().changePopulation(1);
            }
        }
    }

    private void updateTaxIncome() {
        Governance governance;
        for (int i = 0; i < game.getPlayers().size(); i++) {
            governance = game.getPlayers().get(i).getGovernance();
            if (governance.getTaxRate().getRateNumber() > 0)
                governance.setGold(governance.getGold() + (governance.getPopulation() * governance.getTaxRate().getRateNumber()));
            if (governance.getTaxRate().getRateNumber() == 0)
                governance.setPopularity(governance.getPopularity() + governance.getTaxRate().getPopularityIncrement());
            if (governance.getTaxRate().getRateNumber() < 0) {
                if (governance.getGold() < -(governance.getPopulation() * governance.getTaxRate().getRateNumber()))
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
        for (int i = 0; i < game.getPlayers().size(); i++) {
            governance = game.getPlayers().get(i).getGovernance();
            governanceResource = governance.getGovernanceResource();
            amountOfFood = governance.getFoodRate().getRateNumber() * governance.getPopulation();
            if (governanceResource.amountOfFoodInStorage() < amountOfFood)
                governance.setFoodRate(RateNumber.FOOD_RATE_2);
            breadDelta = governanceResource.getAmountOfItemInStockpile(Material.BREAD);
            if (breadDelta > 0) {
                governanceResource.changeAmountOfItemInStockpile(Material.BREAD, breadDelta);
                amountOfFood = 0;
            }
            if (breadDelta < 0) {
                governanceResource.changeAmountOfItemInStockpile(Material.BREAD, 0);
                amountOfFood = -(breadDelta);
            }
            appleDelta = governanceResource.getAmountOfItemInStockpile(Material.APPLE) - amountOfFood;
            if (appleDelta > 0) {
                governanceResource.changeAmountOfItemInStockpile(Material.APPLE, appleDelta);
                amountOfFood = 0;
            }
            if (appleDelta < 0) {
                governanceResource.changeAmountOfItemInStockpile(Material.APPLE, 0);
                amountOfFood = -(appleDelta);
            }
            cheeseDelta = governanceResource.getAmountOfItemInStockpile(Material.CHEESE) - amountOfFood;
            if (cheeseDelta > 0) {
                governanceResource.changeAmountOfItemInStockpile(Material.CHEESE, cheeseDelta);
                amountOfFood = 0;
            }
            if (cheeseDelta < 0) {
                governanceResource.changeAmountOfItemInStockpile(Material.CHEESE, 0);
                amountOfFood = -(cheeseDelta);
            }
            meatDelta = governanceResource.getAmountOfItemInStockpile(Material.MEAT) - amountOfFood;
            if (meatDelta > 0)
                governanceResource.changeAmountOfItemInStockpile(Material.MEAT, meatDelta);
            if (meatDelta < 0)
                governanceResource.changeAmountOfItemInStockpile(Material.MEAT, 0);
            governance.setPopularity(governance.getPopularity() + governance.getFoodRate().getPopularityIncrement());
        }
    }

    private void updateTaxRate() {

    }

    private void updateWorkersEfficiency() {

    }

    private void updateDamageEfficiency(){
        for(int i = 0 ; i < game.getPlayers().size();i++){
            Governance governance = game.getPlayers().get(i).getGovernance();
            double featRate = governance.getFearRate();
            for(int j = 0 ; j < governance.getUnits().size();j++){
                double newDamage = governance.getUnits().get(j).getDamage() +
                        (governance.getUnits().get(j).getDamage() * (5 / 100));
                governance.getUnits().get(j).setDamage(newDamage);
            }
        }
    }


    public boolean isGameEnded() {
        return false;
    }

    public String showGameResult() {
        return null;
    }

    private void updateScores() {
    }

    public void clearGame() {
    }

    public void goToNextPerson() {
        User user = null;
        boolean isNextPlayerFound = false;
        for (User player : game.getPlayers()) {
            if (isNextPlayerFound) {
                user = player;
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
            game.setSelectedBuilding(null);
        }
    }


    public ArrayList<Cell> findPath(int sx, int sy, int tx, int ty, Unit unit) {
        Cell[][] cells = game.getCells();
        ArrayList<Cell> path = new ArrayList<>();
        if (cells[tx][ty].isBlocked(unit)) return null;
        int currentX = sx;
        int currentY = sy;
        path.add(cells[tx][ty]);
        if (backTrack(cells, path, tx, ty, unit)) return path;
        return null;
    }

    public boolean backTrack(Cell[][] cells, ArrayList<Cell> path, int tx, int ty, Unit unit) {
        Cell currentCell = path.get(path.size() - 1);
        int currentX = currentCell.getX();
        int currentY = currentCell.getY();
        if (currentX == tx && currentY == ty) return true;
        int[][] array = prioritizePathFinding(currentX, currentY, tx, ty);
        for (int i = 0; i < 4; i++) {
            if (currentX + array[0][i] >= 0 && currentX + array[0][i] < game.getRow() &&
                    currentY + array[1][i] >= 0 && currentY + array[1][i] < game.getColumn()) {
                if (!cells[currentX + array[0][i]][currentY + array[1][i]].isBlocked(unit)) {
                    path.add(cells[currentX + array[0][i]][currentY + array[1][i]]);
                    if (backTrack(cells, path, tx, ty, unit)) return true;
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
