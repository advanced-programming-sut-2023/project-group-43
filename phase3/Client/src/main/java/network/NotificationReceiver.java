package network;

import com.google.gson.Gson;
import controller.GameControllers.ChangeEnvironmentController;
import controller.GameControllers.GameController;
import javafx.scene.control.Alert;
import model.DataBase;
import model.Game;
import view.ChangeEnvironmentMenu;
import view.GameMenu;
import view.MainMenu;
import view.RegisterMenu;

import java.io.DataInputStream;
import java.io.IOException;

public class NotificationReceiver extends Thread {
    private final DataInputStream dataInputStream;

    public NotificationReceiver(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

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

    private void showData(String data) throws Exception {
        if (data.equals("some players are not online!")) {

        }
    }

    public static String getData() {
        return data;
    }

    public static void setGame(Game game) {
        NotificationReceiver.game = game;
    }

    private void getPacket(Packet packet) throws Exception {
        if (packet.command.equals("users")) {
            DataBase.getInstance().setUsers(packet.value);
        } else if (packet.command.equals("game")) {
            System.out.println("game!");
            NotificationReceiver.data = "game";
            NotificationReceiver.game = (new Gson()).fromJson(packet.value, Game.class);
        }
    }

    public static Game getGame() {
        return game;
    }

    public static void setData(String data) {
        NotificationReceiver.data = data;
    }
}
