package model.tableInfo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import model.DataBase;
import model.User;


public class FriendshipCell {
    private User currentUser;
    private ImageView avatar = new ImageView();
    private int rank;
    private String username;
    private int score;
    private String slogan;
    private Button friendShip = new Button("Friendship");

    public FriendshipCell(User currentUser) {
        this.currentUser = currentUser;
        this.username = currentUser.getUsername();
        this.slogan = currentUser.getSlogan();
        this.score = currentUser.getScore();
        //this.rank = DataBase.getInstance().getRank(currentUser);
        this.slogan = currentUser.getSlogan();

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

    public Button getFriendShip() {
        return friendShip;
    }

    public void setFriendShip(Button friendShip) {
        this.friendShip = friendShip;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
