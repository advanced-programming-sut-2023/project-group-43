package model;

import model.buildings.Building;
import model.units.Unit;
import model.buildings.*;

import java.util.ArrayList;

public class Game {
    private User currentUser;

    private User currentPlayer;
    private ArrayList<User> players;

    private ArrayList<Trade> trades;
    private Cell[][] cells;

    private Building selectedBuilding;

    private Unit selectedUnit;

    private int turns;

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

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public void setTrades(ArrayList<Trade> trades) {
        this.trades = trades;
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void addPlayer(User player) {
        players.add(player);
    }

    public void addTrade(Trade trade) {}

    public void removeTrade(Trade trade) {}
}
