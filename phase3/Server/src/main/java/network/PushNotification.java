package network;

import model.DataBase;

import java.io.IOException;

public class PushNotification extends Thread {

    private Connection connection;

    public PushNotification(Connection connection) {
        this.connection = connection;
    }

    @Override
    public synchronized void run() {
        try {
            sendUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                sendPublicChat();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void sendPublicChat() throws IOException {
        String messages = DataBase.getInstance().getPublicChatJson();
        Packet packet = new Packet("new chat", messages);
        connection.dataOutputStream.writeUTF(packet.toJson());
    }

    private void sendUsers() throws IOException {
        String users = DataBase.getInstance().getJsonString();
        Packet packet = new Packet("users", users);
        connection.dataOutputStream.writeUTF(packet.toJson());
    }
}
