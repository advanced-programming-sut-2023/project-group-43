package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import enums.ChatType;
import network.Client;


public class DataBase {

    private static DataBase dataBase = null;

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    private ArrayList<Chat> chats = new ArrayList<>();
    private Chat publicChat;

    private DataBase() {
        loadData();
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
                if (user.getAvatarNumber() == 0) user.setAvatarNumber(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Reader reader;
            try {
                reader = new FileReader("chats.json");
            } catch (FileNotFoundException e) {
                return;
            }
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            for (JsonElement jsonElement : jsonArray)
                chats.add(gson.fromJson(jsonElement, Chat.class));
            publicChat = getChatByName("public");
            if (publicChat == null) {
                publicChat = new Chat();
                publicChat.setMembers(users);
                publicChat.setName("public");
                chats.add(publicChat);
            }
            else {
                publicChat.setMembers(users);
            }
            publicChat.setChatType(ChatType.PUBLIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Chat getChatByName(String name) {
        for (Chat chat: chats) {
            if (chat.getName().equals(name)) return chat;
        }
        return null;
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
        } try {
            Gson gson = new Gson();

            String json = gson.toJson(chats);
            try {
                FileWriter myWriter = new FileWriter("chats.json");
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

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public void addUser(User user) {

        users.add(user);
        publicChat.addMember(user);
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

    public String getJsonString() {
        return (new Gson()).toJson(users);
    }

    public String getPublicChatJson() {
        return (new Gson()).toJson(publicChat);
    }

    public void addChat(Chat chat) {
        boolean isFound = false;
        for (Chat c: chats) {
            if (c.getName().equals(chat.getName()) && c.getChatType().equals(chat.getChatType())) {
                isFound = true;
                c.setMessages(chat.getMessages());
            }
        }
        if (!isFound) chats.add(chat);
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

    public ArrayList<Client> getClients() {
        return clients;
    }
}