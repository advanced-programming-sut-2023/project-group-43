package network;

import com.google.gson.Gson;

public class Packet {
    String command;
    String value;

    public Packet(String command,String value) {
        this.command = command;
        this.value = value;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
