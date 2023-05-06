package controller;

import enums.BuildingEnums.BuildingEnum;
import enums.Output;
import enums.environmentEnums.Material;
import enums.unitEnums.ArmedWeapon;
import enums.unitEnums.UnitState;
import enums.unitEnums.UnitsEnum;
import model.Cell;
import model.Game;
import model.Governance;
import model.User;
import model.buildings.Building;
import model.buildings.Converter;
import model.units.*;

import java.util.ArrayList;
import java.util.Objects;

public class GameController {

    private final Game game;


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
            game.getSelectedBuilding().setHp(Objects.requireNonNull(BuildingEnum.getBuildingStructureByName(game.getSelectedBuilding().getName())).getHp());
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
                if ((!cellUnit.isHidden()) && cellUnit.getName().equals(type) && cellUnit.getOwner().equals(game.getCurrentPlayer())) {
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
            unit.setCurrentTargetX(x - 1);
            unit.setCurrentTargetY(y - 1);
        }
        return Output.SUCCESSFUL_ACTION;
    }

    public Output patrolUnit(int x1, int y1, int x2, int y2) {
        if (isCoordinateInvalid(x1, y1) || isCoordinateInvalid(x2, y2))
            return Output.INVALID_NUMBER;
        for (Unit unit : game.getSelectedUnit()) {
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
            for (int i = 0; i < game.getSelectedUnit().size(); i++) {
                game.getSelectedUnit().get(i).setHidden(true);
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
        updateUnemployedPopulation();
        updateTaxIncome();
        updatePopularity();
        updateFoodRate();
        updateTaxRate();
        updateEfficiency();
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
                    if (!potentialEnemy.getOwner().getUsername().equals(user.getUsername())) {
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
                        return currentCell;
                    }
                }
            }
        }
        return null;
    }

    private void applyDeathChange() {
        Cell[][] cells = game.getCells();
        int newUnemployedUnit = 0;
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cell[j].getBuilding() != null) {
                    if (cell[j].getBuilding().getHp() <= 0) {
                        cell[j].getBuilding().getOwner().getGovernance().deleteBuilding(cell[j].getBuilding());
                        cell[j].setBuilding(null);
                    }
                }
                if (cell[j].getUnits().size() != 0) {
                    for (int k = 0; k < cell[j].getUnits().size(); k++) {
                        if (cell[j].getUnits().get(k).getHitPoint() <= 0) {
                            cell[j].getUnits().get(k).getOwner().getGovernance().removeUnit(cell[j].getUnits().get(k));
                            cell[j].removeUnit(cell[j].getUnits().get(k));
                            newUnemployedUnit++;
                        }
                    }
                }
            }
        }
        game.getCurrentPlayer().getGovernance().setUnemployedPopulation(game.getCurrentPlayer().getGovernance().getUnemployedPopulation() + newUnemployedUnit);
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
            if((cell = setVariables(currentX, currentY , i, false, user, unit)) != null)
                return cell;
        }
        return null;
    }

    public void updateMovements(){
        for(User user: game.getPlayers()) {
            for (Unit unit: user.getGovernance().getUnits()) {
                unit.move(this);
            }
        }
    }

    private void updateResources() {
        //some building should update automatically
        Cell[][] cells = game.getCells();
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                Converter converter = (Converter) cell[j].getBuilding();
                switch (converter.getName()) {
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


    private void updateUnemployedPopulation() {
    }

    private void updateTaxIncome() {
    }

    private void updatePopularity() {
    }

    private void updateFoodRate() {
    }

    private void updateTaxRate() {
    }

    private void updateEfficiency() {
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
                game.setSelectedBuilding(null);
                break;
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


    public ArrayList<Cell> findPath(int sx, int sy, int tx, int ty) {
        Cell[][] cells = game.getCells();
        ArrayList<Cell> path = new ArrayList<>();
        if (cells[tx][ty].isBlocked()) return null;
        path.add(cells[sx][sy]);
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
                    currentY + array[1][i] >= 0 && currentY + array[1][i] < game.getColumn()) {
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
            return new int[][]{{-1, 0, 1, 0}, {0, -1, 0, 1}};
        } else if (currentX > tx && currentY < ty) {
            return new int[][]{{-1, 0, 1, 0}, {0, 1, 0, -1}};
        } else if (currentX < tx && currentY > ty) {
            return new int[][]{{1, 0, -1, 0}, {0, -1, 0, 1}};
        } else {
            return new int[][]{{1, 0, -1, 0}, {0, 1, 0, -1}};
        }
    }

}
