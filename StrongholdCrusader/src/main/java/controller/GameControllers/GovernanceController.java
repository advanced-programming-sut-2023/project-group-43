package controller.GameControllers;

import enums.Output;
import enums.RateNumber;
import model.Game;
import model.User;

public class GovernanceController {
    private final User currentUser;
    private Game game;

    public GovernanceController(User currentUser, Game game) {
        this.currentUser = currentUser;
        this.game = game;
    }

    private String showPopularityFactors() {
        return """
                <<<Popularity Factors>>
                Food
                Tax
                Fear
                Religion""";
    }

    private String showPopularity(Game game) {
        return String.valueOf(game.getCurrentPlayer().getGovernance().getPopularity());
    }

    private String showFoodList() {
        return """
                <<<Food List>>>
                meat
                cheese
                apple
                bread""";
    }

    private Output foodRate(int rate, Game game) {
        RateNumber rateNumber = RateNumber.getRateNumberEnumByTypeAndRateNumber("food", rate);
        game.getCurrentPlayer().getGovernance().setFoodRate(rateNumber);
        return Output.SUCCESSFUL_FOOD_RATE_CHANGE;
    }

    private int showFoodRate(Game game) {
        return (game.getCurrentPlayer().getGovernance().getFoodRate().getRateNumber());
    }

    private Output taxRate(int rate, Game game) {
        RateNumber rateNumber = RateNumber.getRateNumberEnumByTypeAndRateNumber("tax", rate);
        game.getCurrentPlayer().getGovernance().setTaxRate(rateNumber);
        return Output.SUCCESSFUL_TAX_RATE_CHANGE;
    }

    private int showTaxRate(Game game) {
        return (game.getCurrentPlayer().getGovernance().getTaxRate().getRateNumber());
    }

    private Output fearRate(int rate, Game game) {
        game.getCurrentPlayer().getGovernance().setFearRate(rate);
        return Output.SUCCESSFUL_FEAR_RATE_CHANGE;
    }

    private int showFearRate(Game game) {
        return ((game.getCurrentPlayer().getGovernance().getFearRate()));
    }
}
