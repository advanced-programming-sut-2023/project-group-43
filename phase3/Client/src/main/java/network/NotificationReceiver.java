package network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import controller.GameControllers.ChangeEnvironmentController;
import controller.GameControllers.GameController;
import javafx.scene.control.Alert;
import model.*;
import view.ChangeEnvironmentMenu;
import view.GameMenu;
import view.MainMenu;
import view.RegisterMenu;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NotificationReceiver extends Thread {
    private final DataInputStream dataInputStream;

    public NotificationReceiver(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    private static ArrayList<Chat> chats = new ArrayList<>();

    private static String data;

    private static Game game;

    @Override
    public synchronized void run() {
        while (true) {
            data = null;
            try {
                data = dataInputStream.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!data.startsWith("{")) {
                try {
                    showData(data);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                Packet packet = new Gson().fromJson(data, Packet.class);
                try {
                    getPacket(packet);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void showData(String data) {
        if (data.equals("some players are not online!")) {

        }
    }

    public static String getData() {
        return data;
    }

    public static void setGame(Game game) {
        NotificationReceiver.game = game;
    }

    private void getPacket(Packet packet) {
        switch (packet.command) {
            case "users" -> DataBase.getInstance().setUsers(packet.value);
            case "game" -> {
                NotificationReceiver.data = "game";
                NotificationReceiver.game = (new Gson()).fromJson(packet.value, Game.class);
            }
            case "next person" -> {
                NotificationReceiver.data = "next person";
                NotificationReceiver.game = (new Gson()).fromJson(packet.value, Game.class);
            }
            case "new chat" -> {
                Chat chat = (new Gson()).fromJson(packet.value, Chat.class);
                if (chat != null) {
                    addChat(chat);
                }
            }
        }
    }

    private void addChat(Chat chat) {
        boolean isFound = false;
        for (Chat c: chats) {
            if (c.getName().equals(chat.getName()) && c.getChatType().equals(chat.getChatType())) {
                isFound = true;
                c.setMessages(chat.getMessages());
            }
        }
        if (!isFound) chats.add(chat);
    }

    public static Chat getChatByName(String name) {
        for (Chat chat: chats) {
            if (chat.getName().equals(name)) return chat;
        }
        return null;
    }

    public static ArrayList<Chat> getChats() {
        return chats;
    }

    public static Game getGame() {
        return game;
    }

    public static void setData(String data) {
        NotificationReceiver.data = data;
    }
}
