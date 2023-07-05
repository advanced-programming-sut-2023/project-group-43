package model.tableInfo;


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.User;

public class ScoreboardCell {
    private  User user;
    private ImageView avatar = new ImageView();
    private int rank;
    private String username;
    private int score;
    private Circle state ;
    private String lastSeen;
    private Button friendShip = new Button("Friendship");

    public ScoreboardCell(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.rank = user.getRank();
        this.score = user.getScore();
        this.state = checkOnlineState();
    }

    private Circle checkOnlineState(){
        //if user is online
        state.setRadius(10);
        state.setFill(Color.GREEN);
        //if not
        //state.setFill(Color.RED);
        return state;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Circle getState() {
        return state;
    }

    public void setState(Circle state) {
        this.state = state;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Button getFriendShip() {
        return friendShip;
    }

    public void setFriendShip(Button friendShip) {
        this.friendShip = friendShip;
    }
}
