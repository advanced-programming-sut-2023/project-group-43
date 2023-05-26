package model;

import enums.environmentEnums.Material;

import java.util.ArrayList;

public class Trade {
    private User sender;
    private User receiver;
    private Material resource;
    private int price;
    private int amount;
    private String message;

    private String resourceName;

    private ArrayList<User> users = new ArrayList<>();

    private int id;
    private boolean isAccepted;

    public Trade(User sender, String resource, int amount, int price, String message) {
        this.resourceName = resource;
        this.sender = sender;
        this.amount = amount;
        this.price = price;
        this.message = message;
    }

    public Material getResource() {
        return resource;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public int getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public int getId() {
        return id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean isSeen(User player) {
        for (User user : users) {
            if (user.getUsername().equals(player.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
