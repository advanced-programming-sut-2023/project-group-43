package controller;

import enums.Output;
import enums.RateNumber;
import model.Game;

public class GovernanceController {

    private Game game;

    public GovernanceController(Game game) {
        this.game = game;
    }

    public String showPopularityFactors() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<<<Popularity Factors>>");
        stringBuilder.append("Food");
        stringBuilder.append("Tax");
        stringBuilder.append("Fear");
        stringBuilder.append("Religion");
        return String.valueOf(stringBuilder);
    }

    public String showPopularity() {
        return String.valueOf(game.getCurrentPlayer().getGovernance().getPopularity());
    }

    public String showFoodList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<<<Food List>>>");
        stringBuilder.append("meat");
        stringBuilder.append("cheese");
        stringBuilder.append("apple");
        stringBuilder.append("bread");
        return String.valueOf(stringBuilder);
    }

    public Output foodRate(int rate) {
        RateNumber rateNumber = RateNumber.getRateNumberEnumByTypeAndRateNumber("food", rate);
        game.getCurrentPlayer().getGovernance().setFoodRate(rateNumber);
        return Output.SUCCESSFUL_FOOD_RATE_CHANGE;
        //TODO
    }

    public String showFoodRate() {
        return String.valueOf(game.getCurrentPlayer().getGovernance().getFoodRate());
    }

    public Output taxRate(int rate) {
        RateNumber rateNumber = RateNumber.getRateNumberEnumByTypeAndRateNumber("tax", rate);
        game.getCurrentPlayer().getGovernance().setTaxRate(rateNumber);
        return Output.SUCCESSFUL_TAX_RATE_CHANGE;
    }

    public String showTaxRate() {
        return String.valueOf(game.getCurrentPlayer().getGovernance().getTaxRate());
    }

    public Output fearRate(int rate) {
        game.getCurrentPlayer().getGovernance().setFearRate(rate);
        return Output.SUCCESSFUL_FEAR_RATE_CHANGE;
    }

    public String showFearRate() {
        return String.valueOf(game.getCurrentPlayer().getGovernance().getFearRate());
    }

    public Game getGame() {
        return game;
    }
}
