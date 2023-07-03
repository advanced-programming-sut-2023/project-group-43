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

    @Override
    public synchronized void run() {
        while (true) {
            String data = null;
            try {
                data = dataInputStream.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!data.startsWith("{")) {
                try {
//                    showData(data);
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(data);
        alert.show();
        switch (data) {
            case "some players are not online!":
                enterChangeEnvironmentMenu();
        }
    }

    public void enterChangeEnvironmentMenu() throws Exception {
        ChangeEnvironmentController changeEnvironmentController = new ChangeEnvironmentController(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()));
        ChangeEnvironmentMenu changeEnvironmentMenu = new ChangeEnvironmentMenu();
        changeEnvironmentMenu.setChangeEnvironmentController(changeEnvironmentController);
        changeEnvironmentMenu.start(RegisterMenu.getStage());
    }

    private void getPacket(Packet packet) throws Exception {
        switch (packet.command) {
            case "users":
                DataBase.getInstance().setUsers(packet.value);
//            case "game":
//                Game game = (new Gson()).fromJson(packet.value, Game.class);
//                GameController gameController = new GameController(game);
//                game.setCurrentUser(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()));
//                gameController.initializeGame();
//                GameMenu gameMenu = new GameMenu();
//                gameMenu.setGameController(gameController);
//                gameMenu.setTurns(game.getTurns());
//                gameMenu.start(RegisterMenu.getStage());
        }
    }
}
