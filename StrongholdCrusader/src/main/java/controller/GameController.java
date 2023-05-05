package controller;

import enums.Output;
import model.Cell;
import model.Game;
import model.User;

import java.util.ArrayList;

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
        return null;
    }

    public Output repairCastle() {
        return null;
    }

    public Output selectUnit(int x, int y) {
        return null;
    }

    public Output moveUnit(int x, int y) {
        return null;
    }

    public Output patrolUnit(int x1, int y1, int x2, int y2) {
        return null;
    }

    public Output setUnitState(int x, int y, String state) {
        return null;
    }

    public Output attack(int x, int y, String item) {
        return null;
    }

    private Output attackToEnemy(int x, int y) {
        return null;
    }

    private Output aearialAttack(int x, int y) {
        return null;
    }

    public Output pourOil(String direction) {
        return null;
    }

    public Output digTunnel(int x, int y) {
        return null;
    }

    public Output buildWeapon(String weapon) {
        return null;
    }

    public Output disbandUnit() {
        return null;
    }

    public void applyChanges() {
    }

    private void applyHitPointChange() {
    }

    private void applyDeathChange() {
    }

    public void updateForNextTurn() {
    }

    private void updateResources() {
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

    public void resetSelectCell() {
        selectedX = 0;
        selectedY = 0;
    }

    public void goToNextPerson() {
        User user = null;
        boolean isNextPlayerFound = false;
        for (User player : game.getPlayers()) {
            if (isNextPlayerFound)
                game.setCurrentPlayer(player);
            if (player.getUsername().equals(game.getCurrentPlayer().getUsername())) {
                isNextPlayerFound = true;
            }
        }
        if (user == null) game.setCurrentPlayer(game.getPlayers().get(0));
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
