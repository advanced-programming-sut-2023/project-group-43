package controller.UserControllers;

import model.User;

public class ScoreboardController {
    User currentUser;

    public ScoreboardController(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
