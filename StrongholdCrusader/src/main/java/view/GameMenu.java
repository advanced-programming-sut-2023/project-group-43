package view;

import controller.GameController;
import controller.RegisterAndLoginController;
import model.DataBase;

import java.util.regex.Matcher;

public class GameMenu extends Menu{

    private GameController gameController;

    private int turns;

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void run() {
    }

    private String showMap(Matcher matcher) {return null;}

    private String dropBuilding(Matcher matcher) {
        return null;
    }

    private String selectBuilding(Matcher matcher) {
        return null;
    }

    private String createUnit(Matcher matcher) {
        return null;
    }

    private String selectUnit(Matcher matcher) {
        return null;
    }

    private String moveUnit(Matcher matcher) {
        return null;
    }

    private String patrolUnit(Matcher matcher) {
        return null;
    }

    private String setUnitState(Matcher matcher) {
        return null;
    }

    private String attack(Matcher matcher) {
        return null;
    }

    private String pourOil(Matcher matcher) {
        return null;
    }

    private String digTunnel(Matcher matcher) {
        return null;
    }

    private String buildEquipment(Matcher matcher) {
        return null;
    }

    private void enterMapMenu() {}

    private void enterStoreMenu() {}

    private void enterTradeMenu() {}

    private void enterGovernmentMenu() {}

}
