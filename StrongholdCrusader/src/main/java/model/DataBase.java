package model;

import java.util.ArrayList;
import java.util.Comparator;

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

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for(User user: users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for(User user: users) {
            if(user.getEmail().toLowerCase().equals(email.toLowerCase())) {
                return user;
            }
        }
        return null;
    }
    private static class sortUsers implements Comparator<User> {
        public int compare(User a, User b) {
            if (a.getScore() != b.getScore()) return b.getScore() - a.getScore();
            else return a.getScore() - b.getScore();
        }
    }
    public static ArrayList<User> scoreboard() {
        ArrayList<User> usersScoreboard = new ArrayList<>();
        usersScoreboard.sort(new sortUsers());
        return usersScoreboard;
    }
    public static int getRank(User user) {
        for (int i = 0; i < scoreboard().size(); i++) {
            if (scoreboard().get(i).equals(user))
                return (i+1);
        }
        return -1;
    }
}
