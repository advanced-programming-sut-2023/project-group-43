package model;

import java.util.ArrayList;

public class DataBase {

    private static DataBase dataBase = null;

    ArrayList<User> users = new ArrayList<>();
    User loggedInUser;

    User currentUser;

    private DataBase() {
    }


    public static DataBase getInstance() {
        if(dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
