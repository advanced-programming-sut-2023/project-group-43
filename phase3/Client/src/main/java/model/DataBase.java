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

    private class sortUsers implements Comparator<User> {
        public int compare(User o1, User o2) {
            return Integer.compare(o1.getScore(), o2.getScore());
        }
    }

    public ArrayList<User> scoreboard() {
        ArrayList<User> usersScoreboard = new ArrayList<>();
        usersScoreboard.sort(new sortUsers());
        return usersScoreboard;
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