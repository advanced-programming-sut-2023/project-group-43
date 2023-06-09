package model;

import controller.GameControllers.GameController;
import enums.BuildingEnums.BuildingEnum;
import enums.environmentEnums.Texture;
import model.buildings.Building;
import model.units.Unit;

import java.util.ArrayList;

public class Game {

    private User currentUser;
    private User currentPlayer;
    private int row;
    private int column;
    private ArrayList<User> players = new ArrayList<>();

    private ArrayList<Trade> trades = new ArrayList<>();

    private static int tradeId = 0;
    private Cell[][] cells;

    private Building selectedBuilding;

    private ArrayList<Unit> selectedUnit = new ArrayList<>();

    private int turns;




    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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