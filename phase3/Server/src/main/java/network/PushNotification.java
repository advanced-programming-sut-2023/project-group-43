package network;

import com.google.gson.Gson;
import model.Chat;
import model.DataBase;

import java.io.IOException;

public class PushNotification extends Thread {

    private Client client;
    private Connection connection;

    public PushNotification(Client client) {
        this.connection = client.getConnection();
        this.client = client;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                sendChats();
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

    private void sendChats() throws IOException {
        for (Chat chat: DataBase.getInstance().getChats()) {
            if (client.getUser() != null && chat.isUserAChatMember(client.getUser())) {
                Packet packet = new Packet("new chat", (new Gson()).toJson(chat));
                connection.dataOutputStream.writeUTF(packet.toJson());
            }
        }
    }


}
