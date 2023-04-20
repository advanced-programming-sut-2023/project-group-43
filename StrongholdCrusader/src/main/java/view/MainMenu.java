package view;

import controller.MainController;
import controller.RegisterAndLoginController;
import model.DataBase;

import java.util.Scanner;

public class MainMenu extends Menu{

    private MainController mainController;

    public MainMenu(MainController mainController) {
        this.mainController = mainController;
    }

    public void run(Scanner scanner){
        String input = scanner.nextLine();
        while (true){
            if(input.matches("back"))
                return;
            else if(input.matches(""))
                System.out.println("");;
            else if(input.matches(""))
                System.out.println("");;
            else System.out.println("Invalid Command!");
        }
    }

    private void enterChangeEnvironmentMenu() {
        mainController.enterChangeEnvironmentMenu();
    }

    private void enterProfileMenu() {
        mainController.enterProfileMenu();
    }

}
