package controller;

import model.User;

public class MainUserController {
    private final User currentUser;

    public MainUserController(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
