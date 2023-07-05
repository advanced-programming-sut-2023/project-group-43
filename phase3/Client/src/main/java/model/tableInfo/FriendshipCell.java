package model.tableInfo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import model.DataBase;
import model.User;


public class FriendshipCell {
    private User currentUser;
    private User friendUser;
    private ImageView avatar = new ImageView();
    private int rank;
    private String username;
    private int score;
    private String slogan;
    private Button friendShip ;

    public FriendshipCell(User currentUser , User friendUser) {
        this.currentUser = currentUser;
        this.friendUser = friendUser;

        this.username = friendUser.getUsername();
        this.slogan = friendUser.getSlogan();
        this.score = friendUser.getScore();
        //this.rank = DataBase.getInstance().getRank(currentUser);
        this.slogan = friendUser.getSlogan();
        this.friendShip = new Button("Friendship");
        friendShip.setOnAction(actionEvent -> makeRequestPopup());
    }

    private void makeRequestPopup() {
        Popup popup = new Popup();
        BorderPane borderPane = new BorderPane();
        //if(DataBase.getInstance().isFriend(currentUser , friendUser))

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
