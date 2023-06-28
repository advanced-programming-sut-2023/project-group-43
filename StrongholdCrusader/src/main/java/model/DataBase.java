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
        loadData();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    private void loadData() {
        try {
            Reader reader;
            try {
                reader = new FileReader("data.json");
            } catch (FileNotFoundException e) {
                return;
            }
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            for (JsonElement jsonElement : jsonArray)
                users.add(gson.fromJson(jsonElement, User.class));
            for (User user : users) {
                user.setGovernance(new Governance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
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

    public int getRank(User user) {
        for (int i = 0; i < scoreboard().size(); i++) {
            if (scoreboard().get(i).equals(user))
                return (i + 1);
        }
        return -1;
    }

    public User findLoggedInUser() {
        for (User user : users) {
            if (user.isLoggedIn) return user;
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}