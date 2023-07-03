package network;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.DataBase;
import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
        for (Client client: DataBase.getInstance().getClients()) {
            if(client.getConnection().equals(this)) {
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
                        case "login":
                            Client client = new Client(DataBase.getInstance().getUserByUsername(value), this);
                            DataBase.getInstance().getClients().add(client);
                    }
                } catch (JsonSyntaxException e) {
                    dataOutputStream.writeUTF("400: Missing topic or command fields.");
                }
            }
        }
    }

}
