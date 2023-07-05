package controller.GameControllers;

import controller.MainUserController;
import enums.BuildingEnums.BuildingEnum;
import enums.ImageEnum;
import enums.Output;
import enums.RateNumber;
import enums.environmentEnums.Material;
import enums.environmentEnums.Texture;
import enums.unitEnums.ArmedWeapon;
import enums.unitEnums.UnitState;
import enums.unitEnums.UnitsEnum;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.*;
import model.buildings.*;
import model.units.*;
import view.GameMenu;
import view.MainMenu;
import view.RegisterMenu;

import java.util.*;
import java.util.stream.Collectors;

public class GameController {

    private static Game game;
    private static HashMap<HashMap<User, String>, Pane> allMaps = new HashMap<>();
    private static final HashMap<String, Cell[][]> defaultMaps = new HashMap<>();

    private final Cell village = new Cell();
    private static MiniBar miniBar = new MiniBar();
    public static HashMap<HashMap<User, String>, Pane> getAllMaps() {
        return allMaps;
    }

    public void addToAllMaps(User user, String string, Pane pane) {
        HashMap<User, String> hashMap = new HashMap<>();
        hashMap.put(user, string);
        this.allMaps.put(hashMap, pane);
    }

    public GameController(Game game) {
        this.game = game;
    }

    public static MiniBar getMiniBar() {
        return miniBar;
    }

