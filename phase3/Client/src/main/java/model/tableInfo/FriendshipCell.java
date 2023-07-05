package model.tableInfo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import model.DataBase;
import model.User;
import view.RegisterMenu;


public class FriendshipCell {
    private User currentUser;
    private User friendUser;
    private ImageView avatar = new ImageView();
    private String username;
    private int score;
    private String slogan;
    private Button friendship = new Button("Friendship") ;

    public FriendshipCell(User currentUser , User friendUser) {
        this.currentUser = currentUser;
        this.friendUser = friendUser;

        this.username = friendUser.getUsername();
        this.slogan = friendUser.getSlogan();
        this.score = friendUser.getScore();
        this.slogan = friendUser.getSlogan();
        friendship.setOnAction(ae -> makeRequestPopup());
    }

    private void makeRequestPopup() {
        Popup popup = new Popup();
        BorderPane borderPane = new BorderPane();
        Button back = new Button("Back");
        back.setOnAction(ae -> popup.hide());

        if(DataBase.getInstance().isFriend(currentUser , friendUser)){
            Text text = new Text("Friend");
            borderPane.setCenter(text);
            borderPane.setBottom(back);
            popup.getContent().add(borderPane);
            popup.show(RegisterMenu.getStage());
        }

        //handle request

    }


    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
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
        return friendship;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
