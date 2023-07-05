package model;


import java.util.ArrayList;

public class Chat {

    private String name;
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<User> members = new ArrayList<>();

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void addMember(User user) {
        members.add(user);
    }
}
