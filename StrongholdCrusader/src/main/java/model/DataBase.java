package model;

import java.util.ArrayList;

public class DataBase {

    private static DataBase dataBase = null;

    ArrayList<User> users = new ArrayList<>();
    User loggedInUser;


    private DataBase() {
    }


    public static DataBase getInstance() {
        if(dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
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

    public User getUserByUsername(String userName) {
        return User;
    }
}
