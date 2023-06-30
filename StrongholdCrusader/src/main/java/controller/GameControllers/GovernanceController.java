package controller.GameControllers;

import enums.Output;
import enums.RateNumber;
import model.Game;
import model.User;
import model.buildings.Building;

import java.util.ArrayList;

public class GovernanceController {
    private final User currentUser;
    private Game game;

    public GovernanceController(User currentUser, Game game) {
        this.currentUser = currentUser;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public String showPopularityFactors() {
        return """
                <<<Popularity Factors>>
                Food
                Tax
                Fear
                Religion""";
    }

    public String showPopularity() {
        return String.valueOf(game.getCurrentPlayer().getGovernance().getPopularity());
    }

    public String showFoodList() {
        return """
                <<<Food List>>>
                meat
                cheese
                apple
                bread""";
    }

    public Output foodRate(int rate) {
        RateNumber rateNumber = RateNumber.getRateNumberEnumByTypeAndRateNumber("food", rate);
        game.getCurrentPlayer().getGovernance().setFoodRate(rateNumber);
        return Output.SUCCESSFUL_FOOD_RATE_CHANGE;
    }

    public int showFoodRate() {
        return (game.getCurrentPlayer().getGovernance().getFoodRate().getRateNumber());
    }

    public Output taxRate(int rate) {
        RateNumber rateNumber = RateNumber.getRateNumberEnumByTypeAndRateNumber("tax", rate);
        game.getCurrentPlayer().getGovernance().setTaxRate(rateNumber);
        return Output.SUCCESSFUL_TAX_RATE_CHANGE;
    }

    public int showTaxRate() {
        return (game.getCurrentPlayer().getGovernance().getTaxRate().getRateNumber());
    }

    public int showReligionRate() {
        ArrayList<Building> buildings = game.getCurrentPlayer().getGovernance().getAllBuildingsByName("church");
        return buildings.size();
    }

    public Output fearRate(int rate) {
        game.getCurrentPlayer().getGovernance().setFearRate(rate);
        return Output.SUCCESSFUL_FEAR_RATE_CHANGE;
    }

    public int showFearRate() {
        return ((game.getCurrentPlayer().getGovernance().getFearRate()));
    }
}
