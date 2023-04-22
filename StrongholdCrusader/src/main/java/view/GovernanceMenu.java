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
        while (true){
            input = scanner.nextLine();
            if(input.matches("back"))
                return;
            else if(GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.SHOW_POPULARITY_FACTORS)!= null) {
                System.out.println(showPopularityFactors());
            }
            else if (GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.SHOW_POPULARITY)!= null) {
                System.out.println();
            }
            else if(GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.SHOW_FOOD_LIST)!= null) {
                System.out.println();
            }
            else if(GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.TAX_RATE)!= null) {
                System.out.println();
            }
            else if(GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.TAX_RATE_SHOW) != null) {
                System.out.println();
            }
            else if(GovernanceMenuCommands.getMatcher(input,GovernanceMenuCommands.FEAR_RATE) != null) {
                System.out.println();
            }
            else System.out.println("Invalid Command!");
        }
    }

    //TODO : i am not sure that popularity factors are completed
    private String showPopularityFactors() {
        return "1)food\n2)tax\n3)religion4)fear";
    }

    private String showPopularity() {
        return null;
    }

    private String showFoodList() {
        return null;
    }

    private String foodRate(Matcher matcher) {
        return null;
    }

    public static String taxRate(Matcher matcher) {
        return null;
    }

    public static String fearRate(Matcher matcher) {
        return null;
    }

}
