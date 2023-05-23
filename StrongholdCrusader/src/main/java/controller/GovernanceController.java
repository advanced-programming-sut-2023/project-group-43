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
        stringBuilder.append("\nFood");
        stringBuilder.append("\nTax");
        stringBuilder.append("\nFear");
        stringBuilder.append("\nReligion");
        return String.valueOf(stringBuilder);
    }

    public String showPopularity() {
        return String.valueOf(game.getCurrentPlayer().getGovernance().getPopularity());
    }

    public String showFoodList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<<<Food List>>>");
        stringBuilder.append("\nmeat");
        stringBuilder.append("\ncheese");
        stringBuilder.append("\napple");
        stringBuilder.append("\nbread");
        return String.valueOf(stringBuilder);
    }

    public Output foodRate(int rate) {
        RateNumber rateNumber = RateNumber.getRateNumberEnumByTypeAndRateNumber("food", rate);
        game.getCurrentPlayer().getGovernance().setFoodRate(rateNumber);
        return Output.SUCCESSFUL_FOOD_RATE_CHANGE;
    }

    public String showFoodRate() {
        return String.valueOf(game.getCurrentPlayer().getGovernance().getFoodRate());
    }

    public Output taxRate(int rate) {
        RateNumber rateNumber = RateNumber.getRateNumberEnumByTypeAndRateNumber("tax", rate);
        game.getCurrentPlayer().getGovernance().setTaxRate(rateNumber);
        return Output.SUCCESSFUL_TAX_RATE_CHANGE;
    }

    public int showTaxRate() {
        return (game.getCurrentPlayer().getGovernance().getTaxRate().getRateNumber());
    }

    public Output fearRate(int rate) {
        game.getCurrentPlayer().getGovernance().setFearRate(rate);
        return Output.SUCCESSFUL_FEAR_RATE_CHANGE;
    }

    public int showFearRate() {
        return ((game.getCurrentPlayer().getGovernance().getFearRate()));
    }

    public Game getGame() {
        return game;
    }
}
