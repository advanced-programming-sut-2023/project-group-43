package network;

import com.google.gson.Gson;

public class Packet {
    String command;
    String topic;
    String value;

    public Packet(String command, String topic, String value) {
        this.command = command;
        this.topic = topic;
        this.value = value;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
