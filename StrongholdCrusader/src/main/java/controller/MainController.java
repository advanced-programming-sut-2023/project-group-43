package controller;

import model.*;
import view.ChangeEnvironmentMenu;
import view.ProfileMenu;

public class MainController {
    User currentUser;
    public MainController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void enterChangeEnvironmentMenu() {
        ChangeEnvironmentController changeEnvironmentController = new ChangeEnvironmentController(currentUser);
        ChangeEnvironmentMenu changeEnvironmentMenu = new ChangeEnvironmentMenu(changeEnvironmentController);
        changeEnvironmentMenu.run();
    }

    public void enterProfileMenu() {
        ProfileController profileController = new ProfileController(currentUser);
        ProfileMenu profileMenu = new ProfileMenu(profileController);
        profileMenu.run();
    }
}
