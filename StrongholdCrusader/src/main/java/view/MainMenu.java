package view;

import controller.MainController;
import controller.RegisterAndLoginController;
import model.DataBase;

public class MainMenu extends Menu{

    private MainController mainController;

    public MainMenu(MainController mainController) {
        this.mainController = mainController;
    }

    public void run(){}

    private void enterChangeEnvironmentMenu() {
        mainController.enterChangeEnvironmentMenu();
    }

    private void enterProfileMenu() {
        mainController.enterProfileMenu();
    }

}
