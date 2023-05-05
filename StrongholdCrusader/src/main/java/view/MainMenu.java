package view;

import controller.MainController;
import controller.RegisterAndLoginController;
import enums.menuEnums.MainMenuCommands;
import model.DataBase;

import java.util.Scanner;

public class MainMenu extends Menu{

    private MainController mainController;

    public MainMenu(MainController mainController) {
        this.mainController = mainController;
    }

    public void run(){
        System.out.println("main menu:");
        String input;
        while (true){
            input = scanner.nextLine();
            if(input.matches("back"))
                return;
            else if(MainMenuCommands.getMatcher(input,MainMenuCommands.ENTER_PROFILE_MENU) != null) {
                enterProfileMenu();
            }
            else if(MainMenuCommands.getMatcher(input,MainMenuCommands.ENTER_CHANGE_ENVIRONMENT_MENU)!= null) {
                enterChangeEnvironmentMenu();
            }
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
