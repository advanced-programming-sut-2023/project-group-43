package view;

import controller.GovernanceController;
import enums.menuEnums.GovernanceMenuCommands;
import model.DataBase;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GovernanceMenu extends Menu{

    private GovernanceController governanceController;

    public GovernanceMenu(GovernanceController governanceController) {
        this.governanceController = governanceController;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        System.out.println("governance menu:");
        while (true){
            input = scanner.nextLine();
            if(input.matches("back"))
                return;
            else if(GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.SHOW_POPULARITY_FACTORS)!= null) {
                System.out.println(governanceController.showPopularityFactors());
            }
            else if (GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.SHOW_POPULARITY)!= null) {
                System.out.println(governanceController.showPopularity());
            }
            else if(GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.SHOW_FOOD_LIST)!= null) {
                System.out.println(governanceController.showFoodList());
            }
            else if ((matcher = GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.FOOD_RATE)) != null) {
                System.out.println(foodRate(matcher));

            } else if ((matcher = GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.FOOD_RATE_SHOW))!= null) {
                System.out.println(governanceController.showFoodRate());
            }
            else if(GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.TAX_RATE)!= null) {
                System.out.println(taxRate(matcher));
            }
            else if((matcher = GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.TAX_RATE_SHOW)) != null) {
                System.out.println(governanceController.showTaxRate());
            }
            else if((matcher = GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.FEAR_RATE)) != null) {
                System.out.println(fearRate(matcher));
            }
            else System.out.println("Invalid Command!");
        }
    }

    private String foodRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        return governanceController.foodRate(rate);
    }

    public String taxRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        return governanceController.taxRate(rate);
    }

    public  String fearRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        return governanceController.fearRate(rate);
    }

}
