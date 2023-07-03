package network;

import model.DataBase;
import view.RegisterMenu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;

    public Client(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        Client.dataInputStream = new DataInputStream(socket.getInputStream());
        Client.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        new NotificationReceiver(Client.dataInputStream).start();
    }

}
