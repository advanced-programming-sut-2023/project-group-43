package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class DataBase {

    private static DataBase dataBase = null;

    ArrayList<User> users = new ArrayList<>();

    private DataBase() {
    }

    public void setUsers(String json) {
        System.out.println(json);
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
        for (JsonElement jsonElement : jsonArray)
            users.add(gson.fromJson(jsonElement, User.class));
        for (User user : users) {
            user.setGovernance(new Governance());
        }
    }



    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().toLowerCase().equals(email.toLowerCase())) {
                return user;
            }
        }
        return null;
    }


    public ArrayList<User> scoreboard() {
        ArrayList<User> notSortedUsers = users;
        ArrayList<User> sortedUsers = new ArrayList<>();
        for (int i = 0 ; i < users.size(); i++) {
            User user = getMaxUser(notSortedUsers);
            notSortedUsers.remove(user);
            sortedUsers.add(user);
        }
        return sortedUsers;
    }

    private User getMaxUser(ArrayList<User> notSortedUsers) {
        int maxScore = -1;
        User maxUser = null;
        for (User user: notSortedUsers) {
            if (user.getScore() > maxScore) {
                maxUser = user;
                maxScore = user.getScore();
            }
        }
        return maxUser;
    }

    public Boolean isFriend(User user1 , User user2){
        for(int i = 0 ; i < user1.getFriends().size() ; i++){
            if(user1.getFriends().get(i).equals(user2))
                return true;
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}