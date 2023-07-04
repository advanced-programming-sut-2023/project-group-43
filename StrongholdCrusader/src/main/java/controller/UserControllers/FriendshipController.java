package controller.UserControllers;

import model.User;

public class FriendshipController {
    User currentUser;

    public FriendshipController(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
