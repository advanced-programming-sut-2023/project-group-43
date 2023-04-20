package model;

public class Trade {
    private User sender;
    private User receiver;
    private ResourceType resourceType;
    private int price;
    private String message;

    private int id;

    private boolean isSeen;
    private boolean isAccepted;

    public Trade(User sender, User receiver, ResourceType resourceType, int price, String message) {}

    public ResourceType getResourceType() {
        return resourceType;
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

    public boolean isSeen() {
        return isSeen;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public int getId() {
        return id;
    }

}
