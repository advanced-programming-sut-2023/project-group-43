package network;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.DataBase;
import model.Game;
import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread {
    Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;


    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public synchronized void run() {
        (new PushNotification(this)).start();
        try {
            handleClient();
        } catch (IOException e) {
            removeConnection();
            throw new RuntimeException(e);
        }
    }

    private void removeConnection() {
        for (Client client : DataBase.getInstance().getClients()) {
            if (client.getConnection().equals(this)) {
                DataBase.getInstance().getClients().remove(client);
            }
        }
    }


    private synchronized void handleClient() throws IOException {
        while (true) {
            if (dataInputStream.available() != 0) {
                String data = dataInputStream.readUTF();
                try {
                    Packet packet = new Gson().fromJson(data, Packet.class);
                    String value = packet.value;
                    String command = packet.command;
                    dataOutputStream.writeUTF("");
                    switch (command) {
                        case "new user":
                            User user = (new Gson()).fromJson(value, User.class);
                            DataBase.getInstance().addUser(user);
                            break;
                        case "login":
                            Client client = new Client(DataBase.getInstance().getUserByUsername(value), this);
                            DataBase.getInstance().getClients().add(client);
                            break;
                        case "logout":
                            for (Client gameClient: DataBase.getInstance().getClients()) {
                                if (gameClient.getConnection().equals(this))
                                    DataBase.getInstance().getClients().remove(gameClient);
                            }
                            break;
                        case "next person":
                            Game game1 = (new Gson()).fromJson(value, Game.class);
                            ArrayList<Client> clients1 = new ArrayList<>();
                            for (User player : game1.getPlayers()) {
                                System.out.println("player: " + player.getUsername());
                                for (Client clientGame : DataBase.getInstance().getClients()) {
                                    System.out.println("client: " + clientGame.getUser().getUsername());
                                    if (player.getUsername().equals(clientGame.getUser().getUsername())) {
                                        clients1.add(clientGame);
                                    }
                                }
                            }
                            System.out.println(clients1.size());
                            System.out.println(game1.getPlayers().size());
                            nextPerson(clients1, game1);
                            break;
                        case "start game":
                            Game game = (new Gson()).fromJson(value, Game.class);
                            ArrayList<Client> clients = new ArrayList<>();
                            for (User player : game.getPlayers()) {
                                for (Client clientGame : DataBase.getInstance().getClients()) {
                                    if (player.getUsername().equals(clientGame.getUser().getUsername())) {
                                        clients.add(clientGame);
                                    }
                                }
                            }
                            if (game.getPlayers().size() == clients.size()) {
                                startGame(clients, game);
                            } else {
                                showError();
                            }
                    }
                } catch (JsonSyntaxException e) {
                    dataOutputStream.writeUTF("400: Missing topic or command fields.");
                }
            }
        }
    }

    private void nextPerson(ArrayList<Client> clients, Game game) throws IOException {
        for (Client client : clients) {
            System.out.println("sending game to " + client.getUser().getUsername());
            Packet packet = new Packet("next person", (new Gson()).toJson(game));
            client.getConnection().dataOutputStream.writeUTF(packet.toJson());
        }
    }
    private void showError() throws IOException {
        dataOutputStream.writeUTF("some players are not online!");
    }

    private void startGame(ArrayList<Client> clients, Game game) throws IOException {
        for (Client client : clients) {
            System.out.println("sending game to " + client.getUser().getUsername());
            Packet packet = new Packet("game", (new Gson()).toJson(game));
            client.getConnection().dataOutputStream.writeUTF(packet.toJson());
        }
    }

}
