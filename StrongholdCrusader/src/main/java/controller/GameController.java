package controller;

import enums.BuildingEnums.BuildingEnum;
import enums.Output;
import enums.RateNumber;
import enums.environmentEnums.Material;
import enums.environmentEnums.Texture;
import enums.unitEnums.ArmedWeapon;
import enums.unitEnums.UnitState;
import enums.unitEnums.UnitsEnum;
import model.*;
import model.buildings.*;
import model.units.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameController {

    private final Game game;
    private static final HashMap<String, Cell[][]> defaultMaps = new HashMap<>();

    private final Cell village = new Cell();


    public GameController(Game game) {
        this.game = game;
    }

    public static Cell[][] getDefaultMaps(int mapOption) {
        if (mapOption == 1) return defaultMaps.get("option number 1");
        else return defaultMaps.get("option number 2");
    }

    public static void setDefaultMaps(int row, int column) {
        Cell[][] cells = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cells[i][j] = new Cell();
                if (i % 9 == 0) cells[i][j].setTexture(Texture.GROUND);
                else if ((i % 9) == 1) cells[i][j].setTexture(Texture.GRAVEL_GROUND);
                else if ((i % 9) == 2) cells[i][j].setTexture(Texture.BOULDER);
                else if ((i % 9) == 3) cells[i][j].setTexture(Texture.ROCK);
                else if ((i % 9) == 4) cells[i][j].setTexture(Texture.IRON);
                else if ((i % 9) == 5) cells[i][j].setTexture(Texture.GRASS);
                else if ((i % 9) == 6) cells[i][j].setTexture(Texture.MEADOW);
                else if ((i % 9) == 7) cells[i][j].setTexture(Texture.DENSE_GRASSLAND);
                else cells[i][j].setTexture(Texture.PLAIN);
            }
        }
        for (int i = 0; i < column; i++) {
            if ((i % 9) == 0) cells[0][i].setTexture(Texture.OIL);
            else if ((i % 9) == 1) cells[0][i].setTexture(Texture.SHALLOW_WATER);
            else if ((i % 9) == 2) cells[0][i].setTexture(Texture.RIVER);
            else if ((i % 9) == 3) cells[0][i].setTexture(Texture.SMALL_POND);
            else if ((i % 9) == 4) cells[0][i].setTexture(Texture.BIG_POND);
            else if ((i % 9) == 5) cells[0][i].setTexture(Texture.BEACH);
            else if ((i % 9) == 6) {
                cells[0][i].setTexture(Texture.SEA);
                break;
            }
        }
        defaultMaps.put("option number 1", cells);
        Cell[][] cells2 = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cells2[i][j] = new Cell();
                if ((i % 9) == 0) cells2[i][j].setTexture(Texture.GRAVEL_GROUND);
                else if ((i % 9) == 1) cells2[i][j].setTexture(Texture.GROUND);
                else if ((i % 9) == 2) cells2[i][j].setTexture(Texture.ROCK);
                else if ((i % 9) == 3) cells2[i][j].setTexture(Texture.BOULDER);
                else if ((i % 9) == 4) cells2[i][j].setTexture(Texture.GRASS);
                else if ((i % 9) == 5) cells2[i][j].setTexture(Texture.IRON);
                else if ((i % 9) == 6) cells2[i][j].setTexture(Texture.DENSE_GRASSLAND);
                else if ((i % 9) == 7) cells2[i][j].setTexture(Texture.PLAIN);
                else cells2[i][j].setTexture(Texture.MEADOW);
            }
        }
        for (int i = 0; i < column; i++) {
            if ((i % 9) == 0) cells2[0][i].setTexture(Texture.BEACH);
            else if ((i % 9) == 1) cells2[0][i].setTexture(Texture.BIG_POND);
            else if ((i % 9) == 2) cells2[0][i].setTexture(Texture.SMALL_POND);
            else if ((i % 9) == 3) cells2[0][i].setTexture(Texture.RIVER);
            else if ((i % 9) == 4) cells2[0][i].setTexture(Texture.SHALLOW_WATER);
            else if ((i % 9) == 5) cells2[0][i].setTexture(Texture.OIL);
            else if ((i % 9) == 6) {
                cells2[0][i].setTexture(Texture.SEA);
                break;
            }
        }
        defaultMaps.put("option number 2", cells2);
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

    public Output dropBuilding(int x, int y, String type) {
        if (x <= 0 || y <= 0 || x > game.getCells().length || y > game.getCells()[0].length)
            return Output.WRONG_COORDINATES;
        if (type.matches("headquarter")) return Output.INVALID_BUILDING;
        Building building = BuildingBuilder.BuildingBuilder(type, game.getCurrentPlayer());
        if (building == null) return null;
        Governance governance = game.getCurrentPlayer().getGovernance();
        assert building != null;
        if (type.equals("iron mine") && !game.getCells()[x - 1][y - 1].getTexture().equals(Texture.IRON))
            return Output.INVALID_CELL;
        if (governance.getGold() < building.getCost()) return Output.NOT_ENOUGH_GOLD;
        if (game.getCurrentPlayer().getGovernance().getGovernanceResource().getAmountOfItemInStockpile(Material.WOOD) < building.getWood())
            return Output.NOT_ENOUGH_RESOURCE;
        if (governance.getGovernanceResource().getAmountOfItemInStockpile(Material.STONE) < building.getStone())
            return Output.NOT_ENOUGH_RESOURCE;
        governance.changeGoldAmount(-building.getCost());
        governance.getGovernanceResource().changeAmountOfItemInStockpile(Material.WOOD, building.getWood());
        governance.getGovernanceResource().changeAmountOfItemInStockpile(Material.STONE, building.getStone());
        building.setCell(game.getCells()[x - 1][y - 1]);
        game.getCells()[x - 1][y - 1].setBuilding(building);
        game.getCurrentPlayer().getGovernance().addBuilding(building);
        return Output.SUCCESSFUL_ACTION;
    }

    public Output createUnit(String name, int number) {
        Governance governance = game.getCurrentPlayer().getGovernance();
        if (number <= 0) return Output.INVALID_NUMBER;
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
            Unit newUnit = UnitsBuilder.unitsBuilder(name, game.getCurrentUser());
            game.getCurrentUser().getGovernance().addUnit(newUnit);
        }
        governance.setGold(governance.getGold() - unit.getCost() * number);
        governance.setUnemployedPopulation(governance.getUnemployedPopulation() - number);
        return Output.SUCCESSFUL_UNIT_CREATION;
    }

    public Output dropUnit(int x, int y, String type, int count) {
        ArrayList<Unit> units = game.getCurrentPlayer().getGovernance().getNewUnits(type);
        if (isCoordinateInvalid(x - 1, y - 1)) return Output.WRONG_COORDINATES;
        if (units.size() < count) return Output.NOT_ENOUGH_UNIT;
        Cell cell = game.getCells()[x - 1][y - 1];
        for (int i = 0; i < count; i++) {
            if (cell.isBlocked(units.get(i))) return Output.INVALID_CELL;
            units.get(i).setCell(cell);
            cell.addUnit(units.get(i));
        }
        return Output.SUCCESSFUL_ACTION;
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
        ArrayList<Unit> resultCellUnits = new ArrayList<>();
        if (cellUnits.size() == 0) return Output.NO_UNIT;
        else {
            for (Unit cellUnit : cellUnits) {
                if (cellUnit.getName().equals(type) && cellUnit.getOwner().equals(game.getCurrentPlayer())) {
                    resultCellUnits.add(cellUnit);
                }
            }
            if (resultCellUnits.size() == 0)
                return Output.NO_THIS_TYPE_UNIT;
            else {
                game.setSelectedUnit(resultCellUnits);
                return Output.SELECT_UNIT;
            }
        }
    }

    public Output moveUnit(int x, int y) {
        if (isCoordinateInvalid(x, y)) return Output.INVALID_NUMBER;
        for (Unit unit : game.getSelectedUnit()) {
            if (unit.getCell().equals(village)) unit.setCell(unit.getPreviousCell());
            if (game.getCells()[x - 1][y - 1].isBlocked(unit)) return Output.INVALID_MOVE;
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
        return (x < 0 || y < 0 || x >= game.getRow() || y >= game.getColumn());
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
            return airAttack(x, y);
        return null;
    }

    private Output attackToEnemy(int x, int y) {
        if (isCoordinateInvalid(x - 1, y - 1)) return Output.WRONG_COORDINATES;
        for (Unit unit : game.getSelectedUnit()) {
            if (unit instanceof Troop) {
                int dx = Math.abs(unit.getCell().getX() - (x - 1));
                int dy = Math.abs(unit.getCell().getY() - (y - 1));
                if ((dx > 1 || dy > 1) && unit instanceof Unarmed) return Output.INVALID_DISTANCE;
                else if (unit instanceof Armed) {
                    if (dx > ((Armed) unit).getWeapon().getRange() || dy > ((Armed) unit).getWeapon().getRange())
                        return Output.INVALID_DISTANCE;
                } else return Output.NO_THIS_TYPE_UNIT;
                attack(x, y, unit);
            }
        }
        return Output.SUCCESSFUL_ACTION;
    }

    private Output airAttack(int x, int y) {
        if (isCoordinateInvalid(x - 1, y - 1)) return Output.WRONG_COORDINATES;
        for (Unit unit : game.getSelectedUnit()) {
            if (unit.getName().equals("archer")) {
                int dx = Math.abs(unit.getCell().getX() - (x - 1));
                int dy = Math.abs(unit.getCell().getY() - (y - 1));
                if (dx > ((Armed) unit).getWeapon().getRange() || dy > ((Armed) unit).getWeapon().getRange())
                    return Output.INVALID_DISTANCE;
                attack(x, y, unit);
            }
        }
        return Output.SUCCESSFUL_ACTION;
    }

    private void attack(int x, int y, Unit unit) {
        Cell cell = game.getCells()[x - 1][y - 1];
        Building building = cell.getBuilding();
        if (building != null && building.getOwner().equals(game.getCurrentPlayer())) {
            building.setHp(building.getHp() - (int) Math.floor(unit.getHitPoint()));
        }
        for (Unit potentialEnemy : cell.getUnits()) {
            if (!potentialEnemy.getOwner().equals(game.getCurrentPlayer()) && potentialEnemy.getCell().getBuilding() == null) {
                potentialEnemy.setHitPoint(potentialEnemy.getHitPoint() - unit.getDamage());
            }
        }
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
        completeBuildings();
        updateUnitTargets();
        updateMovements();
        updateResources();
        applyHitPointChange();
        applyDeathChange();
        updateTaxIncome();
        updateReligiousPopularity();
        updateFoodRate();
        updateWorkersEfficiency();
        updateDamageEfficiency();
    }

    private void completeBuildings() {
        for (User user : game.getPlayers()) {
            for (Building building : user.getGovernance().getBuildings()) {
                building.constructBuilding();
            }
        }
    }

    private void applyHitPointChange() {
        for (User user : game.getPlayers()) {
            for (Unit unit : user.getGovernance().getUnits()) {
                if (unit instanceof Engineer) {
                    ((Engineer) unit).chargeTar();
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

    private Cell setVariables(int currentX, int currentY, int range, User user) {
        int sx = currentX - range;
        int fx = currentX + range;
        int sy = currentY - range;
        int fy = currentY + range;
        if (sx < 0) sx = 0;
        if (sy < 0) sy = 0;
        if (fx >= game.getRow()) fx = game.getRow() - 1;
        if (fy >= game.getColumn()) fy = game.getColumn() - 1;
        return findCell(sx, sy, fx, fy, user);
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
                    if (!building.isComplete())
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
                    Cell enemyCell = findEnemy(1000, unit.getCell().getX(), unit.getCell().getY(), user);
                    assert enemyCell != null;
                    unit.setCurrentTargetX(enemyCell.getX());
                    unit.setCurrentTargetY(enemyCell.getY());
                    unit.setNextTargetY(-1);
                    unit.setNextTargetX(-1);
                } else if (unit.getState().equals(UnitState.DEFENSIVE)) {
                    Cell enemyCell = findEnemy(3, unit.getCell().getX(), unit.getCell().getY(), user);
                    assert enemyCell != null;
                    unit.setCurrentTargetX(enemyCell.getX());
                    unit.setCurrentTargetY(enemyCell.getY());
                    unit.setNextTargetY(-1);
                    unit.setNextTargetX(-1);
                }
            }
        }
    }

    private Cell findEnemy(int distance, int currentX, int currentY, User user) {
        for (int i = 0; i < distance; i++) {
            Cell cell;
            if ((cell = setVariables(currentX, currentY, i, user)) != null)
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
                if (cell[j].getBuilding() == null || !cell[j].getBuilding().isComplete()) continue;
                Building building = cell[j].getBuilding();
                if (building instanceof Producer producer) {
                    switch (producer.getName()) {
                        case "wheat farm", "hop farm", "hunting post", "apple garden", "wood cutter", "pitch rig", "quarry", "iron mine" ->
                                producer.produceMaterials();
                    }
                }
                if (building instanceof Converter converter) {
                    switch (converter.getName()) {
                        case "bakery", "dairy products", "beer brewing", "mill", "poleturner", "fletcher", "blacksmith", "armourer" -> {
                            converter.consumeResource();
                            converter.produceMaterials();
                        }
                    }
                }
                if (cell[j].getBuilding() instanceof CastleDepartment) {
                    ((CastleDepartment) cell[j].getBuilding()).reduceEnemySpeed(findUnitsAround(cell[j]));
                    ((CastleDepartment) cell[j].getBuilding()).attackEnemy(game, cell[j].getX(), cell[j].getY());
                }
                if (cell[j].getBuilding() instanceof PopularityBooster)
                    ((PopularityBooster) cell[j].getBuilding()).increasePopularity();
                if (building.getName().equals("hovel")) {
                    Governance governance = building.getOwner().getGovernance();
                    governance.setPopulation(governance.getPopulation() + 8);
                    governance.setUnemployedPopulation(governance.getUnemployedPopulation() + 8);
                }
            }
        }
    }

    private ArrayList<Unit> findUnitsAround(Cell cell) {
        ArrayList<Unit> units = new ArrayList<>();
        int sx = cell.getX() - 1;
        int sy = cell.getY() - 1;
        if (sx < 0) sx = 0;
        if (sy < 0) sy = 0;
        int fx = cell.getX() + 1;
        int fy = cell.getY() + 1;
        if (fx >= game.getRow()) fx--;
        if (fy >= game.getColumn()) fy--;
        for (int x = sx; x <= fx; x++) {
            for (int y = sy; y <= fy; y++) {
                units.addAll(game.getCells()[x][y].getUnits());
            }
        }
        return units;
    }

    private void updateReligiousPopularity() {
        Cell[][] cells = game.getCells();
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                Building building = cell[0].getBuilding();
                if (building.getName().equals("church") || building.getName().equals("cathedral"))
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

    private void updateWorkersEfficiency() {

    }

    private void updateDamageEfficiency() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Governance governance = game.getPlayers().get(i).getGovernance();
            for (int j = 0; j < governance.getUnits().size(); j++) {
                double newDamage = governance.getUnits().get(j).getDamage() +
                        (governance.getUnits().get(j).getDamage() * 5 / 100);
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


    public ArrayList<Cell> findPath(int tx, int ty, Unit unit) {
        Cell[][] cells = game.getCells();
        ArrayList<Cell> path = new ArrayList<>();
        if (cells[tx][ty].isBlocked(unit)) return null;
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
                    if(cells[currentX + array[0][i]][currentY + array[1][i]].getTexture().equals(Texture.SHALLOW_WATER)) {
                        path.add(cells[currentX + array[0][i]][currentY + array[1][i]]);
                    }
                    if (backTrack(cells, path, tx, ty, unit)) return true;
                    path.remove(cells[currentX + array[0][i]][currentY + array[1][i]]);
                    if(cells[currentX + array[0][i]][currentY + array[1][i]].getTexture().equals(Texture.SHALLOW_WATER)) {
                        path.remove(cells[currentX + array[0][i]][currentY + array[1][i]]);
                    }
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
