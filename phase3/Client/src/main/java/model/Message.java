package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private User user;
    private String text;

    private String time;

    private boolean isSeen = false;

    public Message(User user, String text) {
        this.user = user;
        this.text = text;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        time = dateFormat.format(date);
    }

    public String getTime() {
        return time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public boolean isSeen() {
        return isSeen;
    }
}
