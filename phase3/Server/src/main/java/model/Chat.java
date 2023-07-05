package model;


import enums.ChatType;

import java.util.ArrayList;

public class Chat {

    private String name;
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<User> members = new ArrayList<>();
    ChatType chatType;

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

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
    public void addMember(User user) {
        members.add(user);
    }

    public boolean isUserAChatMember(User user) {
        for (User member: members) {
            if (member.getUsername().equals(user.getUsername())) return true;
        }
        return false;
    }
}
