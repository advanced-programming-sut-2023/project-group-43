package model.tableInfo;


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class ScoreboardCell {
    private ImageView avatar = new ImageView();
    private int rank;
    private String username;
    private int score;
    private Circle state = new Circle();
    private String lastSeen;
    private Button friendShip = new Button("Friendship");

    public ScoreboardCell() {
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
