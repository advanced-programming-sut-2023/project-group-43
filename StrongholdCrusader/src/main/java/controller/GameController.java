package controller;

import java.lang.String;
import java.util.ArrayList;

import enums.Output;
import model.*;
import model.buildings.Building;

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

    public Output createUnit(String type, int count) {return null;}

    public Output repairCastle() {return null;}

    public Output selectUnit(int x, int y) {return null;}

    public Output moveUnit(int x, int y) {return null;}

    public Output patrolUnit(int x1, int y1, int x2, int y2) {return null;}

    public void clearGame() {
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
