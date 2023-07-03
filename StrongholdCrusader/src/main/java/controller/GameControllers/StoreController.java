package controller.GameControllers;

import enums.Output;
import enums.environmentEnums.Material;
import model.Game;
import model.Governance;

public class StoreController {

    private static Game game = null;
    private static GameController gameController;


    public StoreController(Game game, GameController gameController) {
        StoreController.game = game;
        StoreController.gameController = gameController;
    }

    public static Game getGame() {
        return game;
    }

    public static GameController getGameController() {
        return gameController;
    }

    public Output buy(Material material, int amount) {
        Governance governance = game.getCurrentPlayer().getGovernance();

        if (governance.getGold() < amount * material.getBuyingPrice())
            return Output.NOT_ENOUGH_MONEY;

        governance.changeGoldAmount(-amount * material.getBuyingPrice());

        governance.getGovernanceResource().changeAmountOfItemInStockpile(material, amount);

        return Output.SUCCESSFUL_PURCHASE;

    }

    public Output sell(Material material, int amount) {

        Governance governance = game.getCurrentPlayer().getGovernance();

        if (governance.getGovernanceResource().getAmountOfItemInStockpile(material) < amount)
            return Output.NOT_ENOUGH_QUANTITY;

        game.getCurrentPlayer().getGovernance().changeGoldAmount(amount * material.getSellingPrice());

        governance.getGovernanceResource().changeAmountOfItemInStockpile(material, -amount);

        return Output.SUCCESSFUL_SALE;
    }
}