    public static void setMiniBar(MiniBar miniBar) {
        GameController.miniBar = miniBar;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String cellInfo(Cell cell) {
        StringBuilder output = new StringBuilder();
        output.append("texture : " + cell.getTexture().getName() + "\n");
        if (cell.getTreeType() == null) {
            output.append("tree : no tree in this cell\n");
        }
        else {
            output.append("tree : " + cell.getTreeType().getTreeType() + "\n");
        }
        if (cell.getBuilding() == null) {
            output.append("buildings : no building in this cell");
        }
        else {
            output.append("buildings : " + cell.getBuilding().getName() + " , owner : " + cell.getBuilding().getOwner().getUsername());
        }
        output.append("\nunits :");
        Map<Unit, Long> counts = cell.getUnits().stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        for (int i = 0; i < game.getPlayers().size(); i++) {
            for (Map.Entry<Unit, Long> entry : counts.entrySet())
                output.append("\n" + entry.getKey().getName() + " <=> " + entry.getValue());
        }
        for (int j = 0; j < game.getPlayers().size(); j++) {
            int playersUnit = 0;
            for (int k = 0; k < cell.getUnits().size(); k++) {
                if (cell.getUnits().get(k).getOwner().equals(game.getPlayers().get(j)))
                    playersUnit++;
            }
            output.append("\n" + game.getPlayers().get(j).getUsername() + " = " + playersUnit);
        }
        return output.toString();
    }

    public static Cell[][] getDefaultMaps(int mapOption) {
        if (mapOption == 1) return defaultMaps.get("option number 1");
        else return defaultMaps.get("option number 2");
    }

    public void initializeGame() {
        game.setCurrentPlayer(game.getPlayers().get(0));
        for (User player : game.getPlayers()) {
            player.getGovernance().setGovernanceResource(new GovernanceResource());
            player.getGovernance().getGovernanceResource().setOwner(player);
            player.getGovernance().setLordDead(false);
            player.getGovernance().setFearRate(0);
            player.getGovernance().setFoodRate(RateNumber.FOOD_RATE_MINUS_2);
            player.getGovernance().setTaxRate(RateNumber.TAX_RATE_0);
            player.getGovernance().setPopulation(15);
            player.getGovernance().setUnemployedPopulation(15);
            player.getGovernance().setGold(100000);
            player.getGovernance().getGovernanceResource().changeAmountOfItemInStockpile(Material.WOOD, 1000);
            player.getGovernance().getGovernanceResource().changeAmountOfItemInStockpile(Material.STONE, 1000);
            player.getGovernance().getGovernanceResource().changeAmountOfItemInStockpile(Material.IRON, 1000);
            initializeResources(player);
        }
    }

    public void initializeResources(User player) {
        for (Material material : Material.values()) {
            player.getGovernance().getGovernanceResource().addToStorage(material);
        }
    }

    public static void setDefaultMaps(int row, int column) {
        System.out.println("this is row " + row);
        System.out.println("this is column " + column);
        Cell[][] cells = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setX(i);
                cells[i][j].setY(j);
                if (i % 9 == 0) cells[i][j].setTexture(Texture.GROUND);
                else if ((i % 9) == 1) cells[i][j].setTexture(Texture.GRAVEL_GROUND);
                else if ((i % 9) == 2) cells[i][j].setTexture(Texture.BOULDER);
                else if ((i % 9) == 3) cells[i][j].setTexture(Texture.ROCK);
                else if ((i % 9) == 4) cells[i][j].setTexture(Texture.GROUND);
                else if ((i % 9) == 5) cells[i][j].setTexture(Texture.GRASS);
                else if ((i % 9) == 6) cells[i][j].setTexture(Texture.MEADOW);
                else if ((i % 9) == 7) cells[i][j].setTexture(Texture.DENSE_GRASSLAND);
                else cells[i][j].setTexture(Texture.PLAIN);
            }
        }
        completeMap(column, cells, Texture.OIL, Texture.SHALLOW_WATER, Texture.RIVER, Texture.SMALL_POND, Texture.BIG_POND, Texture.BEACH);
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
                else if ((i % 9) == 5) cells2[i][j].setTexture(Texture.GROUND);
                else if ((i % 9) == 6) cells2[i][j].setTexture(Texture.DENSE_GRASSLAND);
                else if ((i % 9) == 7) cells2[i][j].setTexture(Texture.PLAIN);
                else cells2[i][j].setTexture(Texture.MEADOW);
            }
        }
        completeMap(column, cells2, Texture.BEACH, Texture.GROUND, Texture.GROUND, Texture.RIVER, Texture.SHALLOW_WATER, Texture.OIL);
        defaultMaps.put("option number 2", cells2);
    }

    private static void completeMap(int column, Cell[][] cells, Texture oil, Texture shallowWater, Texture river, Texture smallPond, Texture bigPond, Texture beach) {
        for (int i = 0; i < column; i++) {
            if ((i % 9) == 0) cells[0][i].setTexture(oil);
            else if ((i % 9) == 1) cells[0][i].setTexture(shallowWater);
            else if ((i % 9) == 2) cells[0][i].setTexture(river);
            else if ((i % 9) == 3) cells[0][i].setTexture(smallPond);
            else if ((i % 9) == 4) cells[0][i].setTexture(bigPond);
            else if ((i % 9) == 5) cells[0][i].setTexture(beach);
            else if ((i % 9) == 6) {
                cells[0][i].setTexture(Texture.SEA);
                break;
            }
        }
    }

    public static Game getGame() {
        return game;
    }

    public Output selectBuilding(int row, int column) {
        if (isCoordinateInvalid(row, column))
            return Output.WRONG_COORDINATES;
        Building building = game.getCells()[row - 1][column - 1].getBuilding();
        if (building != null) {
            if (building.getOwner().equals(game.getCurrentPlayer())) {
                game.setSelectedBuilding(building);
                return Output.SELECT_BUILDING;
            }
            return Output.WRONG_SELECT_FOR_BUILDING;
        }
        return Output.NO_BUILDING;
    }

    public Output dropBuilding(int x, int y, String type) {
        if (x <= 0 || y <= 0 || x > game.getCells().length || y > game.getCells()[0].length)
            return Output.WRONG_COORDINATES;
        if (type.matches("headquarter") && game.getCurrentPlayer().getGovernance().getBuildingByName("headquarter") != null) return Output.INVALID_BUILDING;
        if (game.getCells()[x - 1][y - 1].getBuilding() != null) return Output.INVALID_CELL;
        Building building = BuildingBuilder.BuildingBuilder(type, game.getCurrentPlayer());
        if (building == null) return null;
        Governance governance = game.getCurrentPlayer().getGovernance();
        if (type.equals("iron mine") && !game.getCells()[x - 1][y - 1].getTexture().equals(Texture.IRON))
            return Output.INVALID_CELL;
        if (type.equals("food stockpile") && game.getCurrentPlayer().getGovernance().getAllBuildingsByName("food stockpile") != null) {
            boolean canDropStorage = false;
            for (Building aroundBuilding : findBuildingsAround(game.getCells()[x - 1][y - 1])) {
                if (aroundBuilding.getName().equals("food stockpile") && building.getOwner().equals(game.getCurrentPlayer())) {
                    canDropStorage = true;
                    break;
                }
            }
            if (!canDropStorage) return Output.INVALID_CELL;
        }
        if (governance.getGold() < building.getCost()) return Output.NOT_ENOUGH_GOLD;
        if (game.getCurrentPlayer().getGovernance().getGovernanceResource().getAmountOfItemInStockpile(Material.WOOD) < building.getWood())
            return Output.NOT_ENOUGH_RESOURCE;
        if (governance.getGovernanceResource().getAmountOfItemInStockpile(Material.STONE) < building.getStone())
            return Output.NOT_ENOUGH_RESOURCE;
        if (governance.getUnemployedPopulation() < building.getLadderlans()) return Output.NOT_ENOUGH_UNITS;
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
            Unit newUnit = UnitsBuilder.unitsBuilder(name, game.getCurrentPlayer());
            game.getCurrentPlayer().getGovernance().addUnit(newUnit);
            unit.setCell(null);
        }
        governance.setGold(governance.getGold() - unit.getCost() * number);
        governance.setUnemployedPopulation(governance.getUnemployedPopulation() - number);
        return Output.SUCCESSFUL_UNIT_CREATION;
    }

    public Output dropUnit(int x, int y, int count) {
        ArrayList<Unit> units = game.getCurrentPlayer().getGovernance().getNewUnits();
        if (isCoordinateInvalid(x - 1, y - 1)) return Output.WRONG_COORDINATES;
        if (units.size() < count) return Output.NOT_ENOUGH_UNIT;
        Cell cell = game.getCells()[x - 1][y - 1];
        for (Unit unit : units) {
            if (cell.isBlocked(unit)) return Output.INVALID_CELL;
            unit.setCell(cell);
            cell.addUnit(unit);
        }
        return Output.SUCCESSFUL_ACTION;
    }

    public Output repairCastle() {
        if (game.getSelectedBuilding() != null) {
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
        return Output.NO_BUILDING;
    }

    public Output selectUnit(int x, int y) {
        if (isCoordinateInvalid(x, y)) return Output.WRONG_COORDINATES;
        ArrayList<Unit> selectedUnits = new ArrayList<>();
        for (Unit unit : game.getCells()[x - 1][y - 1].getUnits()) {
            if (unit.getOwner().equals(game.getCurrentPlayer())) {
                selectedUnits.add(unit);
            }
        }
        if (selectedUnits.size() == 0) return Output.NO_THIS_TYPE_UNIT;
        game.setSelectedUnit(selectedUnits);
        return Output.SUCCESSFUL_ACTION;
    }

    public Output moveUnit(int x, int y) {
        if (isCoordinateInvalid(x, y)) return Output.INVALID_NUMBER;
        for (Unit unit : game.getSelectedUnit()) {
            if (unit.getCell().equals(village)) unit.setCell(unit.getPreviousCell());
            if (game.getCells()[x - 1][y - 1].isBlocked(unit)) return Output.INVALID_MOVE;
            if (findPath(unit.getCell().getX(), unit.getCell().getY(), x - 1, y - 1, unit).size() == 0) {
                return Output.INVALID_MOVE;
            }
            unit.setCurrentTargetX(x - 1);
            unit.setCurrentTargetY(y - 1);
            unit.setState(UnitState.MOVING);
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
            unit.setState(UnitState.MOVING);
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
        return Output.UNIT_STATE_SET_SUCCESSFULLY;
    }

    public Output attackToEnemy(int x, int y) {
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

    public Output airAttack(int x, int y) {
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
        if (unit.getName().equals("fire thrower")) {
            cell.setTexture(Texture.FIRE_TEXTURE);
        }
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

    public void applyChanges() {
        savePopularity();
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
        removeDeadGovernance();
        illness();
        updateIllness();
    }

    private void savePopularity() {
        for (User user: game.getPlayers()) {
            user.getGovernance().setPopularityChange(user.getGovernance().getPopularity());
        }
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
                    ((Ladderman) unit).addLadder(findBuildingsAround(unit.getCell()));
                }
                if (unit instanceof Spearman) {
                    ((Spearman) unit).dropLadder(findBuildingsAround(unit.getCell()));
                }
                if (unit instanceof Assassin) {
                    ((Assassin) unit).getCastleDepartment(findBuildingsAround(unit.getCell()));
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

    private ArrayList<Building> findBuildingsAround(Cell cell) {
        ArrayList<Building> buildings = new ArrayList<>();
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
                if (unit.getState() == null) unit.setState(UnitState.STANDING);
                if (unit.getState().equals(UnitState.STANDING)) {
                    unit.setCurrentTargetX(-1);
                    unit.setCurrentTargetY(-1);
                    unit.setNextTargetY(-1);
                    unit.setNextTargetX(-1);
                } else if (unit.getState().equals(UnitState.OFFENSIVE)) {
                    Cell enemyCell = findEnemy(1000, unit.getCell().getX(), unit.getCell().getY(), user);
                    if (enemyCell != null) {
                        unit.setCurrentTargetX(enemyCell.getX());
                        unit.setCurrentTargetY(enemyCell.getY());
                        unit.setNextTargetY(-1);
                        unit.setNextTargetX(-1);
                    }
                } else if (unit.getState().equals(UnitState.DEFENSIVE)) {
                    Cell enemyCell = findEnemy(3, unit.getCell().getX(), unit.getCell().getY(), user);
                    if (enemyCell != null) {
                        unit.setCurrentTargetX(enemyCell.getX());
                        unit.setCurrentTargetY(enemyCell.getY());
                        unit.setNextTargetY(-1);
                        unit.setNextTargetX(-1);
                    }
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
                if (unit.getCell() != null)
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
                Building building = cell[j].getBuilding();
                if (building != null) {
                    if (building.getName().equals("church") || building.getName().equals("cathedral"))
                        building.getOwner().getGovernance().setPopularity(building.getOwner().getGovernance().getPopulation() + 1);
                }
            }
        }
    }
    private void illness() {
        int length = game.getCells().length;
        int width = game.getCells()[0].length;
        Random lengthRandom = new Random();
        int lengthResult = lengthRandom.nextInt(length + 1) - 1;
        Random widthRandom = new Random();
        int widthResult = widthRandom.nextInt(width + 1) - 1;
        int flag = 0;
        if (lengthResult >= 0 && widthResult >= 0) {
            for (int i = 0; i < game.getCells()[lengthResult][widthResult].getUnits().size(); i++) {
                if (game.getCells()[lengthResult][widthResult].getUnits().get(i).getName().equals("engineer"))
                    flag = 1;
            }
        }
        if (flag == 0) {
            game.getCells()[lengthResult][widthResult].setIllness(true);
            game.getCells()[lengthResult][widthResult].setTexture(Texture.ILLNESS);
        }
    }


    private void updateIllness() {
        for (int i = 0; i < game.getCells().length; i++) {
            for (int j = 0; j < game.getCells()[0].length; j++) {
                if (game.getCells()[i][j].isIllness() && game.getCells()[i][j].getBuilding() != null) {
                    int flag = 0;
                    for (int k = 0; k < game.getCells()[i][j].getUnits().size(); k++) {
                        if (game.getCells()[i][j].getUnits().get(k).getOwner().equals(game.getCells()[i][j].getBuilding().getOwner()) && game.getCells()[i][j].getUnits().get(k).getName().equals("engineer"))
                            flag = 1;
                    }
                    if (flag == 0)
                        game.getCells()[i][j].getBuilding().getOwner().getGovernance().setPopularity(game.getCells()[i][j].getBuilding().getOwner().getGovernance().getPopulation() - 1);
                    else {
                        game.getCells()[i][j].setTexture(Texture.GROUND);
                        game.getCells()[i][j].setIllness(false);
                    }
                }
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
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (game.getPlayers().get(i).getGovernance().getFearRate() > 0)
                game.getPlayers().get(i).getGovernance().setTurnsToCompleteBuilding(1);
            if (game.getPlayers().get(i).getGovernance().getFearRate() < 0)
                game.getPlayers().get(i).getGovernance().setTurnsToCompleteBuilding(2);
        }
    }

    private void updateDamageEfficiency() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Governance governance = game.getPlayers().get(i).getGovernance();
            double fearRate = governance.getFearRate();
            for (int j = 0; j < governance.getUnits().size(); j++) {
                double newDamage = governance.getUnits().get(j).getDamage() +
                        (fearRate * 5 / 100);
                governance.getUnits().get(j).setDamage(newDamage);
            }
        }
    }

    public void removeDeadGovernance() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (game.getPlayers().get(i).getGovernance().getLord() == null)
                game.getPlayers().get(i).getGovernance().setLordDead(true);
        }
    }

    public boolean isGameEnded() {
        return game.getPlayers().size() == calculateDeadGovernance() + 1;
    }

    public int calculateDeadGovernance() {
        int counter = 0;
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (game.getPlayers().get(i).getGovernance().isLordDead())
                counter++;
        }
        return counter;
    }

    public void updateScores() {
        findWinner().setScore(findWinner().getScore() + (int) findWinner().getGovernance().getGold());
    }

    public String showGameResult() {
        updateScores();
        StringBuilder ans = new StringBuilder();
        ans.append("<<<GAME OVER>>>" + "\n");
        ans.append("The winner of the game is ").append(findWinner().getUsername()).append("\n");
        ans.append("Losers:" + "\n");
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (!game.getPlayers().get(i).getUsername().equals(findWinner().getUsername()))
                ans.append(game.getPlayers().get(i).getUsername()).append("\n");
        }
        return String.valueOf(ans);
    }

    public User findWinner() {
        int maxGold = 0;
        User winner = game.getCurrentUser();
        for (User user : game.getPlayers()) {
            if (!user.getGovernance().isLordDead()) {
                if (user.getGovernance().getGold() > maxGold)
                    winner = user;
            }
        }
        return winner;
    }

    public void clearGame() {
        game.setPlayers(null);
        game.setCells(null);
        game.setCurrentPlayer(null);
        game.setSelectedBuilding(null);
        game.setSelectedUnit(null);
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


    public ArrayList<Cell> findPath(int sx, int sy, int tx, int ty, Unit unit) {
        Cell[][] cells = game.getCells();
        ArrayList<Cell> path = new ArrayList<>();
        if (cells[tx][ty].isBlocked(unit)) return null;
        path.add(cells[sx][sy]);
        boolean[][] arr = new boolean[game.getRow()][game.getColumn()];
        for (int i = 0; i < game.getRow(); i++) {
            arr[i] = new boolean[game.getColumn()];
        }
        if (backTrack(arr, cells, path, sx, sy, tx, ty, unit)) return path;
        return null;
    }

    public boolean backTrack(boolean[][] arr, Cell[][] cells, ArrayList<Cell> path, int currentX, int currentY, int tx, int ty, Unit unit) {
        if (currentX == tx && currentY == ty) return true;
        int[][] array = prioritizePathFinding(currentX, currentY, tx, ty);
        for (int i = 0; i < 4; i++) {
            if (currentX + array[0][i] >= 0 && currentX + array[0][i] < game.getRow() &&
                    currentY + array[1][i] >= 0 && currentY + array[1][i] < game.getColumn()) {
                if (!cells[currentX + array[0][i]][currentY + array[1][i]].isBlocked(unit) &&
                        !(arr[currentX + array[0][i]][currentY + array[1][i]])) {
                    Cell cell = cells[currentX + array[0][i]][currentY + array[1][i]];
                    path.add(cell);
                    arr[currentX + array[0][i]][currentY + array[1][i]] = true;
                    if (cell.getTexture().equals(Texture.SHALLOW_WATER)) {
                        path.add(cell);
                    }
                    if (backTrack(arr, cells, path, cell.getX(), cell.getY(), tx, ty, unit)) return true;
                    path.remove(cell);
                    if (cell.getTexture().equals(Texture.SHALLOW_WATER)) {
                        path.remove(cell);
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

    public String allCellsInfo(int firstX, int firstY, int finalX, int finalY) {
        int units = 0;
        double averageRate = 0;
        int minRate = 1000;
        int maxRate = 0;
        for (int i = firstX; i <= finalX; i++) {
            for (int j = firstY; j <= finalY; j++) {
                Cell cell = game.getCells()[i][j];
                units += cell.getUnits().size();
                if (cell.getBuilding() != null && cell.getBuilding() instanceof Producer) {
                    int rate = ((Producer) cell.getBuilding()).getProductionRate();
                    averageRate += rate;
                    if (rate > maxRate) maxRate = rate;
                    if (rate < minRate) minRate = rate;
                } else {
                    minRate = 0;
                }
            }
        }
        averageRate /= ((finalX - firstX + 1) * (finalY - firstY + 1));
        return "units: " + units + "\naverage rate: " + averageRate + "\nminimum rate: " + minRate + "\nmaximum rate: " + maxRate;
    }

    public void enterMainMenu() throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setMainUserController(game.getCurrentUser().getUsername());
        mainMenu.start(RegisterMenu.getStage());
    }
}
