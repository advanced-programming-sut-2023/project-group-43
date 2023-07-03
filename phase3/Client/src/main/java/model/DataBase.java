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
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
        for (JsonElement jsonElement : jsonArray)
            users.add(gson.fromJson(jsonElement, User.class));
        for (User user : users) {
            user.setGovernance(new Governance());
        }
    }
    public void saveData() {
        for (User user: users) {
            user.setGovernance(null);
        }
        try {
            Gson gson = new Gson();
            String json = gson.toJson(users);
            try {
                FileWriter myWriter = new FileWriter("data.json");
                myWriter.write(json);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
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
        public int compare(User a, User b) {
            if (a.getScore() != b.getScore()) return b.getScore() - a.getScore();
            else return a.getScore() - b.getScore();
        }
    }

    public ArrayList<User> scoreboard() {
        ArrayList<User> usersScoreboard = new ArrayList<>();
        usersScoreboard.sort(new sortUsers());
        return usersScoreboard;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}