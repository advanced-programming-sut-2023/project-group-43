package model.tableInfo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class FriendshipCell {
    private ImageView avatar = new ImageView();
    private int rank;
    private String username;
    private int score;
    private String email;
    private Button friendShip = new Button("Friendship");

    public FriendshipCell() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Button getFriendShip() {
        return friendShip;
    }

    public void setFriendShip(Button friendShip) {
        this.friendShip = friendShip;
    }
}
