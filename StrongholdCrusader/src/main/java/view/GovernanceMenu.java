package view;

import controller.GameControllers.GovernanceController;
import enums.Output;
import enums.menuEnums.GovernanceMenuCommands;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.regex.Matcher;

import static view.Menu.scanner;

public class GovernanceMenu extends Application {
    private static GovernanceController governanceController;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        //TODO --> handle game for controllers
        //governanceController = new GovernanceController(DataBase.getInstance().findLoggedInUser(), );
        GovernanceMenu.stage = stage;
    }

    public void run() {
        String input;
        Matcher matcher;
        System.out.println("governance menu:");
        while (true) {
            input = scanner.nextLine();
            if (input.matches("show current menu"))
                System.out.println(Output.GOVERNANCE_MENU.getString());
            else if (input.matches("back")) {
                if (governanceController.game().getSelectedBuilding() != null) {
                    if (governanceController.game().getSelectedBuilding().getName().equals("small stone gatehouse"))
                        governanceController.game().setSelectedBuilding(null);
                }
                System.out.println("game menu:");
                return;
            } else if (GovernanceMenuCommands.getMatcher(input, GovernanceMenuCommands.SHOW_POPULARITY_FACTORS) != null) {
                System.out.println(GovernanceController.showPopularityFactors());
            } else if (GovernanceMenuCommands.getMatcher(input, GovernanceMenuCommands.SHOW_POPULARITY) != null) {
                System.out.println(GovernanceController.showPopularity());
            } else if (GovernanceMenuCommands.getMatcher(input, GovernanceMenuCommands.SHOW_FOOD_LIST) != null) {
                System.out.println(GovernanceController.showFoodList());
            } else if ((matcher = GovernanceMenuCommands.getMatcher(input, GovernanceMenuCommands.FOOD_RATE)) != null) {
                System.out.println(foodRate(matcher));

            } else if ((matcher = GovernanceMenuCommands.getMatcher(input, GovernanceMenuCommands.FOOD_RATE_SHOW)) != null) {
                System.out.println(GovernanceController.showFoodRate());
            } else if (GovernanceMenuCommands.getMatcher(input, GovernanceMenuCommands.TAX_RATE) != null) {
                System.out.println(taxRate(matcher));
            } else if (GovernanceMenuCommands.getMatcher(input, GovernanceMenuCommands.TAX_RATE_SHOW) != null) {
                System.out.println(GovernanceController.showTaxRate());
            } else if ((matcher = GovernanceMenuCommands.getMatcher(input, GovernanceMenuCommands.FEAR_RATE)) != null) {
                System.out.println(fearRate(matcher));
            } else if (GovernanceMenuCommands.getMatcher(input, GovernanceMenuCommands.FEAR_RATE_SHOW) != null)
                System.out.println(GovernanceController.showFearRate());
            else {
                System.out.println("Invalid command");
            }
        }
    }


    private String foodRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        return (governanceController.foodRate(rate)).getString();
    }

    public String taxRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        return (governanceController.taxRate(rate)).getString();
    }

    public String fearRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        return (governanceController.fearRate(rate)).getString();
    }

}
