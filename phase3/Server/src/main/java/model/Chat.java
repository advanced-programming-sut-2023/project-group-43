package model;

import enums.ChatType;

import java.util.ArrayList;

public class Chat {

    private String name;
    private ChatType type;
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<User> members = new ArrayList<>();

    public Chat(String name, ChatType chatType) {
        this.name = name;
        this.type = chatType;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatType getType() {
        return type;
    }

    public void setType(ChatType type) {
        this.type = type;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
