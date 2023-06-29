package model;

import enums.BuildingEnums.BuildingEnum;
import enums.environmentEnums.Texture;
import model.buildings.Building;
import model.units.Unit;

import java.util.ArrayList;

public class Game {
    private User currentUser = new User("n","n","n","n","n","n","n");

    private User currentPlayer;

    private int row = 15;
    private int column = 30;
    private ArrayList<User> players = new ArrayList<>();

    private ArrayList<Trade> trades = new ArrayList<>();

    private static int tradeId = 0;
    private Cell[][] cells = new Cell[100][100] ;

    private Building selectedBuilding;

    private ArrayList<Unit> selectedUnit = new ArrayList<>();

    private int turns;
    private int currentMapX;
    private int getCurrentMapY;

    public Game() {

        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < column ; j++){
                Cell cell = new Cell();
                cell.setTexture(Texture.GROUND);
                cells[i][j] = cell;
            }
        }
        for(int i = 5 ; i < 8 ; i++){
            for (int j = 10 ; j < 25 ; j++) {
                Cell cell = new Cell();
                cell.setTexture(Texture.DENSE_GRASSLAND);
                cells[i][j] = cell;
            }
        }
        cells[8][20].setTexture(Texture.SMALL_POND);
        Building building = new Building(BuildingEnum.BARRACK.getName(), currentUser);
        cells[10][10].setBuilding(building);
    }

    public int getCurrentMapX() {
        return currentMapX;
    }

    public void setCurrentMapX(int currentMapX) {
        this.currentMapX = currentMapX;
    }

    public int getGetCurrentMapY() {
        return getCurrentMapY;
    }

    public void setGetCurrentMapY(int getCurrentMapY) {
        this.getCurrentMapY = getCurrentMapY;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(User currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public void setSelectedBuilding(Building building) {
        selectedBuilding = building;
    }

    public ArrayList<Unit> getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(ArrayList<Unit> selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void addPlayer(User player) {
        players.add(player);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public void addTrade(Trade trade) {
        trade.setId(tradeId);
        tradeId++;
        trades.add(trade);
        for (User user : players) {
            if (!user.getUsername().equals(currentPlayer.getUsername())) {
                user.addTrade(trade);
            }
        }
    }

    public Trade getTradeById(int id) {
        for (Trade trade : trades) {
            if (trade.getId() == id && !trade.isAccepted())
                return trade;
        }
        return null;
    }
}