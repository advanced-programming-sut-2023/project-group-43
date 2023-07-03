package network;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
            throw new RuntimeException(e);
        }
    }


    private synchronized void handleClient() throws IOException {
        while (true) {
            if (dataInputStream.available() != 0) {
                String data = dataInputStream.readUTF();
                try {
                    Packet packet = new Gson().fromJson(data, Packet.class);
                    String topic = packet.topic;
                    String value = packet.value;
                    String command = packet.command;
                    dataOutputStream.writeUTF("");
                    switch (command) {

                    }
                } catch (JsonSyntaxException e) {
                    dataOutputStream.writeUTF("400: Missing topic or command fields.");
                }
            }
        }
    }

}
