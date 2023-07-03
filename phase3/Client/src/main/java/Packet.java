import com.google.gson.Gson;

public class Packet {
    String command;
    String topic;
    String value;
    String topic2;
    String value2;

    public Packet(String command, String topic, String value, String topic2, String value2) {
        this.command = command;
        this.topic = topic;
        this.value = value;
        this.topic2 = topic2;
        this.value2 = value2;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
